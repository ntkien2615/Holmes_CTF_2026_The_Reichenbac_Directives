package com.tw.core.b;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import cn.cardoor.user.bean.Token;
import com.tw.core.utils.C0034h;

/* renamed from: com.tw.core.b.v, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0022v extends com.tw.core.g.a implements com.tw.core.f.c, com.tw.core.messenger.server.b {
    private Context bW;

    public C0022v(Context context) {
        this.bW = context;
    }

    @Override // com.tw.core.g.a, com.tw.core.f.a
    public void bD(boolean z, String str) {
    }

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void onCreate() {
        super.onCreate();
        com.tw.core.f.f.get().mq(this);
        com.tw.core.messenger.a.get().lX(this);
    }

    @Override // com.tw.core.b.B
    public String bK() {
        return null;
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "1000";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        C0034h.hr("UserBindManager", "emqMessageChanged %s", bVar);
    }

    @Override // com.tw.core.g.c
    public String bV() {
        return "";
    }

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void bH() {
        super.bH();
        com.tw.core.f.f.get().mr(this);
        com.tw.core.messenger.a.get().lY(this);
    }

    @Override // com.tw.core.messenger.server.b
    public void cY(Message message) {
        C0034h.hr("UserBindManager", "receiveMessage %s %s current mainland %s", Integer.valueOf(message.what), message.obj, Boolean.valueOf(com.tw.core.e.e.lB()));
        if ((message.what == 1002 && com.tw.core.e.e.lB()) || (message.what == 1003 && (!com.tw.core.e.e.lB()))) {
            if (message.obj instanceof Bundle) {
                Bundle bundle = (Bundle) message.obj;
                C0034h.hr("UserBindManager", "classLoader before %s", bundle.getClassLoader());
                bundle.setClassLoader(Token.class.getClassLoader());
                C0034h.hr("UserBindManager", "classLoader after %s", bundle.getClassLoader());
                Token token = (Token) bundle.getParcelable("data");
                C0034h.hr("UserBindManager", "token %s", token);
                C0003c.cb().cd(token);
                return;
            }
            C0034h.hr("UserBindManager", "login out", new Object[0]);
            C0003c.cb().cd(null);
        }
    }

    @Override // com.tw.core.messenger.server.b
    public int[] getMessageId() {
        return new int[]{1002, 1003};
    }
}
