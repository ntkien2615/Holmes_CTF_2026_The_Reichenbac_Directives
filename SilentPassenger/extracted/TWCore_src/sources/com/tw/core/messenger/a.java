package com.tw.core.messenger;

import android.content.Context;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class a {
    public static List ho = new ArrayList();
    private static a hp = new a();

    public static a get() {
        return hp;
    }

    public void lX(com.tw.core.messenger.server.b bVar) {
        ho.add(bVar);
    }

    public void lY(com.tw.core.messenger.server.b bVar) {
        ho.remove(bVar);
    }

    public void lW(Context context, Message message) {
        for (com.tw.core.messenger.server.b bVar : ho) {
            for (int i : bVar.getMessageId()) {
                if (i == message.what) {
                    bVar.cY(message);
                }
            }
        }
    }
}
