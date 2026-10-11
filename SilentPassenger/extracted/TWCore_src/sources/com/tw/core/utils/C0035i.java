package com.tw.core.utils;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* renamed from: com.tw.core.utils.i, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0035i {
    public static List hx(JSONArray jSONArray, Class cls) {
        if (jSONArray != null && jSONArray.length() > 0) {
            try {
                ArrayList arrayList = new ArrayList(jSONArray.length());
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        Object obj = jSONArray.get(i);
                        if (!(obj instanceof String) || !TextUtils.isEmpty((CharSequence) obj)) {
                            arrayList.add(obj);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return arrayList;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
}
