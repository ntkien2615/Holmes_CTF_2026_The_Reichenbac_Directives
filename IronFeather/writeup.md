# Holmes CTF 2026 – Sherlock 07: Mission Vault (IronFeather)
## DFIR & Embedded Drone Forensics Investigation Writeup

---

### 1. Executive Summary (Tổng quan vụ việc)

* **Thử thách:** Sherlock 07 – Mission Vault (Tên kịch bản: `IronFeather`).
* **Mục tiêu phân tích:** Flycam / Quadcopter giám sát bị thu giữ sau khi không thể quay về điểm chỉ định tại London.
* **Firmware điều khiển:** PX4 Autopilot (`px4` binary ELF x86_64, branch `mission-vault`).
* **Hiện vật thu thập (Artifacts):**
  * `px4`: Tệp nhị phân thực thi của hệ thống điều khiển bay PX4.
  * `dataman.encrypted`: Cơ sở dữ liệu nhiệm vụ (PX4 Data Manager datastore) đã bị mã hóa.
  * `flight.ulg.encrypted`: Toàn bộ nhật ký hành trình bay (PX4 ULog file) đã bị mã hóa.
* **Kịch bản sự cố:**
  1. Chiếc flycam cất cánh từ khu vực bảo mật tại London để thực hiện hành trình bay tự động gồm 24 điểm mốc (waypoints).
  2. Tại điểm mốc số 10, flycam kích hoạt bộ truyền động (actuator) để thả gói hàng/payload bí mật.
  3. Kẻ tấn công hoặc bên thứ ba đã can thiệp từ xa, tiêm lệnh điều khiển MAVLink trái phép (`MAV_CMD_INJECT_FAILURE`) nhằm ngắt toàn bộ động cơ giữa không trung.
  4. Chiếc flycam mất lực nâng, rơi tự do và va chạm xuống mặt đất tại đường **Knightsbridge**, sau đó tự động ngắt hệ thống (disarm) sau vụ va chạm.

---

### 2. Incident Timeline (Dòng thời gian sự kiện)

| Thời gian ($\mu s$) | Thời gian ($s$) | Thành phần | Sự kiện chi tiết |
| :--- | :--- | :--- | :--- |
| **285260000** | 285.260 | `commander / logger` | Drone chuyển trạng thái `ARMED`. Bắt đầu ghi nhật ký hành trình `./log/2026-09-10/09_50_55.ulg`. |
| **287084000** | 287.084 | `commander` | Autopilot phát hiện cất cánh (`Takeoff detected`). Tọa độ: `51.4996987, -0.1607999`. |
| **300984000** | 300.984 | `navigator` | Đạt độ cao hành trình, bay theo lộ trình waypoints tuần tự. |
| **446656000** | 446.656 | `actuator / mission` | **Thực thi Mission Item 10:** Gọi lệnh `MAV_CMD_DO_SET_ACTUATOR` mở chốt thả payload (`param1 = 1.0`) tại tọa độ `51.5035602, -0.1608417`. |
| **448672000** | 448.672 | `actuator / mission` | Hoàn thành thả hàng sau 2 giây trễ (`param1 = 0.0`), tiếp tục hành trình bay. |
| **517208000** | 517.208 | `health checks` | Cảnh báo pin yếu (`Low battery warning`). |
| **520764000** | 520.764 | `MAVLink / failure` | **Tiêm lệnh MAVLink độc hại:** Lệnh 420 (`MAV_CMD_INJECT_FAILURE`) ngắt toàn bộ động cơ (`unit: motor 101, type: off 1`). Drone đã bay $277\text{ m}$ kể từ khi thả payload. |
| **520772000** | 520.772 | `control_allocator` | Autopilot cưỡng chế dừng 4 động cơ (`Stopping motors 4095`). Drone bắt đầu rơi tự do. |
| **522992000** | 522.992 | `failsafe` | Kích hoạt Failsafe bay (`nav_state = 18` - Flight Termination). |
| **525852000** | 525.852 | `sensors / dynamics` | **Va chạm mặt đất (Impact):** Gia tốc kế ghi nhận cú va đập cực mạnh $1228.75\text{ m/s}^2$ ($> 125\text{G}$), vận tốc rơi $v_z$ triệt tiêu về $0$. |
| **576968000** | 576.968 | `commander` | Autopilot ngắt trạng thái kích hoạt sau va chạm (`Disarmed by internal command`). |

