package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.nio.ByteBuffer;
import javax.net.SocketFactory;
import org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* loaded from: classes.dex */
public class WebSocketNetworkModule extends TCPNetworkModule {
    private static final String CLASS_NAME = WebSocketNetworkModule.class.getName();
    private static final Logger log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    private String host;
    private ByteArrayOutputStream outputStream;
    private PipedInputStream pipedInputStream;
    private int port;
    ByteBuffer recievedPayload;
    private String uri;
    private WebSocketReceiver webSocketReceiver;

    public WebSocketNetworkModule(SocketFactory socketFactory, String str, String str2, int i, String str3) {
        super(socketFactory, str2, i, str3);
        this.outputStream = new ExtendedByteArrayOutputStream(this);
        this.uri = str;
        this.host = str2;
        this.port = i;
        this.pipedInputStream = new PipedInputStream();
        log.setResourceName(str3);
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void start() {
        super.start();
        new WebSocketHandshake(getSocketInputStream(), getSocketOutputStream(), this.uri, this.host, this.port).execute();
        this.webSocketReceiver = new WebSocketReceiver(getSocketInputStream(), this.pipedInputStream);
        this.webSocketReceiver.start("webSocketReceiver");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OutputStream getSocketOutputStream() {
        return super.getOutputStream();
    }

    InputStream getSocketInputStream() {
        return super.getInputStream();
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public InputStream getInputStream() {
        return this.pipedInputStream;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void stop() {
        getSocketOutputStream().write(new WebSocketFrame((byte) 8, true, "1000".getBytes()).encodeFrame());
        getSocketOutputStream().flush();
        if (this.webSocketReceiver != null) {
            this.webSocketReceiver.stop();
        }
        super.stop();
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public String getServerURI() {
        return "ws://" + this.host + ":" + this.port;
    }
}
