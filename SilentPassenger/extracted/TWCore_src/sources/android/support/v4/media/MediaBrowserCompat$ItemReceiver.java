package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.os.ResultReceiver;

/* loaded from: classes.dex */
class MediaBrowserCompat$ItemReceiver extends ResultReceiver {
    private final String ou;
    private final i ov;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.os.ResultReceiver
    public void onReceiveResult(int i, Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(g.class.getClassLoader());
        }
        if (i != 0 || bundle == null || (!bundle.containsKey("media_item"))) {
            this.ov.onError(this.ou);
            return;
        }
        Parcelable parcelable = bundle.getParcelable("media_item");
        if (parcelable == null || (parcelable instanceof MediaBrowserCompat$MediaItem)) {
            this.ov.sv((MediaBrowserCompat$MediaItem) parcelable);
        } else {
            this.ov.onError(this.ou);
        }
    }
}
