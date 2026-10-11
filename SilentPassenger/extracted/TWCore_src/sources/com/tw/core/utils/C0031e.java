package com.tw.core.utils;

import android.content.Context;
import android.text.TextUtils;

/* renamed from: com.tw.core.utils.e, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0031e {
    private Context er;
    private String es;

    public C0031e(Context context) {
        this.er = context;
        this.es = H.jy(context);
    }

    public void gX(String str) {
        C0034h.hr("ResetUtil", "init language %s new language %s", this.es, str);
        if (TextUtils.isEmpty(com.tw.core.e.e.lC()) && (!TextUtils.equals(this.es, str))) {
            if ("zh_CN".equals(this.es) || !(!"zh_CN".equals(str))) {
                C0029c.gv(this.er);
                C0029c.gu(this.er, "key_device_language", str);
                new Thread(new T(this)).start();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0067 A[Catch: Exception -> 0x0103, TryCatch #0 {Exception -> 0x0103, blocks: (B:3:0x0003, B:5:0x0013, B:9:0x0037, B:10:0x0050, B:12:0x0067, B:14:0x006b, B:16:0x0099, B:18:0x009f, B:23:0x00d2, B:27:0x00ae, B:29:0x00c1, B:30:0x00e9, B:32:0x00ef, B:34:0x00f8), top: B:2:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00c1 A[Catch: Exception -> 0x0103, TryCatch #0 {Exception -> 0x0103, blocks: (B:3:0x0003, B:5:0x0013, B:9:0x0037, B:10:0x0050, B:12:0x0067, B:14:0x006b, B:16:0x0099, B:18:0x009f, B:23:0x00d2, B:27:0x00ae, B:29:0x00c1, B:30:0x00e9, B:32:0x00ef, B:34:0x00f8), top: B:2:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0037 A[Catch: Exception -> 0x0103, TryCatch #0 {Exception -> 0x0103, blocks: (B:3:0x0003, B:5:0x0013, B:9:0x0037, B:10:0x0050, B:12:0x0067, B:14:0x006b, B:16:0x0099, B:18:0x009f, B:23:0x00d2, B:27:0x00ae, B:29:0x00c1, B:30:0x00e9, B:32:0x00ef, B:34:0x00f8), top: B:2:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void gW() {
        /*
            Method dump skipped, instructions count: 271
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tw.core.utils.C0031e.gW():void");
    }
}
