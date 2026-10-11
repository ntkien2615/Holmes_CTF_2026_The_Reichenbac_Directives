package com.android.ex.editstyledtext;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/* loaded from: classes.dex */
public class EditStyledText$SavedStyledTextState extends View.BaseSavedState {
    public int kC;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EditStyledText$SavedStyledTextState(Parcelable parcelable) {
        super(parcelable);
    }

    @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.kC);
    }

    public String toString() {
        return "EditStyledText.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " bgcolor=" + this.kC + "}";
    }
}
