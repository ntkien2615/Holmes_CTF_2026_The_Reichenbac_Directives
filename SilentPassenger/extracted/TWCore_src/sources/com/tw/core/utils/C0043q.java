package com.tw.core.utils;

import com.tw.core.app.DoFunApplication;
import org.json.JSONObject;

/* renamed from: com.tw.core.utils.q, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0043q {
    private static final String eL = C0043q.class.getSimpleName();

    public static String ig(double d, double d2) {
        String str = "";
        try {
            str = C0028b.gr(com.tw.core.R.string.default_province);
            for (String str2 : DoFunApplication.nF().getResources().getStringArray(com.tw.core.R.array.province_location_array)) {
                String[] split = str2.split("[,]");
                String[] split2 = split[1].split("[|]");
                Double valueOf = Double.valueOf(Double.parseDouble(split2[0]));
                Double valueOf2 = Double.valueOf(Double.parseDouble(split2[1]));
                String[] split3 = split[2].split("[|]");
                Double valueOf3 = Double.valueOf(Double.parseDouble(split3[0]));
                Double valueOf4 = Double.valueOf(Double.parseDouble(split3[1]));
                if (d > valueOf.doubleValue() && d < valueOf2.doubleValue() && d2 > valueOf3.doubleValue() && d2 < valueOf4.doubleValue()) {
                    return split[0];
                }
            }
        } catch (Exception e) {
            C0038l.hF(eL, e);
        }
        return str;
    }

    public static com.tw.core.c.c ih(String str) {
        JSONObject optJSONObject;
        try {
            JSONObject jSONObject = new JSONObject(H.jD(str));
            String string = jSONObject.getString("code");
            com.tw.core.c.c cVar = new com.tw.core.c.c();
            cVar.fl(string);
            if ("0".equals(string) && (optJSONObject = jSONObject.optJSONObject("data")) != null) {
                String optString = optJSONObject.optString("title");
                String optString2 = optJSONObject.optString("name");
                String optString3 = optJSONObject.optString("path");
                String optString4 = optJSONObject.optString("flag");
                String optString5 = optJSONObject.optString("version");
                String optString6 = optJSONObject.optString("describe");
                String optString7 = optJSONObject.optString("appId");
                cVar.fj(optString2);
                cVar.fp(optString);
                cVar.fn(optString6);
                cVar.fm(optString4);
                cVar.fo(optString5);
                cVar.fi(optString3);
                cVar.fk(optString7);
                return cVar;
            }
            return cVar;
        } catch (Exception e) {
            C0038l.hF(eL, e);
            return null;
        }
    }
}
