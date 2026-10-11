package com.tw.core.track.model;

/* loaded from: classes.dex */
public abstract class f {
    private String L;
    private long M;
    private long N;

    public long aC() {
        return this.M;
    }

    public String aD() {
        return this.L;
    }

    public long aE() {
        return this.N;
    }

    public f() {
    }

    public f(long j, String str, long j2) {
        this.M = j;
        this.L = str;
        this.N = j2;
    }

    public String toString() {
        return "BaseRequest [requestID=" + this.M + ", ak=" + this.L + ", serviceId=" + this.N + "]";
    }
}
