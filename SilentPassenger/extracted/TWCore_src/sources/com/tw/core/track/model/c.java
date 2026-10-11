package com.tw.core.track.model;

import java.util.Map;

/* loaded from: classes.dex */
public class c extends e {
    private Map v;
    private String w;
    private long x;

    public String V() {
        return this.w;
    }

    public Map U() {
        return this.v;
    }

    public void X(Map map) {
        this.v = map;
    }

    public c() {
    }

    public c(b bVar, CoordType coordType, double d, long j, int i, double d2, double d3, String str, Map map) {
        super(bVar, coordType, d, j, i, d2, d3);
        this.w = str;
        this.v = map;
    }

    @Override // com.tw.core.track.model.e
    public String toString() {
        return "TrackPoint [radius=" + this.radius + ", locTime=" + this.C + ", direction=" + this.direction + ", speed=" + this.speed + ", height=" + this.height + ", objectKey=" + this.w + ", columns=" + this.v + ", sysTime=" + this.x + "]";
    }

    public long W() {
        return this.x;
    }

    public void Y(long j) {
        this.x = j;
    }
}
