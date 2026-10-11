from pyulog import ULog
import numpy as np
import math

ulog = ULog('flight.ulg')
vgp = ulog.get_dataset('vehicle_global_position').data
vlp = ulog.get_dataset('vehicle_local_position').data

ts_release = 446656000
ts_inject  = 520764000

# Haversine formula
def haversine(lat1, lon1, lat2, lon2):
    R = 6371000.0 # Earth radius in meters
    phi1 = math.radians(lat1)
    phi2 = math.radians(lat2)
    dphi = math.radians(lat2 - lat1)
    dlambda = math.radians(lon2 - lon1)
    a = math.sin(dphi/2.0)**2 + math.cos(phi1)*math.cos(phi2)*math.sin(dlambda/2.0)**2
    c = 2 * math.atan2(math.sqrt(a), math.sqrt(1 - a))
    return R * c

# 1. Local position (x, y are north, east in meters)
idx_rel_lp = (vlp['timestamp'] >= ts_release).nonzero()[0][0]
idx_inj_lp = (vlp['timestamp'] >= ts_inject).nonzero()[0][0]

x1, y1 = vlp['x'][idx_rel_lp], vlp['y'][idx_rel_lp]
x2, y2 = vlp['x'][idx_inj_lp], vlp['y'][idx_inj_lp]
euclidean_lp = math.sqrt((x2 - x1)**2 + (y2 - y1)**2)

# Cumulative distance in local position
dx = np.diff(vlp['x'][idx_rel_lp:idx_inj_lp+1])
dy = np.diff(vlp['y'][idx_rel_lp:idx_inj_lp+1])
cum_dist_lp = np.sum(np.sqrt(dx**2 + dy**2))

# 2. Global position
idx_rel_gp = (vgp['timestamp'] >= ts_release).nonzero()[0][0]
idx_inj_gp = (vgp['timestamp'] >= ts_inject).nonzero()[0][0]

lat1, lon1 = vgp['lat'][idx_rel_gp], vgp['lon'][idx_rel_gp]
lat2, lon2 = vgp['lat'][idx_inj_gp], vgp['lon'][idx_inj_gp]
straight_gp = haversine(lat1, lon1, lat2, lon2)

# Cumulative distance in global position
lats = vgp['lat'][idx_rel_gp:idx_inj_gp+1]
lons = vgp['lon'][idx_rel_gp:idx_inj_gp+1]
cum_dist_gp = sum(haversine(lats[i], lons[i], lats[i+1], lons[i+1]) for i in range(len(lats)-1))

print(f"Position at release (ts={ts_release}): lat={lat1:.7f}, lon={lon1:.7f}, x={x1:.3f}, y={y1:.3f}")
print(f"Position at inject  (ts={ts_inject}): lat={lat2:.7f}, lon={lon2:.7f}, x={x2:.3f}, y={y2:.3f}")
print(f"Straight-line displacement (Local Position): {euclidean_lp:.3f} m (nearest: {round(euclidean_lp)})")
print(f"Straight-line displacement (Global Haversine): {straight_gp:.3f} m (nearest: {round(straight_gp)})")
print(f"Cumulative path distance (Local Position): {cum_dist_lp:.3f} m (nearest: {round(cum_dist_lp)})")
print(f"Cumulative path distance (Global Haversine): {cum_dist_gp:.3f} m (nearest: {round(cum_dist_gp)})")
