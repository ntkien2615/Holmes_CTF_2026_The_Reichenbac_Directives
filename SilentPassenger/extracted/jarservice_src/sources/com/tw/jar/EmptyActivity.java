package com.tw.jar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/* loaded from: classes.dex */
public class EmptyActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Intent intent = new Intent("com.tw.jar.action.MainService");
            intent.setPackage(getPackageName());
            startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        finish();
    }
}
