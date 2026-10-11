package com.tw.core.b;

import android.content.Context;
import android.text.TextUtils;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0051y;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class I implements Runnable {
    final /* synthetic */ C0010j cG;
    final /* synthetic */ String cH;

    /* JADX INFO: Access modifiers changed from: package-private */
    public I(C0010j c0010j, String str) {
        this.cG = c0010j;
        this.cH = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        Context context2;
        Context context3;
        com.tw.core.c.f fVar;
        Context context4;
        Context context5;
        List<com.tw.core.c.f> iK = C0051y.iK();
        if (iK != null && iK.size() == 2) {
            com.tw.core.c.f fVar2 = null;
            com.tw.core.c.f fVar3 = null;
            for (com.tw.core.c.f fVar4 : iK) {
                String fJ = fVar4.fJ();
                context3 = this.cG.bd;
                String gt = C0029c.gt(context3, fJ, null);
                if (TextUtils.isEmpty(gt)) {
                    context4 = this.cG.bd;
                    gt = C0051y.iL(context4, fJ);
                    if (!TextUtils.isEmpty(gt)) {
                        context5 = this.cG.bd;
                        C0029c.gu(context5, fJ, gt);
                    }
                }
                if (TextUtils.equals(gt, "DoFunCard")) {
                    com.tw.core.c.f fVar5 = fVar2;
                    fVar = fVar4;
                    fVar4 = fVar5;
                } else {
                    fVar = fVar3;
                }
                fVar3 = fVar;
                fVar2 = fVar4;
            }
            if (fVar3 != null && fVar2 != null) {
                if (!fVar3.fK()) {
                    C0034h.hr("SimCardManager", "兜风卡未启动", new Object[0]);
                    context2 = this.cG.bd;
                    C0051y.iM(context2, fVar3.fL(), true);
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    C0034h.hr("SimCardManager", "兜风卡启动状态", new Object[0]);
                }
                if (!fVar3.fM()) {
                    C0034h.hr("SimCardManager", "兜风卡未设置为默认流量", new Object[0]);
                    context = this.cG.bd;
                    C0051y.iN(context, fVar3.fN(), fVar3.fL());
                    this.cG.cw(this.cH, 1);
                    return;
                }
                C0034h.hr("SimCardManager", "兜风卡使用流量状态", new Object[0]);
                this.cG.cw(this.cH, 105);
                return;
            }
            if (fVar3 == null) {
                this.cG.cw(this.cH, 107);
                return;
            } else {
                this.cG.cw(this.cH, 104);
                return;
            }
        }
        if (iK == null || iK.size() == 0) {
            this.cG.cw(this.cH, 106);
        } else if (iK.size() == 1) {
            this.cG.cw(this.cH, 103);
        }
    }
}
