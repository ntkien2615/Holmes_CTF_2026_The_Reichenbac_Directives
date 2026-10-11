from pyulog import ULog
import numpy as np

ulog = ULog('flight.ulg')
vgp = ulog.get_dataset('vehicle_global_position').data
vlp = ulog.get_dataset('vehicle_local_position').data

# Look from ts=520764000 to 535000000
idx = (vgp['timestamp'] >= 520764000) & (vgp['timestamp'] <= 535000000)
ts = vgp['timestamp'][idx]
lat = vgp['lat'][idx]
lon = vgp['lon'][idx]
alt = vgp['alt'][idx]

print(f"Altitude profile after motor kill:")
for i in range(0, len(ts), 50):
    print(f"  ts={ts[i]}: lat={lat[i]:.7f}, lon={lon[i]:.7f}, alt={alt[i]:.2f}")

# Look at when altitude reaches minimum or stabilizes on ground
print(f"\nFinal ground altitude:")
idx_ground = (vgp['timestamp'] >= 530000000)
print(f"Mean lat on ground: {np.mean(vgp['lat'][idx_ground]):.7f}")
print(f"Mean lon on ground: {np.mean(vgp['lon'][idx_ground]):.7f}")
print(f"At disarm (ts=576968000):")
idx_disarm = (vgp['timestamp'] >= 576968000).nonzero()[0][0]
print(f"  lat={vgp['lat'][idx_disarm]:.7f}, lon={vgp['lon'][idx_disarm]:.7f}, alt={vgp['alt'][idx_disarm]:.2f}")

# Check when vertical velocity vz becomes 0 or acceleration spikes (impact!)
accel = ulog.get_dataset('sensor_accel').data
acc_ts = accel['timestamp']
acc_z = accel['z']
# check spike
idx_acc = (acc_ts >= 520764000) & (acc_ts <= 530000000)
print(f"Max accel between 520764000 and 530000000:")
print(f"  max |z| = {np.max(np.abs(acc_z[idx_acc]))} at ts={acc_ts[idx_acc][np.argmax(np.abs(acc_z[idx_acc]))]}")

# Also check vehicle_local_position vx, vy, vz
idx_lp = (vlp['timestamp'] >= 520764000) & (vlp['timestamp'] <= 530000000)
print(f"Min z in local position (z is down in NED, so max z is ground):")
max_z_idx = np.argmax(vlp['z'][idx_lp])
print(f"  max z = {vlp['z'][idx_lp][max_z_idx]:.2f} at ts={vlp['timestamp'][idx_lp][max_z_idx]}")
impact_ts = vlp['timestamp'][idx_lp][max_z_idx]
idx_impact_gp = (vgp['timestamp'] >= impact_ts).nonzero()[0][0]
print(f"Position at impact (ts={impact_ts}): lat={vgp['lat'][idx_impact_gp]:.7f}, lon={vgp['lon'][idx_impact_gp]:.7f}")

# Check position at 530000000 onwards
print(f"Position at 530s: lat={vgp['lat'][idx_ground][0]:.7f}, lon={vgp['lon'][idx_ground][0]:.7f}")
print(f"Position at 540s: lat={vgp['lat'][(vgp['timestamp']>=540000000).nonzero()[0][0]]:.7f}, lon={vgp['lon'][(vgp['timestamp']>=540000000).nonzero()[0][0]]:.7f}")
print(f"Position at 576s: lat={vgp['lat'][(vgp['timestamp']>=576000000).nonzero()[0][0]]:.7f}, lon={vgp['lon'][(vgp['timestamp']>=576000000).nonzero()[0][0]]:.7f}")
