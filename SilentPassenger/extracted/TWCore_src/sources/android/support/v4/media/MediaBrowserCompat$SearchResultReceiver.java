package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.os.ResultReceiver;
import java.util.ArrayList;

/* loaded from: classes.dex */
class MediaBrowserCompat$SearchResultReceiver extends ResultReceiver {
    private final d ow;
    private final Bundle ox;
    private final String oy;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.os.ResultReceiver
    public void onReceiveResult(int i, Bundle bundle) {
        ArrayList arrayList = null;
        if (bundle != null) {
            bundle.setClassLoader(g.class.getClassLoader());
        }
        if (i != 0 || bundle == null || (!bundle.containsKey("search_results"))) {
            this.ow.sj(this.oy, this.ox);
            return;
        }
        Parcelable[] parcelableArray = bundle.getParcelableArray("search_results");
        if (parcelableArray != null) {
            ArrayList arrayList2 = new ArrayList();
            for (Parcelable parcelable : parcelableArray) {
                arrayList2.add((MediaBrowserCompat$MediaItem) parcelable);
            }
            arrayList = arrayList2;
        }
        this.ow.sk(this.oy, this.ox, arrayList);
    }
}
