package com.tw.core.e;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import android.tw.john.TWUtil;
import com.tw.core.utils.C0038l;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;

/* loaded from: classes.dex */
public class a {
    private a() {
        throw new UnsupportedOperationException("u can't fuck me...");
    }

    public static String kY() {
        if (TextUtils.isEmpty(Build.MANUFACTURER)) {
            return "0";
        }
        return Build.MANUFACTURER;
    }

    public static String kZ() {
        String str = Build.MODEL;
        if (str != null) {
            return str.trim().replaceAll("\\s*", "");
        }
        return "0";
    }

    public static String kU() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            String address = defaultAdapter.getAddress();
            if (!TextUtils.isEmpty(address)) {
                return address;
            }
            return "0";
        }
        return "0";
    }

    public static String lb(Context context) {
        List<Sensor> sensorList = ((SensorManager) context.getSystemService("sensor")).getSensorList(-1);
        String str = "";
        for (int i = 0; i < sensorList.size(); i++) {
            str = str + sensorList.get(i).getName() + ",";
        }
        if (str.length() > 0) {
            return str.substring(0, str.length() - 1);
        }
        return "0";
    }

    public static String la() {
        TWUtil tWUtil = new TWUtil();
        if (tWUtil.open((short[]) null) == 0) {
            String format = String.format("%x", Integer.valueOf(tWUtil.write(65521)));
            tWUtil.close();
            return format;
        }
        return "-1";
    }

    public static String kX() {
        String str = "";
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address").getInputStream()));
            while (str != null) {
                str = lineNumberReader.readLine();
                if (str != null) {
                    return str.trim();
                }
            }
            return "0";
        } catch (IOException e) {
            C0038l.hF("DeviceInfoTools", e);
            return "0";
        }
    }

    public static String ld(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    public static long lc(Context context) {
        long j = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            String readLine = bufferedReader.readLine();
            String[] split = readLine.split("\\s+");
            for (String str : split) {
                C0038l.hE(readLine, str + "\t");
            }
            j = Long.parseLong(split[1]);
            bufferedReader.close();
        } catch (Exception e) {
            C0038l.hF("DeviceInfoTools", e);
        }
        return j / 1024;
    }

    public static String kV() {
        if (TextUtils.isEmpty(Build.DEVICE)) {
            return "0";
        }
        return Build.DEVICE;
    }

    public static String kW(Context context) {
        String ld = ld(((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getIpAddress());
        if (!TextUtils.isEmpty(ld)) {
            return ld;
        }
        return "-1";
    }
}
