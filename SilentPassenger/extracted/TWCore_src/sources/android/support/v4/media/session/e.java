package android.support.v4.media.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.media.MediaMetadataCompat;
import android.text.TextUtils;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.logging.Logger;

/* loaded from: classes.dex */
public abstract class e extends Binder implements y {
    public e() {
        attachInterface(this, "android.support.v4.media.session.IMediaControllerCallback");
    }

    public static y asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("android.support.v4.media.session.IMediaControllerCallback");
        if (queryLocalInterface != null && (queryLocalInterface instanceof y)) {
            return (y) queryLocalInterface;
        }
        return new h(iBinder);
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case Logger.SEVERE /* 1 */:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                qx(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                return true;
            case Logger.WARNING /* 2 */:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                onSessionDestroyed();
                return true;
            case 3:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                qD(parcel.readInt() != 0 ? (PlaybackStateCompat) PlaybackStateCompat.CREATOR.createFromParcel(parcel) : null);
                return true;
            case 4:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                qE(parcel.readInt() != 0 ? (MediaMetadataCompat) MediaMetadataCompat.CREATOR.createFromParcel(parcel) : null);
                return true;
            case Logger.FINE /* 5 */:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                onQueueChanged(parcel.createTypedArrayList(MediaSessionCompat$QueueItem.CREATOR));
                return true;
            case 6:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                onQueueTitleChanged(parcel.readInt() != 0 ? (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel) : null);
                return true;
            case Logger.FINEST /* 7 */:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                onExtrasChanged(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                return true;
            case 8:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                qA(parcel.readInt() != 0 ? (ParcelableVolumeInfo) ParcelableVolumeInfo.CREATOR.createFromParcel(parcel) : null);
                return true;
            case 9:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                qB(parcel.readInt());
                return true;
            case MqttConnectOptions.MAX_INFLIGHT_DEFAULT /* 10 */:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                qz(parcel.readInt() != 0);
                return true;
            case 11:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                qy(parcel.readInt() != 0);
                return true;
            case 12:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                qF(parcel.readInt());
                return true;
            case 13:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                qC();
                return true;
            case 1598968902:
                parcel2.writeString("android.support.v4.media.session.IMediaControllerCallback");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
