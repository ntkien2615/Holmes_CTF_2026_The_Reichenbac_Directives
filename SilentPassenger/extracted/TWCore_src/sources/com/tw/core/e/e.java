package com.tw.core.e;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.tw.core.app.DoFunApplication;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.H;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

/* loaded from: classes.dex */
public class e {
    private static final String gZ = lC();
    private static DecimalFormat gY = new DecimalFormat("#0");
    private static DecimalFormat gX = new DecimalFormat("#0.#");

    public static boolean lM() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static long lK() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return (long) (statFs.getBlockCount() * statFs.getBlockSize() * 9.766E-4d * 9.766E-4d);
    }

    public static long lG() {
        if (lM()) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        }
        return -1L;
    }

    public static long lF() {
        if (lM()) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return (long) (statFs.getBlockCount() * statFs.getBlockSize() * 9.766E-4d * 9.766E-4d);
        }
        return -1L;
    }

    public static String[] lH() {
        String[] strArr = {"", ""};
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"), 8192);
            String[] split = bufferedReader.readLine().split("\\s+");
            for (int i = 2; i < split.length; i++) {
                strArr[0] = strArr[0] + split[i] + " ";
            }
            strArr[1] = strArr[1] + bufferedReader.readLine().split("\\s+")[2];
            bufferedReader.close();
        } catch (IOException e) {
        }
        return strArr;
    }

    public static String lI() {
        String str = "";
        try {
            InputStream inputStream = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq").start().getInputStream();
            byte[] bArr = new byte[24];
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            str = "N/A";
        }
        return str.trim();
    }

    public static String lL() {
        if (TextUtils.isEmpty(Build.VERSION.RELEASE)) {
            return "0";
        }
        return Build.VERSION.RELEASE;
    }

    public static boolean lB() {
        String jy = H.jy(DoFunApplication.nF());
        C0034h.hr("SystemInfoTools", "gmsVersion %s %s", gZ, jy);
        if (TextUtils.isEmpty(gZ)) {
            return "zh_CN".equals(jy);
        }
        return false;
    }

    public static String lC() {
        return SystemProperties.get("ro.com.google.gmsversion");
    }

    public static boolean lD(Context context) {
        String gt = C0029c.gt(context, "key_gms_version", "-1");
        String lC = lC();
        C0034h.hr("SystemInfoTools", "saveGmsVersion %s gmsVersion %s", gt, lC);
        if ("-1".equals(gt)) {
            return false;
        }
        if (TextUtils.isEmpty(gt) && (!TextUtils.isEmpty(lC))) {
            return true;
        }
        if (TextUtils.isEmpty(gt)) {
            return false;
        }
        return TextUtils.isEmpty(lC);
    }

    public static String lE() {
        return SystemProperties.get("ro.tw.version");
    }

    public static boolean lJ() {
        return TextUtils.equals("SP9832A", Build.MODEL);
    }
}
