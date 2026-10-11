package org.eclipse.paho.client.mqttv3.persist;

import java.util.Enumeration;
import java.util.Hashtable;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttPersistable;

/* loaded from: classes.dex */
public class MemoryPersistence implements MqttClientPersistence {
    private Hashtable data;

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void close() {
        this.data.clear();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public Enumeration keys() {
        return this.data.keys();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public MqttPersistable get(String str) {
        return (MqttPersistable) this.data.get(str);
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void open(String str, String str2) {
        this.data = new Hashtable();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void put(String str, MqttPersistable mqttPersistable) {
        this.data.put(str, mqttPersistable);
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void remove(String str) {
        this.data.remove(str);
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void clear() {
        this.data.clear();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public boolean containsKey(String str) {
        return this.data.containsKey(str);
    }
}
