package com.a.b.a;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class bd {
    private static bd a;
    private ExecutorService b = Executors.newCachedThreadPool();
    private ScheduledExecutorService c = Executors.newScheduledThreadPool(10);

    private bd() {
    }

    public static void a() {
        if (a != null) {
            bd bdVar = a;
            bdVar.b.shutdownNow();
            bdVar.c.shutdownNow();
            bdVar.b = null;
            bdVar.c = null;
            a = null;
        }
    }

    public static void a(Runnable runnable) {
        if (a == null) {
            a = new bd();
        }
        a.b.execute(runnable);
    }

    public static void a(Runnable runnable, long j) {
        if (a == null) {
            a = new bd();
        }
        a.c.schedule(runnable, j, TimeUnit.MILLISECONDS);
    }
}
