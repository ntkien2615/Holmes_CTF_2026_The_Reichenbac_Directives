package org.eclipse.paho.client.mqttv3.internal;

import java.io.EOFException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttPingSender;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPingReq;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPingResp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubComp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubRec;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubRel;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSuback;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSubscribe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttUnsubAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttUnsubscribe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* loaded from: classes.dex */
public class ClientState {
    private static final int MAX_MSG_ID = 65535;
    private static final int MIN_MSG_ID = 1;
    private static final String PERSISTENCE_CONFIRMED_PREFIX = "sc-";
    private static final String PERSISTENCE_RECEIVED_PREFIX = "r-";
    private static final String PERSISTENCE_SENT_BUFFERED_PREFIX = "sb-";
    private static final String PERSISTENCE_SENT_PREFIX = "s-";
    private int actualInFlight;
    private CommsCallback callback;
    private boolean cleanSession;
    private ClientComms clientComms;
    private int inFlightPubRels;
    private Hashtable inUseMsgIds;
    private Hashtable inboundQoS2;
    private long keepAlive;
    private Hashtable outboundQoS0;
    private Hashtable outboundQoS1;
    private Hashtable outboundQoS2;
    private volatile Vector pendingFlows;
    private volatile Vector pendingMessages;
    private MqttClientPersistence persistence;
    private MqttWireMessage pingCommand;
    private MqttPingSender pingSender;
    private CommsTokenStore tokenStore;
    private static final String CLASS_NAME = ClientState.class.getName();
    private static final Logger log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    private int nextMsgId = 0;
    private int maxInflight = 0;
    private Object queueLock = new Object();
    private Object quiesceLock = new Object();
    private boolean quiescing = false;
    private long lastOutboundActivity = 0;
    private long lastInboundActivity = 0;
    private long lastPing = 0;
    private Object pingOutstandingLock = new Object();
    private int pingOutstanding = 0;
    private boolean connected = false;

