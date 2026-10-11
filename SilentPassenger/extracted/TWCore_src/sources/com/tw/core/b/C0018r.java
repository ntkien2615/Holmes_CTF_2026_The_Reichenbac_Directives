package com.tw.core.b;

import android.content.Context;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import com.tw.core.utils.C0038l;

/* renamed from: com.tw.core.b.r, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0018r {
    public static String by = "LocationClient";
    private Context bB;
    private long bC;
    private LocationManager bE;
    private InterfaceC0019s bF;
    private int bz;
    private boolean bG = C0038l.ez;
    private GpsStatus.Listener bD = new S(this);
    private LocationListener bA = new T(this);

    public C0018r(Context context) {
        this.bB = context;
    }

    public void db(InterfaceC0019s interfaceC0019s, long j) {
        if (j < 1000) {
            j = 5000;
        }
        this.bF = interfaceC0019s;
        de(j);
    }

    public void da(long j) {
        C0038l.hE(by, "setCollectionInterval mCurInterval = " + this.bC + ",interval=" + j);
        if (j >= 1000 && this.bC != j) {
            de(j);
        }
    }

    public static Location dc(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        if (locationManager == null || locationManager.getAllProviders() == null || !locationManager.getAllProviders().contains("gps")) {
            return null;
        }
        return locationManager.getLastKnownLocation("gps");
    }

    private void de(long j) {
        if (this.bE == null) {
            this.bE = (LocationManager) this.bB.getSystemService("location");
        }
        dd(this.bA);
        if (this.bE != null && this.bE.getAllProviders().contains("gps")) {
            this.bE.requestLocationUpdates("gps", j, 0.0f, this.bA);
            this.bE.addGpsStatusListener(this.bD);
        }
        this.bC = j;
    }

    public void release() {
        dd(this.bA);
        this.bA = null;
        this.bF = null;
        this.bE = null;
    }

    public void dd(LocationListener locationListener) {
        if (this.bE != null && locationListener != null) {
            this.bE.removeUpdates(locationListener);
        }
    }
}
