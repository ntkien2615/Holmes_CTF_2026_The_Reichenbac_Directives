package com.tw.core.model;

import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.HandlerThread;
import com.tw.core.b.B;
import com.tw.core.b.C0018r;
import com.tw.core.b.InterfaceC0019s;
import com.tw.core.track.model.ThirdPartnerGpsModel;
import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0042p;
import com.tw.core.utils.M;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class a implements InterfaceC0019s {
    private List hT = new ArrayList();
    private int hU;
    private Context hV;
    private String hW;
    private C0018r hX;
    private Handler hY;
    private B hZ;
    private int ia;
    private String ib;

    public a(Context context, B b, int i, int i2, String str) {
        this.ia = 30;
        this.hV = context;
        this.ib = str;
        this.hU = i;
        this.hZ = b;
        if (i2 > 0) {
            this.ia = i2;
        }
        this.hW = C0030d.gG() + File.separator + "." + M.kj(str) + ".df";
        if (C0034h.ev) {
            C0034h.hr("ThirdPartnerGpsHandler", "上报地址 %s", str);
            C0034h.hr("ThirdPartnerGpsHandler", "定位频率 %s 上传频率", Integer.valueOf(i), Integer.valueOf(i2));
            C0034h.hr("ThirdPartnerGpsHandler", "存储地址 %s", this.hW);
        }
        mx();
    }

    public void mu() {
        if (this.hX == null) {
            this.hX = new C0018r(this.hV);
            this.hX.db(this, this.hU * 1000);
        }
    }

    public void release() {
        if (this.hX != null) {
            this.hX.release();
            this.hX = null;
        }
        if (this.hY != null) {
            this.hY.removeCallbacksAndMessages(null);
            this.hY = null;
        }
    }

    private void mx() {
        if (this.hY == null) {
            HandlerThread handlerThread = new HandlerThread("Loop_upload_gps_data");
            handlerThread.start();
            this.hY = new e(this, handlerThread.getLooper());
            this.hY.sendEmptyMessageDelayed(9999, 10000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mw() {
        List<ThirdPartnerGpsModel> ek;
        C0034h.hr("ThirdPartnerGpsHandler", "checkOfflineGpsDataAndUpload %s", this.ib);
        if (mv() && (ek = this.hZ.ek(100, this.hW)) != null) {
            this.hZ.el(ek, this.hW);
            C0042p.id().execute(new b(this, ek));
            if (C0034h.ev) {
                for (ThirdPartnerGpsModel thirdPartnerGpsModel : ek) {
                    C0034h.hr("ThirdPartnerGpsHandler", "正常上传的离线点 %s %s", Double.valueOf(thirdPartnerGpsModel.ao()), Double.valueOf(thirdPartnerGpsModel.ap()));
                }
            }
        }
    }

    @Override // com.tw.core.b.InterfaceC0019s
    public void aW(Location location) {
        if (C0034h.ev) {
            C0034h.hr("ThirdPartnerGpsHandler", "定位器 " + this.ib + " onLocation getLatitude=" + location.getLatitude() + ",getLongitude" + location.getLongitude() + ",getAltitude" + location.getAltitude() + ",getAccuracy" + location.getAccuracy() + ",getBearing" + location.getBearing() + ",getSpeed" + location.getSpeed() + ",getTime" + location.getTime(), new Object[0]);
        }
        ThirdPartnerGpsModel aq = ThirdPartnerGpsModel.aq(location);
        if (aq != null) {
            this.hT.add(aq);
        }
        C0034h.hr("ThirdPartnerGpsHandler", "size = %s mRealSendSize = %s", Integer.valueOf(this.hT.size()), Integer.valueOf(this.ia));
        if (this.hT.size() >= this.ia) {
            mt();
        }
    }

    public void mt() {
        if (this.hT.size() > 0) {
            ArrayList<ThirdPartnerGpsModel> arrayList = new ArrayList(this.hT);
            this.hT.clear();
            if (mv()) {
                if (C0034h.ev) {
                    for (ThirdPartnerGpsModel thirdPartnerGpsModel : arrayList) {
                        C0034h.hr("ThirdPartnerGpsHandler", "正在上传的在线点 %s %s", Double.valueOf(thirdPartnerGpsModel.ao()), Double.valueOf(thirdPartnerGpsModel.ap()));
                    }
                }
                C0042p.id().execute(new b(this, arrayList));
                return;
            }
            C0034h.hr("ThirdPartnerGpsHandler", "不满足上传条件", new Object[0]);
            this.hZ.ej(arrayList, this.hW);
        }
    }

    private boolean mv() {
        boolean lp = com.tw.core.e.c.lp(this.hV);
        C0034h.hr("ThirdPartnerGpsHandler", "网络状态 %s", Boolean.valueOf(lp));
        return lp;
    }
}
