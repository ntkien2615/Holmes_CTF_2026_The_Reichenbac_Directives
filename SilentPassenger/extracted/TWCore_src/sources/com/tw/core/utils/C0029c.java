package com.tw.core.utils;

import android.content.Context;
import android.content.SharedPreferences;

/* renamed from: com.tw.core.utils.c, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0029c {
    public static String eo = "DF_ASSISTANT_USER";

    private C0029c() {
        throw new AssertionError();
    }

    public static void gu(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(eo, 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static boolean gF(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(eo, 0).edit();
        edit.putString(str, str2);
        return edit.commit();
    }

    public static void gv(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(eo, 0).edit();
        edit.clear();
        edit.commit();
    }

    public static String gB(Context context, String str) {
        return gt(context, str, null);
    }

    public static String gt(Context context, String str, String str2) {
        return context.getSharedPreferences(eo, 0).getString(str, str2);
    }

    public static void gD(Context context, String str, int i) {
        SharedPreferences.Editor edit = context.getSharedPreferences(eo, 0).edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public static int gy(Context context, String str, int i) {
        return context.getSharedPreferences(eo, 0).getInt(str, i);
    }

    public static void gE(Context context, String str, long j) {
        SharedPreferences.Editor edit = context.getSharedPreferences(eo, 0).edit();
        edit.putLong(str, j);
        edit.apply();
    }

    public static long gz(Context context, String str) {
        return gA(context, str, -1L);
    }

    public static long gA(Context context, String str, long j) {
        return context.getSharedPreferences(eo, 0).getLong(str, j);
    }

    public static void gC(Context context, String str, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(eo, 0).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public static boolean gw(Context context, String str) {
        return gx(context, str, false);
    }

    public static boolean gx(Context context, String str, boolean z) {
        return context.getSharedPreferences(eo, 0).getBoolean(str, z);
    }
}
