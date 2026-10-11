package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
class ExtendedByteArrayOutputStream extends ByteArrayOutputStream {
    final WebSocketNetworkModule webSocketNetworkModule;
    final WebSocketSecureNetworkModule webSocketSecureNetworkModule;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExtendedByteArrayOutputStream(WebSocketNetworkModule webSocketNetworkModule) {
        this.webSocketNetworkModule = webSocketNetworkModule;
        this.webSocketSecureNetworkModule = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExtendedByteArrayOutputStream(WebSocketSecureNetworkModule webSocketSecureNetworkModule) {
        this.webSocketNetworkModule = null;
        this.webSocketSecureNetworkModule = webSocketSecureNetworkModule;
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        ByteBuffer wrap;
        synchronized (this) {
            wrap = ByteBuffer.wrap(toByteArray());
            reset();
        }
        getSocketOutputStream().write(new WebSocketFrame((byte) 2, true, wrap.array()).encodeFrame());
        getSocketOutputStream().flush();
    }

    OutputStream getSocketOutputStream() {
        if (this.webSocketNetworkModule != null) {
            return this.webSocketNetworkModule.getSocketOutputStream();
        }
        if (this.webSocketSecureNetworkModule == null) {
            return null;
        }
        return this.webSocketSecureNetworkModule.getSocketOutputStream();
    }
}
