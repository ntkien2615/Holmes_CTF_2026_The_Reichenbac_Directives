from pyulog import ULog
import numpy as np

ulog = ULog('flight.ulg')
vgp = ulog.get_dataset('vehicle_global_position').data
cmd = ulog.get_dataset('vehicle_command').data

# find command 187 with param1 == 1.0
for i in range(len(cmd['timestamp'])):
    if cmd['command'][i] == 187 and cmd['param1'][i] == 1.0:
        ts = cmd['timestamp'][i]
        print(f"Payload release command 187 triggered at ts={ts}")
        
        # Exact position in vgp
        idx = (vgp['timestamp'] >= ts).nonzero()[0][0]
        # check if exact timestamp exists or interpolate / nearest
        ts_before = vgp['timestamp'][idx-1]
        ts_after = vgp['timestamp'][idx]
        print(f"VGP before: ts={ts_before}, lat={vgp['lat'][idx-1]:.7f}, lon={vgp['lon'][idx-1]:.7f}, alt={vgp['alt'][idx-1]}")
        print(f"VGP at/after: ts={ts_after}, lat={vgp['lat'][idx]:.7f}, lon={vgp['lon'][idx]:.7f}, alt={vgp['alt'][idx]}")
        
        # Also check exact interpolated position
        frac = (ts - ts_before) / (ts_after - ts_before) if ts_after != ts_before else 0
        lat_interp = vgp['lat'][idx-1] + frac * (vgp['lat'][idx] - vgp['lat'][idx-1])
        lon_interp = vgp['lon'][idx-1] + frac * (vgp['lon'][idx] - vgp['lon'][idx-1])
        print(f"Interpolated: lat={lat_interp:.7f}, lon={lon_interp:.7f}")
