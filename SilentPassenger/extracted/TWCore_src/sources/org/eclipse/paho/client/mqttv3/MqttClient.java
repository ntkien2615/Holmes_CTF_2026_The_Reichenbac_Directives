package org.eclipse.paho.client.mqttv3;

import java.util.concurrent.ScheduledExecutorService;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.eclipse.paho.client.mqttv3.util.Debug;

/* loaded from: classes.dex */
public class MqttClient implements IMqttClient {
    protected MqttAsyncClient aClient;
    protected long timeToWait;

    public MqttClient(String str, String str2) {
        this(str, str2, new MqttDefaultFilePersistence());
    }

    public MqttClient(String str, String str2, MqttClientPersistence mqttClientPersistence) {
        this.aClient = null;
        this.timeToWait = -1L;
        this.aClient = new MqttAsyncClient(str, str2, mqttClientPersistence);
    }

    public MqttClient(String str, String str2, MqttClientPersistence mqttClientPersistence, ScheduledExecutorService scheduledExecutorService) {
        this.aClient = null;
        this.timeToWait = -1L;
        this.aClient = new MqttAsyncClient(str, str2, mqttClientPersistence, new ScheduledExecutorPingSender(scheduledExecutorService), scheduledExecutorService);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void connect() {
        connect(new MqttConnectOptions());
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void connect(MqttConnectOptions mqttConnectOptions) {
        this.aClient.connect(mqttConnectOptions, null, null).waitForCompletion(getTimeToWait());
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public IMqttToken connectWithResult(MqttConnectOptions mqttConnectOptions) {
        IMqttToken connect = this.aClient.connect(mqttConnectOptions, null, null);
        connect.waitForCompletion(getTimeToWait());
        return connect;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void disconnect() {
        this.aClient.disconnect().waitForCompletion();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void disconnect(long j) {
        this.aClient.disconnect(j, null, null).waitForCompletion();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void disconnectForcibly() {
        this.aClient.disconnectForcibly();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void disconnectForcibly(long j) {
        this.aClient.disconnectForcibly(j);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void disconnectForcibly(long j, long j2) {
        this.aClient.disconnectForcibly(j, j2);
    }

    public void disconnectForcibly(long j, long j2, boolean z) {
        this.aClient.disconnectForcibly(j, j2, z);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void subscribe(String str) {
        subscribe(new String[]{str}, new int[]{1});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void subscribe(String[] strArr) {
        int[] iArr = new int[strArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = 1;
        }
        subscribe(strArr, iArr);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void subscribe(String str, int i) {
        subscribe(new String[]{str}, new int[]{i});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void subscribe(String[] strArr, int[] iArr) {
        IMqttToken subscribe = this.aClient.subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null);
        subscribe.waitForCompletion(getTimeToWait());
        int[] grantedQos = subscribe.getGrantedQos();
        for (int i = 0; i < grantedQos.length; i++) {
            iArr[i] = grantedQos[i];
        }
        if (grantedQos.length == 1 && iArr[0] == 128) {
            throw new MqttException(128);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void subscribe(String str, IMqttMessageListener iMqttMessageListener) {
        subscribe(new String[]{str}, new int[]{1}, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void subscribe(String[] strArr, IMqttMessageListener[] iMqttMessageListenerArr) {
        int[] iArr = new int[strArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = 1;
        }
        subscribe(strArr, iArr, iMqttMessageListenerArr);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void subscribe(String str, int i, IMqttMessageListener iMqttMessageListener) {
        subscribe(new String[]{str}, new int[]{i}, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void subscribe(String[] strArr, int[] iArr, IMqttMessageListener[] iMqttMessageListenerArr) {
        subscribe(strArr, iArr);
        for (int i = 0; i < strArr.length; i++) {
            this.aClient.comms.setMessageListener(strArr[i], iMqttMessageListenerArr[i]);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public IMqttToken subscribeWithResponse(String str) {
        return subscribeWithResponse(new String[]{str}, new int[]{1});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public IMqttToken subscribeWithResponse(String str, IMqttMessageListener iMqttMessageListener) {
        return subscribeWithResponse(new String[]{str}, new int[]{1}, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public IMqttToken subscribeWithResponse(String str, int i) {
        return subscribeWithResponse(new String[]{str}, new int[]{i});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public IMqttToken subscribeWithResponse(String str, int i, IMqttMessageListener iMqttMessageListener) {
        return subscribeWithResponse(new String[]{str}, new int[]{i}, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public IMqttToken subscribeWithResponse(String[] strArr) {
        int[] iArr = new int[strArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = 1;
        }
        return subscribeWithResponse(strArr, iArr);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public IMqttToken subscribeWithResponse(String[] strArr, IMqttMessageListener[] iMqttMessageListenerArr) {
        int[] iArr = new int[strArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = 1;
        }
        return subscribeWithResponse(strArr, iArr, iMqttMessageListenerArr);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public IMqttToken subscribeWithResponse(String[] strArr, int[] iArr) {
        IMqttToken subscribe = this.aClient.subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null);
        subscribe.waitForCompletion(getTimeToWait());
        return subscribe;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public IMqttToken subscribeWithResponse(String[] strArr, int[] iArr, IMqttMessageListener[] iMqttMessageListenerArr) {
        IMqttToken subscribeWithResponse = subscribeWithResponse(strArr, iArr);
        for (int i = 0; i < strArr.length; i++) {
            this.aClient.comms.setMessageListener(strArr[i], iMqttMessageListenerArr[i]);
        }
        return subscribeWithResponse;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void unsubscribe(String str) {
        unsubscribe(new String[]{str});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void unsubscribe(String[] strArr) {
        this.aClient.unsubscribe(strArr, (Object) null, (IMqttActionListener) null).waitForCompletion(getTimeToWait());
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void publish(String str, byte[] bArr, int i, boolean z) {
        MqttMessage mqttMessage = new MqttMessage(bArr);
        mqttMessage.setQos(i);
        mqttMessage.setRetained(z);
        publish(str, mqttMessage);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void publish(String str, MqttMessage mqttMessage) {
        this.aClient.publish(str, mqttMessage, (Object) null, (IMqttActionListener) null).waitForCompletion(getTimeToWait());
    }

    public void setTimeToWait(long j) {
        if (!(j >= -1)) {
            throw new IllegalArgumentException();
        }
        this.timeToWait = j;
    }

    public long getTimeToWait() {
        return this.timeToWait;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void close() {
        this.aClient.close(false);
    }

    public void close(boolean z) {
        this.aClient.close(z);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public String getClientId() {
        return this.aClient.getClientId();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public IMqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.aClient.getPendingDeliveryTokens();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public String getServerURI() {
        return this.aClient.getServerURI();
    }

    public String getCurrentServerURI() {
        return this.aClient.getCurrentServerURI();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public MqttTopic getTopic(String str) {
        return this.aClient.getTopic(str);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public boolean isConnected() {
        return this.aClient.isConnected();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void setCallback(MqttCallback mqttCallback) {
        this.aClient.setCallback(mqttCallback);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void setManualAcks(boolean z) {
        this.aClient.setManualAcks(z);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void messageArrivedComplete(int i, int i2) {
        this.aClient.messageArrivedComplete(i, i2);
    }

    public static String generateClientId() {
        return MqttAsyncClient.generateClientId();
    }

    public void reconnect() {
        this.aClient.reconnect();
    }

    public Debug getDebug() {
        return this.aClient.getDebug();
    }
}
