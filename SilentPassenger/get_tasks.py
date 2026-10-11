import ssl, urllib.request, json, re
from cryptography.hazmat.primitives import serialization
from cryptography.hazmat.primitives.asymmetric import padding
from cryptography.hazmat.backends import default_backend

# Load keys from bb.java
with open('extracted/stage3_src/sources/com/a/b/a/bb.java', 'r') as f:
    content = f.read()

def parse_arr(name):
    m = re.search(r'private static final byte\[\] ' + name + r' = \{([^}]+)\};', content)
    text = m.group(1).replace('Byte.MAX_VALUE', '127').replace('Byte.MIN_VALUE', '-128')
    nums = [int(x) & 0xff for x in re.findall(r'(-?\d+)', text)]
    return bytes(nums)

pub_bytes = parse_arr('a')
priv_bytes = parse_arr('b')

pub_key = serialization.load_der_public_key(pub_bytes, backend=default_backend())
priv_key = serialization.load_der_private_key(priv_bytes, password=None, backend=default_backend())

def rsa_encrypt(pub_key, data_bytes):
    out = bytearray()
    i = 0
    while i < len(data_bytes):
        chunk = data_bytes[i : i + 117]
        i += 117
        enc = pub_key.encrypt(chunk, padding.PKCS1v15())
        out.extend(enc)
    return bytes(out)

def rsa_decrypt(priv_key, enc_bytes):
    out = bytearray()
    i = 0
    while i < len(enc_bytes):
        chunk = enc_bytes[i : i + 128]
        i += 128
        dec = priv_key.decrypt(chunk, padding.PKCS1v15())
        out.extend(dec)
    return bytes(out)

req_data = {
    "absDevice": {
        "uid": "00005bp",
        "brand": "Allwinner",
        "model": "QUAD-CORE T3 p1",
        "country": "cn"
    },
    "appInfo": {
        "packageName": "com.tw.jar1",
        "appName": "jar1",
        "versionName": "V1.0.0",
        "versionCode": "12"
    },
    "channelId": "2039",
    "dexVersion": "3.68",
    "configVersion": "3.82"
}

body_json = json.dumps(req_data)
print("Sending task request JSON:", body_json)
enc_body = rsa_encrypt(pub_key, body_json.encode('utf-8'))

ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

# Hosts: t2.tshaoushn3.xyz on port 31782 (HTTPS) or 32024 (HTTP)
url = 'https://154.57.164.82:31782/cpc/api/task'
req = urllib.request.Request(url, data=enc_body, headers={
    'Host': 't2.tshaoushn3.xyz',
    'Content-Type': 'application/octet-stream'
})

try:
    with urllib.request.urlopen(req, context=ctx, timeout=5) as resp:
        resp_data = resp.read()
        print('Status:', resp.status, 'len:', len(resp_data))
        dec = rsa_decrypt(priv_key, resp_data)
        print('Decrypted Task Response:')
        print(dec.decode('utf-8', errors='replace'))
except Exception as e:
    print('Request failed:', e)
