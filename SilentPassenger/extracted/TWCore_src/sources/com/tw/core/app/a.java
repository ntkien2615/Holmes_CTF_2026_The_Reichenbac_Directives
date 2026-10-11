package com.tw.core.app;

import com.tw.core.e.e;

/* loaded from: classes.dex */
public class a {
    public static String jo;
    public static String jp;
    public static String jq;
    public static String jr;
    public static String js;
    public static String jt;

    static {
        if (e.lB()) {
            jo = "dofun";
        } else {
            jo = "overseas";
        }
        jr = jo + "/cloud/collect/gps";
        js = jo + "/car/config/";
        jp = jo + "/car/config/#";
        jq = jo + "/car/private/";
        jt = jo + "/cloud/request/baidu/serverid";
    }
}
