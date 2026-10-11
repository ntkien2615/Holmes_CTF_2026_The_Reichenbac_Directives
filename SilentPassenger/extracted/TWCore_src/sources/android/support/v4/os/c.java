package android.support.v4.os;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import org.eclipse.paho.client.mqttv3.logging.Logger;

/* loaded from: classes.dex */
public abstract class c extends Binder implements b {
    public c() {
        attachInterface(this, "android.support.v4.os.IResultReceiver");
    }

    public static b asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("android.support.v4.os.IResultReceiver");
        if (queryLocalInterface != null && (queryLocalInterface instanceof b)) {
            return (b) queryLocalInterface;
        }
        return new e(iBinder);
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        Bundle bundle;
        switch (i) {
            case Logger.SEVERE /* 1 */:
                parcel.enforceInterface("android.support.v4.os.IResultReceiver");
                int readInt = parcel.readInt();
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                } else {
                    bundle = null;
                }
                qq(readInt, bundle);
                return true;
            case 1598968902:
                parcel2.writeString("android.support.v4.os.IResultReceiver");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
