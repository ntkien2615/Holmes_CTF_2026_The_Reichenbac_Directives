package com.a.b.a;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes.dex */
public final class m extends Handler {
    public static final String a = aq.a("TNbhLJUUKJLT", 9);
    private static m b;
    private Context c;

    private m(Looper looper) {
        super(looper);
    }

    public static synchronized m a() {
        m mVar;
        synchronized (m.class) {
            if (b == null) {
                b = new m(Looper.getMainLooper());
            }
            mVar = b;
        }
        return mVar;
    }

    public final void a(Context context) {
        this.c = context;
    }

    public final void a(l lVar) {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putSerializable(a, lVar);
        message.setData(bundle);
        message.what = 2;
        sendMessage(message);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        switch (message.what) {
            case 2:
                l lVar = (l) message.getData().getSerializable(a);
                if (lVar != null) {
                    try {
                        lVar.a();
                        break;
                    } catch (Exception e) {
                        break;
                    }
                }
                break;
        }
        super.handleMessage(message);
    }
}
