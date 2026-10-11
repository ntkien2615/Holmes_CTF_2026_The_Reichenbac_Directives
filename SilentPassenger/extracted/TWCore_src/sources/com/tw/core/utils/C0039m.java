package com.tw.core.utils;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.HandlerThread;
import com.tw.core.app.DoFunApplication;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.tw.core.utils.m, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0039m {
    private static volatile C0039m eI;
    private Context eD;
    private Handler eF;
    private double eB = 6378.137d;
    private LocationListener eE = new X(this);
    private List eG = new ArrayList();
    private int eC = C0029c.gy(DoFunApplication.nF(), "numberOfEmqConnect", 0);
    private int eH = C0029c.gy(DoFunApplication.nF(), "numberOfDistance", 0);

    private C0039m(Context context) {
        this.eD = context;
        C0034h.hr("CalculateDeviceActiveUtil", "emqConnectTime %s mSumDistance %s", Integer.valueOf(this.eC), Integer.valueOf(this.eH));
    }

    public static C0039m hK() {
        if (eI == null) {
            synchronized (C0039m.class) {
                if (eI == null) {
                    eI = new C0039m(DoFunApplication.nF());
                }
            }
        }
        return eI;
    }

    public void hU() {
        if (!hL()) {
            hR();
        }
    }

    public void hS() {
        C0034h.hr("CalculateDeviceActiveUtil", "startEmqConnectTimer", new Object[0]);
        if (com.tw.core.e.e.lB()) {
            C0034h.hs("CalculateDeviceActiveUtil", "国内 stop", new Object[0]);
            return;
        }
        if (hM()) {
            C0034h.hs("CalculateDeviceActiveUtil", "emq 满足", new Object[0]);
            return;
        }
        if (this.eF == null) {
            HandlerThread handlerThread = new HandlerThread("active_emq");
            handlerThread.start();
            this.eF = new Handler(handlerThread.getLooper());
        } else {
            this.eF.removeCallbacksAndMessages(null);
        }
        this.eF.postDelayed(new Y(this), 60000L);
    }

    public void hQ(Context context) {
        if (!com.tw.core.e.e.lB() && hM()) {
            Intent intent = new Intent("dofun.intent.EMQ_CONNECT_TEN");
            intent.putExtra("ActivateTime", C0029c.gz(context, "hwActivateTime"));
            context.sendBroadcast(intent);
            C0034h.hr("CalculateDeviceActiveUtil", "sendHwDeviceActiveBroadcast", new Object[0]);
            com.tw.core.model.c.mX(context).mY();
        }
    }

    public void hP(Context context) {
        if (com.tw.core.e.e.lB() && hL()) {
            Intent intent = new Intent("dofun.intent.SHOW_FLOW");
            intent.putExtra("ActivateTime", C0029c.gz(context, "activateTime"));
            context.sendBroadcast(intent);
            C0034h.hr("CalculateDeviceActiveUtil", "sendDeviceActiveBroadcast", new Object[0]);
            com.tw.core.model.c.mX(context).mY();
        }
    }

    public void hN() {
        if (this.eF != null) {
            this.eF.removeCallbacksAndMessages(null);
        }
    }

    public boolean hL() {
        return this.eH >= 10;
    }

    public boolean hM() {
        return this.eC >= 20;
    }

    private void hR() {
        LocationManager locationManager = (LocationManager) this.eD.getSystemService("location");
        if (locationManager != null && locationManager.getAllProviders().contains("gps") && android.support.v4.app.i.qp(DoFunApplication.nF(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            locationManager.requestLocationUpdates("gps", 10000L, 0.0f, this.eE);
        }
    }

    private void hT() {
        LocationManager locationManager = (LocationManager) this.eD.getSystemService("location");
        if (locationManager != null) {
            locationManager.removeUpdates(this.eE);
        }
    }

    public void hH(Location location) {
        C0038l.hE("CalculateDeviceActiveUtil", "已经跑了第" + this.eH + "次1公里位移");
        if (this.eH >= 10) {
            hT();
            return;
        }
        this.eG.add(location);
        if (Math.abs(((Location) this.eG.get(this.eG.size() - 1)).getTime() - ((Location) this.eG.get(0)).getTime()) >= 300000 && this.eG.size() >= 3) {
            hI(this.eG);
            this.eG.clear();
        }
    }

    private void hI(List list) {
        try {
            Location location = (Location) list.get(list.size() - 1);
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            Location location2 = (Location) list.get(0);
            double latitude2 = location2.getLatitude();
            double longitude2 = location2.getLongitude();
            Location location3 = (Location) list.get((list.size() / 2) + 1);
            double latitude3 = location3.getLatitude();
            double longitude3 = location3.getLongitude();
            double hJ = hJ(latitude2, longitude2, latitude3, longitude3);
            double hJ2 = hJ(latitude3, longitude3, latitude, longitude);
            C0038l.hE("CalculateDeviceActiveUtil", "跑了" + (hJ + hJ2) + "米的位移");
            if ((hJ + hJ2) / 1000.0d >= 1.0d && this.eH <= 10) {
                this.eH++;
                C0038l.hE("CalculateDeviceActiveUtil", "第" + this.eH + "次1公里位移");
                C0029c.gD(this.eD, "numberOfDistance", this.eH);
                if (this.eH >= 10) {
                    C0038l.hE("CalculateDeviceActiveUtil", "达到10次1公里位移=" + this.eH);
                    C0029c.gu(this.eD, "ActiviAddress", new BigDecimal(String.valueOf(latitude)).setScale(6, 4) + "," + new BigDecimal(String.valueOf(longitude)).setScale(6, 4));
                    C0029c.gE(this.eD, "activateTime", location.getTime() / 1000);
                    com.tw.core.model.c.mX(this.eD).mY();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double hO(double d) {
        return (3.141592653589793d * d) / 180.0d;
    }

    private double hJ(double d, double d2, double d3, double d4) {
        double hO = hO(d);
        double hO2 = hO(d3);
        double hO3 = hO(d2) - hO(d4);
        return Math.round(((Math.asin(Math.sqrt(((Math.cos(hO) * Math.cos(hO2)) * Math.pow(Math.sin(hO3 / 2.0d), 2.0d)) + Math.pow(Math.sin((hO - hO2) / 2.0d), 2.0d))) * 2.0d) * this.eB) * 1000000.0d) / 1000;
    }
}
