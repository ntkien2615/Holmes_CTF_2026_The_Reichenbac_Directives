package com.tw.core.utils;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class A {
    private static final String fa = A.class.getSimpleName();
    private static boolean fb;

    public static Map iW(Context context, String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        try {
            String str6 = "0";
            String str7 = "0";
            String str8 = "0";
            String gB = C0029c.gB(context, "mFirstLocation");
            if (!TextUtils.isEmpty(gB)) {
                try {
                    JSONObject jSONObject = new JSONObject(gB);
                    str6 = jSONObject.optString("gpsx", "0");
                    str7 = jSONObject.optString("gpsy", "0");
                    str8 = jSONObject.optString("gpst", "0");
                } catch (Exception e) {
                    str2 = "0";
                    str3 = str7;
                    str4 = str6;
                }
            }
            str2 = str8;
            str3 = str7;
            str4 = str6;
            String le = com.tw.core.e.b.le();
            String lf = com.tw.core.e.b.lf();
            String kV = com.tw.core.e.a.kV();
            String str9 = com.tw.core.e.a.lc(context) + "";
            String lm = com.tw.core.e.b.lm(context);
            String lo = com.tw.core.e.b.lo(context);
            String ls = com.tw.core.e.c.ls(context);
            String str10 = com.tw.core.e.f.lO(context) + "x" + com.tw.core.e.f.lP(context);
            String lQ = com.tw.core.e.f.lQ(context);
            String str11 = com.tw.core.e.e.lK() + "";
            String kZ = com.tw.core.e.a.kZ();
            String kY = com.tw.core.e.a.kY();
            String lL = com.tw.core.e.e.lL();
            String la = com.tw.core.e.a.la();
            String lb = com.tw.core.e.a.lb(context);
            com.tw.core.e.b.le();
            String lR = com.tw.core.e.f.lR(context);
            String kX = com.tw.core.e.a.kX();
            String kW = com.tw.core.e.a.kW(context);
            String kU = com.tw.core.e.a.kU();
            String lh = com.tw.core.e.b.lh(context);
            String ll = com.tw.core.e.b.ll(context);
            String ln = com.tw.core.e.b.ln(context);
            String lg = com.tw.core.e.b.lg(context);
            String lE = com.tw.core.e.e.lE();
            String li = com.tw.core.e.b.li();
            String lk = com.tw.core.e.b.lk();
            String lj = com.tw.core.e.b.lj();
            if (!C0029c.gx(context, "key_cid_change", false)) {
                str5 = null;
            } else {
                str5 = C0029c.gt(context, "key_last_device_cid", null);
            }
            String str12 = TextUtils.isEmpty(lE) ? "unknown" : lE;
            String str13 = SystemProperties.get("ro.fota.oem", "");
            if (TextUtils.isEmpty(str13)) {
                str13 = "unknown";
            }
            HashMap hashMap = new HashMap();
            hashMap.put("mcuType", str);
            hashMap.put("cid", le);
            hashMap.put("cidUnstable", lf);
            hashMap.put("platDetail", kV);
            hashMap.put("simId", ln);
            hashMap.put("imei", ll);
            hashMap.put("netType", ls);
            hashMap.put("screenResolution", str10);
            hashMap.put("screenType", lQ);
            hashMap.put("romSize", str11);
            hashMap.put("plat", kZ);
            hashMap.put("chipPartner", kY);
            hashMap.put("androidVersion", lL);
            hashMap.put("sysVersion", str12);
            hashMap.put("sensor", lb);
            hashMap.put("wifiAddr", kX);
            hashMap.put("ipAddr", kW);
            hashMap.put("bluetooth", kU);
            hashMap.put("simNetName", lh);
            hashMap.put("simCountryCode", lg);
            hashMap.put("brandId", la);
            hashMap.put("screenSize", lR);
            hashMap.put("ramSize", str9);
            hashMap.put("simNetId", lm);
            hashMap.put("simHeadingCode", lo);
            hashMap.put("fotaId", str13);
            hashMap.put("initializeLongitude", str3);
            hashMap.put("initializeLatitude", str4);
            hashMap.put("initializeTime", str2);
            if (str5 != null) {
                hashMap.put("cidOld", str5);
            }
            if (li != null) {
                hashMap.put("cidStable", li);
            }
            if (lk != null) {
                hashMap.put("mcuidStable", lk);
            }
            if (lj != null) {
                hashMap.put("cpuidStable", lj);
            }
            C0038l.hE(fa, "初始化车机信息" + hashMap.toString());
            return hashMap;
        } catch (Exception e2) {
            B.ja().jd(e2);
            return null;
        }
    }

    public static void iX(Context context) {
        C0034h.hr(fa, "car init", new Object[0]);
        if (fb) {
            C0034h.hs(fa, "initing", new Object[0]);
        } else {
            fb = true;
            C0048v.iA(new ac(context));
        }
    }
}
