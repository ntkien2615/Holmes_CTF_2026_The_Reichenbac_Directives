package android.support.v4.app;

import android.app.Notification;
import android.app.RemoteInput;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class j implements g {
    private int lO;
    private RemoteViews lP;
    private final d lQ;
    private final Notification.Builder lR;
    private RemoteViews lU;
    private RemoteViews lV;
    private final List lT = new ArrayList();
    private final Bundle lS = new Bundle();

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(d dVar) {
        this.lQ = dVar;
        if (Build.VERSION.SDK_INT >= 26) {
            this.lR = new Notification.Builder(dVar.ld, dVar.lj);
        } else {
            this.lR = new Notification.Builder(dVar.ld);
        }
        Notification notification = dVar.ll;
        this.lR.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, dVar.lc).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(dVar.kQ).setContentText(dVar.kR).setContentInfo(dVar.lt).setContentIntent(dVar.lq).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(dVar.kY, (notification.flags & 128) != 0).setLargeIcon(dVar.kZ).setNumber(dVar.la).setProgress(dVar.kV, dVar.lk, dVar.lb);
        if (Build.VERSION.SDK_INT >= 16) {
            this.lR.setSubText(dVar.kW).setUsesChronometer(dVar.ln).setPriority(dVar.lm);
            Iterator it = dVar.kM.iterator();
            while (it.hasNext()) {
                qe((f) it.next());
            }
            if (dVar.li != null) {
                this.lS.putAll(dVar.li);
            }
            if (Build.VERSION.SDK_INT < 20) {
                if (dVar.kT) {
                    this.lS.putBoolean("android.support.localOnly", true);
                }
                if (dVar.lx != null) {
                    this.lS.putString("android.support.groupKey", dVar.lx);
                    if (dVar.lf) {
                        this.lS.putBoolean("android.support.isGroupSummary", true);
                    } else {
                        this.lS.putBoolean("android.support.useSideChannel", true);
                    }
                }
                if (dVar.kP != null) {
                    this.lS.putString("android.support.sortKey", dVar.kP);
                }
            }
            this.lV = dVar.kS;
            this.lU = dVar.ls;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            this.lR.setShowWhen(dVar.lp);
            if (Build.VERSION.SDK_INT < 21 && dVar.kO != null && (!dVar.kO.isEmpty())) {
                this.lS.putStringArray("android.people", (String[]) dVar.kO.toArray(new String[dVar.kO.size()]));
            }
        }
        if (Build.VERSION.SDK_INT >= 20) {
            this.lR.setLocalOnly(dVar.kT).setGroup(dVar.lx).setGroupSummary(dVar.lf).setSortKey(dVar.kP);
            this.lO = dVar.kU;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.lR.setCategory(dVar.kN).setColor(dVar.kL).setVisibility(dVar.lh).setPublicVersion(dVar.lr);
            Iterator it2 = dVar.kO.iterator();
            while (it2.hasNext()) {
                this.lR.addPerson((String) it2.next());
            }
            this.lP = dVar.lo;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            this.lR.setExtras(dVar.li).setRemoteInputHistory(dVar.ly);
            if (dVar.kS != null) {
                this.lR.setCustomContentView(dVar.kS);
            }
            if (dVar.ls != null) {
                this.lR.setCustomBigContentView(dVar.ls);
            }
            if (dVar.lo != null) {
                this.lR.setCustomHeadsUpContentView(dVar.lo);
            }
        }
        if (Build.VERSION.SDK_INT >= 26) {
            this.lR.setBadgeIconType(dVar.kX).setShortcutId(dVar.lg).setTimeoutAfter(dVar.lu).setGroupAlertBehavior(dVar.kU);
            if (dVar.lv) {
                this.lR.setColorized(dVar.le);
            }
        }
    }

    public Notification qg() {
        Bundle pA;
        RemoteViews pL;
        RemoteViews pM;
        e eVar = this.lQ.lw;
        if (eVar != null) {
            eVar.pO(this);
        }
        RemoteViews pN = eVar != null ? eVar.pN(this) : null;
        Notification qf = qf();
        if (pN != null) {
            qf.contentView = pN;
        } else if (this.lQ.kS != null) {
            qf.contentView = this.lQ.kS;
        }
        if (Build.VERSION.SDK_INT >= 16 && eVar != null && (pM = eVar.pM(this)) != null) {
            qf.bigContentView = pM;
        }
        if (Build.VERSION.SDK_INT >= 21 && eVar != null && (pL = this.lQ.lw.pL(this)) != null) {
            qf.headsUpContentView = pL;
        }
        if (Build.VERSION.SDK_INT >= 16 && eVar != null && (pA = a.pA(qf)) != null) {
            eVar.pK(pA);
        }
        return qf;
    }

    private void qe(f fVar) {
        Bundle bundle;
        if (Build.VERSION.SDK_INT >= 20) {
            Notification.Action.Builder builder = new Notification.Action.Builder(fVar.pQ(), fVar.pR(), fVar.pT());
            if (fVar.pU() != null) {
                for (RemoteInput remoteInput : h.pX(fVar.pU())) {
                    builder.addRemoteInput(remoteInput);
                }
            }
            if (fVar.pP() != null) {
                bundle = new Bundle(fVar.pP());
            } else {
                bundle = new Bundle();
            }
            bundle.putBoolean("android.support.allowGeneratedReplies", fVar.pS());
            if (Build.VERSION.SDK_INT >= 24) {
                builder.setAllowGeneratedReplies(fVar.pS());
            }
            builder.addExtras(bundle);
            this.lR.addAction(builder.build());
            return;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            this.lT.add(o.ql(this.lR, fVar));
        }
    }

    protected Notification qf() {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.lR.build();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            Notification build = this.lR.build();
            if (this.lO != 0) {
                if (build.getGroup() != null && (build.flags & 512) != 0 && this.lO == 2) {
                    qh(build);
                }
                if (build.getGroup() != null && (build.flags & 512) == 0 && this.lO == 1) {
                    qh(build);
                }
            }
            return build;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.lR.setExtras(this.lS);
            Notification build2 = this.lR.build();
            if (this.lV != null) {
                build2.contentView = this.lV;
            }
            if (this.lU != null) {
                build2.bigContentView = this.lU;
            }
            if (this.lP != null) {
                build2.headsUpContentView = this.lP;
            }
            if (this.lO != 0) {
                if (build2.getGroup() != null && (build2.flags & 512) != 0 && this.lO == 2) {
                    qh(build2);
                }
                if (build2.getGroup() != null && (build2.flags & 512) == 0 && this.lO == 1) {
                    qh(build2);
                }
            }
            return build2;
        }
        if (Build.VERSION.SDK_INT >= 20) {
            this.lR.setExtras(this.lS);
            Notification build3 = this.lR.build();
            if (this.lV != null) {
                build3.contentView = this.lV;
            }
            if (this.lU != null) {
                build3.bigContentView = this.lU;
            }
            if (this.lO != 0) {
                if (build3.getGroup() != null && (build3.flags & 512) != 0 && this.lO == 2) {
                    qh(build3);
                }
                if (build3.getGroup() != null && (build3.flags & 512) == 0 && this.lO == 1) {
                    qh(build3);
                }
            }
            return build3;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            SparseArray<? extends Parcelable> qj = o.qj(this.lT);
            if (qj != null) {
                this.lS.putSparseParcelableArray("android.support.actionExtras", qj);
            }
            this.lR.setExtras(this.lS);
            Notification build4 = this.lR.build();
            if (this.lV != null) {
                build4.contentView = this.lV;
            }
            if (this.lU != null) {
                build4.bigContentView = this.lU;
            }
            return build4;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            Notification build5 = this.lR.build();
            Bundle pA = a.pA(build5);
            Bundle bundle = new Bundle(this.lS);
            for (String str : this.lS.keySet()) {
                if (pA.containsKey(str)) {
                    bundle.remove(str);
                }
            }
            pA.putAll(bundle);
            SparseArray<? extends Parcelable> qj2 = o.qj(this.lT);
            if (qj2 != null) {
                a.pA(build5).putSparseParcelableArray("android.support.actionExtras", qj2);
            }
            if (this.lV != null) {
                build5.contentView = this.lV;
            }
            if (this.lU != null) {
                build5.bigContentView = this.lU;
            }
            return build5;
        }
        return this.lR.getNotification();
    }

    private void qh(Notification notification) {
        notification.sound = null;
        notification.vibrate = null;
        notification.defaults &= -2;
        notification.defaults &= -3;
    }
}
