package com.tw.core;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
class c implements a {
    private IBinder mRemote;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(IBinder iBinder) {
        this.mRemote = iBinder;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.mRemote;
    }

    @Override // com.tw.core.a
    public String nf() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.tw.core.IDameonInterface");
            this.mRemote.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
