package com.tw.core.track.model;

import android.content.Context;
import android.location.Location;
import android.text.TextUtils;
import com.tw.core.b.C0003c;
import com.tw.core.utils.C0038l;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class GpsModel implements Serializable {
    private static DecimalFormat t = new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.CHINA));
    private static DecimalFormat u = new DecimalFormat("#0.000000", new DecimalFormatSymbols(Locale.CHINA));
    private String cid;
    private String direction;
    private String height;
    private String latitude;
    private String longitude;
    private String radius;
    private String speed;
    private String time;
    private String uid;
    private String upid;

    public static GpsModel s(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("latitude");
            String string2 = jSONObject.getString("longitude");
            String string3 = jSONObject.getString("loc_time");
            String string4 = jSONObject.getString("speed");
            String string5 = jSONObject.getString("direction");
            String string6 = jSONObject.getString("height");
            String string7 = jSONObject.getString("radius");
            String optString = jSONObject.has("uid") ? jSONObject.optString("uid") : null;
            GpsModel gpsModel = new GpsModel();
            gpsModel.K(string);
            gpsModel.L(string2);
            gpsModel.O(string3);
            gpsModel.N(string4);
            gpsModel.I(string5);
            gpsModel.J(string6);
            gpsModel.M(string7);
            gpsModel.H(com.tw.core.e.b.le());
            gpsModel.P(optString);
            return gpsModel;
        } catch (JSONException e) {
            e.printStackTrace();
            C0038l.hE("convertGpsModel", "JSONException=" + str);
            return null;
        }
    }

    public static GpsModel t(Context context, Location location) {
        if (location == null) {
            return null;
        }
        GpsModel gpsModel = new GpsModel();
        gpsModel.K(String.valueOf(location.getLatitude()));
        gpsModel.L(String.valueOf(location.getLongitude()));
        gpsModel.O(String.valueOf(location.getTime() / 1000));
        gpsModel.N(String.valueOf(location.getSpeed() * 3.6d));
        gpsModel.I(String.valueOf(location.getBearing()));
        gpsModel.J(String.valueOf(location.getAltitude()));
        gpsModel.M(String.valueOf(location.getAccuracy()));
        gpsModel.H(com.tw.core.e.b.le());
        gpsModel.P(C0003c.cb().ca());
        return gpsModel;
    }

    public static GpsModel R(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("latitude");
            String optString2 = jSONObject.optString("longitude");
            String optString3 = jSONObject.optString("timestamp");
            String optString4 = jSONObject.optString("speed");
            String optString5 = jSONObject.optString("direction");
            String optString6 = jSONObject.optString("altitude");
            String optString7 = jSONObject.optString("precision");
            String optString8 = jSONObject.optString("upid");
            String optString9 = jSONObject.has("uid") ? jSONObject.optString("uid") : null;
            GpsModel gpsModel = new GpsModel();
            gpsModel.K(optString);
            gpsModel.L(optString2);
            gpsModel.O(optString3);
            gpsModel.N(optString4);
            gpsModel.I(optString5);
            gpsModel.J(optString6);
            gpsModel.M(optString7);
            gpsModel.H(com.tw.core.e.b.le());
            gpsModel.P(optString9);
            gpsModel.Q(optString8);
            return gpsModel;
        } catch (JSONException e) {
            e.printStackTrace();
            C0038l.hE("convertGpsModel", "JSONException=" + str);
            return null;
        }
    }

    public static GpsModel G(Location location, long j) {
        if (location == null) {
            return null;
        }
        GpsModel gpsModel = new GpsModel();
        gpsModel.K(u.format(location.getLatitude()));
        gpsModel.L(u.format(location.getLongitude()));
        gpsModel.O(String.valueOf(location.getTime() / 1000));
        gpsModel.N(t.format(location.getSpeed()));
        gpsModel.I(String.valueOf((int) location.getBearing()));
        gpsModel.J(t.format(location.getAltitude()));
        gpsModel.M(t.format(location.getAccuracy()));
        gpsModel.H(com.tw.core.e.b.le());
        gpsModel.P(C0003c.cb().ca());
        gpsModel.Q(String.valueOf(j));
        return gpsModel;
    }

    public String u() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("latitude", z());
            jSONObject.put("longitude", A());
            jSONObject.put("altitude", y());
            jSONObject.put("timestamp", D());
            jSONObject.put("speed", C());
            jSONObject.put("direction", x());
            jSONObject.put("precision", B());
            jSONObject.put("cid", w());
            jSONObject.put("uid", E());
            jSONObject.put("upid", F());
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String v() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("latitude", z());
            jSONObject.put("longitude", A());
            jSONObject.put("loc_time", D());
            jSONObject.put("speed", C());
            jSONObject.put("height", y());
            jSONObject.put("direction", x());
            jSONObject.put("radius", B());
            jSONObject.put("uid", E());
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String w() {
        return this.cid;
    }

    public void H(String str) {
        this.cid = str;
    }

    public String z() {
        return this.latitude;
    }

    public void K(String str) {
        this.latitude = str;
    }

    public String A() {
        return this.longitude;
    }

    public void L(String str) {
        this.longitude = str;
    }

    public String D() {
        return this.time;
    }

    public void O(String str) {
        this.time = str;
    }

    public String x() {
        return this.direction;
    }

    public void I(String str) {
        this.direction = str;
    }

    public String y() {
        return this.height;
    }

    public void J(String str) {
        this.height = str;
    }

    public String B() {
        return this.radius;
    }

    public void M(String str) {
        this.radius = str;
    }

    public String C() {
        return this.speed;
    }

    public void N(String str) {
        this.speed = str;
    }

    public String toString() {
        return "GpsModel{cid='" + this.cid + "', longitude='" + this.longitude + "', latitude='" + this.latitude + "', direction='" + this.direction + "', height='" + this.height + "', radius='" + this.radius + "', speed='" + this.speed + "', time='" + this.time + "', uid='" + this.uid + "', upid='" + this.upid + "'}";
    }

    public String E() {
        return this.uid;
    }

    public void P(String str) {
        this.uid = str;
    }

    public String F() {
        return this.upid;
    }

    public void Q(String str) {
        this.upid = str;
    }
}
