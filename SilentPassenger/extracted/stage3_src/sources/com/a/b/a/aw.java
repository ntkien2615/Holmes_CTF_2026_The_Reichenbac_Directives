package com.a.b.a;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class aw {
    private static String a() {
        String str;
        Exception e;
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(aq.a("7xzwk7kx}qvnw", 8)), 8192);
            str = bufferedReader.readLine().split(aq.a("<^u", 2))[1];
        } catch (Exception e2) {
            str = null;
            e = e2;
        }
        try {
            bufferedReader.close();
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            return str;
        }
        return str;
    }

    public static JSONObject a(Context context) {
        String bssid;
        WifiInfo connectionInfo;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(aq.a("mqim", 4), TextUtils.isEmpty(null) ? b(context, aq.a("OSKO", 6)) : null);
            String a = aq.a("ptlp9", 7);
            int i = Build.VERSION.SDK_INT;
            jSONObject.put(a, TextUtils.isEmpty("") ? b(context, aq.a("RVNR;", 9)) : "");
            String a2 = aq.a("jntj", 1);
            String str = null;
            if (TextUtils.isEmpty(null)) {
                str = v.a(context, aq.a("PTZP", 7));
                if (TextUtils.isEmpty(str)) {
                    str = new u().b();
                    v.a(context, aq.a("RV\\R", 9), str);
                }
            }
            jSONObject.put(a2, str);
            jSONObject.put(aq.a("uik", 8), d(context));
            String a3 = aq.a("erhvsmhMh", 4);
            String string = Settings.Secure.getString(context.getContentResolver(), aq.a("dqgurlgblg", 3));
            if (TextUtils.isEmpty(string)) {
                string = s.b(16).toLowerCase(Locale.US);
            }
            jSONObject.put(a3, string);
            jSONObject.put(aq.a("iyhuk", 7), Build.BRAND);
            jSONObject.put(aq.a("wynov", 10), Build.MODEL);
            jSONObject.put(aq.a("ngxj}gxk", 6), Build.HARDWARE);
            jSONObject.put(aq.a("ocpwhcevwtgt", 2), Build.MANUFACTURER);
            String a4 = aq.a("yixkkt", 6);
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            jSONObject.put(a4, displayMetrics.widthPixels + aq.a(".", 4) + displayMetrics.heightPixels);
            jSONObject.put(aq.a("xipNsy", 5), Build.VERSION.SDK_INT);
            jSONObject.put(aq.a("kpetgogpvcn", 2), Build.VERSION.INCREMENTAL);
            jSONObject.put(aq.a("dqctf", 2), Build.BOARD);
            jSONObject.put(aq.a("no\u0080smo", 10), Build.DEVICE);
            String a5 = aq.a("dpvousz", 1);
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(aq.a("vnutk", 6));
            Locale locale = Locale.getDefault();
            jSONObject.put(a5, telephonyManager.getSimState() == 5 ? telephonyManager.getSimCountryIso().toLowerCase(Locale.getDefault()) : locale.getCountry().toLowerCase(locale));
            String a6 = aq.a("duukf", 2);
            if (a(context, aq.a("ivlzwql6xmzuq{{qwv6IKKM[[g_QNQg[\\I\\M", 8))) {
                WifiInfo connectionInfo2 = ((WifiManager) context.getSystemService(aq.a("zlil", 3))).getConnectionInfo();
                bssid = connectionInfo2 != null ? connectionInfo2.getBSSID() : "";
            } else {
                bssid = "";
            }
            jSONObject.put(a6, bssid);
            jSONObject.put(aq.a("mr|yuj\u0082", 9), Build.DISPLAY);
            jSONObject.put(aq.a("fgxkegJquv", 2), Build.HOST);
            jSONObject.put(aq.a("lm~qkmQl", 8), Build.ID);
            jSONObject.put(aq.a("psxqo|Z|sx~", 10), Build.FINGERPRINT);
            jSONObject.put(aq.a("rtqfwev", 2), Build.PRODUCT);
            jSONObject.put(aq.a("mn\u007frln\\n{rju", 9), Build.SERIAL);
            jSONObject.put(aq.a("mz\u007fWynov", 10), a());
            jSONObject.put(aq.a("|nknSfrj", 5), (!a(context, aq.a("erhvsmh2tivqmwwmsr2EGGIWWc[MJMcWXEXI", 4)) || (connectionInfo = ((WifiManager) context.getSystemService(aq.a("\u007fqnq", 8))).getConnectionInfo()) == null) ? "" : connectionInfo.getSSID());
            jSONObject.put(aq.a("jvo", 6), context.getResources().getDisplayMetrics().densityDpi);
            jSONObject.put(aq.a("ye", 4), WebSettings.getDefaultUserAgent(context));
            jSONObject.put(aq.a("ywivEkirx", 4), System.getProperty(aq.a("lxxt2ekirx", 4)));
        } catch (Exception e) {
            try {
                jSONObject.put(aq.a("ivvsv", 4), e.getLocalizedMessage());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return jSONObject;
    }

    private static boolean a(Context context, String str) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }

    private static String b(Context context, String str) {
        String a = v.a(context, str);
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        String a2 = new u().a();
        v.a(context, str, a2);
        return a2;
    }

    public static JSONObject b(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(aq.a("whjrhnlUhtl", 7), context.getPackageName());
            jSONObject.put(aq.a("dssQdph", 3), context.getApplicationInfo().loadLabel(context.getPackageManager()).toString());
            jSONObject.put(aq.a("wfstjpoObnf", 1), am.a());
            jSONObject.put(aq.a("}lyzpvuJvkl", 7), c(context));
        } catch (Exception e) {
        }
        return jSONObject;
    }

    private static String c(Context context) {
        try {
            return new StringBuilder().append(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode).toString();
        } catch (PackageManager.NameNotFoundException e) {
            return aq.a("jempih", 4);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0026, code lost:
    
        if (com.a.b.a.aq.a("35=33=33=33=33=33", 3).equals(r0) != false) goto L7;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String d(android.content.Context r5) {
        /*
            r3 = 5
            java.lang.String r1 = ""
            java.lang.String r0 = "\u0080ror"
            r2 = 9
            java.lang.String r0 = com.a.b.a.aq.a(r0, r2)     // Catch: java.lang.Exception -> L3f
            java.lang.Object r0 = r5.getSystemService(r0)     // Catch: java.lang.Exception -> L3f
            android.net.wifi.WifiManager r0 = (android.net.wifi.WifiManager) r0     // Catch: java.lang.Exception -> L3f
            android.net.wifi.WifiInfo r0 = r0.getConnectionInfo()     // Catch: java.lang.Exception -> L3f
            java.lang.String r0 = r0.getMacAddress()     // Catch: java.lang.Exception -> L3f
            if (r0 == 0) goto L28
            java.lang.String r1 = "35=33=33=33=33=33"
            r2 = 3
            java.lang.String r1 = com.a.b.a.aq.a(r1, r2)     // Catch: java.lang.Exception -> L47
            boolean r1 = r1.equals(r0)     // Catch: java.lang.Exception -> L47
            if (r1 == 0) goto L32
        L28:
            java.lang.String r1 = "|qfs5"
            r2 = 5
            java.lang.String r1 = com.a.b.a.aq.a(r1, r2)     // Catch: java.lang.Exception -> L47
            java.net.NetworkInterface.getByName(r1)     // Catch: java.lang.Exception -> L47
        L32:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L3e
            java.lang.String r0 = "55?55?55?55?55?55"
            java.lang.String r0 = com.a.b.a.aq.a(r0, r3)
        L3e:
            return r0
        L3f:
            r0 = move-exception
            r4 = r0
            r0 = r1
            r1 = r4
        L43:
            r1.printStackTrace()
            goto L32
        L47:
            r1 = move-exception
            goto L43
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.b.a.aw.d(android.content.Context):java.lang.String");
    }
}
