package com.android.ex.editstyledtext;

import android.text.Editable;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ParagraphStyle;
import android.text.style.QuoteSpan;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class f {
    private d ke;
    private int kf;
    private int kg;
    private b kh;
    private int ki;
    private boolean kj;
    private boolean kk;
    private EditStyledText$SoftKeyReceiver kl;
    private boolean km;
    private int kn;
    private boolean ko;
    final /* synthetic */ d kp;
    private int kq;
    private boolean kr;
    private int ks;
    private SpannableStringBuilder kt;
    private int ku;
    private BackgroundColorSpan kv;

    public void oV(int i) {
        oU(i, true);
    }

    public void oU(int i, boolean z) {
        this.kh.oa(i);
        if (z) {
            this.ke.ok(this.ku, this.ki);
        }
    }

    public void pj(boolean z) {
        Log.d("EditStyledText.EditorManager", "--- onClickSelect");
        this.ku = 5;
        if (this.ki == 0) {
            this.kh.nX();
        } else {
            pg();
            this.kh.nX();
        }
        if (z) {
            this.ke.ok(this.ku, this.ki);
        }
    }

    public void oX() {
        Log.d("EditStyledText.EditorManager", "--- onClickView");
        if (this.ki == 1 || this.ki == 2) {
            this.kh.nX();
            this.ke.ok(this.ku, this.ki);
        }
    }

    public void pf(boolean z) {
        Log.d("EditStyledText.EditorManager", "--- onClickSelectAll");
        oH();
        if (z) {
            this.ke.ok(this.ku, this.ki);
        }
    }

    public void oF() {
        Log.d("EditStyledText.EditorManager", "--- onFixSelectedItem");
        oO();
        this.ke.ok(this.ku, this.ki);
    }

    public void oM() {
        this.kh.oa(14);
    }

    public void oR() {
        Log.d("EditStyledText.EditorManager", "--- onRefreshStyles");
        Editable text = this.ke.getText();
        int length = text.length();
        int width = this.ke.getWidth();
        c[] cVarArr = (c[]) text.getSpans(0, length, c.class);
        for (c cVar : cVarArr) {
            cVar.oc(width);
        }
        j[] jVarArr = (j[]) text.getSpans(0, length, j.class);
        for (j jVar : jVarArr) {
            jVar.pr(this.ke.oj());
        }
        if (cVarArr.length > 0) {
            text.replace(0, 1, "" + text.charAt(0));
        }
    }

    public void setBackgroundColor(int i) {
        this.kn = i;
    }

    public void oW(int i, int i2) {
        Log.d("EditStyledText", "--- setTextComposingMask:" + i + "," + i2);
        int min = Math.min(i, i2);
        int max = Math.max(i, i2);
        int or = (!oP() || this.ks == 16777215) ? this.ke.or(min) : this.ks;
        int oj = this.ke.oj();
        Log.d("EditStyledText", "--- fg:" + Integer.toHexString(or) + ",bg:" + Integer.toHexString(oj) + "," + oP() + ",," + this.ku);
        if (or == oj) {
            int i3 = (~((-16777216) | oj)) | Integer.MIN_VALUE;
            if (this.kv == null || this.kv.getBackgroundColor() != i3) {
                this.kv = new BackgroundColorSpan(i3);
            }
            this.ke.getText().setSpan(this.kv, min, max, 33);
        }
    }

    public void oZ() {
        Log.d("EditStyledText", "--- unsetTextComposingMask");
        if (this.kv != null) {
            this.ke.getText().removeSpan(this.kv);
            this.kv = null;
        }
    }

    public boolean oT() {
        return this.kr;
    }

    public boolean oJ() {
        Editable text = this.ke.getText();
        int length = text.length();
        return ((ParagraphStyle[]) text.getSpans(0, length, ParagraphStyle.class)).length > 0 || ((QuoteSpan[]) text.getSpans(0, length, QuoteSpan.class)).length > 0 || ((CharacterStyle[]) text.getSpans(0, length, CharacterStyle.class)).length > 0 || this.kn != 16777215;
    }

    public boolean oS() {
        return this.km;
    }

    public boolean oP() {
        return this.ko;
    }

    public int oI() {
        return this.kn;
    }

    public int oN() {
        return this.ki;
    }

    public void pi(Editable editable, int i, int i2, int i3) {
        int pe;
        Log.d("EditStyledText.EditorManager", "updateSpanPrevious:" + i + "," + i2 + "," + i3);
        int i4 = i + i3;
        int min = Math.min(i, i4);
        int max = Math.max(i, i4);
        for (Object obj : editable.getSpans(min, min, Object.class)) {
            if ((obj instanceof ForegroundColorSpan) || (obj instanceof AbsoluteSizeSpan) || (obj instanceof j) || (obj instanceof AlignmentSpan)) {
                int spanStart = editable.getSpanStart(obj);
                int spanEnd = editable.getSpanEnd(obj);
                Log.d("EditStyledText.EditorManager", "spantype:" + obj.getClass() + "," + spanStart);
                if ((obj instanceof j) || (obj instanceof AlignmentSpan)) {
                    pe = pe(this.ke.getText(), max);
                } else {
                    pe = this.kj ? spanEnd : max;
                }
                if (spanEnd < pe) {
                    Log.d("EditStyledText.EditorManager", "updateSpanPrevious: extend span");
                    editable.setSpan(obj, spanStart, pe, 33);
                }
            } else if (obj instanceof c) {
                int spanStart2 = editable.getSpanStart(obj);
                int spanEnd2 = editable.getSpanEnd(obj);
                if (i2 > i3) {
                    editable.replace(spanStart2, spanEnd2, "");
                    editable.removeSpan(obj);
                } else if (spanEnd2 == i4 && i4 < editable.length() && this.ke.getText().charAt(i4) != '\n') {
                    this.ke.getText().insert(i4, "\n");
                }
            }
        }
    }

    public void pc(Editable editable, int i, int i2, int i3) {
        Log.d("EditStyledText.EditorManager", "updateSpanNext:" + i + "," + i2 + "," + i3);
        int i4 = i + i3;
        int min = Math.min(i, i4);
        int max = Math.max(i, i4);
        Object[] spans = editable.getSpans(max, max, Object.class);
        for (Object obj : spans) {
            if ((obj instanceof j) || (obj instanceof AlignmentSpan)) {
                int spanStart = editable.getSpanStart(obj);
                int spanEnd = editable.getSpanEnd(obj);
                Log.d("EditStyledText.EditorManager", "spantype:" + obj.getClass() + "," + spanEnd);
                if ((((obj instanceof j) || (obj instanceof AlignmentSpan)) ? oQ(this.ke.getText(), min) : min) < spanStart && i2 > i3) {
                    editable.removeSpan(obj);
                } else if (spanStart > min) {
                    editable.setSpan(obj, min, spanEnd, 33);
                }
            } else if ((obj instanceof c) && editable.getSpanStart(obj) == i4 && i4 > 0 && this.ke.getText().charAt(i4 - 1) != '\n') {
                this.ke.getText().insert(i4, "\n");
                this.ke.setSelection(i4);
            }
        }
    }

    public boolean pd() {
        return this.kt != null && this.kt.length() > 0 && oG(this.kt).length() == 0;
    }

    private void oY() {
        Log.d("EditStyledText.EditorManager", "--- handleCancel");
        this.ku = 0;
        this.ki = 0;
        this.kr = false;
        this.ks = 16777215;
        this.kg = 0;
        this.ko = false;
        this.km = false;
        this.kj = false;
        this.kk = false;
        pg();
        this.ke.setOnClickListener(null);
        ph();
    }

    private void oO() {
        Log.d("EditStyledText.EditorManager", "--- handleComplete:" + this.kf + "," + this.kq);
        if (!this.kr) {
            return;
        }
        if (this.kf == this.kq) {
            Log.d("EditStyledText.EditorManager", "--- cancel handle complete:" + this.kf);
            pa();
        } else {
            if (this.ki == 2) {
                this.ki = 3;
            }
            this.kh.nZ(this.ku);
            d.oA(this.ke, this.ke.getText());
        }
    }

    private SpannableStringBuilder oG(SpannableStringBuilder spannableStringBuilder) {
        SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(spannableStringBuilder);
        for (DynamicDrawableSpan dynamicDrawableSpan : (DynamicDrawableSpan[]) spannableStringBuilder2.getSpans(0, spannableStringBuilder2.length(), DynamicDrawableSpan.class)) {
            if ((dynamicDrawableSpan instanceof c) || (dynamicDrawableSpan instanceof a)) {
                spannableStringBuilder2.replace(spannableStringBuilder2.getSpanStart(dynamicDrawableSpan), spannableStringBuilder2.getSpanEnd(dynamicDrawableSpan), (CharSequence) "");
            }
        }
        return spannableStringBuilder2;
    }

    private void oH() {
        if (!this.kr) {
            return;
        }
        this.kh.oa(11);
    }

    private void pa() {
        oY();
        this.kr = true;
        this.ke.ok(this.ku, this.ki);
    }

    private void pg() {
        Log.d("EditStyledText.EditorManager", "--- offSelect");
        d.oA(this.ke, this.ke.getText());
        int selectionStart = this.ke.getSelectionStart();
        this.ke.setSelection(selectionStart, selectionStart);
        this.ki = 0;
    }

    private int oQ(Editable editable, int i) {
        int i2 = i;
        while (i2 > 0 && editable.charAt(i2 - 1) != '\n') {
            i2--;
        }
        Log.d("EditStyledText.EditorManager", "--- findLineStart:" + i + "," + editable.length() + "," + i2);
        return i2;
    }

    private int pe(Editable editable, int i) {
        int i2 = i;
        while (true) {
            if (i2 >= editable.length()) {
                break;
            }
            if (editable.charAt(i2) != '\n') {
                i2++;
            } else {
                i2++;
                break;
            }
        }
        Log.d("EditStyledText.EditorManager", "--- findLineEnd:" + i + "," + editable.length() + "," + i2);
        return i2;
    }

    public void oK(int i, int i2) {
        Log.d("EditStyledText.EditorManager", "--- showsoftkey");
        if (!this.ke.isFocused() || oS()) {
            return;
        }
        this.kl.kz = Selection.getSelectionStart(this.ke.getText());
        this.kl.kx = Selection.getSelectionEnd(this.ke.getText());
        if (((InputMethodManager) this.kp.getContext().getSystemService("input_method")).showSoftInput(this.ke, 0, this.kl) && this.kl != null) {
            Selection.setSelection(this.kp.getText(), i, i2);
        }
    }

    public void pb() {
        Log.d("EditStyledText.EditorManager", "--- hidesoftkey");
        if (!this.ke.isFocused()) {
            return;
        }
        this.kl.kz = Selection.getSelectionStart(this.ke.getText());
        this.kl.kx = Selection.getSelectionEnd(this.ke.getText());
        ((InputMethodManager) this.ke.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.ke.getWindowToken(), 0, this.kl);
    }

    public void oL() {
        Log.d("EditStyledText.EditorManager", "--- blockSoftKey:");
        pb();
        this.km = true;
    }

    public void ph() {
        Log.d("EditStyledText.EditorManager", "--- unblockSoftKey:");
        this.km = false;
    }
}
