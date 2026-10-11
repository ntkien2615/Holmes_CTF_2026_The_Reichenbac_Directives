package com.tw.core.c;

import java.util.List;

/* loaded from: classes.dex */
public class g extends d {
    private String dY;
    private List dZ;
    private String ea;
    private List eb;
    private List ec;
    private boolean ed;
    private List ee;
    private String ef;
    private List eg;
    private String eh;
    private List ei;
    private String ej;
    private int ek;

    public g(String str, int i, String str2, String str3) {
        super(str, i, str2, str3);
    }

    public String ga() {
        return this.dY;
    }

    public g gb(String str) {
        this.dY = str;
        return this;
    }

    public String fX() {
        return this.ej;
    }

    public g gc(String str) {
        this.ej = str;
        return this;
    }

    public String fY() {
        return this.ea;
    }

    public g gd(String str) {
        this.ea = str;
        return this;
    }

    public List fV() {
        return this.ee;
    }

    public g ge(List list) {
        this.ee = list;
        return this;
    }

    public String fZ() {
        return this.ef;
    }

    public g gf(String str) {
        this.ef = str;
        return this;
    }

    public int getType() {
        return this.ek;
    }

    public g gg(int i) {
        this.ek = i;
        return this;
    }

    public boolean fR() {
        return this.ed;
    }

    public g gh(boolean z) {
        this.ed = z;
        return this;
    }

    public List fW() {
        return this.dZ;
    }

    public g gi(List list) {
        this.dZ = list;
        return this;
    }

    public g gj(List list) {
        this.eg = list;
        return this;
    }

    public g gk(List list) {
        this.ei = list;
        return this;
    }

    public List fS() {
        return this.eb;
    }

    public g gl(List list) {
        this.eb = list;
        return this;
    }

    public List fT() {
        return this.ec;
    }

    public g gn(List list) {
        this.ec = list;
        return this;
    }

    public String fU() {
        return this.eh;
    }

    public g gm(String str) {
        this.eh = str;
        return this;
    }

    @Override // com.tw.core.c.d
    public String toString() {
        return "VersionInfo{" + super.toString() + ", address='" + this.dY + "', title='" + this.ej + "', describe='" + this.ea + "', languageList=" + this.ee + ", md5='" + this.ef + "', type=" + this.ek + ", installNotExists=" + this.ed + ", channelList=" + this.dZ + ", systemBlackList=" + this.eg + ", systemWhiteList=" + this.ei + ", fotaBlackList=" + this.eb + ", fotaWhiteList=" + this.ec + ", systemRegular=" + this.eh + '}';
    }
}
