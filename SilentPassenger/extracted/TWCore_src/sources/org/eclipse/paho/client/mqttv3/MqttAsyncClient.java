package org.eclipse.paho.client.mqttv3;

import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.internal.ConnectActionListener;
import org.eclipse.paho.client.mqttv3.internal.DisconnectedMessageBuffer;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import org.eclipse.paho.client.mqttv3.internal.NetworkModule;
import org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import org.eclipse.paho.client.mqttv3.internal.websocket.WebSocketNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.websocket.WebSocketSecureNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSubscribe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttUnsubscribe;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.eclipse.paho.client.mqttv3.util.Debug;

/* loaded from: classes.dex */
public class MqttAsyncClient implements IMqttAsyncClient {
    private static final String CLIENT_ID_PREFIX = "paho";
    private static final long DISCONNECT_TIMEOUT = 10000;
    private static final char MAX_HIGH_SURROGATE = 56319;
    private static final char MIN_HIGH_SURROGATE = 55296;
    private static final long QUIESCE_TIMEOUT = 30000;
    private String clientId;
    protected ClientComms comms;
    private MqttConnectOptions connOpts;
    private ScheduledExecutorService executorService;
    private MqttCallback mqttCallback;
    private MqttClientPersistence persistence;
    private Timer reconnectTimer;
    private boolean reconnecting;
    private String serverURI;
    private Hashtable topics;
    private Object userContext;
    private static final String CLASS_NAME = MqttAsyncClient.class.getName();
    private static final Logger log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    private static int reconnectDelay = 1000;
    private static Object clientLock = new Object();

    public MqttAsyncClient(String str, String str2) {
        this(str, str2, new MqttDefaultFilePersistence());
    }

    public MqttAsyncClient(String str, String str2, MqttClientPersistence mqttClientPersistence) {
        this(str, str2, mqttClientPersistence, new TimerPingSender());
    }

    public MqttAsyncClient(String str, String str2, MqttClientPersistence mqttClientPersistence, MqttPingSender mqttPingSender) {
        this(str, str2, mqttClientPersistence, mqttPingSender, null);
    }

    public MqttAsyncClient(String str, String str2, MqttClientPersistence mqttClientPersistence, MqttPingSender mqttPingSender, ScheduledExecutorService scheduledExecutorService) {
        this.reconnecting = false;
        log.setResourceName(str2);
        if (str2 == null) {
            throw new IllegalArgumentException("Null clientId");
        }
        int i = 0;
        int i2 = 0;
        while (i < str2.length() - 1) {
            if (Character_isHighSurrogate(str2.charAt(i))) {
                i++;
            }
            i2++;
            i++;
        }
        if (i2 > 65535) {
            throw new IllegalArgumentException("ClientId longer than 65535 characters");
        }
        MqttConnectOptions.validateURI(str);
        this.serverURI = str;
        this.clientId = str2;
        this.persistence = mqttClientPersistence;
        if (this.persistence == null) {
            this.persistence = new MemoryPersistence();
        }
        this.executorService = scheduledExecutorService;
        if (this.executorService == null) {
            this.executorService = Executors.newScheduledThreadPool(10);
        }
        log.fine(CLASS_NAME, "MqttAsyncClient", "101", new Object[]{str2, str, mqttClientPersistence});
        this.persistence.open(str2, str);
        this.comms = new ClientComms(this, this.persistence, mqttPingSender, this.executorService);
        this.persistence.close();
        this.topics = new Hashtable();
    }

    protected static boolean Character_isHighSurrogate(char c) {
        return c >= 55296 && c <= 56319;
    }

    protected NetworkModule[] createNetworkModules(String str, MqttConnectOptions mqttConnectOptions) {
        log.fine(CLASS_NAME, "createNetworkModules", "116", new Object[]{str});
        String[] serverURIs = mqttConnectOptions.getServerURIs();
        if (serverURIs == null) {
            serverURIs = new String[]{str};
        } else if (serverURIs.length == 0) {
            serverURIs = new String[]{str};
        }
        NetworkModule[] networkModuleArr = new NetworkModule[serverURIs.length];
        for (int i = 0; i < serverURIs.length; i++) {
            networkModuleArr[i] = createNetworkModule(serverURIs[i], mqttConnectOptions);
        }
        log.fine(CLASS_NAME, "createNetworkModules", "108");
        return networkModuleArr;
    }

