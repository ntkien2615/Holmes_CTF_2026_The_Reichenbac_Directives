package com.miyc.transfer.a;

/* loaded from: classes.dex */
public final class t {
    public static byte a(int i) {
        return (byte) ((65280 & i) >> 8);
    }

    public static int a(byte b, byte b2) {
        return ((b & 255) << 8) | (b2 & 255);
    }
}
