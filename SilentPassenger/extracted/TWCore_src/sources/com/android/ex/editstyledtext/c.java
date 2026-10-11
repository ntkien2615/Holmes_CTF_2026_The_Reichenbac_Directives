package com.android.ex.editstyledtext;

import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;

/* loaded from: classes.dex */
public class c extends DynamicDrawableSpan {
    e jS;

    @Override // android.text.style.DynamicDrawableSpan
    public Drawable getDrawable() {
        return this.jS;
    }

    public void oc(int i) {
        this.jS.oD(i);
    }
}
