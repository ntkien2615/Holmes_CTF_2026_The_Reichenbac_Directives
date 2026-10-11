package com.tw.core.g;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.tw.core.utils.F;

/* loaded from: classes.dex */
class e extends BroadcastReceiver {
    private F iN;
    final /* synthetic */ d iO;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ e(d dVar, e eVar) {
        this(dVar);
    }

    private e(d dVar) {
        this.iO = dVar;
    }

    public void nB(F f) {
        this.iN = f;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (TextUtils.equals(intent.getAction(), "android.intent.action.PACKAGE_ADDED")) {
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            if (this.iN != null) {
                this.iN.ju(schemeSpecificPart);
                return;
            }
            return;
        }
        TextUtils.equals(intent.getAction(), "android.intent.action.PACKAGE_REMOVED");
    }
}
