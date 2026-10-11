package com.tw.core.notification;

import android.content.Context;
import android.widget.RemoteViews;
import com.tw.core.R;
import com.tw.core.utils.H;

/* loaded from: classes.dex */
public class NotificationView extends RemoteViews {
    public NotificationView(Context context) {
        super(context.getPackageName(), R.layout.layout_notification);
    }

    public void nM(String str, String str2, int i) {
        setTextViewText(R.id.tv_notify_title, str);
        setTextViewText(R.id.tv_notify_content, str2);
        setTextViewText(R.id.tv_notify_time, H.jz(System.currentTimeMillis(), "HH:mm"));
        setImageViewResource(R.id.iv_notify_icon, i);
    }
}
