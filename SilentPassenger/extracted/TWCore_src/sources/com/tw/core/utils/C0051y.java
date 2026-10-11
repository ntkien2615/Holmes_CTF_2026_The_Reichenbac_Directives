package com.tw.core.utils;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.tw.core.app.DoFunApplication;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.tw.core.utils.y, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0051y {
    public static List iK() {
        ArrayList arrayList;
        boolean booleanValue;
        Context nF = DoFunApplication.nF();
        if (Build.VERSION.SDK_INT < 22) {
            return null;
        }
        try {
            Class<?> cls = Class.forName("android.telephony.SubscriptionManager");
            Method declaredMethod = cls.getDeclaredMethod("from", Context.class);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, nF);
            Method declaredMethod2 = cls.getDeclaredMethod("getActiveSubscriptionInfoList", new Class[0]);
            declaredMethod2.setAccessible(true);
            List list = (List) declaredMethod2.invoke(invoke, new Object[0]);
            TelephonyManager telephonyManager = (TelephonyManager) nF.getSystemService("phone");
            if (list != null) {
                ArrayList arrayList2 = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    try {
                        Object obj = list.get(i);
                        Method declaredMethod3 = obj.getClass().getDeclaredMethod("getSimSlotIndex", new Class[0]);
                        declaredMethod3.setAccessible(true);
                        int intValue = ((Integer) declaredMethod3.invoke(obj, new Object[0])).intValue();
                        Method declaredMethod4 = obj.getClass().getDeclaredMethod("getIccId", new Class[0]);
                        declaredMethod4.setAccessible(true);
                        String str = (String) declaredMethod4.invoke(obj, new Object[0]);
                        Method declaredMethod5 = obj.getClass().getDeclaredMethod("getSubscriptionId", new Class[0]);
                        declaredMethod5.setAccessible(true);
                        int intValue2 = ((Integer) declaredMethod5.invoke(obj, new Object[0])).intValue();
                        boolean z = intValue2 == iP(nF).intValue();
                        if (Build.VERSION.SDK_INT >= 26) {
                            Method method = TelephonyManager.class.getMethod("getSimState", Integer.TYPE);
                            method.setAccessible(true);
                            booleanValue = ((Integer) method.invoke(telephonyManager, Integer.valueOf(intValue))).intValue() == 5;
                        } else {
                            Method method2 = TelephonyManager.class.getMethod("isSimStandby", Integer.TYPE);
                            method2.setAccessible(true);
                            booleanValue = ((Boolean) method2.invoke(telephonyManager, Integer.valueOf(intValue))).booleanValue();
                        }
                        com.tw.core.c.f fVar = new com.tw.core.c.f(str, intValue, z);
                        fVar.fO(booleanValue);
                        fVar.fP(intValue2);
                        arrayList2.add(fVar);
                    } catch (Exception e) {
                        e = e;
                        arrayList = arrayList2;
                        C0038l.hF("SimCardUtil", e);
                        return arrayList;
                    }
                }
                arrayList = arrayList2;
            } else {
                arrayList = null;
            }
        } catch (Exception e2) {
            e = e2;
            arrayList = null;
        }
        try {
            C0034h.hr("SimCardUtil", "querySimInfo: %s", arrayList);
            return arrayList;
        } catch (Exception e3) {
            e = e3;
            C0038l.hF("SimCardUtil", e);
            return arrayList;
        }
    }

    public static String iL(Context context, String str) {
        if (!com.tw.core.e.c.lp(context)) {
            return null;
        }
        if (Looper.getMainLooper() != Looper.myLooper()) {
            String hc = C0032f.hc(com.tw.core.a.a.i + "iccid=" + str, null);
            C0034h.hr("SimCardUtil", "queryDoFunCard response: %s", hc);
            if (TextUtils.isEmpty(hc)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(hc);
                if ("0".equals(jSONObject.optString("code"))) {
                    return "DoFunCard";
                }
                if ("-1".equals(jSONObject.optString("code"))) {
                    return "UnDoFunCard";
                }
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        C0038l.hE("SimCardUtil", "querySimCardType net close");
        return null;
    }

    public static int iO(Context context) {
        if (Build.VERSION.SDK_INT < 22) {
            return 0;
        }
        try {
            Class<?> cls = Class.forName("android.telephony.SubscriptionManager");
            Method declaredMethod = cls.getDeclaredMethod("from", Context.class);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, context);
            Method declaredMethod2 = cls.getDeclaredMethod("getActiveSubscriptionInfoCount", new Class[0]);
            declaredMethod2.setAccessible(true);
            return ((Integer) declaredMethod2.invoke(invoke, new Object[0])).intValue();
        } catch (Exception e) {
            C0038l.hF("SimCardUtil", e);
            return 0;
        }
    }

    public static void iM(Context context, int i, boolean z) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (Build.VERSION.SDK_INT < 26) {
                Method method = TelephonyManager.class.getMethod("setSimStandby", Integer.TYPE, Boolean.TYPE);
                method.setAccessible(true);
                method.invoke(telephonyManager, Integer.valueOf(i), Boolean.valueOf(z));
            }
        } catch (Exception e) {
            C0038l.hF("SimCardUtil", e);
        }
    }

    public static void iQ(boolean z, Context context, int i) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("setDataEnabled", Integer.TYPE, Boolean.TYPE);
            if (declaredMethod != null) {
                declaredMethod.invoke(telephonyManager, Integer.valueOf(i), Boolean.valueOf(z));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void iN(Context context, int i, int i2) {
        try {
            if (!iR(context, i2)) {
                C0034h.hr("SimCardUtil", "通过切数据方式切卡", new Object[0]);
                Method declaredMethod = Class.forName("android.telephony.SubscriptionManager").getDeclaredMethod("from", Context.class);
                declaredMethod.setAccessible(true);
                Object invoke = declaredMethod.invoke(null, context);
                invoke.getClass().getMethod("setDefaultDataSubId", Integer.TYPE).invoke(invoke, Integer.valueOf(i));
                iQ(true, context, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean iR(Context context, int i) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        try {
            Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("setPrimaryCard", Integer.TYPE);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(telephonyManager, Integer.valueOf(i));
            C0034h.hr("SimCardUtil", "通过切主卡方式切卡", new Object[0]);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Integer iP(Context context) {
        int i = -1;
        try {
            Method declaredMethod = Class.forName("android.telephony.SubscriptionManager").getDeclaredMethod("from", Context.class);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, context);
            Method method = invoke.getClass().getMethod("getDefaultDataSubId", new Class[0]);
            return method != null ? Integer.valueOf(((Integer) method.invoke(invoke, new Object[0])).intValue()) : i;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return i;
        } catch (NoSuchMethodException e2) {
            try {
                Method declaredMethod2 = Class.forName("android.telephony.SubscriptionManager").getDeclaredMethod("from", Context.class);
                declaredMethod2.setAccessible(true);
                Object invoke2 = declaredMethod2.invoke(null, context);
                Method method2 = invoke2.getClass().getMethod("getDefaultDataSubscriptionId", new Class[0]);
                if (method2 != null) {
                    return Integer.valueOf(((Integer) method2.invoke(invoke2, new Object[0])).intValue());
                }
                return i;
            } catch (IllegalAccessException e3) {
                e2.printStackTrace();
                return i;
            } catch (NoSuchMethodException e4) {
                try {
                    Method declaredMethod3 = Class.forName("android.telephony.SubscriptionManager").getDeclaredMethod("from", Context.class);
                    declaredMethod3.setAccessible(true);
                    Object invoke3 = declaredMethod3.invoke(null, context);
                    Method method3 = invoke3.getClass().getMethod("getDefaultDataPhoneId", new Class[0]);
                    if (method3 != null) {
                        i = Integer.valueOf(((Integer) method3.invoke(invoke3, new Object[0])).intValue());
                        C0034h.hr("SimCardUtil", ((Integer) method3.invoke(invoke3, new Object[0])).intValue() + "", new Object[0]);
                        return i;
                    }
                    return i;
                } catch (IllegalAccessException e5) {
                    e2.printStackTrace();
                    return i;
                } catch (NoSuchMethodException e6) {
                    e2.printStackTrace();
                    return i;
                } catch (InvocationTargetException e7) {
                    e2.printStackTrace();
                    return i;
                } catch (Exception e8) {
                    e2.printStackTrace();
                    return i;
                }
            } catch (InvocationTargetException e9) {
                e2.printStackTrace();
                return i;
            } catch (Exception e10) {
                e2.printStackTrace();
                return i;
            }
        } catch (InvocationTargetException e11) {
            e11.printStackTrace();
            return i;
        } catch (Exception e12) {
            e12.printStackTrace();
            return i;
        }
    }
}
