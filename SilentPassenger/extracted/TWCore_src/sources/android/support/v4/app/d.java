package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class d {
    int kL;
    public ArrayList kM;
    String kN;
    public ArrayList kO;
    String kP;
    CharSequence kQ;
    CharSequence kR;
    RemoteViews kS;
    boolean kT;
    int kU;
    int kV;
    CharSequence kW;
    int kX;
    PendingIntent kY;
    Bitmap kZ;
    int la;
    boolean lb;
    RemoteViews lc;
    public Context ld;
    boolean le;
    boolean lf;
    String lg;
    int lh;
    Bundle li;
    String lj;
    int lk;
    Notification ll;
    int lm;
    boolean ln;
    RemoteViews lo;
    boolean lp;
    PendingIntent lq;
    Notification lr;
    RemoteViews ls;
    CharSequence lt;
    long lu;
    boolean lv;
    e lw;
    String lx;
    CharSequence[] ly;

    public d(Context context, String str) {
        this.kM = new ArrayList();
        this.lp = true;
        this.kT = false;
        this.kL = 0;
        this.lh = 0;
        this.kX = 0;
        this.kU = 0;
        this.ll = new Notification();
        this.ld = context;
        this.lj = str;
        this.ll.when = System.currentTimeMillis();
        this.ll.audioStreamType = -1;
        this.lm = 0;
        this.kO = new ArrayList();
    }

    public d(Context context) {
        this(context, null);
    }

    public d pB(int i) {
        this.ll.icon = i;
        return this;
    }

    public d pF(CharSequence charSequence) {
        this.kQ = pE(charSequence);
        return this;
    }

    public d pD(CharSequence charSequence) {
        this.kR = pE(charSequence);
        return this;
    }

    public d pC(RemoteViews remoteViews) {
        this.ll.contentView = remoteViews;
        return this;
    }

    public d pI(PendingIntent pendingIntent) {
        this.lq = pendingIntent;
        return this;
    }

    public d pH(boolean z) {
        pG(16, z);
        return this;
    }

    private void pG(int i, boolean z) {
        if (z) {
            this.ll.flags |= i;
        } else {
            this.ll.flags &= ~i;
        }
    }

    public Notification pJ() {
        return new j(this).qg();
    }

    protected static CharSequence pE(CharSequence charSequence) {
        if (charSequence == null) {
            return charSequence;
        }
        if (charSequence.length() > 5120) {
            return charSequence.subSequence(0, 5120);
        }
        return charSequence;
    }
}
