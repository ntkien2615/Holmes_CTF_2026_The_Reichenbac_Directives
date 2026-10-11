package com.tw.core.c;

/* loaded from: classes.dex */
public class c {
    private String dA;
    private String dB;
    private String dC;
    private String dD;
    private String dE;
    private String dF;
    private String dG;
    private String dy;
    private String dz;

    public void fl(String str) {
        this.dB = str;
    }

    public void fj(String str) {
        this.dz = str;
    }

    public void fp(String str) {
        this.dG = str;
    }

    public void fn(String str) {
        this.dD = str;
    }

    public String fh() {
        return this.dC;
    }

    public void fm(String str) {
        this.dC = str;
    }

    public void fo(String str) {
        this.dE = str;
    }

    public String ff() {
        return this.dy;
    }

    public void fi(String str) {
        this.dy = str;
    }

    public String toString() {
        return "VersionUpdateBean{code='" + this.dB + "', appName='" + this.dz + "', versionTitle='" + this.dG + "', versionContent='" + this.dD + "', versionSize='" + this.dF + "', versionCode='" + this.dC + "', versionName='" + this.dE + "', addressLink='" + this.dy + "', appid='" + this.dA + "'}";
    }

    public String fg() {
        return this.dA;
    }

    public void fk(String str) {
        this.dA = str;
    }
}
