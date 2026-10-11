import struct

def parse_dex_strings(dex_bytes):
    magic = dex_bytes[:8]
    string_ids_size = struct.unpack('<I', dex_bytes[0x38:0x3C])[0]
    string_ids_off = struct.unpack('<I', dex_bytes[0x3C:0x40])[0]
    strings = []
    for i in range(string_ids_size):
        str_off = struct.unpack('<I', dex_bytes[string_ids_off + i*4 : string_ids_off + (i+1)*4])[0]
        # read uleb128
        pos = str_off
        length = 0
        shift = 0
        while True:
            b = dex_bytes[pos]
            pos += 1
            length |= (b & 0x7f) << shift
            if (b & 0x80) == 0:
                break
            shift += 7
        # read string until null
        end = dex_bytes.find(b'\x00', pos)
        s = dex_bytes[pos:end].decode('utf-8', errors='replace')
        strings.append(s)
    return strings

with open('extracted/TWCore/classes.dex', 'rb') as f:
    dex = f.read()

strings = parse_dex_strings(dex)
print(f'Total strings: {len(strings)}')

print('\n--- Potential system properties (ro., persist., etc.) ---')
for s in strings:
    if any(s.startswith(p) for p in ['ro.', 'persist.', 'sys.', 'net.', 'gsm.']):
        print('  Property:', s)

print('\n--- Potential MQTT / URLs / IPs / Ports ---')
for s in strings:
    if any(k in s.lower() for k in ['tcp://', 'mqtt', 'broker', 'topic', 'ssl://', '1883', '154.57']):
        print('  MQTT/URL:', s)

print('\n--- Potential regions / countries / languages ---')
for s in strings:
    if any(k in s.lower() for k in ['region', 'country', 'locale']):
        print('  Region/Locale:', s)
