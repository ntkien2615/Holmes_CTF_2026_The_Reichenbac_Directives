package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class l implements Parcelable.Creator {
    /* JADX WARN: Type inference failed for: r0v0, types: [android.support.v4.app.Fragment$SavedState] */
    @Override // android.os.Parcelable.Creator
    public Fragment$SavedState createFromParcel(final Parcel parcel) {
        final ClassLoader classLoader = null;
        return new Parcelable(parcel, classLoader) { // from class: android.support.v4.app.Fragment$SavedState
            public static final Parcelable.Creator CREATOR = new l();
            final Bundle lN;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.lN = parcel.readBundle();
                if (classLoader != null && this.lN != null) {
                    this.lN.setClassLoader(classLoader);
                }
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel2, int i) {
                parcel2.writeBundle(this.lN);
            }
        };
    }

    @Override // android.os.Parcelable.Creator
    public Fragment$SavedState[] newArray(int i) {
        return new Fragment$SavedState[i];
    }
}
