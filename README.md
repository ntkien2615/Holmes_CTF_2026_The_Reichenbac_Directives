# Holmes CTF 2026 — DFIR Sherlocks & Investigations

Repository lưu trữ tài liệu đề bài, bằng chứng số (evidence), các kịch bản phân tích/giải mã (DFIR solve scripts) và tài liệu giải trình chi tiết (**Writeups**) cho các thử thách điều tra số (**Sherlocks**) tại kỳ thi **Holmes CTF 2026** (Hack The Box).

---

## 🏆 Chứng chỉ hoàn thành (Certificate of Completion)

<p align="center">
  <a href="./assets/Certificate-MeoBoCute.pdf" target="_blank">
    <img src="./assets/certificate.png" alt="Holmes CTF 2026 Certificate" width="850">
  </a>
  <br>
  <em>🎓 Chứng chỉ chứng nhận hoàn thành Holmes CTF 2026 — Nhấn vào ảnh hoặc <a href="./assets/Certificate-MeoBoCute.pdf">tại đây</a> để xem file PDF gốc.</em>
</p>

---

## 📌 Tổng quan các thử thách (Sherlocks Overview)

| Thử thách (Folder) | Sherlock # | Thể loại (Category) | Trạng thái | Tài liệu Writeup | Bằng chứng / Scripts |
| :--- | :---: | :--- | :---: | :--- | :--- |
| **[SilentDividend](./SilentDividend/)** | `01` | Malware Analysis / Web3 Forensics | ✅ Hoàn thành (10/10) | [writeup.md](./SilentDividend/writeup.md) | `X0_Contract.sol`, `DANGER.txt` |
| **[PaperGhost](./PaperGhost/)** | `04` | Windows Triage / Event Log Forensics | ✅ Hoàn thành | [writeup.md](./PaperGhost/writeup.md) | `Triage/` (Windows Artifacts) |
| **[PoisonedBranch](./PoisonedBranch/)** | `05` | Git Supply Chain / Linux DFIR | ✅ Hoàn thành | [writeup.md](./PoisonedBranch/writeup.md) | `LOOT.zip`, `moran_id_rsa`, `decrypted_loot/` |
| **[SilentPassenger](./SilentPassenger/)** | `06` | Android & Automotive IVI Forensics | ✅ Hoàn thành (20/20) | [writeup.md](./SilentPassenger/writeup.md) | `verify_all_20.py`, `extracted/` |
| **[IronFeather](./IronFeather/)** | `07` | Drone Forensics / PX4 Autopilot ULog | ✅ Hoàn thành | [writeup.md](./IronFeather/writeup.md) | `calc_distance.py`, `find_crash.py` |

---

## 🔍 Tóm tắt phương pháp & Kết quả điều tra (Technical Summaries)

### 1. Sherlock 01 — Silent Dividend (`SilentDividend`)
* **Bối cảnh:** Bác sĩ Watson mất tích sau khi điều tra các vụ tống tiền bí ẩn liên quan ứng dụng thanh toán TrustSettle.
* **Kỹ thuật điều tra:**
  * Giải nén gói cài đặt NSIS/Electron (`TrustSettle 1.0.0.exe`).
  * Phân tích luồng thực thi Lua script theo dõi biến động thư mục qua API `FILE_NOTIFY_INFORMATION` và `WinHttpSendRequest`.
  * Tương tác và dịch ngược hợp đồng thông minh `X0_Contract.sol` trên Ethereum/Polygon, gọi hàm `resolveState()` để khôi phục khóa giải mã.
  * Phân tích file HTML lừa đảo giả mạo ví Web3 (`BrowserProvider`), trích xuất giao dịch `approve()` với `uint256.max` và giải mã flag tọa độ.
* **Flags / Targets:** `AUTH=NAPOLEON SETTLEMENT_REFERENCE=SR-4821`, Tọa độ `51.5049,0.0348`.

---

### 2. Sherlock 04 — The False Employee (`PaperGhost`)
* **Bối cảnh:** Nhân viên văn phòng Nội các Clara Voss bị tấn công vật lý qua chiếc USB dán nhãn nội bộ giả mạo `CO-USB-0091`.
* **Kỹ thuật điều tra:**
  * Phân tích Registry hives (`SOFTWARE`, `SYSTEM`, `NTUSER.DAT`) và Nhật ký sự kiện Windows (Event Logs).
  * Xác định thời điểm mount ổ USB Lexar và thực thi payload `update.exe` từ UserAssist.
  * Điều tra hành vi kích hoạt bí mật microphone (ghi âm 175s) và webcam (stream hình ảnh 127s) của mã độc C2 Agent.
  * Phục hồi tài liệu bị đánh cắp liên quan đến dự án **DIOGENES**.

