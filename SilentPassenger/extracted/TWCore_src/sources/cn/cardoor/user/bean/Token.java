package cn.cardoor.user.bean;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Token implements Parcelable {
    public static final Parcelable.Creator CREATOR = new a();
    private int pg;
    private String ph;
    private String pi;
    private long pj;

    public Token() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Token(Parcel parcel) {
        this.ph = parcel.readString();
        this.pi = parcel.readString();
        this.pj = parcel.readLong();
        this.pg = parcel.readInt();
    }

    public JSONObject tF() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("refreshToken", this.ph);
            jSONObject.put("token", this.pi);
            jSONObject.put("expireTime", this.pg);
            jSONObject.put("tokenTime", this.pj);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Token tG(JSONObject jSONObject) {
        String optString = jSONObject.optString("refreshToken");
        String optString2 = jSONObject.optString("token");
        int optInt = jSONObject.optInt("expireTime");
        return new Token().tI(optString).tJ(optString2).tH(optInt).tK(jSONObject.optLong("tokenTime"));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.ph);
        parcel.writeString(this.pi);
        parcel.writeLong(this.pj);
        parcel.writeInt(this.pg);
    }

    public String getToken() {
        return this.pi;
    }

    public Token tJ(String str) {
        this.pi = str;
        return this;
    }

    public Token tI(String str) {
        this.ph = str;
        return this;
    }

    public Token tH(int i) {
        this.pg = i;
        return this;
    }

    public Token tK(long j) {
        this.pj = j;
        return this;
    }

    public String toString() {
        return "Token{refreshToken='" + this.ph + "', token='" + this.pi + "', expireTime=" + this.pg + ", tokenTime=" + this.pj + '}';
    }
}
