package org.eclipse.paho.client.mqttv3.util;

/* loaded from: classes.dex */
public final class Strings {
    private static final int INDEX_NOT_FOUND = -1;

    public static boolean equalsAny(CharSequence charSequence, CharSequence[] charSequenceArr) {
        boolean z;
        if (charSequence != null) {
            z = false;
        } else {
            z = charSequenceArr == null;
        }
        if (charSequenceArr != null) {
            for (CharSequence charSequence2 : charSequenceArr) {
                z = z || charSequence2.equals(charSequence);
            }
        }
        return z;
    }

    public static boolean containsAny(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence2 == null) {
            return false;
        }
        return containsAny(charSequence, toCharArray(charSequence2));
    }

    public static boolean containsAny(CharSequence charSequence, char[] cArr) {
        if (isEmpty(charSequence) || isEmpty(cArr)) {
            return false;
        }
        int length = charSequence.length();
        int length2 = cArr.length;
        int i = length + INDEX_NOT_FOUND;
        int i2 = length2 + INDEX_NOT_FOUND;
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = charSequence.charAt(i3);
            for (int i4 = 0; i4 < length2; i4++) {
                if (cArr[i4] == charAt) {
                    if (!Character.isHighSurrogate(charAt) || i4 == i2) {
                        return true;
                    }
                    if (i3 < i && cArr[i4 + 1] == charSequence.charAt(i3 + 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    private static boolean isEmpty(char[] cArr) {
        return cArr == null || cArr.length == 0;
    }

    private static char[] toCharArray(CharSequence charSequence) {
        if (charSequence instanceof String) {
            return ((String) charSequence).toCharArray();
        }
        int length = charSequence.length();
        char[] cArr = new char[charSequence.length()];
        for (int i = 0; i < length; i++) {
            cArr[i] = charSequence.charAt(i);
        }
        return cArr;
    }

    public static int countMatches(CharSequence charSequence, CharSequence charSequence2) {
        int i = 0;
        if (isEmpty(charSequence) || isEmpty(charSequence2)) {
            return 0;
        }
        int i2 = 0;
        while (true) {
            int indexOf = indexOf(charSequence, charSequence2, i);
            if (indexOf != INDEX_NOT_FOUND) {
                i2++;
                i = indexOf + charSequence2.length();
            } else {
                return i2;
            }
        }
    }

    private static int indexOf(CharSequence charSequence, CharSequence charSequence2, int i) {
        return charSequence.toString().indexOf(charSequence2.toString(), i);
    }

    private Strings() {
    }
}
