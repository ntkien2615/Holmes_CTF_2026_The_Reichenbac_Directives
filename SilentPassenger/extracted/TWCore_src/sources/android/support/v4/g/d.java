package android.support.v4.g;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* loaded from: classes.dex */
public class d {
    static int oP;
    static Object[] oQ;
    static Object[] oR;
    static int oS;
    int[] oT = i.pb;
    Object[] oU = i.pc;
    int oO = 0;

    private static int ta(int[] iArr, int i, int i2) {
        try {
            return i.tC(iArr, i, i2);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ConcurrentModificationException();
        }
    }

    int th(Object obj, int i) {
        int i2 = this.oO;
        if (i2 == 0) {
            return -1;
        }
        int ta = ta(this.oT, i2, i);
        if (ta < 0) {
            return ta;
        }
        if (obj.equals(this.oU[ta << 1])) {
            return ta;
        }
        int i3 = ta + 1;
        while (i3 < i2 && this.oT[i3] == i) {
            if (obj.equals(this.oU[i3 << 1])) {
                return i3;
            }
            i3++;
        }
        for (int i4 = ta - 1; i4 >= 0 && this.oT[i4] == i; i4--) {
            if (obj.equals(this.oU[i4 << 1])) {
                return i4;
            }
        }
        return ~i3;
    }

    int tc() {
        int i = this.oO;
        if (i == 0) {
            return -1;
        }
        int ta = ta(this.oT, i, 0);
        if (ta < 0) {
            return ta;
        }
        if (this.oU[ta << 1] == null) {
            return ta;
        }
        int i2 = ta + 1;
        while (i2 < i && this.oT[i2] == 0) {
            if (this.oU[i2 << 1] == null) {
                return i2;
            }
            i2++;
        }
        for (int i3 = ta - 1; i3 >= 0 && this.oT[i3] == 0; i3--) {
            if (this.oU[i3 << 1] == null) {
                return i3;
            }
        }
        return ~i2;
    }

    private void tb(int i) {
        Class<j> cls;
        if (i == 8) {
            cls = j.class;
            synchronized (cls) {
                if (oR != null) {
                    Object[] objArr = oR;
                    this.oU = objArr;
                    oR = (Object[]) objArr[0];
                    this.oT = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    oS--;
                }
            }
            return;
        }
        if (i == 4) {
            cls = j.class;
            synchronized (cls) {
                if (oQ != null) {
                    Object[] objArr2 = oQ;
                    this.oU = objArr2;
                    oQ = (Object[]) objArr2[0];
                    this.oT = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    oP--;
                }
            }
            return;
        }
        this.oT = new int[i];
        this.oU = new Object[i << 1];
        this.oT = new int[i];
        this.oU = new Object[i << 1];
    }

