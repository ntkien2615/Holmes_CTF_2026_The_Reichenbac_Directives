package android.support.v4.app;

import android.app.RemoteInput;
import android.os.Bundle;
import java.util.Set;

/* loaded from: classes.dex */
public final class h {
    private final Set lH;
    private final boolean lI;
    private final Bundle lJ;
    private final CharSequence[] lK;
    private final CharSequence lL;
    private final String lM;

    public String pZ() {
        return this.lM;
    }

    public CharSequence qc() {
        return this.lL;
    }

    public CharSequence[] qd() {
        return this.lK;
    }

    public Set pW() {
        return this.lH;
    }

    public boolean qa() {
        return this.lI;
    }

    public Bundle pY() {
        return this.lJ;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RemoteInput[] pX(h[] hVarArr) {
        if (hVarArr == null) {
            return null;
        }
        RemoteInput[] remoteInputArr = new RemoteInput[hVarArr.length];
        for (int i = 0; i < hVarArr.length; i++) {
            remoteInputArr[i] = qb(hVarArr[i]);
        }
        return remoteInputArr;
    }

    static RemoteInput qb(h hVar) {
        return new RemoteInput.Builder(hVar.pZ()).setLabel(hVar.qc()).setChoices(hVar.qd()).setAllowFreeFormInput(hVar.qa()).addExtras(hVar.pY()).build();
    }
}
