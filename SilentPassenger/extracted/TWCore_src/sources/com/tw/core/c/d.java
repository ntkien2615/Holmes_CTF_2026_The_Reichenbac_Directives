package com.tw.core.c;

/* loaded from: classes.dex */
public class d {
    private String dH;
    private int dI;
    private String dJ;
    private String name;

    public d(String str, int i, String str2, String str3) {
        this.dH = str;
        this.dI = i;
        this.dJ = str2;
        this.name = str3;
    }

    public String fq() {
        return this.dH;
    }

    public int fr() {
        return this.dI;
    }

    public String toString() {
        return "BaseAppInfo{pkgName='" + this.dH + "', verCode=" + this.dI + ", verName='" + this.dJ + "', name='" + this.name + "'}";
    }
}
