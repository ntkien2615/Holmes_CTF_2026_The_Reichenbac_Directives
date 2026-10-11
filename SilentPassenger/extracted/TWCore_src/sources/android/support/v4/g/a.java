package android.support.v4.g;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
final class a implements Iterator, Map.Entry {
    int oE;
    boolean oF = false;
    int oG = -1;
    final /* synthetic */ e oH;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(e eVar) {
        this.oH = eVar;
        this.oE = eVar.tx() - 1;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.oG < this.oE;
    }

    @Override // java.util.Iterator
    public Map.Entry next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.oG++;
        this.oF = true;
        return this;
    }

    @Override // java.util.Iterator
    public void remove() {
        if (!this.oF) {
            throw new IllegalStateException();
        }
        this.oH.ty(this.oG);
        this.oG--;
        this.oE--;
        this.oF = false;
    }

    @Override // java.util.Map.Entry
    public Object getKey() {
        if (!this.oF) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return this.oH.tm(this.oG, 0);
    }

    @Override // java.util.Map.Entry
    public Object getValue() {
        if (!this.oF) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return this.oH.tm(this.oG, 1);
    }

    @Override // java.util.Map.Entry
    public Object setValue(Object obj) {
        if (!this.oF) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return this.oH.tt(this.oG, obj);
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!this.oF) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (i.tD(entry.getKey(), this.oH.tm(this.oG, 0))) {
            return i.tD(entry.getValue(), this.oH.tm(this.oG, 1));
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        if (!this.oF) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        Object tm = this.oH.tm(this.oG, 0);
        Object tm2 = this.oH.tm(this.oG, 1);
        return (tm2 != null ? tm2.hashCode() : 0) ^ (tm == null ? 0 : tm.hashCode());
    }

    public final String toString() {
        return getKey() + "=" + getValue();
    }
}
