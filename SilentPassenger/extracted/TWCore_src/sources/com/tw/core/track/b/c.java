package com.tw.core.track.b;

import android.text.TextUtils;
import com.tw.core.track.IllegalTrackArgumentException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class c {
    public static boolean aL(com.tw.core.track.model.b bVar) {
        if (bVar == null) {
            throw new IllegalTrackArgumentException("latLng can not be null");
        }
        if (Math.abs(bVar.S()) > 90.0d || Math.abs(bVar.T()) > 180.0d) {
            throw new IllegalTrackArgumentException("latLng is invalid value");
        }
        return true;
    }

    public static void aJ(com.tw.core.track.model.c cVar, StringBuilder sb) {
        com.tw.core.track.model.b ai = cVar.ai();
        if (aL(ai)) {
            sb.append("&latitude=").append(ai.S());
            sb.append("&longitude=").append(ai.T());
        }
        sb.append("&loc_time=").append(cVar.ah());
        if (cVar.ae() == null) {
            throw new IllegalTrackArgumentException("coordType can not be null.");
        }
        sb.append("&coord_type_input=").append(cVar.ae().name());
        sb.append("&speed=").append(cVar.ak());
        sb.append("&direction=").append(cVar.af());
        sb.append("&height=").append(cVar.ag());
        sb.append("&radius=").append(cVar.aj());
        sb.append("&sysT=").append(cVar.W());
        if (!b.aH(cVar.V())) {
            sb.append("&_obejct_key=").append(a.aF(cVar.V()));
        }
        if (cVar.U() != null) {
            aM(cVar.U(), sb);
        }
    }

    public static void aK(Map map, StringBuilder sb) {
        if (map == null) {
            throw new IllegalTrackArgumentException("track points can not be null.");
        }
        JSONArray jSONArray = new JSONArray();
        try {
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                List<com.tw.core.track.model.c> list = (List) ((Map.Entry) it.next()).getValue();
                if (list != null && list.size() > 0) {
                    for (com.tw.core.track.model.c cVar : list) {
                        if (cVar != null) {
                            if (cVar.ae() == null) {
                                throw new IllegalTrackArgumentException("coordType can not be null.");
                            }
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("entity_name", cVar.V());
                            jSONObject.put("loc_time", cVar.ah());
                            com.tw.core.track.model.b ai = cVar.ai();
                            if (aL(ai)) {
                                jSONObject.put("latitude", ai.S());
                                jSONObject.put("longitude", ai.T());
                            }
                            jSONObject.put("coord_type_input", cVar.ae().name());
                            jSONObject.put("speed", cVar.ak());
                            jSONObject.put("direction", cVar.af());
                            jSONObject.put("height", cVar.ag());
                            jSONObject.put("radius", cVar.aj());
                            Map U = cVar.U();
                            if (U != null) {
                                aN(U, jSONObject);
                            }
                            jSONArray.put(jSONObject);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.append("&point_list=").append(jSONArray.toString());
    }

    public static void aM(Map map, StringBuilder sb) {
        for (Map.Entry entry : map.entrySet()) {
            sb.append("&").append((String) entry.getKey()).append("=").append(a.aF((String) entry.getValue()));
        }
    }

    private static void aN(Map map, JSONObject jSONObject) {
        for (Map.Entry entry : map.entrySet()) {
            if (!TextUtils.isEmpty((CharSequence) entry.getKey()) && !TextUtils.isEmpty((CharSequence) entry.getValue())) {
                try {
                    jSONObject.put((String) entry.getKey(), a.aF((String) entry.getValue()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
