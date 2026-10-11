package com.ast.sdk;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.a.b.a.am;
import com.a.b.a.aq;
import com.a.b.a.ar;
import com.a.b.a.ba;
import com.a.b.a.bd;
import com.a.b.a.i;
import com.a.b.a.k;
import com.a.b.a.m;
import com.a.b.a.s;
import com.a.b.a.v;
import java.io.File;

/* loaded from: classes.dex */
public class BillingMain extends ar {
    public static Context a;
    private boolean b = false;

    private void deleteFile(Context context) {
        File file = new File(context.getFilesDir(), aq.a("{ls6riz", 8));
        if (file.exists()) {
            file.delete();
        }
        File file2 = new File(context.getFilesDir(), aq.a("|mt7mn\u0081", 9));
        if (file2.exists()) {
            file2.delete();
        }
    }

    private String getCurrentProcessName() {
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) a.getSystemService(aq.a("jl}r\u007fr}\u0082", 9))).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return "";
    }

    private void init(Context context) {
        am.a(context);
        bd.a(new a(this, context, v.a(context, aq.a("kzzSn", 10))), 1000L);
    }

    private void update() {
    }

    @Override // com.a.b.a.ar
    public void AppAction(String str, String str2) {
    }

    public void callForId(Context context, Bundle bundle) {
    }

    public void checkExit(Context context, String str) {
    }

    @Override // com.a.b.a.ar
    public void errorResult(String str) {
        Log.i(aq.a("thpu", 7), aq.a("no\u0082*|ovyknon*", 10) + str);
    }

    public void init(Context context, Activity activity) {
        try {
            ba.a = s.b(context);
            Log.i(aq.a("rwr}", 9), aq.a("who$mrmx$gsvi", 4));
            deleteFile(context);
            a = context;
            i.a(context);
            m.a().a(context);
            s.a();
            s.a(context);
            init(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init(Context context, Activity activity, String str) {
        if (!TextUtils.isEmpty(str)) {
            v.a(context, aq.a("bqqJe", 1), str);
        }
        k.a().a = Integer.valueOf(str).intValue();
        init(context, activity);
    }

    public void init(Context context, Activity activity, String str, String str2) {
        init(context, activity, str);
    }

    @Override // com.a.b.a.ar
    public void init(Context context, Activity activity, String str, String str2, String str3, String str4) {
        init(context, activity, str);
    }

    public void push(Context context) {
    }

    @Override // com.a.b.a.ar
    public void report(Context context) {
    }

    public void share() {
    }

    public void showBanner(Context context, View view, int i) {
    }

    public void showSorceWall(Context context, int i) {
    }

    public void showSpotAds(Context context) {
    }

    @Override // com.a.b.a.ar
    public void start() {
        try {
            this.b = true;
            bd.a(new b(this), 3000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.a.b.a.ar
    public void stop() {
        try {
            this.b = false;
            bd.a();
            i.a();
            s.a(v.a(a, aq.a("bqqJe", 1)));
        } catch (Exception e) {
        }
    }

    public void unRegistReceiver(Context context) {
    }
}
