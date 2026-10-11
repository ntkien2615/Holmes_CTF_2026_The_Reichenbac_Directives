package android.support.v4.g;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
final class b implements Set {
    final /* synthetic */ e oI;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(e eVar) {
        this.oI = eVar;
    }

    @Override // java.util.Set, java.util.Collection
    /* renamed from: sX, reason: merged with bridge method [inline-methods] */
    public boolean add(Map.Entry entry) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection collection) {
        int tx = this.oI.tx();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            this.oI.tz(entry.getKey(), entry.getValue());
        }
        return tx != this.oI.tx();
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        this.oI.tu();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        int tn = this.oI.tn(entry.getKey());
        if (tn < 0) {
            return false;
        }
        return i.tD(this.oI.tm(tn, 1), entry.getValue());
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return this.oI.tx() == 0;
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return new a(this.oI);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public int size() {
        return this.oI.tx();
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray(Object[] objArr) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean equals(Object obj) {
        return e.tB(this, obj);
    }

    @Override // java.util.Set, java.util.Collection
    public int hashCode() {
        int tx = this.oI.tx() - 1;
        int i = 0;
        while (tx >= 0) {
            Object tm = this.oI.tm(tx, 0);
            Object tm2 = this.oI.tm(tx, 1);
            tx--;
            i += (tm2 == null ? 0 : tm2.hashCode()) ^ (tm == null ? 0 : tm.hashCode());
        }
        return i;
    }
}
