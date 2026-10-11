package com.tw.core;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import org.eclipse.paho.client.mqttv3.logging.Logger;

/* loaded from: classes.dex */
public abstract class b extends Binder implements a {
    public b() {
        attachInterface(this, "com.tw.core.IDameonInterface");
    }

    public static a asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.tw.core.IDameonInterface");
        if (queryLocalInterface != null && (queryLocalInterface instanceof a)) {
            return (a) queryLocalInterface;
        }
        return new c(iBinder);
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case Logger.SEVERE /* 1 */:
                parcel.enforceInterface("com.tw.core.IDameonInterface");
                String nf = nf();
                parcel2.writeNoException();
                parcel2.writeString(nf);
                return true;
            case 1598968902:
                parcel2.writeString("com.tw.core.IDameonInterface");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
