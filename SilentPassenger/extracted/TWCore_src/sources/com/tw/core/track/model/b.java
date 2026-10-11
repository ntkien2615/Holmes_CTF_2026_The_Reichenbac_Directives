package com.tw.core.track.model;

/* loaded from: classes.dex */
public class b {
    private double latitude;
    private double longitude;

    public double S() {
        return this.latitude;
    }

    public double T() {
        return this.longitude;
    }

    public b() {
    }

    public b(double d, double d2) {
        this.latitude = d;
        this.longitude = d2;
    }

    public String toString() {
        return "LatLng [latitude=" + this.latitude + ", longitude=" + this.longitude + "]";
    }
}
