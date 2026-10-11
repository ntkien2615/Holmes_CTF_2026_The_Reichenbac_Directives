from pyulog import ULog
import numpy as np

ulog = ULog('flight.ulg')

# Check home_position
hp = ulog.get_dataset('home_position').data
print(f"home_position: lat={hp['lat'][0]:.7f}, lon={hp['lon'][0]:.7f}, alt={hp['alt'][0]}")

# Check vehicle_global_position around takeoff
vgp = ulog.get_dataset('vehicle_global_position').data
takeoff = ulog.get_dataset('takeoff_status').data
print(f"takeoff_status timestamps: {takeoff['timestamp']}")
print(f"takeoff_status takeoff_state: {takeoff['takeoff_state']}")

# Check actuator_armed
armed = ulog.get_dataset('actuator_armed').data
armed_ts = armed['timestamp'][armed['armed'] == 1]
print(f"First armed timestamp: {armed_ts[0]}")

# Position at armed_ts
idx = (vgp['timestamp'] >= armed_ts[0]).nonzero()[0][0]
print(f"vgp at first armed: lat={vgp['lat'][idx]:.7f}, lon={vgp['lon'][idx]:.7f}, alt={vgp['alt'][idx]}")

# Position at first takeoff status
first_takeoff_ts = takeoff['timestamp'][0]
idx2 = (vgp['timestamp'] >= first_takeoff_ts).nonzero()[0][0]
print(f"vgp at first takeoff status: lat={vgp['lat'][idx2]:.7f}, lon={vgp['lon'][idx2]:.7f}, alt={vgp['alt'][idx2]}")

# Check sensor_gps
gps = ulog.get_dataset('sensor_gps').data
gps_idx = (gps['timestamp'] >= armed_ts[0]).nonzero()[0][0]
print(f"sensor_gps at armed: lat={gps['latitude_deg'][gps_idx]:.7f}, lon={gps['longitude_deg'][gps_idx]:.7f}, alt={gps['altitude_msl_m'][gps_idx]}")

# Also vehicle_gps_position
vgps = ulog.get_dataset('vehicle_gps_position').data
vgps_idx = (vgps['timestamp'] >= armed_ts[0]).nonzero()[0][0]
print(f"vehicle_gps_position at armed: lat={vgps['lat'][vgps_idx] * 1e-7:.7f}, lon={vgps['lon'][vgps_idx] * 1e-7:.7f}")