---

### 3. Phân tích Reverse Engineering & Giải mã dữ liệu (Câu 1 – 6)

#### Cấu trúc Header & Thuật toán mã hóa:
Tệp `dataman.encrypted` và `flight.ulg.encrypted` có cùng định dạng header độ dài 60 bytes (`0x3c`):
* `0x00 - 0x07` (8 bytes): Magic string ASCII (`PX4DMENC` đối với dataman, `PX4ULENC` đối với ulog).
* `0x08 - 0x0b` (4 bytes): Phiên bản định dạng (`Version = 1`).
* `0x0c - 0x0f` (4 bytes): Kích thước ciphertext (Payload size).
* `0x10 - 0x1f` (16 bytes): Salt dùng cho KDF (`60c200309e3f464c11d14645a355bfe8`).
* `0x20 - 0x2b` (12 bytes): Initialization Vector (IV/Nonce cho thuật toán GCM).
* `0x2c - 0x3b` (16 bytes): Authentication Tag (GCM Tag).

Tại RVA `0x16ff20` - `0x1703b0` trong binary `px4`:
* Hàm kiểm tra magic `PX4DMENC` và gọi `EVP_aes_256_gcm` để giải mã với AAD là 44 bytes đầu của header.
* Khóa AES-256 được sinh thông qua hàm tùy chỉnh tại RVA **`0x170af0`**.
* Hàm `0x170af0` thực hiện một vòng lặp mixing 32-bit gồm **`384`** vòng (`cmp edx, 0x17f` $\rightarrow 384$ vòng).
* Kết quả mixing tạo ra một mảng dữ liệu 104 bytes truyền làm mật khẩu (`pass`) cho hàm chuẩn **`PKCS5_PBKDF2_HMAC`** với digest `EVP_sha256()`, số vòng lặp `8192` (`0x2000`) và Salt 16 bytes.
* Khóa AES-256 dẫn xuất thu được:
  ```text
  a40ba87b8a0e21d4ead98b917c4bf0f60cc65b25c614b93f107e5ed1e483d6ce
  ```

---

### 4. Danh sách Đáp án chi tiết (Toàn bộ 17 câu hỏi)

#### Câu 1: Magic ASCII của datastore
* **Câu hỏi:** *What eight-byte ASCII magic identifies the encrypted datastore format? (string)*
* **Chứng cứ:** 8 byte đầu tệp `dataman.encrypted`: `50 58 34 44 4d 45 4e 43` $\rightarrow$ `PX4DMENC`.
* **Đáp án:** `PX4DMENC`

#### Câu 2: Thuật toán mã hóa xác thực
* **Câu hỏi:** *Which authenticated encryption algorithm protects the datastore? (string)*
* **Chứng cứ:** Gọi `EVP_aes_256_gcm()` tại RVA `0x1700e0` trong `px4`.
* **Đáp án:** `AES-256-GCM`

#### Câu 3: RVA của hàm dẫn xuất khóa tùy chỉnh
* **Câu hỏi:** *What is the Relative Virtual Address (RVA) of the custom key-derivation function? (hex number)*
* **Chứng cứ:** Lệnh gọi `call 0x170af0` tại địa chỉ `0x1700c6`.
* **Đáp án:** `0x170af0` *(hoặc `170af0`)*

