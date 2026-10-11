package android.support.v4.g;

import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes.dex */
final class h implements Collection {
    final /* synthetic */ e pa;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(e eVar) {
        this.pa = eVar;
    }

    @Override // java.util.Collection
    public boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public void clear() {
        this.pa.tu();
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        return this.pa.to(obj) >= 0;
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return this.pa.tx() == 0;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return new c(this.pa, 1);
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        int i = this.pa.to(obj);
        if (i < 0) {
            return false;
        }
        this.pa.ty(i);
        return true;
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection collection) {
        int i = 0;
        int tx = this.pa.tx();
        boolean z = false;
        while (i < tx) {
            if (collection.contains(this.pa.tm(i, 1))) {
                this.pa.ty(i);
                i--;
                tx--;
                z = true;
            }
            i++;
        }
        return z;
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection collection) {
        int i = 0;
        int tx = this.pa.tx();
        boolean z = false;
        while (i < tx) {
            if (!collection.contains(this.pa.tm(i, 1))) {
                this.pa.ty(i);
                i--;
                tx--;
                z = true;
            }
            i++;
        }
        return z;
    }

    @Override // java.util.Collection
    public int size() {
        return this.pa.tx();
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        return this.pa.tl(1);
    }

    @Override // java.util.Collection
    public Object[] toArray(Object[] objArr) {
        return this.pa.tp(objArr, 1);
    }
}
