package com.tw.core.b;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0042p;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.tw.core.b.d, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0004d extends com.tw.core.g.a implements com.tw.core.f.c {
    private static final String aR = com.tw.core.app.a.jo + "/cloud/collect/bluetooth/phone";
    private boolean aM;
    private JSONArray aN = null;
    private boolean aO;
    private C0005e aP;
    private Context aQ;

    public C0004d(Context context) {
        this.aQ = context;
        ch();
    }

    private void ch() {
        if (this.aP == null) {
            this.aP = new C0005e(this);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.aispeech.action.CONTACTS_SYNC_SUCCESS");
        this.aQ.registerReceiver(this.aP, intentFilter);
    }

    @Override // com.tw.core.b.B
    public String bK() {
        return null;
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "0307";
    }

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void onCreate() {
        super.onCreate();
        com.tw.core.f.f.get().mq(this);
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        C0034h.hr("PhoneContactUpload", "emqMessageChanged %s", bVar);
        try {
            JSONObject jSONObject = new JSONObject(bVar.hq);
            this.aM = jSONObject.optBoolean("collect_on_off");
            this.aO = jSONObject.optBoolean("upload_on_off");
            if (!this.aM) {
                C0034h.hs("PhoneContactUpload", "注销广播", new Object[0]);
                if (this.aP != null) {
                    this.aQ.unregisterReceiver(this.aP);
                    this.aP = null;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.tw.core.g.a, com.tw.core.f.a
    public void bD(boolean z, String str) {
        C0034h.hr("PhoneContactUpload", "connected", new Object[0]);
    }

    @Override // com.tw.core.g.c
    public String bV() {
        return aR;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cg(Intent intent) {
        C0042p.id().execute(new D(this, intent));
    }
}
