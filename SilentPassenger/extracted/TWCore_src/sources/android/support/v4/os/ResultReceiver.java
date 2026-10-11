package android.support.v4.os;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator CREATOR = new d();
    b mO;
    final boolean mN = false;
    final Handler mM = null;

    /* JADX INFO: Access modifiers changed from: protected */
    public void onReceiveResult(int i, Bundle bundle) {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        synchronized (this) {
            if (this.mO == null) {
                this.mO = new f(this);
            }
            parcel.writeStrongBinder(this.mO.asBinder());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResultReceiver(Parcel parcel) {
        this.mO = c.asInterface(parcel.readStrongBinder());
    }
}
