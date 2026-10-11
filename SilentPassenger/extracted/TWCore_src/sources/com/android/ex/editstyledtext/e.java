package com.android.ex.editstyledtext;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

/* loaded from: classes.dex */
public class e extends ShapeDrawable {
    private static boolean kb = false;
    private Spannable kc;
    private int kd;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        oB();
        canvas.drawRect(new Rect(0, 9, this.kd, 11), getPaint());
    }

    public void oD(int i) {
        if (kb) {
            Log.d("EditStyledTextSpan", "--- renewBounds:" + i);
        }
        if (i > 20) {
            i -= 20;
        }
        this.kd = i;
        setBounds(0, 0, i, 20);
    }

    private void oC(int i) {
        if (kb) {
            Log.d("EditStyledTextSpan", "--- renewColor:" + i);
        }
        getPaint().setColor(i);
    }

    private void oB() {
        c oE = oE();
        Spannable spannable = this.kc;
        ForegroundColorSpan[] foregroundColorSpanArr = (ForegroundColorSpan[]) spannable.getSpans(spannable.getSpanStart(oE), spannable.getSpanEnd(oE), ForegroundColorSpan.class);
        if (kb) {
            Log.d("EditStyledTextSpan", "--- renewColor:" + foregroundColorSpanArr.length);
        }
        if (foregroundColorSpanArr.length > 0) {
            oC(foregroundColorSpanArr[foregroundColorSpanArr.length - 1].getForegroundColor());
        }
    }

    private c oE() {
        Spannable spannable = this.kc;
        c[] cVarArr = (c[]) spannable.getSpans(0, spannable.length(), c.class);
        if (cVarArr.length > 0) {
            for (c cVar : cVarArr) {
                if (cVar.getDrawable() == this) {
                    return cVar;
                }
            }
        }
        Log.e("EditStyledTextSpan", "---renewBounds: Couldn't find");
        return null;
    }
}
