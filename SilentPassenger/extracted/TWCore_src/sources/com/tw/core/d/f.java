package com.tw.core.d;

import android.os.Looper;
import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0034h;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class f {
    private static volatile f gH;
    private Set gG = new HashSet();

    public static f kL() {
        if (gH == null) {
            synchronized (f.class) {
                if (gH == null) {
                    gH = new f();
                }
            }
        }
        return gH;
    }

    public boolean kN(String str) {
        return this.gG.contains(str);
    }

    public boolean kM(String str, long j, String str2, String str3) {
        Process exec;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            C0034h.hs("LogcatManager", "当前主线程禁止抓日志", new Object[0]);
            return false;
        }
        if (!C0030d.gO(new File(str2))) {
            return false;
        }
        try {
            this.gG.add(str3);
            String format = String.format("logcat %s -f %s & \n", str, str2 + File.separator + str3);
            C0034h.hr("LogcatManager", "开始抓取日志 %s", format);
            exec = Runtime.getRuntime().exec(format);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.gG.remove(str3);
        }
        if (exec == null) {
            return false;
        }
        try {
            Thread.sleep(j);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        C0034h.hr("LogcatManager", "停止抓日志线程", new Object[0]);
        exec.destroy();
        return true;
    }
}
