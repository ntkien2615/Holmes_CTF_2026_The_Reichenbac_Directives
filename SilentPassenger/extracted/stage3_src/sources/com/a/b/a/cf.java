package com.a.b.a;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class cf {
    public static HashMap<String, Class<? extends bt>> a;
    private static Pattern b;

    static {
        HashMap<String, Class<? extends bt>> hashMap = new HashMap<>();
        a = hashMap;
        hashMap.put(aq.a("xkz{xt", 6), bs.class);
        a.put(aq.a("iuv\u007f", 6), bf.class);
        a.put(aq.a("jvvr", 2), bk.class);
        a.put(aq.a("{if", 4), bw.class);
        a.put(aq.a("psehpmf", 4), bl.class);
        a.put(aq.a("qtfiqng7", 5), bn.class);
        a.put(aq.a("psehpmf7", 4), br.class);
        a.put(aq.a("fggrnkpm", 2), bi.class);
        a.put(aq.a("ywfhjwtzyj", 5), bu.class);
        b = Pattern.compile(aq.a("b\u0081.ad\u0081\u0083(c0/b\u0083", 6));
    }

    public static bt a(Context context, JSONObject jSONObject) {
        String jSONObject2 = jSONObject.toString();
        Matcher matcher = b.matcher(jSONObject2);
        while (matcher.find()) {
            String group = matcher.group(1);
            jSONObject2 = jSONObject2.replace(aq.a("\u0085", 10) + group + aq.a("\u0086", 9), a(context, group));
        }
        aq.a("prbvfulsw", 3);
        try {
            jSONObject = new JSONObject(jSONObject2);
        } catch (JSONException e) {
        }
        try {
            bt newInstance = a.get(jSONObject.optJSONObject(aq.a("~k}uSxpy", 10)).optString(aq.a("vciPcog", 2))).newInstance();
            try {
                newInstance.a(jSONObject);
                return newInstance;
            } catch (Exception e2) {
                return newInstance;
            }
        } catch (Exception e3) {
            return null;
        }
    }

    private static String a(Context context, String str) {
        if (str.startsWith(aq.a("yhukp", 7))) {
            String[] split = str.split(aq.a("e", 6));
            if (split.length <= 2) {
                return str;
            }
            int parseInt = Integer.parseInt(split[1]);
            return new StringBuilder().append(s.a.nextInt(Integer.parseInt(split[2]) - parseInt) + parseInt).toString();
        }
        if (str.startsWith(aq.a("zivl{", 8))) {
            String[] split2 = str.split(aq.a("`", 1));
            return split2.length > 1 ? s.b(Integer.parseInt(split2[1])) : str;
        }
        if (str.startsWith(aq.a("nk~o", 10))) {
            return new StringBuilder().append(System.currentTimeMillis()).toString();
        }
        if (str.startsWith(aq.a("rpcog", 2))) {
            return context.getPackageName();
        }
        if (!str.startsWith(aq.a("pn}", 9))) {
            return str;
        }
        String[] split3 = str.split(aq.a("e", 6));
        if (split3.length <= 2) {
            return str;
        }
        String a2 = v.a(context, split3[1]);
        if (!TextUtils.isEmpty(a2)) {
            return a2;
        }
        String b2 = s.b(Integer.parseInt(split3[2]));
        v.a(context, split3[1], b2);
        return b2;
    }
}
