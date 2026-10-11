package com.tw.core.b;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import com.tw.core.utils.C0034h;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.tw.core.b.o, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0015o extends com.tw.core.g.a implements com.tw.core.f.c, com.tw.core.messenger.server.b, com.tw.core.messenger.a.e {
    private Context bv;

    public C0015o(Context context) {
        this.bv = context;
    }

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void onCreate() {
        super.onCreate();
        com.tw.core.f.f.get().mq(this);
        com.tw.core.messenger.a.get().lX(this);
    }

    @Override // com.tw.core.g.a, com.tw.core.f.a
    public void bD(boolean z, String str) {
    }

    @Override // com.tw.core.b.B
    public String bK() {
        return null;
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "9999";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        C0034h.hr("AppMessageManager", "emqMessageChanged %s", bVar);
        try {
            JSONObject jSONObject = new JSONObject(bVar.hq);
            com.tw.core.messenger.a.a.lT().lS(this.bv, jSONObject.optString("dest"), new Q(this, jSONObject.optString("data")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.tw.core.g.c
    public String bV() {
        return null;
    }

    @Override // com.tw.core.messenger.server.b
    public void cY(Message message) {
        C0034h.hr("AppMessageManager", "onMessage %s", message);
        if (message.obj instanceof Bundle) {
            Bundle bundle = (Bundle) message.obj;
            String string = bundle.getString("data");
            String string2 = bundle.getString("topic");
            if (string != null && (!TextUtils.isEmpty(string2))) {
                com.tw.core.f.d.mb().mc(string.replaceAll("\n", "").replaceAll("\r", ""), string2, com.tw.core.f.d.mb().hu, new R(this, Message.obtain(message)));
            }
        }
    }

    @Override // com.tw.core.messenger.server.b
    public int[] getMessageId() {
        return new int[]{9999};
    }

    @Override // com.tw.core.messenger.a.e
    public void cX(Message message) {
        Bundle data = message.getData();
        if (data != null) {
            int i = data.getInt("code", -1);
            int i2 = data.getInt("status", -1);
            String string = data.getString("taskId");
            String string2 = data.getString("msgId");
            C0034h.hr("AppMessageManager", "onApply code %s msgId %s status %s", Integer.valueOf(i), string2, Integer.valueOf(i2));
            if ("0700".equals(string2) && i == 0 && i2 >= 0) {
                com.tw.core.d.g.kO(string, i2 == 1);
            }
        }
    }
}
