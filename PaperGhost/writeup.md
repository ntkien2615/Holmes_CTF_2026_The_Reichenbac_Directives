# Holmes CTF 2026 – Sherlock 04: The False Employee (PaperGhost)
## DFIR Investigation & Solution Writeup

---

### 1. Executive Summary (Tổng quan vụ việc)

* **Thử thách:** Sherlock 04 – The False Employee (Triage: `PaperGhost.zip`)
* **Nạn nhân:** Clara Voss (`cvoss`), Ministerial Briefing Office, Văn phòng Nội các.
* **Máy trạm:** `CO-LT-0469` (Windows 10 Enterprise x64).
* **Tác nhân đe dọa (Threat Actor):** Elias Venn (đội lốt nhân viên hỗ trợ IT) liên kết với tổ chức tình báo **VON BORK / NAPOLEON**.
* **Kịch bản tấn công:** Tấn công vật lý thông qua thiết bị lưu trữ ngoại vi (USB Dropped Attack). Kẻ tấn công để lại một USB có gắn thẻ tài sản nội bộ giả mạo (`CO-USB-0091`) trên bàn của Clara Voss. Nạn nhân lầm tưởng đây là gói cập nhật trình điều khiển định kỳ nên đã cắm USB và chạy tệp thực thi. Payload kích hoạt gián điệp, bí mật bật micro và webcam để theo dõi nạn nhân, đồng thời trích xuất các tài liệu nhạy cảm liên quan đến các nhà thầu dự án **DIOGENES** về máy chủ C2 qua mạng.

---

### 2. Incident Timeline (Dòng thời gian sự kiện)

| Thời gian (UTC) | Thành phần / Tiến trình | Mô tả chi tiết hành động |
| :--- | :--- | :--- |
| **2026-08-19 15:35:50** | `USBSTOR / PnP` | USB Flash Drive (`Lexar`, serial `RS200000000627E4&0`) được cắm lần đầu vào máy trạm và gán nhãn `CO-USB-0091` (Mount ổ `E:`). |
| **2026-08-19 15:36:25** | `Explorer / UserAssist` | Clara Voss mở thư mục trên USB và thực thi payload `E:\CO-LT-0469 update package\update.exe`. |
| **2026-08-19 15:38:08** | `update.exe (C2 Agent)` | Mã độc kích hoạt Micro (`REDRAGON Live Camera Audio`) để nghe lén âm thanh trong phòng làm việc. |
| **2026-08-19 15:41:03** | `update.exe (C2 Agent)` | Phiên ghi âm qua micro dừng lại (thời lượng nghe lén: 175 giây). |
| **2026-08-19 15:42:28** | `update.exe (C2 Agent)` | Mã độc kích hoạt Webcam (`REDRAGON Live Camera`) để quay quét hình ảnh phòng làm việc. |
| **2026-08-19 15:44:35** | `update.exe (C2 Agent)` | Phiên truyền hình ảnh từ webcam kết thúc (thời lượng stream: 126.98s $\approx$ 127 giây). |
| **2026-08-19 15:51:06** | `PnP Driver` | Thiết bị USB được rút ra khỏi máy trạm (`LastRemovalDate`). |
| **Giai đoạn Exfiltration** | `SRUM / Network` | Ghi nhận lưu lượng tải dữ liệu độc hại ra C2 đạt **172.064531 MB**, bao gồm hồ sơ nhân sự dự án DIOGENES. |

---

### 3. Phân tích chi tiết từng câu hỏi & Chứng cứ kỹ thuật

#### Câu 1: Thời điểm Clara Voss cắm USB lần đầu tiên
* **Câu hỏi:** *The rookie's first move was a planted USB. When did Clara Voss first connect the device Elias Venn left at her desk? (YYYY-MM-DD hh:mm:ss)*
* **Chứng cứ phân tích:**
  * File: `C\Windows\System32\config\SYSTEM`
  * Khóa: `ControlSet001\Enum\USB\VID_21C4&PID_0CD1\RS200000000627E4\Properties\{83da6326-97a6-4088-9453-a1923f573b29}`
  * Thuộc tính `0064` (InstallDate) và `0065` (FirstInstallDate):
    * Hex FILETIME: `134316273503974120`
    * Chuyển đổi UTC: `2026-08-19 15:35:50.397412`
* **Đáp án:** `2026-08-19 15:35:50`

---

