package com.tw.core.b;

import android.content.Context;
import android.text.TextUtils;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0051y;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ae implements Runnable {
    final /* synthetic */ C dp;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ae(C c) {
        this.dp = c;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        Context context2;
        Context context3;
        context = this.dp.cx;
        int iO = C0051y.iO(context);
        if (iO < 1) {
            C0034h.hs("SimCardManager", "卡数量少于1 卡数量%s", Integer.valueOf(iO));
            return;
        }
        List iK = C0051y.iK();
        if (iK != null && iK.size() > 0) {
            JSONArray jSONArray = new JSONArray();
            Iterator it = iK.iterator();
            while (it.hasNext()) {
                jSONArray.put(((com.tw.core.c.f) it.next()).fQ());
            }
            context2 = this.dp.cx;
            String gt = C0029c.gt(context2, "key_sim_card_upload_pkg", null);
            JSONArray jSONArray2 = new JSONArray();
            if (!TextUtils.isEmpty(gt)) {
                try {
                    JSONArray jSONArray3 = new JSONArray(gt);
                    if (jSONArray3.length() > 0) {
                        for (int i = 0; i < jSONArray3.length(); i++) {
                            String optString = jSONArray3.optString(i);
                            if (com.tw.core.e.d.lu(optString)) {
                                jSONArray2.put(optString);
                            }
                        }
                        C0034h.hr("SimCardManager", "安装的包名 %s", jSONArray2.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("cid", com.tw.core.e.b.le());
                jSONObject.put("plat", com.tw.core.e.a.kZ());
                jSONObject.put("card_list", jSONArray);
                jSONObject.put("competing_list", jSONArray2);
                String ca = C0003c.cb().ca();
                if (!TextUtils.isEmpty(ca)) {
                    jSONObject.put("uid", ca);
                }
                context3 = this.dp.cx;
                String gt2 = C0029c.gt(context3, "all_card_info", null);
                C0034h.hr("SimCardManager", "currentCard %s lastCard %s", jSONObject.toString(), gt2);
                if (TextUtils.equals(gt2, jSONObject.toString())) {
                    C0034h.hs("SimCardManager", "卡信息没变", new Object[0]);
                } else {
                    this.dp.et(jSONObject);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }
}
