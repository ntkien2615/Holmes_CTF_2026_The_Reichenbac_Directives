package android.support.v4.g;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
final class c implements Iterator {
    int oJ;
    final int oK;
    int oL;
    final /* synthetic */ e oM;
    boolean oN = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(e eVar, int i) {
        this.oM = eVar;
        this.oK = i;
        this.oJ = eVar.tx();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.oL < this.oJ;
    }

    @Override // java.util.Iterator
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Object tm = this.oM.tm(this.oL, this.oK);
        this.oL++;
        this.oN = true;
        return tm;
    }

    @Override // java.util.Iterator
    public void remove() {
        if (!this.oN) {
            throw new IllegalStateException();
        }
        this.oL--;
        this.oJ--;
        this.oN = false;
        this.oM.ty(this.oL);
    }
}
