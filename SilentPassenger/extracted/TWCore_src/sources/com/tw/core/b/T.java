package com.tw.core.b;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import com.tw.core.utils.C0038l;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class T implements LocationListener {
    final /* synthetic */ C0018r da;

    /* JADX INFO: Access modifiers changed from: package-private */
    public T(C0018r c0018r) {
        this.da = c0018r;
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String str) {
    }

    @Override // android.location.LocationListener
    public void onProviderDisabled(String str) {
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
        InterfaceC0019s interfaceC0019s;
        int i;
        int i2;
        InterfaceC0019s interfaceC0019s2;
        interfaceC0019s = this.da.bF;
        if (interfaceC0019s != null && location != null) {
            double accuracy = location.getAccuracy();
            i = this.da.bz;
            if (i < 5 || accuracy > 10.0d || accuracy <= 0.0d) {
                String str = C0018r.by;
                StringBuilder append = new StringBuilder().append("数据有漂移，GPScount：");
                i2 = this.da.bz;
                C0038l.hE(str, append.append(i2).append("--精度：").append(accuracy).toString());
                return;
            }
            interfaceC0019s2 = this.da.bF;
            interfaceC0019s2.aW(location);
        }
    }
}
