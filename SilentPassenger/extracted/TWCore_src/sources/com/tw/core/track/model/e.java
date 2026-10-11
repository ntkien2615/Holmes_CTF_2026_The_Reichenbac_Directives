package com.tw.core.track.model;

/* loaded from: classes.dex */
public class e {
    protected long C;
    private CoordType D;
    private b E;
    protected int direction;
    protected double height;
    protected double radius;
    protected double speed;

    public b ai() {
        return this.E;
    }

    public CoordType ae() {
        return this.D;
    }

    public double aj() {
        return this.radius;
    }

    public long ah() {
        return this.C;
    }

    public int af() {
        return this.direction;
    }

    public double ak() {
        return this.speed;
    }

    public double ag() {
        return this.height;
    }

    public e() {
    }

    public e(b bVar, CoordType coordType, double d, long j, int i, double d2, double d3) {
        this.E = bVar;
        this.D = coordType;
        this.radius = d;
        this.C = j;
        this.direction = i;
        this.speed = d2;
        this.height = d3;
    }

    public String toString() {
        return "Point [location=" + this.E + ", coordType=" + this.D + ", radius=" + this.radius + ", locTime=" + this.C + ", direction=" + this.direction + ", speed=" + this.speed + ", height=" + this.height + "]";
    }
}
