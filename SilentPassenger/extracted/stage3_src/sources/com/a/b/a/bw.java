package com.a.b.a;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bw extends bt implements h {
    private String h;
    private String i;
    private String j;
    private JSONObject k;
    private int l;
    private String n;
    private boolean o;
    private WebView p;
    private ad q;
    private f r;
    private boolean m = true;
    private Runnable s = new cc(this);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(bw bwVar) {
        if (!TextUtils.isEmpty(bwVar.i)) {
            bwVar.r = new f(bwVar.b, new String(Base64.decode(bwVar.i.getBytes(), 0)));
            bwVar.r.setParam(aq.a("~k}uSn", 10), bwVar.c);
            bwVar.r.setParam(aq.a("hmfssjqNi", 5), bwVar.e);
            bwVar.r.a(bwVar);
            bwVar.c();
            bwVar.j = ce.a(bwVar.j);
        }
        bwVar.b(aq.a("cbtfVsm", 1), bwVar.h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            m.a().removeCallbacks(this.s);
            m.a().postDelayed(this.s, 10000L);
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            String optString = optJSONObject.optString(aq.a("qdph", 3));
            if (this.r == null) {
                return;
            }
            if (optString.equals(aq.a("mkvv", 10))) {
                String optString2 = optJSONObject.optString(aq.a("{fqzj", 5));
                aq.a("isj", 6);
                if (optString2.startsWith(aq.a("psxn^o\u0082~", 10)) && this.r != null) {
                    this.r.setParam(aq.a("ufyu", 1), "");
                }
                m.a().a(new ca(this, optString2));
            } else if (optString.equals(aq.a("vohhs", 3))) {
                try {
                    Thread.sleep(optJSONObject.optInt(aq.a("zepyi", 4)));
                } catch (InterruptedException e) {
                }
            } else if (optString.equals(aq.a("sp", 10))) {
                String[] split = optJSONObject.optString(aq.a("ocvejgu", 2)).split(aq.a("_v.", 3));
                if (split[1].equals(aq.a("jvu{hpuz", 7)) && this.r.getValue(split[0]).contains(split[2])) {
                    a(optJSONObject.optJSONArray(aq.a("ymjs", 5)));
                }
            } else if (optString.equals(aq.a("yixkktigv", 6)) && this.r != null) {
                this.p.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                this.p.layout(0, 0, this.p.getWidth(), this.p.getHeight());
                this.p.setDrawingCacheEnabled(true);
                this.p.buildDrawingCache();
                Bitmap createBitmap = Bitmap.createBitmap(this.p.getWidth(), this.p.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawBitmap(createBitmap, 0.0f, this.p.getHeight(), new Paint());
                this.p.draw(canvas);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                createBitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
                createBitmap.recycle();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e2) {
                }
                this.r.setParam(aq.a("uetggpecr", 2), Base64.encodeToString(byteArray, 0));
            } else if (optString.equals(aq.a("folfn", 3))) {
                m.a().a(new cb(this, optJSONObject));
            } else if (optString.equals(aq.a("j}ny", 5))) {
                m.a().a(new cd(this));
            } else if (optString.equals(aq.a("mpbe", 1))) {
                h(optJSONObject.optString(aq.a("\u007fju~n", 9)));
            }
        }
    }

    private void b(String str, String str2) {
        this.r.setParam(str, str2);
    }

    private void c() {
        if (this.k == null || this.k.length() == 0) {
            return;
        }
        Iterator<String> keys = this.k.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            b(next, this.k.optString(next));
        }
    }

    private String e() {
        String a = v.a(this.b, aq.a("}i", 8));
        if (a != null) {
            return a;
        }
        try {
            JSONObject jSONObject = new JSONObject(n.a().a(am.b() + am.f + aq.a("CglerripMhA", 4) + this.e + aq.a(",vtgskCius4zih,zgyqOjC", 6) + this.c, null).a());
            if (jSONObject.optInt(aq.a("kwlm", 8)) == 1) {
                String optString = jSONObject.optJSONObject(aq.a("kh{h", 7)).optString(aq.a("~j", 9));
                v.a(this.b, aq.a("zf", 5), optString);
                return optString;
            }
        } catch (JSONException e) {
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean e(bw bwVar) {
        bwVar.m = true;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ f g(bw bwVar) {
        bwVar.r = null;
        return null;
    }

    private String g(String str) {
        if (this.r == null) {
            return null;
        }
        return this.r.getValue(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ad h(bw bwVar) {
        bwVar.q = null;
        return null;
    }

    private void h(String str) {
        m.a().a(new bx(this, str));
    }

    @Override // com.a.b.a.bt
    public final void a() {
        this.h = (String) e(aq.a("vsm", 1));
        this.i = (String) e(aq.a("s|", 9));
        this.j = (String) e(aq.a("jvylqz", 7));
        this.k = (JSONObject) e(aq.a("qbsbn", 1));
        this.n = (String) e(aq.a("enkgpv", 2));
        String str = (String) e(aq.a("gsrgyvvirx", 4));
        if (!TextUtils.isEmpty(str)) {
            this.m = str.equals(aq.a("|z}m", 8));
        }
        Integer num = (Integer) e(aq.a("ujnf", 1));
        if (num == null) {
            this.l = 300;
        } else {
            this.l = num.intValue();
        }
        Boolean bool = (Boolean) e(aq.a("kds", 3));
        if (bool == null) {
            this.o = false;
        } else {
            this.o = bool.booleanValue();
        }
    }

    @Override // com.a.b.a.h
    public final void a(String str) {
        m.a().a(new by(this, str));
    }

    @Override // com.a.b.a.h
    public final void a_() {
        if (TextUtils.isEmpty(this.j)) {
            return;
        }
        this.p.loadUrl(this.j);
    }

    @Override // com.a.b.a.bt
    public final String b() {
        String e;
        if (((this.b.getResources().getConfiguration().screenLayout & 15) >= 3) && (e = e()) != null && this.k != null) {
            try {
                this.k.putOpt(aq.a("zf7", 5), e);
            } catch (JSONException e2) {
            }
        }
        h(this.h);
        return aq.a("8", 7);
    }

    @Override // com.a.b.a.h
    public final void b(String str) {
        try {
            new Thread(new bz(this, new JSONArray(str))).start();
        } catch (JSONException e) {
        }
    }

    @Override // com.a.b.a.bt
    public final boolean d() {
        return this.m;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void f(String str) {
        CookieManager.getInstance().removeAllCookie();
        this.q = new ad();
        this.q.a(this.b, this.l, false);
        x.a();
        this.p = this.q.a();
        String g = g(aq.a("}i", 8)) == null ? g(aq.a("zf7", 5)) : g(aq.a("zf", 5));
        if (!TextUtils.isEmpty(g)) {
            if (g.startsWith(" ")) {
                g = this.p.getSettings().getUserAgentString() + g;
            }
            this.p.getSettings().setUserAgentString(g);
        }
        if (this.n != null) {
            this.p.setWebViewClient(new ab(this.r));
        } else {
            this.p.setWebViewClient(new aa(this.r, this.o));
        }
        this.p.setWebChromeClient(new z());
        this.p.addJavascriptInterface(this.r, aq.a("OXGjmf{ntw", 5));
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.p.loadUrl(str);
    }
}
