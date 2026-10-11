import json, os, hashlib, re

print("=== QUESTION 1: Privileged Android package responsible for unauthorized application delivery ===")
# format: /path/to/file.apk:<64 lowercase hex characters>
# Look at TWCore.apk or where it was located in system.img
# In transcript, line 360 had SHA256: d7569563cd0491e76d28a1c6a9929234ffffcf035194d2288d888dced7aa11c2
if os.path.exists('extracted/TWCore.apk'):
    with open('extracted/TWCore.apk', 'rb') as f:
        data = f.read()
        print('TWCore.apk sha256:', hashlib.sha256(data).hexdigest())

# Let's check where TWCore was extracted from system.img
