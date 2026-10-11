package com.tw.core.f;

import com.tw.core.utils.C0034h;
import com.tw.core.utils.D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/* loaded from: classes.dex */
public class d {
    private static volatile d hC;
    private static final String hw;
    private static final String hx;
    private volatile boolean hA;
    private MqttConnectOptions hB;
    private MqttAsyncClient hE;
    private List hF;
    private Thread hI;
    private String[] hy = {com.tw.core.a.a.h};
    public int hv = 0;
    public int hu = 1;
    public int hG = 2;
    private final AtomicInteger hz = new AtomicInteger(1);
    private final MqttCallbackExtended hD = new g(this);
    final Map hH = new HashMap();

    static {
        hw = D.fj ? "dofuntest" : "dofun";
        hx = D.fj ? "dofuntest" : "dofun666666";
    }

    public static d mb() {
        if (hC == null) {
            synchronized (d.class) {
                if (hC == null) {
                    hC = new d();
                }
            }
        }
        return hC;
    }

    private d() {
        mk();
    }

    private void mk() {
        this.hI = new Thread(new i(this));
        this.hI.start();
    }

    private MqttConnectOptions mj() {
        if (this.hB == null) {
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            C0034h.hr("MqttClientManager", "Connecting to broker: ", new Object[0]);
            mqttConnectOptions.setServerURIs(this.hy);
            mqttConnectOptions.setUserName(hw);
            mqttConnectOptions.setPassword(hx.toCharArray());
            mqttConnectOptions.setCleanSession(true);
            mqttConnectOptions.setKeepAliveInterval(300);
            mqttConnectOptions.setConnectionTimeout(30);
            mqttConnectOptions.setMqttVersion(4);
            this.hB = mqttConnectOptions;
        }
        return this.hB;
    }

    public synchronized void md() {
        C0034h.hr("MqttClientManager", "connectMqtt", new Object[0]);
        if (this.hA) {
            C0034h.hs("MqttClientManager", "mqtt is connecting", new Object[0]);
            return;
        }
        if (isConnected()) {
            C0034h.hs("MqttClientManager", "mqtt is connected", new Object[0]);
            return;
        }
        this.hA = true;
        try {
            if (this.hE == null) {
                this.hE = new MqttAsyncClient(this.hy[0], com.tw.core.e.b.le(), new MemoryPersistence());
                this.hE.setCallback(this.hD);
            }
            this.hE.connect(mj(), null, new j(this));
        } catch (Throwable th) {
            th.printStackTrace();
            this.hA = false;
        }
    }

    public void me(a aVar) {
        if (aVar == null) {
            return;
        }
        if (this.hF == null) {
            this.hF = new ArrayList();
        }
        if (!this.hF.contains(aVar)) {
            this.hF.add(aVar);
        }
    }

    public void mf(a aVar) {
        if (aVar != null && this.hF != null && this.hF.contains(aVar)) {
            this.hF.remove(aVar);
        }
    }

    public void ml(String str, e eVar) {
        mc(str, com.tw.core.app.a.jr, this.hu, eVar);
    }

    public void mc(String str, String str2, int i, e eVar) {
        C0034h.hr("MqttClientManager", "sendMsg=%s topic=%s", str, str2);
        if (!isConnected()) {
            C0034h.hs("MqttClientManager", "sendMsg no connect", new Object[0]);
            if (eVar != null) {
                eVar.ex(false);
                return;
            }
            return;
        }
        try {
            MqttMessage mqttMessage = new MqttMessage(str.getBytes());
            mqttMessage.setQos(i);
            this.hE.publish(str2, mqttMessage, (Object) null, new k(this, eVar));
        } catch (Exception e) {
            e.printStackTrace();
            if (eVar != null) {
                eVar.ex(false);
            }
        }
    }

    public void mg(String[] strArr, int[] iArr) {
        if (isConnected()) {
            try {
                this.hE.subscribe(strArr, iArr);
                return;
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
        mi(strArr, iArr);
    }

    public void mi(String[] strArr, int[] iArr) {
        if (strArr != null && iArr != null) {
            synchronized (this.hH) {
                if (!this.hH.containsKey(strArr)) {
                    this.hH.put(strArr, iArr);
                }
            }
        }
    }

    public void mh(String[] strArr) {
        if (isConnected()) {
            try {
                this.hE.unsubscribe(strArr);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isConnected() {
        if (this.hE != null) {
            return this.hE.isConnected();
        }
        return false;
    }

    public void disconnect() {
        C0034h.hs("MqttClientManager", "disconnect", new Object[0]);
        if (isConnected()) {
            try {
                this.hE.disconnect();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }
}
