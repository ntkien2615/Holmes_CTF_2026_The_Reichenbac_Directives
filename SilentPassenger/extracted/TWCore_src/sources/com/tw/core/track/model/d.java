package com.tw.core.track.model;

/* loaded from: classes.dex */
public abstract class d {
    private long A;
    private int B;
    private String y;
    private String z;

    public void ac(String str) {
        this.y = str;
    }

    public long ad() {
        return this.A;
    }

    public int Z() {
        return this.B;
    }

    public void aa(int i) {
        this.B = i;
    }

    public void ab(String str) {
        this.z = str;
    }

    public d(long j, int i, String str) {
        this.A = j;
        this.B = i;
        this.z = str;
    }

    public String toString() {
        return "BaseResponse [responseID=" + this.A + ", status=" + this.B + ", message=" + this.z + "]";
    }
}
