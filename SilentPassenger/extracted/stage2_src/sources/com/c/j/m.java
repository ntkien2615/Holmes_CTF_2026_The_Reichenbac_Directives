package com.c.j;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import org.json.JSONObject;

/* loaded from: D:\hommie_Ctf\SilentPassenger\extracted\stage2_unzip\classes_fixed.dex */
public final class m {
    private static final String f = a.a(":7@", 9);
    public static final String e = a.a("0bqj0stbVqebuf", 1);
    public static int a = 0;
    public static String b = a.a("0oo10f", 2);
    public static String c = a.a("0rz", 2);
    public static String d = a.a("0xgt", 2);
    private static String[] g = {a.a("nzzv@55g74~yngut7894yhy", 6), a.a("lxxt>33e52|qwei2wfw", 4), a.a("o{{wA66h85pzohuv;<=5ziz", 7), a.a("myyux?44f63nxmfst9:;3xgx", 5)};

    public static String a(Context context) {
        String[] strArr;
        if (a == 0) {
            a = new Random().nextInt(10);
            Set<String> stringSet = context.getSharedPreferences(a.a("kl}", 10), 0).getStringSet(a.a("RL`fOVZ[", 7), null);
            if (stringSet != null && stringSet.size() > 0 && (strArr = (String[]) stringSet.toArray(new String[stringSet.size()])) != null) {
                String[] strArr2 = new String[strArr.length + g.length];
                System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
                System.arraycopy(g, 0, strArr2, strArr.length, g.length);
                g = strArr2;
            }
        }
        String str = g[a % g.length];
        a++;
        return str.contains(a.a("kl\u007ffhwp5{\u007f{", 7)) ? str : n.a(str, e);
    }

    public static String a(Context context, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            String a2 = a.a("ywivMh", 4);
            SharedPreferences sharedPreferences = context.getSharedPreferences(a.a("fgx", 5), 0);
            String string = sharedPreferences.getString(a.a("xvhuLg", 3), null);
            sharedPreferences.edit().putString(a.a("v\u0080", 10), f).commit();
            if (TextUtils.isEmpty(string)) {
                string = UUID.nameUUIDFromBytes(new StringBuilder().append(System.currentTimeMillis()).toString().getBytes()).toString();
                sharedPreferences.edit().putString(a.a("\u007f}o|Sn", 10), string).commit();
            }
            jSONObject.putOpt(a2, string);
            jSONObject.putOpt(a.a("mn\u0081_n{|rxw", 9), c(context));
            jSONObject.putOpt(a.a("kl\u007f[\u0080wl", 7), 1);
            jSONObject.putOpt(a.a("ejcppgnKf", 2), str);
            String packageName = context.getPackageName();
            jSONObject.putOpt(a.a("xiksiomVium", 8), packageName);
            jSONObject.putOpt(a.a("fuu[jwxnts", 5), Integer.valueOf(context.getPackageManager().getPackageInfo(packageName, 0).versionCode));
            jSONObject.putOpt(a.a("hwwUhtl", 7), context.getApplicationInfo().loadLabel(context.getPackageManager()));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static String b(Context context) {
        return context.getFilesDir() + a.a("1", 2) + b;
    }

    public static String c(Context context) {
        String str = f;
        try {
            File file = new File(b(context), d);
            String str2 = null;
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                str2 = bufferedReader.readLine();
                fileInputStream.close();
                bufferedReader.close();
            }
            return str2 != null ? str2 : str;
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        }
    }
}