---

### 3. Sherlock 05 — Backdoored Repository and Exposed Server (`PoisonedBranch`)
* **Bối cảnh:** Lập trình viên chính phủ Tom Ainsworth bị cài cắm mã độc chuỗi cung ứng thông qua kho mã nguồn mở nội bộ giả mạo.
* **Kỹ thuật điều tra:**
  * Phân tích mã nguồn `ticket_parser.py` và module mã độc ẩn `telemetry.py` (sử dụng thuật toán XOR giữa `diogenes.jpg` và `calibration.bin`).
  * Trích xuất ELF implant gián điệp chạy ngầm từ `/home/Tom/.cache/.ticket-parser/.integrity` và kênh kết nối C2 `203.0.113.10:31337`.
  * Điều tra tấn công Known-Plaintext Attack bằng công cụ `bkcrack` đối với kho tài liệu nén `LOOT.zip`.
  * Khôi phục khóa SSH Private Key của đối tượng đe dọa Sebastian Moran và các tài liệu mật bị exfiltrate.

---

### 4. Sherlock 06 — Silent Passenger (`SilentPassenger`)
* **Bối cảnh:** Hệ thống IVI thông tin giải trí của xe ô tô (Android Allwinner T3) bị xâm nhập và biến thành trạm chuyển tiếp proxy di động.
* **Kỹ thuật điều tra:**
  * Bóc tách ứng dụng hệ thống đặc quyền `/system/priv-app/TWCore/TWCore.apk`.
  * Phân tích cơ chế cập nhật firmware qua MQTT broker `tcp://mqtt.car.cardoor.cn:1883` với tài khoản `dofun:dofun666666` và topic `dofun/car/config/#`.
  * Dịch ngược đa tầng mã độc: `jarservice-v1.12.apk` $\to$ `stage2.dex` $\to$ `stage3_sdk.jar` $\to$ C2 plugin `sh66.io`.
  * Tái dựng thuật toán mã hóa AES-CBC, giải mã cấu hình C2 endpoint, API update và khôi phục tọa độ gián điệp cuối cùng.
* **Xác thực:** Toàn bộ 20 câu hỏi được kiểm chứng tự động qua script [`verify_all_20.py`](./SilentPassenger/verify_all_20.py).

---

### 5. Sherlock 07 — Mission Vault (`IronFeather`)
* **Bối cảnh:** Thiết bị bay Quadcopter giám sát bị rơi tự do trên bầu trời London do bị kẻ tấn công can thiệp qua MAVLink.
* **Kỹ thuật điều tra:**
  * Dịch ngược tệp nhị phân điều khiển bay `px4` (PX4 Autopilot).
  * Khôi phục thuật toán giải mã và giải mã thành công nhật ký hành trình `flight.ulg.encrypted` cùng cơ sở dữ liệu `dataman.encrypted`.
  * Phân tích dữ liệu cảm biến bay (GPS, Gyro, Actuators) bằng bộ script Python tự viết (`calc_distance.py`, `find_crash.py`,...).
  * Xác định chính xác vị trí thả hàng tại waypoint 10, điểm rơi tại đường Knightsbridge và mã lệnh can thiệp `MAV_CMD_INJECT_FAILURE`.

---

## 🛠️ Cấu trúc thư mục sau khi dọn dẹp (Workspace Structure)

Workspace đã được dọn dẹp sạch sẽ các tệp trích xuất tạm và rác dung lượng lớn (giải phóng hơn **2.1 GB** bộ nhớ):
- Các tệp `.img`, tệp giải nén trùng lặp hàng trăm MB (`system.img`, `Tom_extracted`, `uac_extracted`, `extracted_sandbox`, bộ công cụ `jadx`, `bkcrack`...) đã được dọn dẹp.
- Giữ lại nguyên vẹn toàn bộ:
  - File đề bài gốc (`.pdf`, `.zip` gốc cần thiết).
  - Các tệp kịch bản giải mã Python (`*.py`), hợp đồng thông minh (`*.sol`).
  - Các bài viết giải trình chi tiết chuẩn mực (`writeup.md`) trong từng thư mục.
