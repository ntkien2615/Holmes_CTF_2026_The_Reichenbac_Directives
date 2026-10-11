package com.tw.core.e;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.logging.Logger;

/* loaded from: classes.dex */
public class c {
    private c() {
        throw new UnsupportedOperationException("u can't fuck me...");
    }

    private static NetworkInfo lq(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static boolean lp(Context context) {
        NetworkInfo[] allNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null && (allNetworkInfo = connectivityManager.getAllNetworkInfo()) != null) {
                for (NetworkInfo networkInfo : allNetworkInfo) {
                    if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean lt(Context context) {
        NetworkInfo lq = lq(context);
        if (lq != null) {
            return lq.isConnected();
        }
        return false;
    }

    public static int lr(Context context) {
        NetworkInfo lq = lq(context);
        if (lq != null && lq.isAvailable()) {
            if (lq.getType() == 1) {
                return 1;
            }
            if (lq.getType() != 0) {
                return 5;
            }
            switch (lq.getSubtype()) {
                case Logger.SEVERE /* 1 */:
                case Logger.WARNING /* 2 */:
                case 4:
                case Logger.FINEST /* 7 */:
                case 11:
                case 16:
                    return 2;
                case 3:
                case Logger.FINE /* 5 */:
                case 6:
                case 8:
                case 9:
                case MqttConnectOptions.MAX_INFLIGHT_DEFAULT /* 10 */:
                case 12:
                case 14:
                case 15:
                case 17:
                    return 3;
                case 13:
                case 18:
                    return 4;
                default:
                    String subtypeName = lq.getSubtypeName();
                    return (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) ? 3 : 5;
            }
        }
        return -1;
    }

    public static String ls(Context context) {
        int lr = lr(context);
        if (1 == lr) {
            return "NETWORK_WIFI";
        }
        if (4 == lr) {
            return "NETWORK_4G";
        }
        if (3 == lr) {
            return "NETWORK_3G";
        }
        if (2 == lr) {
            return "NETWORK_2G";
        }
        if (-1 == lr) {
            return "NETWORK_NO";
        }
        return "NETWORK_UNKNOWN";
    }
}
