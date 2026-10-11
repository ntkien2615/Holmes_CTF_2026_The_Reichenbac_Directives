package com.tw.core.f;

import android.text.TextUtils;
import android.util.Base64;
import com.tw.core.utils.I;

/* loaded from: classes.dex */
public class b {
    public final String hq;
    public final String hr;
    public final String hs;
    public final long ht;

    private b(String str, String str2, long j, String str3) {
        this.hs = str;
        this.hr = str2;
        this.ht = j;
        this.hq = str3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static b ma(String str) {
        try {
            if (!TextUtils.isEmpty(str) && str.length() > 22) {
                return new b(str.substring(0, 4), str.substring(4, 14), 1000 * I.jV(I.jU(str.substring(14, 22)), 4), new String(Base64.decode(str.substring(22), 8), "UTF-8"));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toString() {
        return "EmqMessage{msgId='" + this.hs + "', taskId='" + this.hr + "', msgTime=" + this.ht + ", content='" + this.hq + "'}";
    }
}
