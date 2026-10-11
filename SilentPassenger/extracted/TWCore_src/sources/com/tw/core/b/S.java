package com.tw.core.b;

import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.LocationManager;
import com.tw.core.utils.C0034h;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class S implements GpsStatus.Listener {
    final /* synthetic */ C0018r cZ;

    /* JADX INFO: Access modifiers changed from: package-private */
    public S(C0018r c0018r) {
        this.cZ = c0018r;
    }

    @Override // android.location.GpsStatus.Listener
    public void onGpsStatusChanged(int i) {
        LocationManager locationManager;
        LocationManager locationManager2;
        int i2;
        int i3;
        int i4;
        try {
            locationManager = this.cZ.bE;
            if (locationManager != null && i == 4) {
                this.cZ.bz = 0;
                locationManager2 = this.cZ.bE;
                GpsStatus gpsStatus = locationManager2.getGpsStatus(null);
                int maxSatellites = gpsStatus.getMaxSatellites();
                Iterator<GpsSatellite> it = gpsStatus.getSatellites().iterator();
                while (it.hasNext()) {
                    i3 = this.cZ.bz;
                    if (i3 > maxSatellites) {
                        break;
                    }
                    if (it.next().usedInFix()) {
                        C0018r c0018r = this.cZ;
                        i4 = c0018r.bz;
                        c0018r.bz = i4 + 1;
                    }
                }
                String str = C0018r.by;
                i2 = this.cZ.bz;
                C0034h.hr(str, "卫星数量：%s", Integer.valueOf(i2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
