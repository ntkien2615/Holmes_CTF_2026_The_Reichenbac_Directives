package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.logging.Logger;

/* loaded from: classes.dex */
public abstract class u extends Binder implements i {
    public u() {
        attachInterface(this, "android.support.v4.media.session.IMediaSession");
    }

    public static i asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("android.support.v4.media.session.IMediaSession");
        if (queryLocalInterface != null && (queryLocalInterface instanceof i)) {
            return (i) queryLocalInterface;
        }
        return new s(iBinder);
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case Logger.SEVERE /* 1 */:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rL(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (MediaSessionCompat$ResultReceiverWrapper) MediaSessionCompat$ResultReceiverWrapper.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case Logger.WARNING /* 2 */:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                boolean rH = rH(parcel.readInt() != 0 ? (KeyEvent) KeyEvent.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeInt(rH ? 1 : 0);
                return true;
            case 3:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rU(e.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 4:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rB(e.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case Logger.FINE /* 5 */:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                boolean rT = rT();
                parcel2.writeNoException();
                parcel2.writeInt(rT ? 1 : 0);
                return true;
            case 6:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                String rb = rb();
                parcel2.writeNoException();
                parcel2.writeString(rb);
                return true;
            case Logger.FINEST /* 7 */:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                String rj = rj();
                parcel2.writeNoException();
                parcel2.writeString(rj);
                return true;
            case 8:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                PendingIntent ro = ro();
                parcel2.writeNoException();
                if (ro != null) {
                    parcel2.writeInt(1);
                    ro.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            case 9:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                long rz = rz();
                parcel2.writeNoException();
                parcel2.writeLong(rz);
                return true;
            case MqttConnectOptions.MAX_INFLIGHT_DEFAULT /* 10 */:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                ParcelableVolumeInfo ry = ry();
                parcel2.writeNoException();
                if (ry != null) {
                    parcel2.writeInt(1);
                    ry.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            case 11:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rA(parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 12:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rf(parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 13:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rk();
                parcel2.writeNoException();
                return true;
            case 14:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rq(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 15:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rD(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 16:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rS(parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 17:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rQ(parcel.readLong());
                parcel2.writeNoException();
                return true;
            case 18:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rr();
                parcel2.writeNoException();
                return true;
            case 19:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                stop();
                parcel2.writeNoException();
                return true;
            case 20:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                next();
                parcel2.writeNoException();
                return true;
            case 21:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rm();
                parcel2.writeNoException();
                return true;
            case 22:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rP();
                parcel2.writeNoException();
                return true;
            case 23:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rC();
                parcel2.writeNoException();
                return true;
            case 24:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rt(parcel.readLong());
                parcel2.writeNoException();
                return true;
            case 25:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                ru(parcel.readInt() != 0 ? (RatingCompat) RatingCompat.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 26:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rh(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 27:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                MediaMetadataCompat rg = rg();
                parcel2.writeNoException();
                if (rg != null) {
                    parcel2.writeInt(1);
                    rg.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            case 28:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                PlaybackStateCompat rw = rw();
                parcel2.writeNoException();
                if (rw != null) {
                    parcel2.writeInt(1);
                    rw.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            case 29:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                List rx = rx();
                parcel2.writeNoException();
                parcel2.writeTypedList(rx);
                return true;
            case MqttConnectOptions.CONNECTION_TIMEOUT_DEFAULT /* 30 */:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                CharSequence rE = rE();
                parcel2.writeNoException();
                if (rE != null) {
                    parcel2.writeInt(1);
                    TextUtils.writeToParcel(rE, parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            case 31:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                Bundle rM = rM();
                parcel2.writeNoException();
                if (rM != null) {
                    parcel2.writeInt(1);
                    rM.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            case 32:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                int rO = rO();
                parcel2.writeNoException();
                parcel2.writeInt(rO);
                return true;
            case 33:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rG();
                parcel2.writeNoException();
                return true;
            case 34:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                ra(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 35:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rv(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 36:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rp(parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 37:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                int rN = rN();
                parcel2.writeNoException();
                parcel2.writeInt(rN);
                return true;
            case 38:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                boolean rc = rc();
                parcel2.writeNoException();
                parcel2.writeInt(rc ? 1 : 0);
                return true;
            case 39:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rK(parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 40:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rd(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 41:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                ri(parcel.readInt() != 0 ? (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 42:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rF(parcel.readInt() != 0 ? (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 43:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                re(parcel.readInt() != 0 ? (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 44:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rn(parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 45:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                boolean rI = rI();
                parcel2.writeNoException();
                parcel2.writeInt(rI ? 1 : 0);
                return true;
            case 46:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rl(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 47:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                int rR = rR();
                parcel2.writeNoException();
                parcel2.writeInt(rR);
                return true;
            case 48:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rs(parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 51:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rJ(parcel.readInt() != 0 ? (RatingCompat) RatingCompat.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 1598968902:
                parcel2.writeString("android.support.v4.media.session.IMediaSession");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