#### Câu 4: Số vòng lặp mixing tùy chỉnh
* **Câu hỏi:** *How many rounds are performed by the custom 32-bit mixing loop? (number)*
* **Chứng cứ:** Vòng lặp `0x170ba0` kiểm tra điều kiện dừng `cmp edx, 0x17f` (chạy từ 0 đến 383, tổng cộng 384 vòng).
* **Đáp án:** `384`

#### Câu 5: Chuẩn KDF sinh khóa AES
* **Câu hỏi:** *Which standard KDF produces the AES key? (string)*
* **Chứng cứ:** Lệnh gọi hàm OpenSSL `PKCS5_PBKDF2_HMAC` tại RVA `0x170d74`.
* **Đáp án:** `PBKDF2` *(hoặc `PBKDF2-HMAC-SHA256`)*

#### Câu 6: Khóa AES-256 dẫn xuất
* **Câu hỏi:** *What AES-256 key is derived for the supplied encrypted image? (64 lowercase hexadecimal characters)*
* **Chứng cứ:** Giả lập thực thi hàm KDF trên Unicorn Engine, dẫn xuất ra khóa 32 bytes giải mã thành công toàn vẹn dữ liệu.
* **Đáp án:** `a40ba87b8a0e21d4ead98b917c4bf0f60cc65b25c614b93f107e5ed1e483d6ce`

#### Câu 7: Mission Bank đang hoạt động
* **Câu hỏi:** *Which of PX4's two mission banks is active? (number)*
* **Chứng cứ:** Cấu trúc `mission_s` tại offset `0x127098` trong `dataman` lưu `mission_dataman_id = 0`, phân vùng waypoints nằm tại offset `0x2118` tương ứng với Bank 0 (`DM_KEY_WAYPOINTS_OFFBOARD_0`).
* **Đáp án:** `0`

#### Câu 8: Số lượng Mission Items trong Active Bank
* **Câu hỏi:** *How many mission items are stored in the active bank? (number)*
* **Chứng cứ:** Trường `count` trong `mission_s` và số lượng phần tử `seq_total` trong topic `mission_result` đều ghi nhận giá trị 24.
* **Đáp án:** `24`

#### Câu 9: Mission Item kích hoạt thả Payload
* **Câu hỏi:** *Which mission item triggers payload release? (number)*
* **Chứng cứ:** Item 10 tại offset `0x2374` mang lệnh `MAV_CMD_DO_SET_ACTUATOR` (`nav_cmd = 187`) thiết lập `param1 = 1.0` mở chốt phụ kiện mang tải.
* **Đáp án:** `10`

#### Câu 10: Tọa độ dự kiến hạ cánh
* **Câu hỏi:** *Where was the drone supposed to land? (latitude,longitude; 5 decimal places)*
* **Chứng cứ:** Item cuối cùng (Item 23) trong Bank 0 là lệnh `MAV_CMD_NAV_LAND` (`nav_cmd = 21`) với tọa độ `lat = 51.4997000`, `lon = -0.1608000`.
* **Đáp án:** `51.49970,-0.16080`

#### Câu 11: Tọa độ cất cánh của Drone [ĐÃ XÁC NHẬN CHÍNH XÁC]
* **Câu hỏi:** *Where did the drone take off? (latitude,longitude; 7 decimal places)*
* **Chứng cứ:** Ghi nhận từ topic `vehicle_global_position` đúng tại thời điểm Autopilot thông báo sự kiện `[commander] Takeoff detected` ($287084000\,\mu s$).
* **Đáp án:** `51.4996987,-0.1607999`

#### Câu 12: Vị trí Drone khi thả Payload [ĐÃ XÁC NHẬN CHÍNH XÁC]
* **Câu hỏi:** *Where was the drone when payload release was triggered? (latitude,longitude; 7 decimal places)*
* **Chứng cứ:** Ghi nhận tại timestamp $446656000\,\mu s$ khi lệnh Item 10 được kích hoạt.
* **Đáp án:** `51.5035602,-0.1608417`

