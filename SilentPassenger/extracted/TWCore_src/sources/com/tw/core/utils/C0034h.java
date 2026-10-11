package com.tw.core.utils;

import android.text.TextUtils;
import android.util.Log;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.tw.core.utils.h, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0034h {
    public static String ew = "DFLog";
    public static boolean ev = false;
    private static final String CLASS_NAME = C0034h.class.getName();

    public static void hu(String str, Object... objArr) {
        if (ev) {
            hr(ew, str, objArr);
        }
    }

    public static void hr(String str, String str2, Object... objArr) {
        if (ev) {
            String ht = ht(str2, objArr);
            C0037k.hC(str, ht);
            Log.d(str, ht);
        }
    }

    public static void hv(String str, Object... objArr) {
        if (ev) {
            hs(ew, str, objArr);
        }
    }

    public static void hs(String str, String str2, Object... objArr) {
        if (ev) {
            String ht = ht(str2, objArr);
            C0037k.hC(str, ht);
            Log.e(str, ht);
        }
    }

    public static void hw(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            hu("Empty/Null json content", new Object[0]);
            return;
        }
        try {
            String trim = str2.trim();
            if (trim.startsWith("{")) {
                hu(new JSONObject(trim).toString(2), new Object[0]);
            } else if (trim.startsWith("[")) {
                hr(str, new JSONArray(trim).toString(2), new Object[0]);
            } else {
                hs(str, "Invalid Json", new Object[0]);
            }
        } catch (JSONException e) {
            hs(str, "Invalid Json", new Object[0]);
        }
    }

    private static String ht(String str, Object... objArr) {
        String str2;
        if (objArr != null && objArr.length != 0) {
            str = String.format(Locale.US, str, objArr);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        int i = 2;
        while (true) {
            if (i >= stackTrace.length) {
                str2 = "<unknown>";
                break;
            }
            StackTraceElement stackTraceElement = stackTrace[i];
            if (stackTraceElement.getClassName().equals(CLASS_NAME)) {
                i++;
            } else {
                str2 = "(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")";
                break;
            }
        }
        return String.format(Locale.US, "thread[%s, %d] %s: %s", Thread.currentThread().getName(), Long.valueOf(Thread.currentThread().getId()), str2, str);
    }
}
