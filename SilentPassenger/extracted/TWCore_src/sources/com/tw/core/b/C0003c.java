package com.tw.core.b;

import android.content.Context;
import android.text.TextUtils;
import cn.cardoor.user.bean.Token;
import com.tw.core.app.DoFunApplication;
import com.tw.core.utils.C0027a;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0034h;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.tw.core.b.c, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0003c {
    private static volatile C0003c aK;
    private final Object aJ = new Object();
    private final List aL = new ArrayList();
    private Context aG = DoFunApplication.nF();
    private String aI = bZ();
    private volatile boolean aH = C0029c.gx(this.aG, "KeyOverseasTrackSwitch", false);

    public static C0003c cb() {
        if (aK == null) {
            synchronized (C0003c.class) {
                if (aK == null) {
                    aK = new C0003c();
                }
            }
        }
        return aK;
    }

    public void bW(com.tw.core.h.a aVar) {
        if (aVar != null) {
            synchronized (this.aL) {
                C0034h.hr("UserManager", "addUserListener %s", aVar);
                this.aL.add(aVar);
            }
        }
    }

    public void cc(com.tw.core.h.a aVar) {
        if (aVar != null) {
            synchronized (this.aL) {
                C0034h.hr("UserManager", "removeUserListener %s", aVar);
                this.aL.remove(aVar);
            }
        }
    }

    public void ce(boolean z) {
        C0029c.gC(this.aG, "KeyOverseasTrackSwitch", z);
        boolean z2 = !com.tw.core.e.e.lB() ? z : true;
        if (bY() != z2) {
            this.aH = z;
            C0034h.hr("UserManager", "new track switch %s", Boolean.valueOf(z2));
            Iterator it = this.aL.iterator();
            while (it.hasNext()) {
                ((com.tw.core.h.a) it.next()).dt(z2);
            }
        }
    }

    public boolean bY() {
        if (com.tw.core.e.e.lB()) {
            return true;
        }
        return this.aH;
    }

    private void cf(String str) {
        C0034h.hr("UserManager", "setUserId %s", str);
        this.aI = str;
        C0029c.gu(this.aG, "key_app_bind_user_id", str);
        synchronized (this.aL) {
            if (!TextUtils.isEmpty(str)) {
                Iterator it = this.aL.iterator();
                while (it.hasNext()) {
                    ((com.tw.core.h.a) it.next()).dr(str);
                }
            } else {
                Iterator it2 = this.aL.iterator();
                while (it2.hasNext()) {
                    ((com.tw.core.h.a) it2.next()).ds();
                }
            }
        }
    }

    private String bZ() {
        String str = null;
        Token bX = bX();
        if (bX != null && (!TextUtils.isEmpty(bX.getToken()))) {
            str = new C0027a(bX.getToken()).gp();
        }
        C0034h.hr("UserManager", "getUidByCache %s", str);
        return str;
    }

    public void cd(Token token) {
        synchronized (this.aJ) {
            C0034h.hr("UserManager", "setToken %s", token);
            if (token == null || TextUtils.isEmpty(token.getToken())) {
                C0029c.gu(this.aG, "KEY_TOKEN_DATA", null);
                cf(null);
            } else {
                C0029c.gu(this.aG, "KEY_TOKEN_DATA", token.tF().toString());
                cf(new C0027a(token.getToken()).gp());
            }
        }
    }

    public Token bX() {
        try {
            String gt = C0029c.gt(this.aG, "KEY_TOKEN_DATA", null);
            C0034h.hr("UserManager", "cache token %s", gt);
            if (!TextUtils.isEmpty(gt)) {
                return Token.tG(new JSONObject(gt));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String ca() {
        return this.aI;
    }
}