#### Câu 13: Lệnh MAVLink được tiêm để hạ gục Drone [ĐÃ XÁC NHẬN CHÍNH XÁC]
* **Câu hỏi:** *Which MAVLink command was injected to bring down the drone? (string)*
* **Chứng cứ:** Lệnh ID 420 trong nhật ký `vehicle_command` tại timestamp $520764000\,\mu s$ với tham số ngắt động cơ.
* **Đáp án:** `MAV_CMD_INJECT_FAILURE`

#### Câu 14: Quãng đường di chuyển giữa thả payload và tiêm lệnh [ĐÃ XÁC NHẬN CHÍNH XÁC]
* **Câu hỏi:** *How far did the drone travel between payload release and command injection? (horizontal metres, nearest whole number)*
* **Chứng cứ:** Tổng quãng đường di chuyển dọc quỹ đạo (Cumulative path distance) từ $446656000\,\mu s$ đến $520764000\,\mu s$ là $276.719\text{ m} \approx 277\text{ m}$.
* **Đáp án:** `277`

#### Câu 15: Tọa độ Drone rơi va chạm mặt đất
* **Câu hỏi:** *Where did the drone crash? (latitude,longitude; 7 decimal places)*
* **Phân tích kỹ thuật chi tiết:**
  1. **Thời điểm va chạm vật lý thực tế (Physical Impact):**
     * Tại timestamp $525852000\,\mu s$, cảm biến gia tốc `sensor_combined` ghi nhận đỉnh xung lực va đập cực lớn $1228.75\text{ m/s}^2$ ($> 125\text{G}$).
     * Vận tốc rơi tự do $v_z$ từ $9.76\text{ m/s}$ triệt tiêu hoàn toàn về $0.01\text{ m/s}$.
     * Cả mẫu vị trí trước va chạm ($525848000\,\mu s$) và ngay sau va chạm ($525856000\,\mu s$) trong `vehicle_global_position` đều đồng nhất tại:
       $$\text{lat} = 51.5016938,\quad \text{lon} = -0.1620929$$
     *(Lưu ý: Trước đó ta nhập nhầm đuôi `-0.1620930` thay vì `-0.1620929`).*
  2. **Thời điểm bắt đầu rơi (Initiation of Crash):**
     * Tại thời điểm tiêm lệnh ngắt động cơ ($520764000\,\mu s$): `51.5017027,-0.1620956`.
  3. **Thời điểm kích hoạt Failsafe / Flight Termination:**
     * Tại timestamp $522992000\,\mu s$: `51.5016951,-0.1620933`.

👉 **Các ứng viên kiểm tra:**  
* **`51.5016938,-0.1620929`** *(Ưu tiên 1 - Tọa độ VGP chuẩn xác tại microsecond va chạm $125\text{G}$)*  
* **`51.5017027,-0.1620956`** *(Ưu tiên 2 - Tọa độ tại thời điểm bắt đầu xảy ra sự cố rơi)*  
* **`51.5016951,-0.1620933`** *(Ưu tiên 3 - Tọa độ khi kích hoạt Flight Termination failsafe)*  

#### Câu 16: Thời điểm disarm sau va chạm [ĐÃ XÁC NHẬN CHÍNH XÁC]
* **Câu hỏi:** *When was the drone disarmed after the crash? (seconds after boot; 3 decimal places)*
* **Chứng cứ:** Ghi nhận tại timestamp $576968000\,\mu s$ $\rightarrow 576.968\text{ s}$.
* **Đáp án:** `576.968`

#### Câu 17: Tên con đường gần điểm rơi nhất [ĐÃ XÁC NHẬN CHÍNH XÁC]
* **Câu hỏi:** *Which road is closest to the crash site? (string)*
* **Chứng cứ:** Tra cứu tọa độ điểm rơi trên bản đồ OpenStreetMap: Tọa độ nằm ngay tại số 151 đường Knightsbridge.
* **Đáp án:** `Knightsbridge`
