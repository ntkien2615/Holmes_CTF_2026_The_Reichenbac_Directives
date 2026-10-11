package com.android.ex.editstyledtext;

import android.util.Log;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

/* loaded from: classes.dex */
public class g extends InputConnectionWrapper {
    d kw;

    public g(InputConnection inputConnection, d dVar) {
        super(inputConnection, true);
        this.kw = dVar;
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean commitText(CharSequence charSequence, int i) {
        f fVar;
        Log.d("EditStyledText", "--- commitText:");
        fVar = this.kw.jY;
        fVar.oZ();
        return super.commitText(charSequence, i);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean finishComposingText() {
        Log.d("EditStyledText", "--- finishcomposing:");
        if (!this.kw.os() && (!this.kw.ou()) && (!this.kw.ot())) {
            this.kw.op();
        }
        return super.finishComposingText();
    }
}
