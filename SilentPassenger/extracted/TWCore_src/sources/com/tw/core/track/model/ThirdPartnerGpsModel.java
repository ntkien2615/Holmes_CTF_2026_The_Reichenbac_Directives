package com.tw.core.track.model;

import android.location.Location;
import android.text.TextUtils;
import com.tw.core.utils.C0038l;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ThirdPartnerGpsModel implements Serializable {
    private int direction;
    private double height;
    private double latitude;
    private double longitude;
    private double radius;
    private double speed;
    private long time;
    private static DecimalFormat G = new DecimalFormat("#0.000000", new DecimalFormatSymbols(Locale.CHINA));
    private static DecimalFormat F = new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.CHINA));

    public static ThirdPartnerGpsModel am(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            double optDouble = jSONObject.optDouble("latitude");
            double optDouble2 = jSONObject.optDouble("longitude");
            long optLong = jSONObject.optLong("loc_time");
            double optDouble3 = jSONObject.optDouble("speed");
            int optInt = jSONObject.optInt("direction");
            double optDouble4 = jSONObject.optDouble("height");
            double optDouble5 = jSONObject.optDouble("radius");
            ThirdPartnerGpsModel thirdPartnerGpsModel = new ThirdPartnerGpsModel();
            thirdPartnerGpsModel.ax(optDouble);
            thirdPartnerGpsModel.ay(optDouble2);
            thirdPartnerGpsModel.aB(optLong);
            thirdPartnerGpsModel.aA(optDouble3);
            thirdPartnerGpsModel.av(optInt);
            thirdPartnerGpsModel.aw(optDouble4);
            thirdPartnerGpsModel.az(optDouble5);
            return thirdPartnerGpsModel;
        } catch (JSONException e) {
            e.printStackTrace();
            C0038l.hE("convertGpsModel", "JSONException=" + str);
            return null;
        }
    }

    public JSONObject al() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("latitude", ao());
            jSONObject.put("longitude", ap());
            jSONObject.put("loc_time", an());
            jSONObject.put("speed", au());
            jSONObject.put("height", as());
            jSONObject.put("direction", ar());
            jSONObject.put("radius", at());
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ThirdPartnerGpsModel aq(Location location) {
        if (location == null) {
            return null;
        }
        ThirdPartnerGpsModel thirdPartnerGpsModel = new ThirdPartnerGpsModel();
        thirdPartnerGpsModel.ax(Double.parseDouble(G.format(location.getLatitude())));
        thirdPartnerGpsModel.ay(Double.parseDouble(G.format(location.getLongitude())));
        thirdPartnerGpsModel.aB(location.getTime());
        thirdPartnerGpsModel.aA(Double.parseDouble(F.format(location.getSpeed())));
        thirdPartnerGpsModel.av((int) location.getBearing());
        thirdPartnerGpsModel.aw(Double.parseDouble(F.format(location.getAltitude())));
        thirdPartnerGpsModel.az(Double.parseDouble(F.format(location.getAccuracy())));
        return thirdPartnerGpsModel;
    }

    public double ap() {
        return this.longitude;
    }

    public void ay(double d) {
        this.longitude = d;
    }

    public double ao() {
        return this.latitude;
    }

    public void ax(double d) {
        this.latitude = d;
    }

    public double as() {
        return this.height;
    }

    public void aw(double d) {
        this.height = d;
    }

    public double at() {
        return this.radius;
    }

    public void az(double d) {
        this.radius = d;
    }

    public double au() {
        return this.speed;
    }

    public void aA(double d) {
        this.speed = d;
    }

    public int ar() {
        return this.direction;
    }

    public void av(int i) {
        this.direction = i;
    }

    public long an() {
        return this.time;
    }

    public void aB(long j) {
        this.time = j;
    }

    public String toString() {
        return "ThirdPartnerGpsModel{longitude=" + this.longitude + ", latitude=" + this.latitude + ", direction=" + this.direction + ", height=" + this.height + ", radius=" + this.radius + ", speed=" + this.speed + ", time=" + this.time + '}';
    }
}
