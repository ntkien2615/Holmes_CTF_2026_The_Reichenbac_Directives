package com.a.b.a;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public final class v {
    public static final String a = aq.a("]^Y\\OiWKSX", 10);
    public static final String b = aq.a("bqqJe", 1);
    public static final String c = aq.a("KOGK", 2);
    public static final String d = aq.a("SW]S", 10);
    public static final String e = aq.a("VIK6", 4);
    private SharedPreferences f;

    public v(Context context, String str) {
        this.f = context.getSharedPreferences(str, 0);
    }

    private static SharedPreferences a(Context context) {
        return context.getSharedPreferences(a, 0);
    }

    public static String a(Context context, String str) {
        return a(context).getString(str, "");
    }

    public static void a(Context context, String str, long j) {
        SharedPreferences.Editor edit = a(context).edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public static void a(Context context, String str, String str2) {
        SharedPreferences.Editor edit = a(context).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public static long b(Context context, String str) {
        return b(context, str, 0L);
    }

    public static long b(Context context, String str, long j) {
        return a(context).getLong(str, j);
    }

    public final void a(String str) {
        SharedPreferences.Editor edit = this.f.edit();
        for (int i = 0; i < 5; i++) {
            edit.remove(aq.a("wtnWrfcvg", 2) + (i + 1)).apply();
        }
        edit.remove(str).apply();
        edit.commit();
    }

    public final void a(String str, String str2) {
        this.f.edit().putString(str, str2).commit();
    }

    public final void a(String str, String[] strArr) {
        this.f.edit().putStringSet(str, new HashSet(Arrays.asList(strArr))).commit();
    }

    public final void b(String str, String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return;
        }
        HashSet hashSet = new HashSet();
        SharedPreferences.Editor edit = this.f.edit();
        for (int i = 0; i < strArr.length; i++) {
            hashSet.add(strArr[i]);
            edit.putString(aq.a("zwqZuifyj", 5) + (i + 1), strArr[i]).apply();
        }
        edit.putStringSet(str, hashSet).apply();
        edit.commit();
    }

    public final String[] b(String str) {
        Set<String> stringSet = this.f.getStringSet(str, null);
        if (stringSet == null) {
            return null;
        }
        return (String[]) stringSet.toArray(new String[stringSet.size()]);
    }

    public final String c(String str) {
        return this.f.getString(str, null);
    }
}