#### Câu 2: Số serial của chiếc USB để lại hiện trường
* **Câu hỏi:** *Every USB carries a serial scar. What serial number did the dropped device leave behind? (string)*
* **Chứng cứ phân tích:**
  * File: `C\Windows\System32\config\SYSTEM`
  * Khóa: `ControlSet001\Enum\USBSTOR\Disk&Ven_Lexar&Prod_USB_Flash_Drive&Rev_2.00\RS200000000627E4&0`
  * Trong kiến trúc driver `USBSTOR` của Windows, định danh phiên bản lưu trữ ngoại vi được gắn kèm chỉ số LUN (Logical Unit Number) tạo thành chuỗi định danh duy nhất của thiết bị trên hệ điều hành.
* **Đáp án:** `RS200000000627E4&0`

---

#### Câu 3: Đường dẫn đầy đủ của Payload
* **Câu hỏi:** *VON BORK's payload hid inside a fake update package Elias delivered. What is the full path of the payload? (full path of file, starting with drive letter)*
* **Chứng cứ phân tích:**
  * Kiểm tra `SYSTEM\MountedDevices`: Volume GUID `{61486a42-9be3-11f1-8640-000c29177a52}` tương ứng với phần cứng USB Lexar được gán ký tự ổ đĩa `E:`.
  * Khóa UserAssist trong `NTUSER.DAT` ghi nhận đường dẫn thực thi:
    `E:\CO-LT-0469 update package\update.exe`
  * Cơ sở dữ liệu SRUM (`SRUDB.dat`) ánh xạ volume này qua ký hiệu:
    `\Device\HarddiskVolume5\CO-LT-0469 update package\update.exe`
* **Đáp án:** `E:\CO-LT-0469 update package\update.exe`

---

#### Câu 4: Thời điểm Voss thực thi gói mã độc
* **Câu hỏi:** *Believing it a routine update, Voss launched the spyware. At what exact timestamp did she execute the malicious package? (YYYY-MM-DD hh:mm:ss)*
* **Chứng cứ phân tích:**
  * File: `C\Users\cvoss\NTUSER.DAT`
  * Khóa: `Software\Microsoft\Windows\CurrentVersion\Explorer\UserAssist\{CEBFF5CD-ACE2-4F4F-9178-9926F41749EA}\Count`
  * Tên mục (ROT13 decoded): `E:\CO-LT-0469 update package\update.exe`
  * Cấu trúc binary UserAssist (Windows 10/11) lưu timestamp tại offset `60` (8 bytes FILETIME):
    * Hex: `90 b5 cd 7e f0 2f dd 01`
    * Decimal: `134316273857050000`
    * UTC Datetime: `2026-08-19 15:36:25.705000`
* **Đáp án:** `2026-08-19 15:36:25`

---

#### Câu 5: Tên tài sản / Tên thiết bị của chiếc USB
* **Câu hỏi:** *DIOGENES tagged the USB with an asset name that surfaced as the device name on connection. What was it? (string)*
* **Chứng cứ phân tích:**
  * File: `C\Windows\System32\config\SOFTWARE`
  * Khóa: `Microsoft\Windows Portable Devices\Devices\SWD#WPDBUSENUM#_??_USBSTOR#DISK&VEN_LEXAR&PROD_USB_FLASH_DRIVE&REV_2.00#RS200000000627E4&0#{53F56307-B6BF-11D0-94F2-00A0C91EFB8B}`
  * Giá trị: `FriendlyName` = `CO-USB-0091`
* **Đáp án:** `CO-USB-0091`

---

#### Câu 6: Thời điểm mã độc bắt đầu thu âm qua Micro
* **Câu hỏi:** *Once C2 Access was live, VON BORK's listening post woke the microphone to spy on Voss's meetings. At what time did capture begin? (YYYY-MM-DD hh:mm:ss)*
* **Chứng cứ phân tích:**
  * File: `C\Windows\System32\config\SOFTWARE`
  * Khóa: `Microsoft\Windows\CurrentVersion\CapabilityAccessManager\ConsentStore\microphone\NonPackaged\E:#CO-LT-0469 update package#update.exe`
  * Giá trị:
    * `LastUsedTimeStart`: `134316274881131799` -> `2026-08-19 15:38:08.113179 UTC`
    * `LastUsedTimeStop`: `134316276632589248` -> `2026-08-19 15:41:03.258924 UTC`
* **Đáp án:** `2026-08-19 15:38:08`

---

