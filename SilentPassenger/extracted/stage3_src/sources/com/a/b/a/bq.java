package com.a.b.a;

import java.lang.reflect.Method;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class bq extends l {
    final /* synthetic */ Method a;
    final /* synthetic */ Object b;
    final /* synthetic */ Object[] c;
    final /* synthetic */ bn d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bq(bn bnVar, Method method, Object obj, Object[] objArr) {
        this.d = bnVar;
        this.a = method;
        this.b = obj;
        this.c = objArr;
    }

    @Override // com.a.b.a.l
    public final void a() {
        HashMap hashMap;
        String str;
        try {
            this.a.invoke(this.b, this.c);
            hashMap = bn.r;
            str = this.d.j;
            hashMap.put(str, this.b);
        } catch (Exception e) {
            aq.a("mpbemjc", 1);
            new StringBuilder().append(aq.a("ordgole#huuru#", 3)).append(e.getMessage());
        }
    }
}
