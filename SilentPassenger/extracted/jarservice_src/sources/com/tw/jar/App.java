package com.tw.jar;

import android.app.Application;
import android.util.Log;
import com.x.zg.ifz;

/* loaded from: classes.dex */
public class App extends Application {
    private static final String TAG = "App";

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "load 2039");
        ifz.t(this);
    }
}