#### Câu 7: Thời lượng Webcam stream ghi hình
* **Câu hỏi:** *VON BORK mapped Voss's office through her webcam — who came and went, what lay on her desk. For how many seconds did the webcam stream? (number)*
* **Chứng cứ phân tích:**
  * File: `C\Windows\System32\config\SOFTWARE`
  * Khóa: `Microsoft\Windows\CurrentVersion\CapabilityAccessManager\ConsentStore\webcam\NonPackaged\E:#CO-LT-0469 update package#update.exe`
  * Giá trị:
    * `LastUsedTimeStart`: `134316277486814313` (`2026-08-19 15:42:28.681431 UTC`)
    * `LastUsedTimeStop`: `134316278756619812` (`2026-08-19 15:44:35.661981 UTC`)
  * Công thức tính số giây:
    $$\text{Duration} = \frac{134316278756619812 - 134316277486814313}{10{,}000{,}000} = \frac{1{,}269{,}805{,}499}{10{,}000{,}000} = 126.9805499 \approx 127 \text{ giây}$$
* **Đáp án:** `127`

---

#### Câu 8: Dung lượng dữ liệu outbound gửi về máy chủ C2
* **Câu hỏi:** *The riverside relay drank Voss's secrets. How many decimal megabytes of outbound traffic flowed from the compromised machine to the C2? (***.******)*
* **Chứng cứ phân tích:**
  * File: `C\Windows\System32\SRU\SRUDB.dat` (System Resource Usage Monitor)
  * Bảng: `{973F5D5C-1D90-4944-BE8E-24B94231A174}` (Network Data Usage)
  * Bản ghi cho ứng dụng `\Device\HarddiskVolume5\CO-LT-0469 update package\update.exe` (Ánh xạ `AppId: 438`):
    * `BytesSent` = `172064531` bytes.
  * Chuyển đổi sang Decimal Megabytes ($1\text{ MB} = 1{,}000{,}000\text{ bytes}$):
    $$\text{Traffic (MB)} = \frac{172{,}064{,}531}{1{,}000{,}000} = 172.064531\text{ MB}$$
* **Đáp án:** `172.064531`

---

#### Câu 9: Thông tin tài khoản của lập trình viên DIOGENES
* **Câu hỏi:** *Voss reviewed DIOGENES contractors NAPOLEON may now hunt. What set of credentials did the spying surface for a developer working on DIOGENES tickets? (username:password)*
* **Chứng cứ phân tích:**
  * File: `C\ProgramData\Microsoft\search\data\applications\windows\Windows.edb`
  * Bảng `SystemIndex_PropertyStore`, bản ghi `WorkID: 89` tương ứng với tệp hồ sơ nhân sự `C:\Users\cvoss\Desktop\DIOGENES_26\EXT-0419.pdf`.
  * Thuộc tính `4625-System_Search_AutoSummary` chứa nội dung được lập chỉ mục:
    ```text
    PERSONNEL & ACCESS FILE - EXTERNAL CONTRACTOR
    DIOGENES Ticketing Support
    Classification: OFFICIAL-SENSITIVE
    Name:               Tom Ainsworth
    Title:              Software Developer, DIOGENES Ticketing Support (External Contractor)
    Contractor ID:      EXT-0419
    Internal server:    srv-diogenes-tickets-01.internal
    Username:           tainsworth
    Password:           D10g3n3s_T1ck3ts#2026
    Access scope:       EXT-3 (ticketing host only)
    Assigned repository: parse_diogenese_tickets
    ```
* **Đáp án:** `tainsworth:D10g3n3s_T1ck3ts#2026`

---

### 4. Bảng tổng hợp đáp án (Cheatsheet)

| # | Câu hỏi | Format | Đáp án nộp |
| :-: | :--- | :---: | :--- |
| **1** | Thời điểm Clara Voss cắm USB lần đầu | `YYYY-MM-DD hh:mm:ss` | `2026-08-19 15:35:50` |
| **2** | Serial number của dropped USB | `string` | `RS200000000627E4&0` |
| **3** | Đường dẫn đầy đủ của Payload | Full path có ký tự ổ đĩa | `E:\CO-LT-0469 update package\update.exe` |
| **4** | Thời điểm thực thi gói payload | `YYYY-MM-DD hh:mm:ss` | `2026-08-19 15:36:25` |
| **5** | Tên thiết bị / Asset Name của USB | `string` | `CO-USB-0091` |
| **6** | Thời điểm bắt đầu kích hoạt Microphone | `YYYY-MM-DD hh:mm:ss` | `2026-08-19 15:38:08` |
| **7** | Thời lượng Webcam stream (giây) | `number` | `127` |
| **8** | Lưu lượng gửi về C2 (Decimal Megabytes) | `***.******` | `172.064531` |
| **9** | Credentials của Developer DIOGENES | `username:password` | `tainsworth:D10g3n3s_T1ck3ts#2026` |
