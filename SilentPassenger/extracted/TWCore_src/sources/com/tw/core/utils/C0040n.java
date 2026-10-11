package com.tw.core.utils;

import java.util.HashMap;
import org.json.JSONArray;

/* renamed from: com.tw.core.utils.n, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0040n {
    public static String hY(HashMap hashMap, String str) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("device_no");
        jSONArray.put("partner_name");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jSONArray.length(); i++) {
            String optString = jSONArray.optString(i);
            String str2 = (String) hashMap.get(optString);
            if (str2 == null) {
                C0034h.hs("BaiDuSignUtil", "请求参数必须带 %s", optString);
            } else {
                sb.append(str2).append("|");
            }
        }
        hashMap.put("_sign_key", jSONArray);
        String sb2 = sb.append(str).toString();
        C0034h.hr("BaiDuSignUtil", "md5之前的sign %s", sb2);
        String kk = M.kk(sb2);
        C0034h.hr("BaiDuSignUtil", "最终sign %s", kk);
        hashMap.put("sign", kk);
        return kk;
    }
}
