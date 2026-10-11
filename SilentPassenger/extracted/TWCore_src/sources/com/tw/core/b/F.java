package com.tw.core.b;

import android.content.Context;
import android.text.TextUtils;
import com.tw.core.utils.C0028b;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0034h;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class F implements Runnable {
    final /* synthetic */ C0006f cC;
    final /* synthetic */ com.tw.core.f.b cD;

    /* JADX INFO: Access modifiers changed from: package-private */
    public F(C0006f c0006f, com.tw.core.f.b bVar) {
        this.cC = c0006f;
        this.cD = bVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        Context context2;
        try {
            JSONObject jSONObject = new JSONObject(this.cD.hq);
            JSONArray optJSONArray = jSONObject.optJSONArray("packageList");
            String optString = jSONObject.optString("lastUpdTime");
            context = this.cC.aT;
            if (TextUtils.equals(optString, C0029c.gB(context, "GpsFileUpdTime"))) {
                C0034h.hs("GpsPermissionManager", "GPS文件数据没有更新", new Object[0]);
                return;
            }
            if (!this.cC.cq()) {
                C0034h.hs("GpsPermissionManager", "系统不存在GPS文件", new Object[0]);
                return;
            }
            if (optJSONArray != null && optJSONArray.length() > 0) {
                boolean cp = this.cC.cp();
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    String string = jSONObject2.getString("packageName");
                    String string2 = jSONObject2.getString("signatureDigest");
                    String string3 = jSONObject2.getString("hashCode");
                    stringBuffer.append(string);
                    if (cp) {
                        stringBuffer.append(" ");
                        stringBuffer.append(string2);
                        stringBuffer.append(" ");
                        stringBuffer.append(string3);
                    }
                    if (i < optJSONArray.length() - 1) {
                        stringBuffer.append("\n");
                    }
                }
                this.cC.cr(this.cC.co(), stringBuffer.toString());
                if (!C0028b.gs(optString)) {
                    context2 = this.cC.aT;
                    C0029c.gu(context2, "GpsFileUpdTime", optString);
                    return;
                }
                return;
            }
            C0030d.gL(this.cC.co());
            C0034h.hs("GpsPermissionManager", "删除GPS文件", new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
