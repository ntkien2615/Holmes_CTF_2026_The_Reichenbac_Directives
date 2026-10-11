package com.tw.core.e;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.FileReader;

/* loaded from: classes.dex */
public class b {
    private static String gR;
    private static String gS;
    private static String gT;
    private static int gQ = -1;
    private static int gU = 60;
    private static boolean gV = true;

    private b() {
        throw new UnsupportedOperationException("u can't fuck me...");
    }

    public static String lh(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (TextUtils.isEmpty(telephonyManager.getNetworkOperatorName())) {
            return "0";
        }
        return telephonyManager.getNetworkOperatorName();
    }

    public static String ll(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (TextUtils.isEmpty(telephonyManager.getDeviceId())) {
            return "0";
        }
        return telephonyManager.getDeviceId();
    }

    public static String ln(Context context) {
        String simSerialNumber = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
        if (TextUtils.isEmpty(simSerialNumber)) {
            return "0";
        }
        return simSerialNumber;
    }

    public static String lg(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (TextUtils.isEmpty(telephonyManager.getNetworkCountryIso())) {
            return "0";
        }
        return telephonyManager.getNetworkCountryIso();
    }

    public static String lm(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (TextUtils.isEmpty(telephonyManager.getSimOperator())) {
            return "0";
        }
        return telephonyManager.getSimOperator();
    }

    public static String lo(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (TextUtils.isEmpty(telephonyManager.getSubscriberId())) {
            return "0";
        }
        return telephonyManager.getSubscriberId();
    }

    public static String lk() {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        try {
            if (!TextUtils.isEmpty(gT)) {
                return gT;
            }
            try {
                bufferedReader2 = new BufferedReader(new FileReader("/sys/block/mmcblk0/device/1d35"));
                try {
                    gT = bufferedReader2.readLine();
                    String str = gT;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    return str;
                } catch (Exception e) {
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    return null;
                } catch (Throwable th) {
                    bufferedReader = bufferedReader2;
                    th = th;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                bufferedReader2 = null;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
        } catch (Exception e3) {
        }
    }

    public static String lj() {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        try {
            if (!TextUtils.isEmpty(gS)) {
                return gS;
            }
            try {
                bufferedReader2 = new BufferedReader(new FileReader("/sys/block/mmcblk0/device/3305"));
                try {
                    gS = bufferedReader2.readLine();
                    String str = gS;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    return str;
                } catch (Exception e) {
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    return null;
                } catch (Throwable th) {
                    bufferedReader = bufferedReader2;
                    th = th;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                bufferedReader2 = null;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
        } catch (Exception e3) {
        }
    }

    public static String li() {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        try {
            try {
                bufferedReader2 = new BufferedReader(new FileReader("/sys/block/mmcblk0/device/0394"));
            } catch (Exception e) {
                return null;
            }
        } catch (Exception e2) {
            bufferedReader2 = null;
        } catch (Throwable th) {
            th = th;
            bufferedReader = null;
        }
        try {
            String readLine = bufferedReader2.readLine();
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            return readLine;
        } catch (Exception e3) {
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            return null;
        } catch (Throwable th2) {
            bufferedReader = bufferedReader2;
            th = th2;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
    }

    public static String lf() {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader("/sys/block/mmcblk0/device/cid"));
            } catch (Exception e) {
                return "0";
            }
        } catch (Exception e2) {
            bufferedReader = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            String readLine = bufferedReader.readLine();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return readLine;
        } catch (Exception e3) {
            if (bufferedReader != null) {
                bufferedReader.close();
                return "0";
            }
            return "0";
        } catch (Throwable th2) {
            bufferedReader2 = bufferedReader;
            th = th2;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            throw th;
        }
    }

    public static String le() {
        if (!TextUtils.isEmpty(gR)) {
            return gR;
        }
        String lj = lj();
        String lk = lk();
        String lf = lf();
        if (!TextUtils.equals(lj, lf) || !(!"00000000000000000000000000000000".equals(lj))) {
            if (TextUtils.equals(lk, lf) && (!"00000000000000000000000000000000".equals(lk))) {
                lj = lk;
            } else {
                lj = li();
                if (TextUtils.isEmpty(lj) || "00000000000000000000000000000000".equals(lj)) {
                    lj = lf;
                }
            }
        }
        gR = lj;
        return gR;
    }
}
