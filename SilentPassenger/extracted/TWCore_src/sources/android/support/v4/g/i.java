package android.support.v4.g;

/* loaded from: classes.dex */
class i {
    static final int[] pb = new int[0];
    static final long[] pd = new long[0];
    static final Object[] pc = new Object[0];

    i() {
    }

    public static boolean tD(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj != null) {
            return obj.equals(obj2);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int tC(int[] iArr, int i, int i2) {
        int i3 = 0;
        int i4 = i - 1;
        while (i3 <= i4) {
            int i5 = (i3 + i4) >>> 1;
            int i6 = iArr[i5];
            if (i6 < i2) {
                i3 = i5 + 1;
            } else if (i6 > i2) {
                i4 = i5 - 1;
            } else {
                return i5;
            }
        }
        return ~i3;
    }
}