    private NetworkModule createNetworkModule(String str, MqttConnectOptions mqttConnectOptions) {
        SocketFactory createSocketFactory;
        SSLSocketFactoryFactory sSLSocketFactoryFactory;
        String[] enabledCipherSuites;
        SocketFactory socketFactory;
        SSLSocketFactoryFactory sSLSocketFactoryFactory2;
        String[] enabledCipherSuites2;
        log.fine(CLASS_NAME, "createNetworkModule", "115", new Object[]{str});
        SocketFactory socketFactory2 = mqttConnectOptions.getSocketFactory();
        int validateURI = MqttConnectOptions.validateURI(str);
        try {
            URI uri = new URI(str);
            if (uri.getHost() == null && str.contains("_")) {
                try {
                    Field declaredField = URI.class.getDeclaredField("host");
                    declaredField.setAccessible(true);
                    declaredField.set(uri, getHostName(str.substring(uri.getScheme().length() + 3)));
                } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
                    throw ExceptionHelper.createMqttException(e.getCause());
                }
            }
            String host = uri.getHost();
            int port = uri.getPort();
            switch (validateURI) {
                case MqttConnectOptions.MQTT_VERSION_DEFAULT /* 0 */:
                    int i = port != -1 ? port : 1883;
                    if (socketFactory2 == null) {
                        socketFactory2 = SocketFactory.getDefault();
                    } else if (socketFactory2 instanceof SSLSocketFactory) {
                        throw ExceptionHelper.createMqttException(32105);
                    }
                    TCPNetworkModule tCPNetworkModule = new TCPNetworkModule(socketFactory2, host, i, this.clientId);
                    tCPNetworkModule.setConnectTimeout(mqttConnectOptions.getConnectionTimeout());
                    return tCPNetworkModule;
                case Logger.SEVERE /* 1 */:
                    int i2 = port != -1 ? port : 8883;
                    if (socketFactory2 == null) {
                        SSLSocketFactoryFactory sSLSocketFactoryFactory3 = new SSLSocketFactoryFactory();
                        Properties sSLProperties = mqttConnectOptions.getSSLProperties();
                        if (sSLProperties != null) {
                            sSLSocketFactoryFactory3.initialize(sSLProperties, null);
                        }
                        sSLSocketFactoryFactory2 = sSLSocketFactoryFactory3;
                        socketFactory2 = sSLSocketFactoryFactory3.createSocketFactory(null);
                    } else {
                        if (!(socketFactory2 instanceof SSLSocketFactory)) {
                            throw ExceptionHelper.createMqttException(32105);
                        }
                        sSLSocketFactoryFactory2 = null;
                    }
                    SSLNetworkModule sSLNetworkModule = new SSLNetworkModule((SSLSocketFactory) socketFactory2, host, i2, this.clientId);
                    sSLNetworkModule.setSSLhandshakeTimeout(mqttConnectOptions.getConnectionTimeout());
                    sSLNetworkModule.setSSLHostnameVerifier(mqttConnectOptions.getSSLHostnameVerifier());
                    if (sSLSocketFactoryFactory2 != null && (enabledCipherSuites2 = sSLSocketFactoryFactory2.getEnabledCipherSuites(null)) != null) {
                        sSLNetworkModule.setEnabledCiphers(enabledCipherSuites2);
                        return sSLNetworkModule;
                    }
                    return sSLNetworkModule;
                case Logger.WARNING /* 2 */:
                default:
                    log.fine(CLASS_NAME, "createNetworkModule", "119", new Object[]{str});
                    return null;
                case 3:
                    int i3 = port != -1 ? port : 80;
                    if (socketFactory2 == null) {
                        socketFactory = SocketFactory.getDefault();
                    } else {
                        if (socketFactory2 instanceof SSLSocketFactory) {
                            throw ExceptionHelper.createMqttException(32105);
                        }
                        socketFactory = socketFactory2;
                    }
                    WebSocketNetworkModule webSocketNetworkModule = new WebSocketNetworkModule(socketFactory, str, host, i3, this.clientId);
                    webSocketNetworkModule.setConnectTimeout(mqttConnectOptions.getConnectionTimeout());
                    return webSocketNetworkModule;
                case 4:
                    int i4 = port != -1 ? port : 443;
                    if (socketFactory2 == null) {
                        SSLSocketFactoryFactory sSLSocketFactoryFactory4 = new SSLSocketFactoryFactory();
                        Properties sSLProperties2 = mqttConnectOptions.getSSLProperties();
                        if (sSLProperties2 != null) {
                            sSLSocketFactoryFactory4.initialize(sSLProperties2, null);
                        }
                        createSocketFactory = sSLSocketFactoryFactory4.createSocketFactory(null);
                        sSLSocketFactoryFactory = sSLSocketFactoryFactory4;
                    } else {
                        if (!(socketFactory2 instanceof SSLSocketFactory)) {
                            throw ExceptionHelper.createMqttException(32105);
                        }
                        sSLSocketFactoryFactory = null;
                        createSocketFactory = socketFactory2;
                    }
                    WebSocketSecureNetworkModule webSocketSecureNetworkModule = new WebSocketSecureNetworkModule((SSLSocketFactory) createSocketFactory, str, host, i4, this.clientId);
                    webSocketSecureNetworkModule.setSSLhandshakeTimeout(mqttConnectOptions.getConnectionTimeout());
                    if (sSLSocketFactoryFactory != null && (enabledCipherSuites = sSLSocketFactoryFactory.getEnabledCipherSuites(null)) != null) {
                        webSocketSecureNetworkModule.setEnabledCiphers(enabledCipherSuites);
                        return webSocketSecureNetworkModule;
                    }
                    return webSocketSecureNetworkModule;
            }
        } catch (URISyntaxException e2) {
            throw new IllegalArgumentException("Malformed URI: " + str + ", " + e2.getMessage());
        }
    }

    private String getHostName(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf == -1) {
            indexOf = str.indexOf(47);
        }
        if (indexOf == -1) {
            indexOf = str.length();
        }
        return str.substring(0, indexOf);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect(Object obj, IMqttActionListener iMqttActionListener) {
        return connect(new MqttConnectOptions(), obj, iMqttActionListener);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect() {
        return connect(null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect(MqttConnectOptions mqttConnectOptions) {
        return connect(mqttConnectOptions, null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect(MqttConnectOptions mqttConnectOptions, Object obj, IMqttActionListener iMqttActionListener) {
        if (this.comms.isConnected()) {
            throw ExceptionHelper.createMqttException(32100);
        }
        if (this.comms.isConnecting()) {
            throw new MqttException(32110);
        }
        if (this.comms.isDisconnecting()) {
            throw new MqttException(32102);
        }
        if (this.comms.isClosed()) {
            throw new MqttException(32111);
        }
        MqttConnectOptions mqttConnectOptions2 = mqttConnectOptions != null ? mqttConnectOptions : new MqttConnectOptions();
        this.connOpts = mqttConnectOptions2;
        this.userContext = obj;
        boolean isAutomaticReconnect = mqttConnectOptions2.isAutomaticReconnect();
        Logger logger = log;
        String str = CLASS_NAME;
        Object[] objArr = new Object[8];
        objArr[0] = Boolean.valueOf(mqttConnectOptions2.isCleanSession());
        objArr[1] = new Integer(mqttConnectOptions2.getConnectionTimeout());
        objArr[2] = new Integer(mqttConnectOptions2.getKeepAliveInterval());
        objArr[3] = mqttConnectOptions2.getUserName();
        objArr[4] = mqttConnectOptions2.getPassword() != null ? "[notnull]" : "[null]";
        objArr[5] = mqttConnectOptions2.getWillMessage() != null ? "[notnull]" : "[null]";
        objArr[6] = obj;
        objArr[7] = iMqttActionListener;
        logger.fine(str, "connect", "103", objArr);
        this.comms.setNetworkModules(createNetworkModules(this.serverURI, mqttConnectOptions2));
        this.comms.setReconnectCallback(new MqttReconnectCallback(isAutomaticReconnect));
        MqttToken mqttToken = new MqttToken(getClientId());
        ConnectActionListener connectActionListener = new ConnectActionListener(this, this.persistence, this.comms, mqttConnectOptions2, mqttToken, obj, iMqttActionListener, this.reconnecting);
        mqttToken.setActionCallback(connectActionListener);
        mqttToken.setUserContext(this);
        if (this.mqttCallback instanceof MqttCallbackExtended) {
            connectActionListener.setMqttCallbackExtended((MqttCallbackExtended) this.mqttCallback);
        }
        this.comms.setNetworkModuleIndex(0);
        connectActionListener.connect();
        return mqttToken;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect(Object obj, IMqttActionListener iMqttActionListener) {
        return disconnect(QUIESCE_TIMEOUT, obj, iMqttActionListener);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect() {
        return disconnect(null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect(long j) {
        return disconnect(j, null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect(long j, Object obj, IMqttActionListener iMqttActionListener) {
        log.fine(CLASS_NAME, "disconnect", "104", new Object[]{new Long(j), obj, iMqttActionListener});
        MqttToken mqttToken = new MqttToken(getClientId());
        mqttToken.setActionCallback(iMqttActionListener);
        mqttToken.setUserContext(obj);
        try {
            this.comms.disconnect(new MqttDisconnect(), j, mqttToken);
            log.fine(CLASS_NAME, "disconnect", "108");
            return mqttToken;
        } catch (MqttException e) {
            log.fine(CLASS_NAME, "disconnect", "105", null, e);
            throw e;
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void disconnectForcibly() {
        disconnectForcibly(QUIESCE_TIMEOUT, DISCONNECT_TIMEOUT);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void disconnectForcibly(long j) {
        disconnectForcibly(QUIESCE_TIMEOUT, j);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void disconnectForcibly(long j, long j2) {
        this.comms.disconnectForcibly(j, j2);
    }

    public void disconnectForcibly(long j, long j2, boolean z) {
        this.comms.disconnectForcibly(j, j2, z);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public boolean isConnected() {
        return this.comms.isConnected();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public String getClientId() {
        return this.clientId;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public String getServerURI() {
        return this.serverURI;
    }

    public String getCurrentServerURI() {
        return this.comms.getNetworkModules()[this.comms.getNetworkModuleIndex()].getServerURI();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MqttTopic getTopic(String str) {
        MqttTopic.validate(str, false);
        MqttTopic mqttTopic = (MqttTopic) this.topics.get(str);
        if (mqttTopic == null) {
            MqttTopic mqttTopic2 = new MqttTopic(str, this.comms);
            this.topics.put(str, mqttTopic2);
            return mqttTopic2;
        }
        return mqttTopic;
    }

    public IMqttToken checkPing(Object obj, IMqttActionListener iMqttActionListener) {
        log.fine(CLASS_NAME, "ping", "117");
        MqttToken checkForActivity = this.comms.checkForActivity();
        log.fine(CLASS_NAME, "ping", "118");
        return checkForActivity;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i, Object obj, IMqttActionListener iMqttActionListener) {
        return subscribe(new String[]{str}, new int[]{i}, obj, iMqttActionListener);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i) {
        return subscribe(new String[]{str}, new int[]{i}, (Object) null, (IMqttActionListener) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String[] strArr, int[] iArr) {
        return subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener) {
        if (strArr.length != iArr.length) {
            throw new IllegalArgumentException();
        }
        for (String str : strArr) {
            this.comms.removeMessageListener(str);
        }
        if (log.isLoggable(5)) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < strArr.length; i++) {
                if (i > 0) {
                    stringBuffer.append(", ");
                }
                stringBuffer.append("topic=").append(strArr[i]).append(" qos=").append(iArr[i]);
                MqttTopic.validate(strArr[i], true);
            }
            log.fine(CLASS_NAME, "subscribe", "106", new Object[]{stringBuffer.toString(), obj, iMqttActionListener});
        }
        MqttToken mqttToken = new MqttToken(getClientId());
        mqttToken.setActionCallback(iMqttActionListener);
        mqttToken.setUserContext(obj);
        mqttToken.internalTok.setTopics(strArr);
        this.comms.sendNoWait(new MqttSubscribe(strArr, iArr), mqttToken);
        log.fine(CLASS_NAME, "subscribe", "109");
        return mqttToken;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i, Object obj, IMqttActionListener iMqttActionListener, IMqttMessageListener iMqttMessageListener) {
        return subscribe(new String[]{str}, new int[]{i}, obj, iMqttActionListener, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i, IMqttMessageListener iMqttMessageListener) {
        return subscribe(new String[]{str}, new int[]{i}, (Object) null, (IMqttActionListener) null, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String[] strArr, int[] iArr, IMqttMessageListener[] iMqttMessageListenerArr) {
        return subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null, iMqttMessageListenerArr);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener, IMqttMessageListener[] iMqttMessageListenerArr) {
        if (iMqttMessageListenerArr.length != iArr.length || iArr.length != strArr.length) {
            throw new IllegalArgumentException();
        }
        IMqttToken subscribe = subscribe(strArr, iArr, obj, iMqttActionListener);
        for (int i = 0; i < strArr.length; i++) {
            this.comms.setMessageListener(strArr[i], iMqttMessageListenerArr[i]);
        }
        return subscribe;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String str, Object obj, IMqttActionListener iMqttActionListener) {
        return unsubscribe(new String[]{str}, obj, iMqttActionListener);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String str) {
        return unsubscribe(new String[]{str}, (Object) null, (IMqttActionListener) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String[] strArr) {
        return unsubscribe(strArr, (Object) null, (IMqttActionListener) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String[] strArr, Object obj, IMqttActionListener iMqttActionListener) {
        if (log.isLoggable(5)) {
            String str = "";
            int i = 0;
            while (i < strArr.length) {
                if (i > 0) {
                    str = String.valueOf(str) + ", ";
                }
                String str2 = String.valueOf(str) + strArr[i];
                i++;
                str = str2;
            }
            log.fine(CLASS_NAME, "unsubscribe", "107", new Object[]{str, obj, iMqttActionListener});
        }
        for (String str3 : strArr) {
            MqttTopic.validate(str3, true);
        }
        for (String str4 : strArr) {
            this.comms.removeMessageListener(str4);
        }
        MqttToken mqttToken = new MqttToken(getClientId());
        mqttToken.setActionCallback(iMqttActionListener);
        mqttToken.setUserContext(obj);
        mqttToken.internalTok.setTopics(strArr);
        this.comms.sendNoWait(new MqttUnsubscribe(strArr), mqttToken);
        log.fine(CLASS_NAME, "unsubscribe", "110");
        return mqttToken;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void setCallback(MqttCallback mqttCallback) {
        this.mqttCallback = mqttCallback;
        this.comms.setCallback(mqttCallback);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void setManualAcks(boolean z) {
        this.comms.setManualAcks(z);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void messageArrivedComplete(int i, int i2) {
        this.comms.messageArrivedComplete(i, i2);
    }

    public static String generateClientId() {
        return CLIENT_ID_PREFIX + System.nanoTime();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.comms.getPendingDeliveryTokens();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z, Object obj, IMqttActionListener iMqttActionListener) {
        MqttMessage mqttMessage = new MqttMessage(bArr);
        mqttMessage.setQos(i);
        mqttMessage.setRetained(z);
        return publish(str, mqttMessage, obj, iMqttActionListener);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z) {
        return publish(str, bArr, i, z, null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage) {
        return publish(str, mqttMessage, (Object) null, (IMqttActionListener) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage, Object obj, IMqttActionListener iMqttActionListener) {
        log.fine(CLASS_NAME, "publish", "111", new Object[]{str, obj, iMqttActionListener});
        MqttTopic.validate(str, false);
        MqttDeliveryToken mqttDeliveryToken = new MqttDeliveryToken(getClientId());
        mqttDeliveryToken.setActionCallback(iMqttActionListener);
        mqttDeliveryToken.setUserContext(obj);
        mqttDeliveryToken.setMessage(mqttMessage);
        mqttDeliveryToken.internalTok.setTopics(new String[]{str});
        this.comms.sendNoWait(new MqttPublish(str, mqttMessage), mqttDeliveryToken);
        log.fine(CLASS_NAME, "publish", "112");
        return mqttDeliveryToken;
    }

    public void reconnect() {
        log.fine(CLASS_NAME, "reconnect", "500", new Object[]{this.clientId});
        if (this.comms.isConnected()) {
            throw ExceptionHelper.createMqttException(32100);
        }
        if (this.comms.isConnecting()) {
            throw new MqttException(32110);
        }
        if (this.comms.isDisconnecting()) {
            throw new MqttException(32102);
        }
        if (this.comms.isClosed()) {
            throw new MqttException(32111);
        }
        stopReconnectCycle();
        attemptReconnect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void attemptReconnect() {
        log.fine(CLASS_NAME, "attemptReconnect", "500", new Object[]{this.clientId});
        try {
            connect(this.connOpts, this.userContext, new MqttReconnectActionListener("attemptReconnect"));
        } catch (MqttSecurityException e) {
            log.fine(CLASS_NAME, "attemptReconnect", "804", null, e);
        } catch (MqttException e2) {
            log.fine(CLASS_NAME, "attemptReconnect", "804", null, e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startReconnectCycle() {
        log.fine(CLASS_NAME, "startReconnectCycle", "503", new Object[]{this.clientId, new Long(reconnectDelay)});
        this.reconnectTimer = new Timer("MQTT Reconnect: " + this.clientId);
        this.reconnectTimer.schedule(new ReconnectTask(this, null), reconnectDelay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopReconnectCycle() {
        log.fine(CLASS_NAME, "stopReconnectCycle", "504", new Object[]{this.clientId});
        synchronized (clientLock) {
            if (this.connOpts.isAutomaticReconnect()) {
                if (this.reconnectTimer != null) {
                    this.reconnectTimer.cancel();
                    this.reconnectTimer = null;
                }
                reconnectDelay = 1000;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ReconnectTask extends TimerTask {
        private static final String methodName = "ReconnectTask.run";

        private ReconnectTask() {
        }

        /* synthetic */ ReconnectTask(MqttAsyncClient mqttAsyncClient, ReconnectTask reconnectTask) {
            this();
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            MqttAsyncClient.log.fine(MqttAsyncClient.CLASS_NAME, methodName, "506");
            MqttAsyncClient.this.attemptReconnect();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class MqttReconnectCallback implements MqttCallbackExtended {
        final boolean automaticReconnect;

        MqttReconnectCallback(boolean z) {
            this.automaticReconnect = z;
        }

        @Override // org.eclipse.paho.client.mqttv3.MqttCallback
        public void connectionLost(Throwable th) {
            if (this.automaticReconnect) {
                MqttAsyncClient.this.comms.setRestingState(true);
                MqttAsyncClient.this.reconnecting = true;
                MqttAsyncClient.this.startReconnectCycle();
            }
        }

        @Override // org.eclipse.paho.client.mqttv3.MqttCallback
        public void messageArrived(String str, MqttMessage mqttMessage) {
        }

        @Override // org.eclipse.paho.client.mqttv3.MqttCallback
        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        }

        @Override // org.eclipse.paho.client.mqttv3.MqttCallbackExtended
        public void connectComplete(boolean z, String str) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class MqttReconnectActionListener implements IMqttActionListener {
        final String methodName;

        MqttReconnectActionListener(String str) {
            this.methodName = str;
        }

        @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
        public void onSuccess(IMqttToken iMqttToken) {
            MqttAsyncClient.log.fine(MqttAsyncClient.CLASS_NAME, this.methodName, "501", new Object[]{iMqttToken.getClient().getClientId()});
            MqttAsyncClient.this.comms.setRestingState(false);
            MqttAsyncClient.this.stopReconnectCycle();
        }

        @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
        public void onFailure(IMqttToken iMqttToken, Throwable th) {
            MqttAsyncClient.log.fine(MqttAsyncClient.CLASS_NAME, this.methodName, "502", new Object[]{iMqttToken.getClient().getClientId()});
            if (MqttAsyncClient.reconnectDelay < 128000) {
                MqttAsyncClient.reconnectDelay *= 2;
            }
            rescheduleReconnectCycle(MqttAsyncClient.reconnectDelay);
        }

        private void rescheduleReconnectCycle(int i) {
            MqttAsyncClient.log.fine(MqttAsyncClient.CLASS_NAME, String.valueOf(this.methodName) + ":rescheduleReconnectCycle", "505", new Object[]{MqttAsyncClient.this.clientId, String.valueOf(MqttAsyncClient.reconnectDelay)});
            synchronized (MqttAsyncClient.clientLock) {
                if (MqttAsyncClient.this.connOpts.isAutomaticReconnect()) {
                    if (MqttAsyncClient.this.reconnectTimer != null) {
                        MqttAsyncClient.this.reconnectTimer.schedule(new ReconnectTask(MqttAsyncClient.this, null), i);
                    } else {
                        MqttAsyncClient.reconnectDelay = i;
                        MqttAsyncClient.this.startReconnectCycle();
                    }
                }
            }
        }
    }

    public void setBufferOpts(DisconnectedBufferOptions disconnectedBufferOptions) {
        this.comms.setDisconnectedMessageBuffer(new DisconnectedMessageBuffer(disconnectedBufferOptions));
    }

    public int getBufferedMessageCount() {
        return this.comms.getBufferedMessageCount();
    }

    public MqttMessage getBufferedMessage(int i) {
        return this.comms.getBufferedMessage(i);
    }

    public void deleteBufferedMessage(int i) {
        this.comms.deleteBufferedMessage(i);
    }

    public int getInFlightMessageCount() {
        return this.comms.getActualInFlight();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void close() {
        close(false);
    }

    public void close(boolean z) {
        log.fine(CLASS_NAME, "close", "113");
        this.comms.close(z);
        log.fine(CLASS_NAME, "close", "114");
    }

    public Debug getDebug() {
        return new Debug(this.clientId, this.comms);
    }
}
