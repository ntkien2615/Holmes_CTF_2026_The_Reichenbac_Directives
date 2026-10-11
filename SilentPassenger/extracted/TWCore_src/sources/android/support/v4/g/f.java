package android.support.v4.g;

import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class f extends e {
    final /* synthetic */ j oY;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(j jVar) {
        this.oY = jVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.g.e
    public int tx() {
        return this.oY.oO;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.g.e
    public Object tm(int i, int i2) {
        return this.oY.oU[(i << 1) + i2];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.g.e
    public int tn(Object obj) {
        return this.oY.ti(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.g.e
    public int to(Object obj) {
        return this.oY.sY(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.g.e
    public Map ts() {
        return this.oY;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.g.e
    public void tz(Object obj, Object obj2) {
        this.oY.put(obj, obj2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.g.e
    public Object tt(int i, Object obj) {
        return this.oY.tj(i, obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.g.e
    public void ty(int i) {
        this.oY.sZ(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.g.e
    public void tu() {
        this.oY.clear();
    }
}
