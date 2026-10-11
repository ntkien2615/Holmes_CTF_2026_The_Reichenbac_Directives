package com.tw.core.b;

import android.content.Context;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0048v;
import org.json.JSONObject;

/* renamed from: com.tw.core.b.i, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0009i extends com.tw.core.g.a implements com.tw.core.f.c {
    private Context bc;

    public C0009i(Context context) {
        this.bc = context;
    }

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void onCreate() {
        super.onCreate();
        com.tw.core.f.f.get().mq(this);
    }

    @Override // com.tw.core.g.a, com.tw.core.f.a
    public void bD(boolean z, String str) {
        super.bD(z, str);
        boolean gx = C0029c.gx(this.bc, "key_report_device_info_switch", false);
        C0034h.hr("DeviceInfoReportManager", "reportInfoSwitch %s", Boolean.valueOf(gx));
        if (gx) {
            long gA = C0029c.gA(this.bc, "key_report_device_info_period", -1L);
            C0034h.hr("DeviceInfoReportManager", "reportInfoPeriod %s", Long.valueOf(gA));
            if (gA > 0) {
                cu(gA);
            }
        }
    }

    private void cu(long j) {
        long gA = C0029c.gA(this.bc, "key_report_device_info_time", 0L);
        C0034h.hr("DeviceInfoReportManager", "lastReportInfoTime %s", Long.valueOf(gA));
        long abs = Math.abs(System.currentTimeMillis() - gA);
        C0034h.hr("DeviceInfoReportManager", "period %s", Long.valueOf(abs));
        if (abs >= 1000 * j) {
            C0034h.hr("DeviceInfoReportManager", "report info", new Object[0]);
            ct();
        }
    }

    private void ct() {
        C0048v.iA(new G(this));
    }

    @Override // com.tw.core.b.B
    public String bK() {
        return null;
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "0317";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        C0034h.hr("DeviceInfoReportManager", "emqMessageChanged %s", bVar);
        try {
            JSONObject jSONObject = new JSONObject(bVar.hq);
            boolean optBoolean = jSONObject.optBoolean("collect_on_off");
            long optLong = jSONObject.optLong("collect_frequency", -1L);
            C0029c.gC(this.bc, "key_report_device_info_switch", optBoolean);
            C0029c.gE(this.bc, "key_report_device_info_period", optLong);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.tw.core.g.c
    public String bV() {
        return null;
    }
}