    private static void td(int[] iArr, Object[] objArr, int i) {
        Class<j> cls;
        if (iArr.length == 8) {
            cls = j.class;
            synchronized (cls) {
                if (oS < 10) {
                    objArr[0] = oR;
                    objArr[1] = iArr;
                    for (int i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    oR = objArr;
                    oS++;
                }
            }
        } else if (iArr.length == 4) {
            cls = j.class;
            synchronized (cls) {
                if (oP < 10) {
                    objArr[0] = oQ;
                    objArr[1] = iArr;
                    for (int i3 = (i << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    oQ = objArr;
                    oP++;
                }
            }
        } else {
            return;
        }
    }

    public void clear() {
        if (this.oO > 0) {
            int[] iArr = this.oT;
            Object[] objArr = this.oU;
            int i = this.oO;
            this.oT = i.pb;
            this.oU = i.pc;
            this.oO = 0;
            td(iArr, objArr, i);
        }
        if (this.oO > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public void tg(int i) {
        int i2 = this.oO;
        if (this.oT.length < i) {
            int[] iArr = this.oT;
            Object[] objArr = this.oU;
            tb(i);
            if (this.oO > 0) {
                System.arraycopy(iArr, 0, this.oT, 0, i2);
                System.arraycopy(objArr, 0, this.oU, 0, i2 << 1);
            }
            td(iArr, objArr, i2);
        }
        if (this.oO != i2) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return ti(obj) >= 0;
    }

    public int ti(Object obj) {
        return obj == null ? tc() : th(obj, obj.hashCode());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int sY(Object obj) {
        int i = 1;
        int i2 = this.oO * 2;
        Object[] objArr = this.oU;
        if (obj == null) {
            while (i < i2) {
                if (objArr[i] != null) {
                    i += 2;
                } else {
                    return i >> 1;
                }
            }
            return -1;
        }
        while (i < i2) {
            if (!obj.equals(objArr[i])) {
                i += 2;
            } else {
                return i >> 1;
            }
        }
        return -1;
    }

    public boolean containsValue(Object obj) {
        return sY(obj) >= 0;
    }

    public Object get(Object obj) {
        int ti = ti(obj);
        if (ti >= 0) {
            return this.oU[(ti << 1) + 1];
        }
        return null;
    }

    public Object tf(int i) {
        return this.oU[i << 1];
    }

    public Object te(int i) {
        return this.oU[(i << 1) + 1];
    }

    public Object tj(int i, Object obj) {
        int i2 = (i << 1) + 1;
        Object obj2 = this.oU[i2];
        this.oU[i2] = obj;
        return obj2;
    }

    public boolean isEmpty() {
        return this.oO <= 0;
    }

    public Object put(Object obj, Object obj2) {
        int hashCode;
        int th;
        int i = 8;
        int i2 = this.oO;
        if (obj == null) {
            th = tc();
            hashCode = 0;
        } else {
            hashCode = obj.hashCode();
            th = th(obj, hashCode);
        }
        if (th >= 0) {
            int i3 = (th << 1) + 1;
            Object obj3 = this.oU[i3];
            this.oU[i3] = obj2;
            return obj3;
        }
        int i4 = ~th;
        if (i2 >= this.oT.length) {
            if (i2 >= 8) {
                i = (i2 >> 1) + i2;
            } else if (i2 < 4) {
                i = 4;
            }
            int[] iArr = this.oT;
            Object[] objArr = this.oU;
            tb(i);
            if (i2 != this.oO) {
                throw new ConcurrentModificationException();
            }
            if (this.oT.length > 0) {
                System.arraycopy(iArr, 0, this.oT, 0, iArr.length);
                System.arraycopy(objArr, 0, this.oU, 0, objArr.length);
            }
            td(iArr, objArr, i2);
        }
        if (i4 < i2) {
            System.arraycopy(this.oT, i4, this.oT, i4 + 1, i2 - i4);
            System.arraycopy(this.oU, i4 << 1, this.oU, (i4 + 1) << 1, (this.oO - i4) << 1);
        }
        if (i2 != this.oO || i4 >= this.oT.length) {
            throw new ConcurrentModificationException();
        }
        this.oT[i4] = hashCode;
        this.oU[i4 << 1] = obj;
        this.oU[(i4 << 1) + 1] = obj2;
        this.oO++;
        return null;
    }

    public Object remove(Object obj) {
        int ti = ti(obj);
        if (ti >= 0) {
            return sZ(ti);
        }
        return null;
    }

    public Object sZ(int i) {
        int i2;
        Object obj = this.oU[(i << 1) + 1];
        int i3 = this.oO;
        if (i3 <= 1) {
            td(this.oT, this.oU, i3);
            this.oT = i.pb;
            this.oU = i.pc;
            i2 = 0;
        } else {
            int i4 = i3 - 1;
            if (this.oT.length > 8 && this.oO < this.oT.length / 3) {
                int i5 = i3 > 8 ? (i3 >> 1) + i3 : 8;
                int[] iArr = this.oT;
                Object[] objArr = this.oU;
                tb(i5);
                if (i3 != this.oO) {
                    throw new ConcurrentModificationException();
                }
                if (i > 0) {
                    System.arraycopy(iArr, 0, this.oT, 0, i);
                    System.arraycopy(objArr, 0, this.oU, 0, i << 1);
                }
                if (i < i4) {
                    System.arraycopy(iArr, i + 1, this.oT, i, i4 - i);
                    System.arraycopy(objArr, (i + 1) << 1, this.oU, i << 1, (i4 - i) << 1);
                }
                i2 = i4;
            } else {
                if (i < i4) {
                    System.arraycopy(this.oT, i + 1, this.oT, i, i4 - i);
                    System.arraycopy(this.oU, (i + 1) << 1, this.oU, i << 1, (i4 - i) << 1);
                }
                this.oU[i4 << 1] = null;
                this.oU[(i4 << 1) + 1] = null;
                i2 = i4;
            }
        }
        if (i3 != this.oO) {
            throw new ConcurrentModificationException();
        }
        this.oO = i2;
        return obj;
    }

    public int size() {
        return this.oO;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof d) {
            d dVar = (d) obj;
            if (size() != dVar.size()) {
                return false;
            }
            for (int i = 0; i < this.oO; i++) {
                try {
                    Object tf = tf(i);
                    Object te = te(i);
                    Object obj2 = dVar.get(tf);
                    if (te == null) {
                        if (obj2 != null || (!dVar.containsKey(tf))) {
                            return false;
                        }
                    } else if (!te.equals(obj2)) {
                        return false;
                    }
                } catch (ClassCastException e) {
                    return false;
                } catch (NullPointerException e2) {
                    return false;
                }
            }
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (size() != map.size()) {
            return false;
        }
        for (int i2 = 0; i2 < this.oO; i2++) {
            try {
                Object tf2 = tf(i2);
                Object te2 = te(i2);
                Object obj3 = map.get(tf2);
                if (te2 == null) {
                    if (obj3 != null || (!map.containsKey(tf2))) {
                        return false;
                    }
                } else if (!te2.equals(obj3)) {
                    return false;
                }
            } catch (ClassCastException e3) {
                return false;
            } catch (NullPointerException e4) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int[] iArr = this.oT;
        Object[] objArr = this.oU;
        int i = this.oO;
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            Object obj = objArr[i2];
            i4 += (obj == null ? 0 : obj.hashCode()) ^ iArr[i3];
            i3++;
            i2 += 2;
        }
        return i4;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.oO * 28);
        sb.append('{');
        for (int i = 0; i < this.oO; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            Object tf = tf(i);
            if (tf != this) {
                sb.append(tf);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object te = te(i);
            if (te != this) {
                sb.append(te);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
