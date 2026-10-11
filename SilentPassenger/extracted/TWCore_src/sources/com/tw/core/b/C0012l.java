package com.tw.core.b;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.util.Base64;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0035i;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.tw.core.b.l, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0012l extends com.tw.core.g.a implements com.tw.core.f.c {
    private static final String br = com.tw.core.app.a.jo + "/cloud/receipt/cutstarter";
    private Context bp;
    private PackageManager bq;

    public C0012l(Context context) {
        this.bp = context;
        this.bq = this.bp.getPackageManager();
    }

    @Override // com.tw.core.g.a, com.tw.core.f.a
    public void bD(boolean z, String str) {
    }

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void onCreate() {
        super.onCreate();
        com.tw.core.f.f.get().mq(this);
    }

    @Override // com.tw.core.b.B
    public String bK() {
        return null;
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "0312";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        C0034h.hr("SwitchLauncherManager", "emqMessageChanged %s", bVar);
        try {
            JSONObject jSONObject = new JSONObject(bVar.hq);
            List hx = C0035i.hx(jSONObject.optJSONArray("language"), String.class);
            String optString = jSONObject.optString("system_version_regex");
            if (hx != null && hx.size() > 0 && (!hx.contains(com.tw.core.utils.H.jy(this.bp)))) {
                C0034h.hs("SwitchLauncherManager", "语言环境不匹配 %s", com.tw.core.utils.H.jy(this.bp));
                cT(bVar.hr, 101);
                return;
            }
            String lE = com.tw.core.e.e.lE();
            if (!com.tw.core.utils.H.jE(optString) && (!Pattern.matches(optString, lE))) {
                C0034h.hs("SwitchLauncherManager", "系统版本不匹配 %s", lE);
                cT(bVar.hr, 102);
                return;
            }
            String optString2 = jSONObject.optString("dest");
            List hx2 = C0035i.hx(jSONObject.optJSONArray("source"), String.class);
            if (!TextUtils.isEmpty(optString2) && optString2.contains("|")) {
                String[] split = optString2.split("\\|");
                cU(bVar.hr, split[0], split[1], hx2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cT(String str, int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", str);
            jSONObject.put("cid", com.tw.core.e.b.le());
            jSONObject.put("time", System.currentTimeMillis() / 1000);
            jSONObject.put("code", i);
            nk("0507" + Base64.encodeToString(jSONObject.toString().getBytes(), 8), new M(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void cU(String str, String str2, String str3, List list) {
        if (!com.tw.core.e.d.lu(str2)) {
            C0034h.hs("SwitchLauncherManager", "%s 未安装", str2);
            cT(str, 103);
            return;
        }
        String cS = cS();
        if (TextUtils.equals(str2, cS)) {
            C0034h.hs("SwitchLauncherManager", "当前设置的Launcher为默认Launcher", new Object[0]);
            cT(str, 104);
            return;
        }
        if (list != null && (!list.contains(cS))) {
            C0034h.hs("SwitchLauncherManager", "原Launcher不匹配", new Object[0]);
            cT(str, 105);
            return;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        List<ResolveInfo> queryIntentActivities = this.bq.queryIntentActivities(intent, 0);
        ComponentName[] componentNameArr = new ComponentName[queryIntentActivities.size()];
        ComponentName componentName = new ComponentName(str2, str3);
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i;
            if (i3 < queryIntentActivities.size()) {
                ResolveInfo resolveInfo = queryIntentActivities.get(i3);
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                if (activityInfo != null) {
                    componentNameArr[i3] = new ComponentName(activityInfo.packageName, activityInfo.name);
                    if (componentName.getClassName().equals(activityInfo.name)) {
                        i2 = resolveInfo.match;
                    }
                }
                i = i3 + 1;
            } else {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.MAIN");
                intentFilter.addCategory("android.intent.category.HOME");
                intentFilter.addCategory("android.intent.category.DEFAULT");
                this.bq.clearPackagePreferredActivities(cS);
                this.bq.addPreferredActivity(intentFilter, i2, componentNameArr, componentName);
                cT(str, 1);
                C0034h.hr("SwitchLauncherManager", "切换成功 %s", str2);
                return;
            }
        }
    }

    private String cS() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        String str = this.bq.resolveActivity(intent, 65536).activityInfo.packageName;
        C0034h.hr("SwitchLauncherManager", "当前设置的启动器 %s", str);
        return str;
    }

    @Override // com.tw.core.g.c
    public String bV() {
        return br;
    }
}
