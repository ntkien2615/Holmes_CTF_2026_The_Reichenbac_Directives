package com.tw.core.g;

import com.tw.core.utils.C0038l;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class i implements com.tw.core.f.e {
    final /* synthetic */ a iX;
    final /* synthetic */ String iY;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(a aVar, String str) {
        this.iX = aVar;
        this.iY = str;
    }

    @Override // com.tw.core.f.e
    /* renamed from: nE, reason: merged with bridge method [inline-methods] */
    public void ex(Boolean bool) {
        C0038l.hE("BaseDataReport", "report mReportContent result = " + bool);
        if (!bool.booleanValue()) {
            this.iX.eo(Arrays.asList(this.iY));
        }
    }
}
