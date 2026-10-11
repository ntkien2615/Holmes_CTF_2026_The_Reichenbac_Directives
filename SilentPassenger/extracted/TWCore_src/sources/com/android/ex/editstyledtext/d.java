package com.android.ex.editstyledtext;

import android.R;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.NoCopySpan;
import android.text.Selection;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class d extends EditText {
    private static CharSequence jT;
    private static CharSequence jU;
    private static final NoCopySpan.Concrete jV = new NoCopySpan.Concrete();
    private static CharSequence ka;
    private InputConnection jW;
    private Drawable jX;
    private f jY;
    private ArrayList jZ;

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent;
        if (motionEvent.getAction() == 1) {
            cancelLongPress();
            boolean ot = ot();
            if (!ot) {
                oy();
            }
            int selectionStart = Selection.getSelectionStart(getText());
            int selectionEnd = Selection.getSelectionEnd(getText());
            onTouchEvent = super.onTouchEvent(motionEvent);
            if (isFocused() && oo() == 0) {
                if (ot) {
                    this.jY.oK(Selection.getSelectionStart(getText()), Selection.getSelectionEnd(getText()));
                } else {
                    this.jY.oK(selectionStart, selectionEnd);
                }
            }
            this.jY.oX();
            this.jY.oZ();
        } else {
            onTouchEvent = super.onTouchEvent(motionEvent);
        }
        oe(motionEvent);
        return onTouchEvent;
    }

    @Override // android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        EditStyledText$SavedStyledTextState editStyledText$SavedStyledTextState = new EditStyledText$SavedStyledTextState(super.onSaveInstanceState());
        editStyledText$SavedStyledTextState.kC = this.jY.oI();
        return editStyledText$SavedStyledTextState;
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof EditStyledText$SavedStyledTextState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        EditStyledText$SavedStyledTextState editStyledText$SavedStyledTextState = (EditStyledText$SavedStyledTextState) parcelable;
        super.onRestoreInstanceState(editStyledText$SavedStyledTextState.getSuperState());
        setBackgroundColor(editStyledText$SavedStyledTextState.kC);
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.jY != null) {
            this.jY.oR();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x000d. Please report as an issue. */
    @Override // android.widget.EditText, android.widget.TextView
    public boolean onTextContextMenuItem(int i) {
        boolean z = getSelectionStart() != getSelectionEnd();
        switch (i) {
            case 16776961:
                og();
                return true;
            case 16776962:
                on();
                return true;
            case 16776963:
                oy();
                return true;
            case 16776964:
                op();
                return true;
            case R.id.selectAll:
                oz();
                return true;
            case R.id.cut:
                if (z) {
                    od();
                } else {
                    this.jY.pf(false);
                    od();
                }
                return true;
            case R.id.copy:
                if (z) {
                    oh();
                } else {
                    this.jY.pf(false);
                    oh();
                }
                return true;
            case R.id.paste:
                ov();
                return true;
            case R.id.startSelectingText:
                oi();
                this.jY.oL();
                return super.onTextContextMenuItem(i);
            case R.id.stopSelectingText:
                of();
                return super.onTextContextMenuItem(i);
            default:
                return super.onTextContextMenuItem(i);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onCreateContextMenu(ContextMenu contextMenu) {
        super.onCreateContextMenu(contextMenu);
        i iVar = new i(this, null);
        if (jT != null) {
            contextMenu.add(0, 16776961, 0, jT).setOnMenuItemClickListener(iVar);
        }
        if (ol() && jU != null) {
            contextMenu.add(0, 16776962, 0, jU).setOnMenuItemClickListener(iVar);
        }
        if (this.jY.pd()) {
            contextMenu.add(0, R.id.paste, 0, ka).setOnMenuItemClickListener(iVar).setAlphabeticShortcut('v');
        }
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.jY != null) {
            this.jY.pc(getText(), i, i2, i3);
            this.jY.pi(getText(), i, i2, i3);
            if (i3 > i2) {
                this.jY.oW(i, i + i3);
            } else if (i2 < i3) {
                this.jY.oZ();
            }
            if (this.jY.oP()) {
                if (i3 > i2) {
                    this.jY.oX();
                    of();
                } else if (i3 < i2) {
                    this.jY.oV(22);
                }
            }
        }
        super.onTextChanged(charSequence, i, i2, i3);
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        this.jW = new g(super.onCreateInputConnection(editorInfo), this);
        return this.jW;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (z) {
            oy();
        } else if (!ou()) {
            op();
        }
    }

    private void oe(MotionEvent motionEvent) {
        if (this.jZ != null) {
            Iterator it = this.jZ.iterator();
            while (it.hasNext()) {
                ((k) it.next()).ps(motionEvent);
            }
        }
    }

    public boolean ou() {
        boolean z = false;
        if (this.jZ == null) {
            return false;
        }
        Iterator it = this.jZ.iterator();
        while (true) {
            boolean z2 = z;
            if (it.hasNext()) {
                z = ((k) it.next()).pu() | z2;
            } else {
                return z2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ok(int i, int i2) {
        if (this.jZ != null) {
            Iterator it = this.jZ.iterator();
            while (it.hasNext()) {
                ((k) it.next()).pt(i, i2);
            }
        }
    }

    public void oy() {
        this.jY.oV(20);
    }

    public void op() {
        this.jY.oV(21);
    }

    public void oh() {
        this.jY.oV(1);
    }

    public void od() {
        this.jY.oV(7);
    }

    public void ov() {
        this.jY.oV(2);
    }

    public void oi() {
        this.jY.pj(true);
    }

    public void oz() {
        this.jY.pf(true);
    }

    public void of() {
        this.jY.oF();
    }

    public void og() {
        this.jY.oV(12);
    }

    public void on() {
        this.jY.oM();
    }

    private void oq() {
        this.jY.oR();
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        if (i != 16777215) {
            super.setBackgroundColor(i);
        } else {
            setBackgroundDrawable(this.jX);
        }
        this.jY.setBackgroundColor(i);
        oq();
    }

    public boolean ot() {
        return this.jY.oT();
    }

    public boolean ol() {
        return this.jY.oJ();
    }

    public boolean os() {
        return this.jY.oS();
    }

    public int oo() {
        return this.jY.oN();
    }

    public int oj() {
        return this.jY.oI();
    }

    public int or(int i) {
        if (i < 0 || i > getText().length()) {
            return -16777216;
        }
        ForegroundColorSpan[] foregroundColorSpanArr = (ForegroundColorSpan[]) getText().getSpans(i, i, ForegroundColorSpan.class);
        if (foregroundColorSpanArr.length > 0) {
            return foregroundColorSpanArr[0].getForegroundColor();
        }
        return -16777216;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void oA(View view, Spannable spannable) {
        spannable.removeSpan(jV);
    }
}
