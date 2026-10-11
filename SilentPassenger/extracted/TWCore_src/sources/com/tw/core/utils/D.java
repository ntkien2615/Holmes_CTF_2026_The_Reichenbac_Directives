package com.tw.core.utils;

import android.os.Environment;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class D {
    public static boolean fj = false;
    public static boolean fk;

    static {
        fk = false;
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return;
        }
        try {
            fk = new JSONObject(C0030d.gT(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DoFun/core")).optBoolean("logEnable");
        } catch (JSONException e) {
        }
    }
}
