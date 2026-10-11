from pyulog import ULog
import numpy as np

ulog = ULog('flight.ulg')

# 1. Check actuator_armed for disarm
armed = ulog.get_dataset('actuator_armed').data
for i in range(len(armed['timestamp'])):
    print(f"armed ts={armed['timestamp'][i]}: armed={armed['armed'][i]}")

# 2. Check failure_detector_status
try:
    fds = ulog.get_dataset('failure_detector_status').data
    print("failure_detector_status:")
    for k in fds.keys():
        if len(fds[k]) > 0 and any(fds[k] != 0):
            print(f"  {k}: {fds[k][fds[k] != 0][:5]}")
except Exception as e:
    print("failure_detector_status err:", e)

# 3. Check vehicle_land_detected
vld = ulog.get_dataset('vehicle_land_detected').data
print(f"vehicle_land_detected final states: landed={vld['landed'][-5:]}, freefall={vld['freefall'][-5:]}, ground_contact={vld['ground_contact'][-5:]}")
print(f"land_detected ts: {vld['timestamp'][-5:]}")

# 4. Check vehicle_global_position at the end
vgp = ulog.get_dataset('vehicle_global_position').data
print(f"vgp last 5 points:")
for i in range(-5, 0):
    ts = vgp['timestamp'][i]
    lat = vgp['lat'][i]
    lon = vgp['lon'][i]
    alt = vgp['alt'][i]
    print(f"  ts={ts}: lat={lat:.7f}, lon={lon:.7f}, alt={alt}")

# 5. Check vehicle_status
vs = ulog.get_dataset('vehicle_status').data
print(f"vehicle_status arming_state: {vs['arming_state'][-5:]}, nav_state: {vs['nav_state'][-5:]}")

# 6. Check events or error messages in ulog
for msg in ulog.logged_messages:
    print(f"LOG [{msg.timestamp}]: {msg.message}")
