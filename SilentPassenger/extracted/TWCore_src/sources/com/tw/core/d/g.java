package com.tw.core.d;

import com.tw.core.utils.C0032f;
import java.util.Collections;

/* loaded from: classes.dex */
public class g {
    public static void kP(String str) {
        C0032f.hg(Collections.emptyMap(), com.tw.core.a.a.l + "?logId=" + str, new m());
    }

    public static void kQ(String str) {
        C0032f.hg(Collections.emptyMap(), com.tw.core.a.a.j + "?logId=" + str, new n());
    }

    public static void kO(String str, boolean z) {
        C0032f.hg(Collections.emptyMap(), com.tw.core.a.a.k + "?switchId=" + str + "&switchStatus=" + z, new o());
    }
}
