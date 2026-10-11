package com.a.b.a;

import android.content.ClipboardManager;
import android.text.TextUtils;

/* loaded from: classes.dex */
public class bf extends bt {
    private String h;
    private String i;
    private String j;
    private ClipboardManager k;
    private ClipboardManager.OnPrimaryClipChangedListener l = new bh(this);

    @Override // com.a.b.a.bt
    public final void a() {
        this.h = (String) e(aq.a("~o\u0082~", 10));
        this.i = (String) e(aq.a("zwq", 5));
    }

    @Override // com.a.b.a.bt
    public final String b() {
        String d = d(this.h);
        if (!TextUtils.isEmpty(this.i)) {
            d = d + "     " + n.a().a(this.i, null).a();
        }
        this.j = d;
        m.a().a(new bg(this));
        return aq.a(";", 10);
    }
}