    /* JADX INFO: Access modifiers changed from: protected */
    public ClientState(MqttClientPersistence mqttClientPersistence, CommsTokenStore commsTokenStore, CommsCallback commsCallback, ClientComms clientComms, MqttPingSender mqttPingSender) {
        this.clientComms = null;
        this.callback = null;
        this.actualInFlight = 0;
        this.inFlightPubRels = 0;
        this.outboundQoS2 = null;
        this.outboundQoS1 = null;
        this.outboundQoS0 = null;
        this.inboundQoS2 = null;
        this.pingSender = null;
        log.setResourceName(clientComms.getClient().getClientId());
        log.finer(CLASS_NAME, "<Init>", "");
        this.inUseMsgIds = new Hashtable();
        this.pendingFlows = new Vector();
        this.outboundQoS2 = new Hashtable();
        this.outboundQoS1 = new Hashtable();
        this.outboundQoS0 = new Hashtable();
        this.inboundQoS2 = new Hashtable();
        this.pingCommand = new MqttPingReq();
        this.inFlightPubRels = 0;
        this.actualInFlight = 0;
        this.persistence = mqttClientPersistence;
        this.callback = commsCallback;
        this.tokenStore = commsTokenStore;
        this.clientComms = clientComms;
        this.pingSender = mqttPingSender;
        restoreState();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setMaxInflight(int i) {
        this.maxInflight = i;
        this.pendingMessages = new Vector(this.maxInflight);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setKeepAliveSecs(long j) {
        this.keepAlive = 1000 * j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long getKeepAlive() {
        return this.keepAlive;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setCleanSession(boolean z) {
        this.cleanSession = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean getCleanSession() {
        return this.cleanSession;
    }

    private String getSendPersistenceKey(MqttWireMessage mqttWireMessage) {
        return PERSISTENCE_SENT_PREFIX + mqttWireMessage.getMessageId();
    }

    private String getSendConfirmPersistenceKey(MqttWireMessage mqttWireMessage) {
        return PERSISTENCE_CONFIRMED_PREFIX + mqttWireMessage.getMessageId();
    }

    private String getReceivedPersistenceKey(MqttWireMessage mqttWireMessage) {
        return PERSISTENCE_RECEIVED_PREFIX + mqttWireMessage.getMessageId();
    }

    private String getReceivedPersistenceKey(int i) {
        return PERSISTENCE_RECEIVED_PREFIX + i;
    }

    private String getSendBufferedPersistenceKey(MqttWireMessage mqttWireMessage) {
        return PERSISTENCE_SENT_BUFFERED_PREFIX + mqttWireMessage.getMessageId();
    }

    protected void clearState() {
        log.fine(CLASS_NAME, "clearState", ">");
        this.persistence.clear();
        this.inUseMsgIds.clear();
        this.pendingMessages.clear();
        this.pendingFlows.clear();
        this.outboundQoS2.clear();
        this.outboundQoS1.clear();
        this.outboundQoS0.clear();
        this.inboundQoS2.clear();
        this.tokenStore.clear();
    }

    private MqttWireMessage restoreMessage(String str, MqttPersistable mqttPersistable) {
        MqttWireMessage mqttWireMessage;
        try {
            mqttWireMessage = MqttWireMessage.createWireMessage(mqttPersistable);
        } catch (MqttException e) {
            log.fine(CLASS_NAME, "restoreMessage", "602", new Object[]{str}, e);
            if (e.getCause() instanceof EOFException) {
                if (str == null) {
                    mqttWireMessage = null;
                } else {
                    this.persistence.remove(str);
                    mqttWireMessage = null;
                }
            } else {
                throw e;
            }
        }
        log.fine(CLASS_NAME, "restoreMessage", "601", new Object[]{str, mqttWireMessage});
        return mqttWireMessage;
    }

    private void insertInOrder(Vector vector, MqttWireMessage mqttWireMessage) {
        int messageId = mqttWireMessage.getMessageId();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < vector.size()) {
                if (((MqttWireMessage) vector.elementAt(i2)).getMessageId() <= messageId) {
                    i = i2 + 1;
                } else {
                    vector.insertElementAt(mqttWireMessage, i2);
                    return;
                }
            } else {
                vector.addElement(mqttWireMessage);
                return;
            }
        }
    }

    private Vector reOrder(Vector vector) {
        int i;
        int i2;
        Vector vector2 = new Vector();
        if (vector.size() == 0) {
            return vector2;
        }
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i3 < vector.size()) {
            int messageId = ((MqttWireMessage) vector.elementAt(i3)).getMessageId();
            if (messageId - i6 <= i5) {
                i2 = i4;
                i = i5;
            } else {
                i = messageId - i6;
                i2 = i3;
            }
            i3++;
            i5 = i;
            i6 = messageId;
            i4 = i2;
        }
        if (((MqttWireMessage) vector.elementAt(0)).getMessageId() + (MAX_MSG_ID - i6) > i5) {
            i4 = 0;
        }
        for (int i7 = i4; i7 < vector.size(); i7++) {
            vector2.addElement(vector.elementAt(i7));
        }
        for (int i8 = 0; i8 < i4; i8++) {
            vector2.addElement(vector.elementAt(i8));
        }
        return vector2;
    }

    protected void restoreState() {
        Enumeration keys = this.persistence.keys();
        int i = this.nextMsgId;
        Vector vector = new Vector();
        log.fine(CLASS_NAME, "restoreState", "600");
        int i2 = i;
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            MqttWireMessage restoreMessage = restoreMessage(str, this.persistence.get(str));
            if (restoreMessage != null) {
                if (str.startsWith(PERSISTENCE_RECEIVED_PREFIX)) {
                    log.fine(CLASS_NAME, "restoreState", "604", new Object[]{str, restoreMessage});
                    this.inboundQoS2.put(new Integer(restoreMessage.getMessageId()), restoreMessage);
                } else if (str.startsWith(PERSISTENCE_SENT_PREFIX)) {
                    MqttPublish mqttPublish = (MqttPublish) restoreMessage;
                    int max = Math.max(mqttPublish.getMessageId(), i2);
                    if (this.persistence.containsKey(getSendConfirmPersistenceKey(mqttPublish))) {
                        MqttPubRel mqttPubRel = (MqttPubRel) restoreMessage(str, this.persistence.get(getSendConfirmPersistenceKey(mqttPublish)));
                        if (mqttPubRel != null) {
                            log.fine(CLASS_NAME, "restoreState", "605", new Object[]{str, restoreMessage});
                            this.outboundQoS2.put(new Integer(mqttPubRel.getMessageId()), mqttPubRel);
                        } else {
                            log.fine(CLASS_NAME, "restoreState", "606", new Object[]{str, restoreMessage});
                        }
                    } else {
                        mqttPublish.setDuplicate(true);
                        if (mqttPublish.getMessage().getQos() == 2) {
                            log.fine(CLASS_NAME, "restoreState", "607", new Object[]{str, restoreMessage});
                            this.outboundQoS2.put(new Integer(mqttPublish.getMessageId()), mqttPublish);
                        } else {
                            log.fine(CLASS_NAME, "restoreState", "608", new Object[]{str, restoreMessage});
                            this.outboundQoS1.put(new Integer(mqttPublish.getMessageId()), mqttPublish);
                        }
                    }
                    this.tokenStore.restoreToken(mqttPublish).internalTok.setClient(this.clientComms.getClient());
                    this.inUseMsgIds.put(new Integer(mqttPublish.getMessageId()), new Integer(mqttPublish.getMessageId()));
                    i2 = max;
                } else if (str.startsWith(PERSISTENCE_SENT_BUFFERED_PREFIX)) {
                    MqttPublish mqttPublish2 = (MqttPublish) restoreMessage;
                    i2 = Math.max(mqttPublish2.getMessageId(), i2);
                    if (mqttPublish2.getMessage().getQos() == 2) {
                        log.fine(CLASS_NAME, "restoreState", "607", new Object[]{str, restoreMessage});
                        this.outboundQoS2.put(new Integer(mqttPublish2.getMessageId()), mqttPublish2);
                    } else if (mqttPublish2.getMessage().getQos() == 1) {
                        log.fine(CLASS_NAME, "restoreState", "608", new Object[]{str, restoreMessage});
                        this.outboundQoS1.put(new Integer(mqttPublish2.getMessageId()), mqttPublish2);
                    } else {
                        log.fine(CLASS_NAME, "restoreState", "511", new Object[]{str, restoreMessage});
                        this.outboundQoS0.put(new Integer(mqttPublish2.getMessageId()), mqttPublish2);
                        this.persistence.remove(str);
                    }
                    this.tokenStore.restoreToken(mqttPublish2).internalTok.setClient(this.clientComms.getClient());
                    this.inUseMsgIds.put(new Integer(mqttPublish2.getMessageId()), new Integer(mqttPublish2.getMessageId()));
                } else if (str.startsWith(PERSISTENCE_CONFIRMED_PREFIX) && !this.persistence.containsKey(getSendPersistenceKey((MqttPubRel) restoreMessage))) {
                    vector.addElement(str);
                }
            }
        }
        Enumeration elements = vector.elements();
        while (elements.hasMoreElements()) {
            String str2 = (String) elements.nextElement();
            log.fine(CLASS_NAME, "restoreState", "609", new Object[]{str2});
            this.persistence.remove(str2);
        }
        this.nextMsgId = i2;
    }

    private void restoreInflightMessages() {
        this.pendingMessages = new Vector(this.maxInflight);
        this.pendingFlows = new Vector();
        Enumeration keys = this.outboundQoS2.keys();
        while (keys.hasMoreElements()) {
            Object nextElement = keys.nextElement();
            MqttWireMessage mqttWireMessage = (MqttWireMessage) this.outboundQoS2.get(nextElement);
            if (mqttWireMessage instanceof MqttPublish) {
                log.fine(CLASS_NAME, "restoreInflightMessages", "610", new Object[]{nextElement});
                mqttWireMessage.setDuplicate(true);
                insertInOrder(this.pendingMessages, (MqttPublish) mqttWireMessage);
            } else if (mqttWireMessage instanceof MqttPubRel) {
                log.fine(CLASS_NAME, "restoreInflightMessages", "611", new Object[]{nextElement});
                insertInOrder(this.pendingFlows, (MqttPubRel) mqttWireMessage);
            }
        }
        Enumeration keys2 = this.outboundQoS1.keys();
        while (keys2.hasMoreElements()) {
            Object nextElement2 = keys2.nextElement();
            MqttPublish mqttPublish = (MqttPublish) this.outboundQoS1.get(nextElement2);
            mqttPublish.setDuplicate(true);
            log.fine(CLASS_NAME, "restoreInflightMessages", "612", new Object[]{nextElement2});
            insertInOrder(this.pendingMessages, mqttPublish);
        }
        Enumeration keys3 = this.outboundQoS0.keys();
        while (keys3.hasMoreElements()) {
            Object nextElement3 = keys3.nextElement();
            MqttPublish mqttPublish2 = (MqttPublish) this.outboundQoS0.get(nextElement3);
            log.fine(CLASS_NAME, "restoreInflightMessages", "512", new Object[]{nextElement3});
            insertInOrder(this.pendingMessages, mqttPublish2);
        }
        this.pendingFlows = reOrder(this.pendingFlows);
        this.pendingMessages = reOrder(this.pendingMessages);
    }

    public void send(MqttWireMessage mqttWireMessage, MqttToken mqttToken) {
        if (mqttWireMessage.isMessageIdRequired() && mqttWireMessage.getMessageId() == 0) {
            if ((mqttWireMessage instanceof MqttPublish) && ((MqttPublish) mqttWireMessage).getMessage().getQos() != 0) {
                mqttWireMessage.setMessageId(getNextMessageId());
            } else if ((mqttWireMessage instanceof MqttPubAck) || (mqttWireMessage instanceof MqttPubRec) || (mqttWireMessage instanceof MqttPubRel) || (mqttWireMessage instanceof MqttPubComp) || (mqttWireMessage instanceof MqttSubscribe) || (mqttWireMessage instanceof MqttSuback) || (mqttWireMessage instanceof MqttUnsubscribe) || (mqttWireMessage instanceof MqttUnsubAck)) {
                mqttWireMessage.setMessageId(getNextMessageId());
            }
        }
        if (mqttToken != null) {
            try {
                mqttToken.internalTok.setMessageID(mqttWireMessage.getMessageId());
            } catch (Exception e) {
            }
        }
        if (mqttWireMessage instanceof MqttPublish) {
            synchronized (this.queueLock) {
                if (this.actualInFlight >= this.maxInflight) {
                    log.fine(CLASS_NAME, "send", "613", new Object[]{new Integer(this.actualInFlight)});
                    throw new MqttException(32202);
                }
                MqttMessage message = ((MqttPublish) mqttWireMessage).getMessage();
                log.fine(CLASS_NAME, "send", "628", new Object[]{new Integer(mqttWireMessage.getMessageId()), new Integer(message.getQos()), mqttWireMessage});
                switch (message.getQos()) {
                    case 1:
                        this.outboundQoS1.put(new Integer(mqttWireMessage.getMessageId()), mqttWireMessage);
                        this.persistence.put(getSendPersistenceKey(mqttWireMessage), (MqttPublish) mqttWireMessage);
                        break;
                    case Logger.WARNING /* 2 */:
                        this.outboundQoS2.put(new Integer(mqttWireMessage.getMessageId()), mqttWireMessage);
                        this.persistence.put(getSendPersistenceKey(mqttWireMessage), (MqttPublish) mqttWireMessage);
                        break;
                }
                this.tokenStore.saveToken(mqttToken, mqttWireMessage);
                this.pendingMessages.addElement(mqttWireMessage);
                this.queueLock.notifyAll();
            }
            return;
        }
        log.fine(CLASS_NAME, "send", "615", new Object[]{new Integer(mqttWireMessage.getMessageId()), mqttWireMessage});
        if (mqttWireMessage instanceof MqttConnect) {
            synchronized (this.queueLock) {
                this.tokenStore.saveToken(mqttToken, mqttWireMessage);
                this.pendingFlows.insertElementAt(mqttWireMessage, 0);
                this.queueLock.notifyAll();
            }
            return;
        }
        if (mqttWireMessage instanceof MqttPingReq) {
            this.pingCommand = mqttWireMessage;
        } else if (mqttWireMessage instanceof MqttPubRel) {
            this.outboundQoS2.put(new Integer(mqttWireMessage.getMessageId()), mqttWireMessage);
            this.persistence.put(getSendConfirmPersistenceKey(mqttWireMessage), (MqttPubRel) mqttWireMessage);
        } else if (mqttWireMessage instanceof MqttPubComp) {
            this.persistence.remove(getReceivedPersistenceKey(mqttWireMessage));
        }
        synchronized (this.queueLock) {
            if (!(mqttWireMessage instanceof MqttAck)) {
                this.tokenStore.saveToken(mqttToken, mqttWireMessage);
            }
            this.pendingFlows.addElement(mqttWireMessage);
            this.queueLock.notifyAll();
        }
    }

    public void persistBufferedMessage(MqttWireMessage mqttWireMessage) {
        String sendBufferedPersistenceKey;
        String sendBufferedPersistenceKey2 = getSendBufferedPersistenceKey(mqttWireMessage);
        try {
            mqttWireMessage.setMessageId(getNextMessageId());
            sendBufferedPersistenceKey = getSendBufferedPersistenceKey(mqttWireMessage);
        } catch (MqttException e) {
        }
        try {
            try {
                this.persistence.put(sendBufferedPersistenceKey, (MqttPublish) mqttWireMessage);
            } catch (MqttPersistenceException e2) {
                log.fine(CLASS_NAME, "persistBufferedMessage", "515");
                this.persistence.open(this.clientComms.getClient().getClientId(), this.clientComms.getClient().getServerURI());
                this.persistence.put(sendBufferedPersistenceKey, (MqttPublish) mqttWireMessage);
            }
            log.fine(CLASS_NAME, "persistBufferedMessage", "513", new Object[]{sendBufferedPersistenceKey});
        } catch (MqttException e3) {
            sendBufferedPersistenceKey2 = sendBufferedPersistenceKey;
            log.warning(CLASS_NAME, "persistBufferedMessage", "513", new Object[]{sendBufferedPersistenceKey2});
        }
    }

    public void unPersistBufferedMessage(MqttWireMessage mqttWireMessage) {
        try {
            log.fine(CLASS_NAME, "unPersistBufferedMessage", "517", new Object[]{mqttWireMessage.getKey()});
            this.persistence.remove(getSendBufferedPersistenceKey(mqttWireMessage));
        } catch (MqttPersistenceException e) {
            log.fine(CLASS_NAME, "unPersistBufferedMessage", "518", new Object[]{mqttWireMessage.getKey()});
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void undo(MqttPublish mqttPublish) {
        synchronized (this.queueLock) {
            log.fine(CLASS_NAME, "undo", "618", new Object[]{new Integer(mqttPublish.getMessageId()), new Integer(mqttPublish.getMessage().getQos())});
            if (mqttPublish.getMessage().getQos() == 1) {
                this.outboundQoS1.remove(new Integer(mqttPublish.getMessageId()));
            } else {
                this.outboundQoS2.remove(new Integer(mqttPublish.getMessageId()));
            }
            this.pendingMessages.removeElement(mqttPublish);
            this.persistence.remove(getSendPersistenceKey(mqttPublish));
            this.tokenStore.removeToken(mqttPublish);
            if (mqttPublish.getMessage().getQos() > 0) {
                releaseMessageId(mqttPublish.getMessageId());
                mqttPublish.setMessageId(0);
            }
            checkQuiesceLock();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x019c A[Catch: all -> 0x0127, TryCatch #1 {, blocks: (B:18:0x0039, B:20:0x003d, B:22:0x0041, B:24:0x0045, B:28:0x0055, B:30:0x0094, B:31:0x00ad, B:33:0x019c, B:34:0x01a1, B:36:0x0185, B:42:0x012c, B:46:0x013c, B:47:0x0182, B:49:0x00d0, B:53:0x00e0, B:54:0x0126), top: B:17:0x0039 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.eclipse.paho.client.mqttv3.MqttToken checkForActivity(org.eclipse.paho.client.mqttv3.IMqttActionListener r15) {
        /*
            Method dump skipped, instructions count: 448
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.paho.client.mqttv3.internal.ClientState.checkForActivity(org.eclipse.paho.client.mqttv3.IMqttActionListener):org.eclipse.paho.client.mqttv3.MqttToken");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MqttWireMessage get() {
        synchronized (this.queueLock) {
            MqttWireMessage mqttWireMessage = null;
            while (mqttWireMessage == null) {
                if ((this.pendingMessages.isEmpty() && this.pendingFlows.isEmpty()) || (this.pendingFlows.isEmpty() && this.actualInFlight >= this.maxInflight)) {
                    try {
                        log.fine(CLASS_NAME, "get", "644");
                        this.queueLock.wait();
                        log.fine(CLASS_NAME, "get", "647");
                    } catch (InterruptedException e) {
                    }
                }
                if (!this.connected && (this.pendingFlows.isEmpty() || !(((MqttWireMessage) this.pendingFlows.elementAt(0)) instanceof MqttConnect))) {
                    log.fine(CLASS_NAME, "get", "621");
                    return null;
                }
                if (!this.pendingFlows.isEmpty()) {
                    MqttWireMessage mqttWireMessage2 = (MqttWireMessage) this.pendingFlows.remove(0);
                    if (mqttWireMessage2 instanceof MqttPubRel) {
                        this.inFlightPubRels++;
                        log.fine(CLASS_NAME, "get", "617", new Object[]{new Integer(this.inFlightPubRels)});
                    }
                    checkQuiesceLock();
                    mqttWireMessage = mqttWireMessage2;
                } else if (!this.pendingMessages.isEmpty()) {
                    if (this.actualInFlight < this.maxInflight) {
                        MqttWireMessage mqttWireMessage3 = (MqttWireMessage) this.pendingMessages.elementAt(0);
                        this.pendingMessages.removeElementAt(0);
                        this.actualInFlight++;
                        log.fine(CLASS_NAME, "get", "623", new Object[]{new Integer(this.actualInFlight)});
                        mqttWireMessage = mqttWireMessage3;
                    } else {
                        log.fine(CLASS_NAME, "get", "622");
                    }
                }
            }
            return mqttWireMessage;
        }
    }

    public void setKeepAliveInterval(long j) {
        this.keepAlive = j;
    }

    public void notifySentBytes(int i) {
        if (i > 0) {
            this.lastOutboundActivity = System.currentTimeMillis();
        }
        log.fine(CLASS_NAME, "notifySentBytes", "643", new Object[]{new Integer(i)});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifySent(MqttWireMessage mqttWireMessage) {
        this.lastOutboundActivity = System.currentTimeMillis();
        log.fine(CLASS_NAME, "notifySent", "625", new Object[]{mqttWireMessage.getKey()});
        MqttToken token = this.tokenStore.getToken(mqttWireMessage);
        token.internalTok.notifySent();
        if (mqttWireMessage instanceof MqttPingReq) {
            synchronized (this.pingOutstandingLock) {
                long currentTimeMillis = System.currentTimeMillis();
                synchronized (this.pingOutstandingLock) {
                    this.lastPing = currentTimeMillis;
                    this.pingOutstanding++;
                }
                log.fine(CLASS_NAME, "notifySent", "635", new Object[]{new Integer(this.pingOutstanding)});
            }
            return;
        }
        if ((mqttWireMessage instanceof MqttPublish) && ((MqttPublish) mqttWireMessage).getMessage().getQos() == 0) {
            token.internalTok.markComplete(null, null);
            this.callback.asyncOperationComplete(token);
            decrementInFlight();
            releaseMessageId(mqttWireMessage.getMessageId());
            this.tokenStore.removeToken(mqttWireMessage);
            checkQuiesceLock();
        }
    }

    private void decrementInFlight() {
        synchronized (this.queueLock) {
            this.actualInFlight--;
            log.fine(CLASS_NAME, "decrementInFlight", "646", new Object[]{new Integer(this.actualInFlight)});
            if (!checkQuiesceLock()) {
                this.queueLock.notifyAll();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean checkQuiesceLock() {
        int count = this.tokenStore.count();
        if (!this.quiescing || count != 0 || this.pendingFlows.size() != 0 || !this.callback.isQuiesced()) {
            return false;
        }
        log.fine(CLASS_NAME, "checkQuiesceLock", "626", new Object[]{new Boolean(this.quiescing), new Integer(this.actualInFlight), new Integer(this.pendingFlows.size()), new Integer(this.inFlightPubRels), Boolean.valueOf(this.callback.isQuiesced()), new Integer(count)});
        synchronized (this.quiesceLock) {
            this.quiesceLock.notifyAll();
        }
        return true;
    }

    public void notifyReceivedBytes(int i) {
        if (i > 0) {
            this.lastInboundActivity = System.currentTimeMillis();
        }
        log.fine(CLASS_NAME, "notifyReceivedBytes", "630", new Object[]{new Integer(i)});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyReceivedAck(MqttAck mqttAck) {
        this.lastInboundActivity = System.currentTimeMillis();
        log.fine(CLASS_NAME, "notifyReceivedAck", "627", new Object[]{new Integer(mqttAck.getMessageId()), mqttAck});
        MqttToken token = this.tokenStore.getToken(mqttAck);
        if (token == null) {
            log.fine(CLASS_NAME, "notifyReceivedAck", "662", new Object[]{new Integer(mqttAck.getMessageId())});
        } else if (mqttAck instanceof MqttPubRec) {
            send(new MqttPubRel((MqttPubRec) mqttAck), token);
        } else if ((mqttAck instanceof MqttPubAck) || (mqttAck instanceof MqttPubComp)) {
            notifyResult(mqttAck, token, null);
        } else if (mqttAck instanceof MqttPingResp) {
            synchronized (this.pingOutstandingLock) {
                this.pingOutstanding = Math.max(0, this.pingOutstanding - 1);
                notifyResult(mqttAck, token, null);
                if (this.pingOutstanding == 0) {
                    this.tokenStore.removeToken(mqttAck);
                }
            }
            log.fine(CLASS_NAME, "notifyReceivedAck", "636", new Object[]{new Integer(this.pingOutstanding)});
        } else if (mqttAck instanceof MqttConnack) {
            int returnCode = ((MqttConnack) mqttAck).getReturnCode();
            if (returnCode == 0) {
                synchronized (this.queueLock) {
                    if (this.cleanSession) {
                        clearState();
                        this.tokenStore.saveToken(token, mqttAck);
                    }
                    this.inFlightPubRels = 0;
                    this.actualInFlight = 0;
                    restoreInflightMessages();
                    connected();
                }
                this.clientComms.connectComplete((MqttConnack) mqttAck, null);
                notifyResult(mqttAck, token, null);
                this.tokenStore.removeToken(mqttAck);
                synchronized (this.queueLock) {
                    this.queueLock.notifyAll();
                }
            } else {
                throw ExceptionHelper.createMqttException(returnCode);
            }
        } else {
            notifyResult(mqttAck, token, null);
            releaseMessageId(mqttAck.getMessageId());
            this.tokenStore.removeToken(mqttAck);
        }
        checkQuiesceLock();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyReceivedMsg(MqttWireMessage mqttWireMessage) {
        this.lastInboundActivity = System.currentTimeMillis();
        log.fine(CLASS_NAME, "notifyReceivedMsg", "651", new Object[]{new Integer(mqttWireMessage.getMessageId()), mqttWireMessage});
        if (!this.quiescing) {
            if (mqttWireMessage instanceof MqttPublish) {
                MqttPublish mqttPublish = (MqttPublish) mqttWireMessage;
                switch (mqttPublish.getMessage().getQos()) {
                    case MqttConnectOptions.MQTT_VERSION_DEFAULT /* 0 */:
                    case 1:
                        if (this.callback != null) {
                            this.callback.messageArrived(mqttPublish);
                            return;
                        }
                        return;
                    case Logger.WARNING /* 2 */:
                        this.persistence.put(getReceivedPersistenceKey(mqttWireMessage), (MqttPublish) mqttWireMessage);
                        this.inboundQoS2.put(new Integer(mqttPublish.getMessageId()), mqttPublish);
                        send(new MqttPubRec(mqttPublish), null);
                        return;
                    default:
                        return;
                }
            }
            if (mqttWireMessage instanceof MqttPubRel) {
                MqttPublish mqttPublish2 = (MqttPublish) this.inboundQoS2.get(new Integer(mqttWireMessage.getMessageId()));
                if (mqttPublish2 != null) {
                    if (this.callback != null) {
                        this.callback.messageArrived(mqttPublish2);
                        return;
                    }
                    return;
                }
                send(new MqttPubComp(mqttWireMessage.getMessageId()), null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyComplete(MqttToken mqttToken) {
        MqttWireMessage wireMessage = mqttToken.internalTok.getWireMessage();
        if (wireMessage != null && (wireMessage instanceof MqttAck)) {
            log.fine(CLASS_NAME, "notifyComplete", "629", new Object[]{new Integer(wireMessage.getMessageId()), mqttToken, wireMessage});
            MqttAck mqttAck = (MqttAck) wireMessage;
            if (mqttAck instanceof MqttPubAck) {
                this.persistence.remove(getSendPersistenceKey(wireMessage));
                this.persistence.remove(getSendBufferedPersistenceKey(wireMessage));
                this.outboundQoS1.remove(new Integer(mqttAck.getMessageId()));
                decrementInFlight();
                releaseMessageId(wireMessage.getMessageId());
                this.tokenStore.removeToken(wireMessage);
                log.fine(CLASS_NAME, "notifyComplete", "650", new Object[]{new Integer(mqttAck.getMessageId())});
            } else if (mqttAck instanceof MqttPubComp) {
                this.persistence.remove(getSendPersistenceKey(wireMessage));
                this.persistence.remove(getSendConfirmPersistenceKey(wireMessage));
                this.persistence.remove(getSendBufferedPersistenceKey(wireMessage));
                this.outboundQoS2.remove(new Integer(mqttAck.getMessageId()));
                this.inFlightPubRels--;
                decrementInFlight();
                releaseMessageId(wireMessage.getMessageId());
                this.tokenStore.removeToken(wireMessage);
                log.fine(CLASS_NAME, "notifyComplete", "645", new Object[]{new Integer(mqttAck.getMessageId()), new Integer(this.inFlightPubRels)});
            }
            checkQuiesceLock();
        }
    }

    protected void notifyResult(MqttWireMessage mqttWireMessage, MqttToken mqttToken, MqttException mqttException) {
        mqttToken.internalTok.markComplete(mqttWireMessage, mqttException);
        mqttToken.internalTok.notifyComplete();
        if (mqttWireMessage != null && (mqttWireMessage instanceof MqttAck) && !(mqttWireMessage instanceof MqttPubRec)) {
            log.fine(CLASS_NAME, "notifyResult", "648", new Object[]{mqttToken.internalTok.getKey(), mqttWireMessage, mqttException});
            this.callback.asyncOperationComplete(mqttToken);
        }
        if (mqttWireMessage == null) {
            log.fine(CLASS_NAME, "notifyResult", "649", new Object[]{mqttToken.internalTok.getKey(), mqttException});
            this.callback.asyncOperationComplete(mqttToken);
        }
    }

    public void connected() {
        log.fine(CLASS_NAME, "connected", "631");
        this.connected = true;
        this.pingSender.start();
    }

    public Vector resolveOldTokens(MqttException mqttException) {
        log.fine(CLASS_NAME, "resolveOldTokens", "632", new Object[]{mqttException});
        if (mqttException == null) {
            mqttException = new MqttException(32102);
        }
        Vector outstandingTokens = this.tokenStore.getOutstandingTokens();
        Enumeration elements = outstandingTokens.elements();
        while (elements.hasMoreElements()) {
            MqttToken mqttToken = (MqttToken) elements.nextElement();
            synchronized (mqttToken) {
                if (!mqttToken.isComplete() && !mqttToken.internalTok.isCompletePending() && mqttToken.getException() == null) {
                    mqttToken.internalTok.setException(mqttException);
                }
            }
            if (!(mqttToken instanceof MqttDeliveryToken)) {
                this.tokenStore.removeToken(mqttToken.internalTok.getKey());
            }
        }
        return outstandingTokens;
    }

    public void disconnected(MqttException mqttException) {
        log.fine(CLASS_NAME, "disconnected", "633", new Object[]{mqttException});
        this.connected = false;
        try {
            if (this.cleanSession) {
                clearState();
            }
            this.pendingMessages.clear();
            this.pendingFlows.clear();
            synchronized (this.pingOutstandingLock) {
                this.pingOutstanding = 0;
            }
        } catch (MqttException e) {
        }
    }

    private synchronized void releaseMessageId(int i) {
        this.inUseMsgIds.remove(new Integer(i));
    }

    private synchronized int getNextMessageId() {
        int i;
        int i2 = 0;
        synchronized (this) {
            int i3 = this.nextMsgId;
            do {
                this.nextMsgId++;
                if (this.nextMsgId > MAX_MSG_ID) {
                    this.nextMsgId = 1;
                }
                if (this.nextMsgId == i3 && (i2 = i2 + 1) == 2) {
                    throw ExceptionHelper.createMqttException(32001);
                }
            } while (this.inUseMsgIds.containsKey(new Integer(this.nextMsgId)));
            Integer num = new Integer(this.nextMsgId);
            this.inUseMsgIds.put(num, num);
            i = this.nextMsgId;
        }
        return i;
    }

    public void quiesce(long j) {
        if (!(j <= 0)) {
            log.fine(CLASS_NAME, "quiesce", "637", new Object[]{new Long(j)});
            synchronized (this.queueLock) {
                this.quiescing = true;
            }
            this.callback.quiesce();
            notifyQueueLock();
            synchronized (this.quiesceLock) {
                try {
                    int count = this.tokenStore.count();
                    if (count > 0 || this.pendingFlows.size() > 0 || !this.callback.isQuiesced()) {
                        log.fine(CLASS_NAME, "quiesce", "639", new Object[]{new Integer(this.actualInFlight), new Integer(this.pendingFlows.size()), new Integer(this.inFlightPubRels), new Integer(count)});
                        this.quiesceLock.wait(j);
                    }
                } catch (InterruptedException e) {
                }
            }
            synchronized (this.queueLock) {
                this.pendingMessages.clear();
                this.pendingFlows.clear();
                this.quiescing = false;
                this.actualInFlight = 0;
            }
            log.fine(CLASS_NAME, "quiesce", "640");
        }
    }

    public void notifyQueueLock() {
        synchronized (this.queueLock) {
            log.fine(CLASS_NAME, "notifyQueueLock", "638");
            this.queueLock.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void deliveryComplete(MqttPublish mqttPublish) {
        log.fine(CLASS_NAME, "deliveryComplete", "641", new Object[]{new Integer(mqttPublish.getMessageId())});
        this.persistence.remove(getReceivedPersistenceKey(mqttPublish));
        this.inboundQoS2.remove(new Integer(mqttPublish.getMessageId()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void deliveryComplete(int i) {
        log.fine(CLASS_NAME, "deliveryComplete", "641", new Object[]{new Integer(i)});
        this.persistence.remove(getReceivedPersistenceKey(i));
        this.inboundQoS2.remove(new Integer(i));
    }

    public int getActualInFlight() {
        return this.actualInFlight;
    }

    public int getMaxInFlight() {
        return this.maxInflight;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void close() {
        this.inUseMsgIds.clear();
        if (this.pendingMessages != null) {
            this.pendingMessages.clear();
        }
        this.pendingFlows.clear();
        this.outboundQoS2.clear();
        this.outboundQoS1.clear();
        this.outboundQoS0.clear();
        this.inboundQoS2.clear();
        this.tokenStore.clear();
        this.inUseMsgIds = null;
        this.pendingMessages = null;
        this.pendingFlows = null;
        this.outboundQoS2 = null;
        this.outboundQoS1 = null;
        this.outboundQoS0 = null;
        this.inboundQoS2 = null;
        this.tokenStore = null;
        this.callback = null;
        this.clientComms = null;
        this.persistence = null;
        this.pingCommand = null;
    }

    public Properties getDebug() {
        Properties properties = new Properties();
        properties.put("In use msgids", this.inUseMsgIds);
        properties.put("pendingMessages", this.pendingMessages);
        properties.put("pendingFlows", this.pendingFlows);
        properties.put("maxInflight", new Integer(this.maxInflight));
        properties.put("nextMsgID", new Integer(this.nextMsgId));
        properties.put("actualInFlight", new Integer(this.actualInFlight));
        properties.put("inFlightPubRels", new Integer(this.inFlightPubRels));
        properties.put("quiescing", Boolean.valueOf(this.quiescing));
        properties.put("pingoutstanding", new Integer(this.pingOutstanding));
        properties.put("lastOutboundActivity", new Long(this.lastOutboundActivity));
        properties.put("lastInboundActivity", new Long(this.lastInboundActivity));
        properties.put("outboundQoS2", this.outboundQoS2);
        properties.put("outboundQoS1", this.outboundQoS1);
        properties.put("outboundQoS0", this.outboundQoS0);
        properties.put("inboundQoS2", this.inboundQoS2);
        properties.put("tokens", this.tokenStore);
        return properties;
    }
}
