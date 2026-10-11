package com.tw.core.c;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class f {
    private String dS;
    private String dT;
    private boolean dU;
    private boolean dV;
    private int dW;
    private int dX;

    public f(String str, int i, boolean z) {
        this.dT = str;
        this.dW = i;
        this.dV = z;
    }

    public JSONObject fQ() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("iccid", this.dT);
            jSONObject.put("card_slot", this.dW);
            jSONObject.put("is_use", this.dV ? 0 : 1);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String fJ() {
        return this.dT;
    }

    public String toString() {
        return "SimInfo{iccId='" + this.dT + "', simSlotIndex='" + this.dW + "', isSimDefaultData='" + this.dV + "', cardType='" + this.dS + "', isOpen='" + this.dU + "', subscriptionId='" + this.dX + "'}";
    }

    public boolean fK() {
        return this.dU;
    }

    public void fO(boolean z) {
        this.dU = z;
    }

    public int fL() {
        return this.dW;
    }

    public int fN() {
        return this.dX;
    }

    public void fP(int i) {
        this.dX = i;
    }

    public boolean fM() {
        return this.dV;
    }
}
