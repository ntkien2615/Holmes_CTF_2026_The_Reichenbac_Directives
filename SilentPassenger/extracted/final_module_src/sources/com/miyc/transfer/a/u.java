package com.miyc.transfer.a;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public final class u {
    private static final ExecutorService a = Executors.newCachedThreadPool();

    public static void a(Runnable runnable) {
        a.execute(runnable);
    }
}
