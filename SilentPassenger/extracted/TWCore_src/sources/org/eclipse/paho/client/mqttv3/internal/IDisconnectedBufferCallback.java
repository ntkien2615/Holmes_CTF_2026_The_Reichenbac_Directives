package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.BufferedMessage;

/* loaded from: classes.dex */
public interface IDisconnectedBufferCallback {
    void publishBufferedMessage(BufferedMessage bufferedMessage);
}
