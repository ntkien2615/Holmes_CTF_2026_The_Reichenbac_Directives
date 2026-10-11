package com.tw.core.messenger;

import android.os.Bundle;
import android.os.Message;

/* loaded from: classes.dex */
public class b {
    public static Message lZ(String str, int i) {
        Message obtain = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putString("data", str);
        obtain.what = i;
        obtain.obj = bundle;
        return obtain;
    }
}
