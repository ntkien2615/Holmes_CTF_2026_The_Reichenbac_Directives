package com.a.b.a;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class ak extends Thread {
    private static List<Integer> d = new ArrayList();
    public cg a;
    private final Context b;
    private a c;

    public ak(Context context, a aVar) {
        this.b = context;
        this.c = aVar;
    }

    public static String a(bt btVar, int i) {
        try {
            String b = btVar.b();
            if (b.equals(aq.a(":", 9)) || i <= 0) {
                return b;
            }
            Thread.sleep(2000L);
            return a(btVar, i - 1);
        } catch (Exception e) {
            return aq.a("gttayqtma", 2) + e.getLocalizedMessage();
        }
    }

    private static void a(String str, String str2) {
        aq.a("ylz|s{", 7);
        new StringBuilder().append(str).append(aq.a("5555", 8)).append(str2);
    }

    private String[] b() {
        String[] strArr = new String[2];
        aq.a("tufq", 1);
        aq.a("凈変丌证", 2);
        ao aoVar = new ao(this.b, this.c);
        try {
            aq.a("wxit", 4);
            aq.a("觩枖脠朲", 6);
            String a = aoVar.a();
            if (a != null && !a.equals(aq.a("5", 4))) {
                strArr[0] = aq.a("n{{x{", 9);
                strArr[1] = a;
                return strArr;
            }
            this.a = aoVar.a;
            if (this.a == null || this.a.a == null || this.a.a.size() == 0) {
                strArr[0] = aq.a("Idlohg", 3);
                strArr[1] = aq.a("莹变脜朮夳货", 2);
                return strArr;
            }
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.a.a.size()) {
                    strArr[0] = aq.a("Vxffhvv", 3);
                    strArr[1] = aq.a("戙动", 9);
                    return strArr;
                }
                bt btVar = this.a.a.get(i2);
                JSONObject jSONObject = (JSONObject) btVar.e(aq.a("wlphu", 3));
                int i3 = btVar.g;
                if (jSONObject != null) {
                    long optLong = jSONObject.optLong(aq.a("x{tzosk", 6));
                    int optInt = jSONObject.optInt(aq.a("ynrjx", 5));
                    int optInt2 = jSONObject.optInt(aq.a("ijqf~", 5));
                    long optLong2 = jSONObject.optLong(aq.a("gpfVkog", 2));
                    if (optLong2 == 0) {
                        optLong2 = optLong;
                    }
                    if (optLong2 - System.currentTimeMillis() >= 0 && !d.contains(Integer.valueOf(i3))) {
                        long currentTimeMillis = optLong - System.currentTimeMillis();
                        if (currentTimeMillis < 0) {
                            currentTimeMillis = 1000;
                        }
                        d.add(Integer.valueOf(i3));
                        aq.a("GnqqnslYmwjfi", 5);
                        new StringBuilder().append(aq.a("宣旿伄努C", 9)).append(currentTimeMillis);
                        for (int i4 = 0; i4 < optInt; i4++) {
                            bd.a(new al(this, i3, btVar), (i4 * optInt2) + currentTimeMillis);
                        }
                    }
                } else {
                    String a2 = a(btVar, 1);
                    aq.a("Lsvvsxq^r|okn", 10);
                    new StringBuilder().append(aq.a("扯衔脢朴", 8)).append(i2).append(aq.a("丫芃為扨衍绔枝；", 1)).append(a2);
                    JSONArray jSONArray = new JSONArray();
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put(aq.a("wdvnLg", 3), btVar.c);
                        jSONObject2.put(aq.a("xkvuxzZ\u007fvk", 6), 1);
                        if (TextUtils.isEmpty(a2) || !a2.equals(aq.a("6", 5))) {
                            jSONObject2.put(aq.a("vcumUvcvwu", 2), 2);
                            jSONObject2.put(aq.a("tzn", 7), String.valueOf(a2));
                        } else {
                            jSONObject2.put(aq.a("ubtlTubuvt", 1), 1);
                        }
                        jSONArray.put(jSONObject2);
                        aq.a("uhvxow", 3);
                        int i5 = 120;
                        while (!btVar.d()) {
                            int i6 = i5 - 1;
                            if (i5 > 0) {
                                Thread.sleep(1000L);
                                i5 = i6;
                            }
                        }
                    } catch (Exception e) {
                    }
                }
                i = i2 + 1;
            }
        } catch (Exception e2) {
            strArr[0] = aq.a("jwwtw", 5);
            strArr[1] = aq.a("讧贿夷贫２觩枖脠朲夷贫e", 6) + e2.getMessage();
            return strArr;
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        try {
            String[] b = b();
            if (b[0].equals(aq.a("ivvsv", 4))) {
                a(aq.a("Gttqt", 2), b[1]);
            } else if (b[0].equals(aq.a("Niqtml", 8))) {
                a(aq.a("Lgorkj", 6), b[1]);
            } else if (b[0].equals(aq.a("Xzhhjxx", 5))) {
                a(aq.a("Xzhhjxx", 5), b[1]);
            } else {
                a(aq.a("Xzhhjxx", 5), aq.a("札矨锜诲", 3));
            }
        } catch (Exception e) {
            a(aq.a("Ivvsv", 4), aq.a("讥贽夵贩c", 4) + e.getMessage());
        }
    }
}
