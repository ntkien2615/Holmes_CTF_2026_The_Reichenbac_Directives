package com.tw.core.utils;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/* loaded from: classes.dex */
final class X implements LocationListener {
    final /* synthetic */ C0039m gc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public X(C0039m c0039m) {
        this.gc = c0039m;
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
        this.gc.hH(location);
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
}
