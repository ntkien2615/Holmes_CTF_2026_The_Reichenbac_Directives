package com.tw.core.model;

import com.tw.core.track.model.ThirdPartnerGpsModel;
import com.tw.core.utils.C0032f;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b implements Runnable {
    private List ic;
    final /* synthetic */ a id;

    public b(a aVar, List list) {
        this.id = aVar;
        this.ic = list;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator it = this.ic.iterator();
            while (it.hasNext()) {
                JSONObject al = ((ThirdPartnerGpsModel) it.next()).al();
                if (al != null) {
                    al.put("cid", com.tw.core.e.b.le());
                    jSONArray.put(al);
                }
            }
            if (jSONArray.length() > 0) {
                str = this.id.ib;
                C0032f.hh(jSONArray, str, new d(this));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
