package android.support.v4.media.session;

import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import java.util.List;

/* loaded from: classes.dex */
class A extends b {
    /* JADX INFO: Access modifiers changed from: package-private */
    public A(c cVar) {
        super(cVar);
    }

    @Override // android.support.v4.media.session.b, android.support.v4.media.session.y
    public void onSessionDestroyed() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b, android.support.v4.media.session.y
    public void qE(MediaMetadataCompat mediaMetadataCompat) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b, android.support.v4.media.session.y
    public void onQueueChanged(List list) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b, android.support.v4.media.session.y
    public void onQueueTitleChanged(CharSequence charSequence) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b, android.support.v4.media.session.y
    public void onExtrasChanged(Bundle bundle) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b, android.support.v4.media.session.y
    public void qA(ParcelableVolumeInfo parcelableVolumeInfo) {
        throw new AssertionError();
    }
}
