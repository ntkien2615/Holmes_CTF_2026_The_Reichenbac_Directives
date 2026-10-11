package com.tw.core.utils;

import android.os.Looper;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: com.tw.core.utils.p, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0042p {
    public static volatile C0042p eK;
    private Map eJ = new HashMap();

    public static boolean ic() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    /* renamed from: if, reason: not valid java name */
    public static C0042p m1if() {
        if (eK == null) {
            synchronized (C0042p.class) {
                if (eK == null) {
                    eK = new C0042p();
                }
            }
        }
        return eK;
    }

    private C0042p() {
    }

    public Map ie() {
        return this.eJ;
    }

    public static ExecutorService id() {
        ExecutorService executorService = (ExecutorService) m1if().ie().get(1);
        if (executorService == null) {
            ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
            m1if().ie().put(1, newCachedThreadPool);
            return newCachedThreadPool;
        }
        return executorService;
    }
}
