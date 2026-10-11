package com.a.b.a;

import android.content.Intent;
import android.net.Uri;

/* loaded from: classes.dex */
final class bj extends l {
    final /* synthetic */ bi a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bj(bi biVar) {
        this.a = biVar;
    }

    @Override // com.a.b.a.l
    public final void a() {
        String str;
        String str2;
        str = this.a.h;
        Uri parse = Uri.parse(str);
        Intent intent = new Intent();
        intent.setAction(aq.a("ivlzwql6qv|mv|6ik|qwv6^QM_", 8));
        intent.setData(parse);
        str2 = this.a.h;
        if (str2.startsWith(aq.a("o{{w", 7))) {
            intent.setClassName(aq.a("eqo0cpftqkf0dtqyugt", 2), aq.a("dpn/boespje/cspxtfs/CspxtfsBdujwjuz", 1));
        }
        this.a.b.startActivity(intent);
    }
}
