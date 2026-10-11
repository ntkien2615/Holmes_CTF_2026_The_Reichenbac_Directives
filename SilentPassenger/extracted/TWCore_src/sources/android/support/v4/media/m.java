package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

/* loaded from: classes.dex */
public final class m {
    private String nS;
    private Uri nT;
    private Uri nU;
    private CharSequence nV;
    private Bundle nW;
    private CharSequence nX;
    private Bitmap nY;
    private CharSequence nZ;

    public m sH(String str) {
        this.nS = str;
        return this;
    }

    public m sE(CharSequence charSequence) {
        this.nX = charSequence;
        return this;
    }

    public m sB(CharSequence charSequence) {
        this.nV = charSequence;
        return this;
    }

    public m sD(CharSequence charSequence) {
        this.nZ = charSequence;
        return this;
    }

    public m sz(Bitmap bitmap) {
        this.nY = bitmap;
        return this;
    }

    public m sG(Uri uri) {
        this.nU = uri;
        return this;
    }

    public m sC(Bundle bundle) {
        this.nW = bundle;
        return this;
    }

    public m sA(Uri uri) {
        this.nT = uri;
        return this;
    }

    public MediaDescriptionCompat sF() {
        return new MediaDescriptionCompat(this.nS, this.nX, this.nV, this.nZ, this.nY, this.nU, this.nW, this.nT);
    }
}
