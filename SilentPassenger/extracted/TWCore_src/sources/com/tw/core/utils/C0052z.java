package com.tw.core.utils;

import android.app.ActivityManager;
import com.tw.core.app.DoFunApplication;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

/* renamed from: com.tw.core.utils.z, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0052z {
    public static String iU(String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(String.format("pm path %s \n", str)).getInputStream()));
            StringBuilder sb = new StringBuilder();
            while (true) {
                try {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine + "\n");
                    } finally {
                        bufferedReader.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            C0034h.hr("RuntimeCmdManager", "result %s", sb);
            String replace = sb.toString().replace("package:", "").replace("\n", "");
            File file = new File(replace);
            if (file.exists()) {
                C0034h.hr("RuntimeCmdManager", "file %s", file);
                return replace;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    public static Process iT(String str) {
        Process process;
        try {
            process = Runtime.getRuntime().exec(str);
        } catch (IOException e) {
            C0038l.hE("RuntimeCmdManager", "exec Runtime commond:" + str + ", IOException" + e);
            process = null;
        }
        C0038l.hE("RuntimeCmdManager", "exec Runtime commond:" + str + ", Process:" + process);
        return process;
    }

    public static boolean iS(int i) {
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) DoFunApplication.nF().getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.equals(DoFunApplication.nF().getPackageName()) && i == runningAppProcessInfo.pid) {
                return true;
            }
        }
        return false;
    }

    public static boolean iV(String str) {
        Iterator<T> it = ((ActivityManager) DoFunApplication.nF().getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
        while (it.hasNext()) {
            if (((ActivityManager.RunningServiceInfo) it.next()).service.getClassName().equals(str)) {
                return true;
            }
        }
        return false;
    }
}
