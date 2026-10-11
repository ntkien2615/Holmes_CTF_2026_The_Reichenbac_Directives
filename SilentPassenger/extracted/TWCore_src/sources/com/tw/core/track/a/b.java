package com.tw.core.track.a;

import com.tw.core.track.IllegalTrackArgumentException;
import com.tw.core.track.d;
import com.tw.core.track.e;
import com.tw.core.track.f;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0038l;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b {
    public static void k(e eVar) {
        StringBuilder sb = new StringBuilder();
        n(eVar, sb);
        p(eVar.aC(), "track/addpoint", a.j("track/addpoint", sb.toString(), "POST"));
    }

    public static void l(f fVar) {
        StringBuilder sb = new StringBuilder();
        n(fVar, sb);
        p(fVar.aC(), "track/addpoints", a.j("track/addpoints", sb.toString(), "POST"));
    }

    public static void n(com.tw.core.track.model.f fVar, StringBuilder sb) {
        if (fVar == null) {
            throw new IllegalTrackArgumentException("request can not be null.");
        }
        m(fVar, sb);
        C0034h.hr("TrackHandler", "packRequest %s", sb);
        if (fVar instanceof e) {
            e eVar = (e) fVar;
            sb.append("&entity_name=").append(com.tw.core.track.b.a.aF(eVar.bl()));
            com.tw.core.track.model.c bm = eVar.bm();
            if (bm == null) {
                throw new IllegalTrackArgumentException("trackPoint can not be null.");
            }
            com.tw.core.track.b.c.aJ(bm, sb);
            return;
        }
        com.tw.core.track.b.c.aK(((f) fVar).bn(), sb);
    }

    private static void m(com.tw.core.track.model.f fVar, StringBuilder sb) {
        if (com.tw.core.track.b.b.aH(fVar.aD())) {
            throw new IllegalTrackArgumentException("ak can not be null or empty string.");
        }
        if (fVar.aE() <= 0) {
            throw new IllegalTrackArgumentException("serviceId is lower than 0.");
        }
        sb.append("ak=").append(fVar.aD());
        sb.append("&service_id=").append(fVar.aE());
    }

    public static void p(long j, String str, String str2) {
        Map map = d.bf().ae;
        if (map == null) {
            return;
        }
        com.tw.core.track.a aVar = new com.tw.core.track.a(j, 0, "success");
        o(aVar, str2);
        com.tw.core.track.model.a aVar2 = (com.tw.core.track.model.a) map.get(Long.valueOf(j));
        if (aVar2 == null) {
            return;
        }
        if (aVar.Z() == 0) {
            aVar2.r(j);
        } else {
            aVar2.q(aVar);
        }
    }

    private static void o(com.tw.core.track.model.d dVar, String str) {
        if (C0038l.ez) {
            C0038l.hE("parseCommonResponse", "result=" + str);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("status")) {
                dVar.aa(jSONObject.getInt("status"));
            }
            if (jSONObject.has("message")) {
                dVar.ab(jSONObject.getString("message"));
            }
            if (jSONObject.has("fail_info")) {
                dVar.ac(jSONObject.getString("fail_info"));
            }
        } catch (Exception e) {
            dVar.aa(-2);
            dVar.ab("解析失败");
        }
    }
}
