package android.support.v4.g;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class e {
    g oV;
    h oW;
    b oX;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object tm(int i, int i2);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int tn(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int to(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Map ts();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object tt(int i, Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void tu();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int tx();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void ty(int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void tz(Object obj, Object obj2);

    public static boolean tv(Map map, Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!map.containsKey(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static boolean tA(Map map, Collection collection) {
        int size = map.size();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            map.remove(it.next());
        }
        return size != map.size();
    }

    public static boolean tw(Map map, Collection collection) {
        int size = map.size();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return size != map.size();
    }

    public Object[] tl(int i) {
        int tx = tx();
        Object[] objArr = new Object[tx];
        for (int i2 = 0; i2 < tx; i2++) {
            objArr[i2] = tm(i2, i);
        }
        return objArr;
    }

    public Object[] tp(Object[] objArr, int i) {
        int tx = tx();
        Object[] objArr2 = objArr.length < tx ? (Object[]) Array.newInstance(objArr.getClass().getComponentType(), tx) : objArr;
        for (int i2 = 0; i2 < tx; i2++) {
            objArr2[i2] = tm(i2, i);
        }
        if (objArr2.length > tx) {
            objArr2[tx] = null;
        }
        return objArr2;
    }

    public static boolean tB(Set set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set2 = (Set) obj;
        try {
            if (set.size() == set2.size()) {
                return set.containsAll(set2);
            }
            return false;
        } catch (ClassCastException e) {
            return false;
        } catch (NullPointerException e2) {
            return false;
        }
    }

    public Set tr() {
        if (this.oX == null) {
            this.oX = new b(this);
        }
        return this.oX;
    }

    public Set tq() {
        if (this.oV == null) {
            this.oV = new g(this);
        }
        return this.oV;
    }

    public Collection tk() {
        if (this.oW == null) {
            this.oW = new h(this);
        }
        return this.oW;
    }
}
