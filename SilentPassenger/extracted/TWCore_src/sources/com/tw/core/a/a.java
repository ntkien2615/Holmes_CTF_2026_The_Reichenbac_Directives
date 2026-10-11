package com.tw.core.a;

import com.tw.core.e.e;
import com.tw.core.utils.D;

/* loaded from: classes.dex */
public class a {
    public static final String a;
    public static final String b;
    public static final String c;
    public static final String d;
    public static final String e;
    public static final String f;
    public static final String g;
    public static String h;
    public static final String i;
    public static final String j;
    public static final String k;
    public static final String l;
    public static String m;
    public static final String n;
    public static final String o;
    public static final String p;
    public static final String q;
    public static final String r;

    static {
        if (e.lB()) {
            m = D.fj ? "http://core.car.test.cardoor.cn/core/api/" : "http://core.car.cardoor.cn/core/api/";
            h = D.fj ? "tcp://mqtt.car.test.cardoor.cn:1883" : "tcp://mqtt.car.cardoor.cn:1883";
        } else {
            m = D.fj ? "http://overseas.dev.cardoor.cn/core/api/" : "http://core.car.dofuncar.com/core/api/";
            h = D.fj ? "tcp://overseas.dev.cardoor.cn:1883" : "tcp://mqtt.car.dofuncar.com";
        }
        g = m + "car/init";
        n = m + "car/activate";
        c = m + "bind/binded?";
        i = m + "flowcard/simplecheck?";
        r = m + "upgradeauto/upinfo?";
        q = m + "upgradeauto/upresult";
        e = m + "serviceid/car?";
        a = m + "car/baseinfocyele";
        b = m + "car/gpsinfo";
        f = m + "common/action/parseUid?";
        d = m + "account/car/refreshtoken?";
        p = m + "logsystemcatch/upload";
        l = m + "logsystemcatch/logreceipt";
        o = m + "logappcatch/upload";
        j = m + "logappcatch/logreceipt";
        k = m + "logappcatch/logswitchreceipt";
    }
}
