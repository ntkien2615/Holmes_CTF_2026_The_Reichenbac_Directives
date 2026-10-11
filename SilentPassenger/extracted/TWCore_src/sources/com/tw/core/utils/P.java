package com.tw.core.utils;

/* loaded from: classes.dex */
public class P {
    public final int fI;
    public final String fJ;
    public final long fK;

    public P(String str, long j, int i) {
        this.fJ = str;
        this.fK = j;
        this.fI = i;
    }

    public String toString() {
        return "AppUsageStats{pkgName='" + this.fJ + "', usageTime=" + this.fK + ", launchCount=" + this.fI + '}';
    }
}
