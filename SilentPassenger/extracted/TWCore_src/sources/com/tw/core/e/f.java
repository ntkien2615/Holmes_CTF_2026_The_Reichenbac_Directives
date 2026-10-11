package com.tw.core.e;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.tw.core.app.DoFunApplication;
import com.tw.core.utils.C0034h;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/* loaded from: classes.dex */
public class f {
    private static final String ha = f.class.getSimpleName();
    private static String hb;

    private f() {
        throw new UnsupportedOperationException("u can't fuck me...");
    }

    public static String lQ(Context context) {
        if (hb == null) {
            try {
                hb = String.valueOf(Configuration.class.getMethod("getForceRotation", (Class[]) null).invoke(null, (Object[]) null));
            } catch (Exception e) {
                e.printStackTrace();
                hb = String.valueOf(context.getResources().getConfiguration().orientation);
            }
            C0034h.hr(ha, "sScreenOrientation = %s", hb);
        }
        return hb;
    }

    public static int lN() {
        return DoFunApplication.nF().getResources().getDisplayMetrics().densityDpi;
    }

    public static int lO(Context context) {
        WindowManager windowManager = (WindowManager) context.getApplicationContext().getSystemService("window");
        if (windowManager != null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.widthPixels;
        }
        return -1;
    }

    public static int lP(Context context) {
        WindowManager windowManager = (WindowManager) context.getApplicationContext().getSystemService("window");
        if (windowManager != null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.heightPixels;
        }
        return -1;
    }

    public static String lR(Context context) {
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealSize(new Point());
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.CHINA)).format(Math.sqrt(Math.pow(r1.y / displayMetrics.ydpi, 2.0d) + Math.pow(r1.x / displayMetrics.xdpi, 2.0d)));
    }
}
