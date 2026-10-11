package com.android.ex.editstyledtext;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.Selection;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class EditStyledText$SoftKeyReceiver extends ResultReceiver {
    int kx;
    d ky;
    int kz;

    @Override // android.os.ResultReceiver
    protected void onReceiveResult(int i, Bundle bundle) {
        if (i != 2) {
            Selection.setSelection(this.ky.getText(), this.kz, this.kx);
        }
    }
}
