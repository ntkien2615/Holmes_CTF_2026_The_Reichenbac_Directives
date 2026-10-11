package com.miyc.transfer;

import java.util.concurrent.ThreadFactory;

/* loaded from: classes.dex */
final class m implements ThreadFactory {
    final /* synthetic */ l a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(l lVar) {
        this.a = lVar;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, com.miyc.transfer.a.a.a("FolhqwKdqgohu0", 3) + System.currentTimeMillis());
        thread.setDaemon(true);
        return thread;
    }
}
