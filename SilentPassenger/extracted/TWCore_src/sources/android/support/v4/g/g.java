package android.support.v4.g;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
final class g implements Set {
    final /* synthetic */ e oZ;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(e eVar) {
        this.oZ = eVar;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        this.oZ.tu();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean contains(Object obj) {
        return this.oZ.tn(obj) >= 0;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection collection) {
        return e.tv(this.oZ.ts(), collection);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return this.oZ.tx() == 0;
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return new c(this.oZ, 0);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean remove(Object obj) {
        int tn = this.oZ.tn(obj);
        if (tn < 0) {
            return false;
        }
        this.oZ.ty(tn);
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection collection) {
        return e.tA(this.oZ.ts(), collection);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection collection) {
        return e.tw(this.oZ.ts(), collection);
    }

    @Override // java.util.Set, java.util.Collection
    public int size() {
        return this.oZ.tx();
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray() {
        return this.oZ.tl(0);
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray(Object[] objArr) {
        return this.oZ.tp(objArr, 0);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean equals(Object obj) {
        return e.tB(this, obj);
    }

    @Override // java.util.Set, java.util.Collection
    public int hashCode() {
        int i = 0;
        for (int tx = this.oZ.tx() - 1; tx >= 0; tx--) {
            Object tm = this.oZ.tm(tx, 0);
            i += tm == null ? 0 : tm.hashCode();
        }
        return i;
    }
}
