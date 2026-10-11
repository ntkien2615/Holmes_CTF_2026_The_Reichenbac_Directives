package android.support.v4.media.session;

import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.os.Bundle;
import java.util.List;

/* loaded from: classes.dex */
class w extends MediaController.Callback {
    protected final x nB;

    public w(x xVar) {
        this.nB = xVar;
    }

    @Override // android.media.session.MediaController.Callback
    public void onSessionDestroyed() {
        this.nB.onSessionDestroyed();
    }

    @Override // android.media.session.MediaController.Callback
    public void onSessionEvent(String str, Bundle bundle) {
        this.nB.onSessionEvent(str, bundle);
    }

    @Override // android.media.session.MediaController.Callback
    public void onPlaybackStateChanged(PlaybackState playbackState) {
        this.nB.sb(playbackState);
    }

    @Override // android.media.session.MediaController.Callback
    public void onMetadataChanged(MediaMetadata mediaMetadata) {
        this.nB.sc(mediaMetadata);
    }

    @Override // android.media.session.MediaController.Callback
    public void onQueueChanged(List list) {
        this.nB.onQueueChanged(list);
    }

    @Override // android.media.session.MediaController.Callback
    public void onQueueTitleChanged(CharSequence charSequence) {
        this.nB.onQueueTitleChanged(charSequence);
    }

    @Override // android.media.session.MediaController.Callback
    public void onExtrasChanged(Bundle bundle) {
        this.nB.onExtrasChanged(bundle);
    }

    @Override // android.media.session.MediaController.Callback
    public void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
        this.nB.sa(playbackInfo.getPlaybackType(), n.rX(playbackInfo), playbackInfo.getVolumeControl(), playbackInfo.getMaxVolume(), playbackInfo.getCurrentVolume());
    }
}
