package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.InputStream;

/* loaded from: classes.dex */
public class CountingInputStream extends InputStream {
    private int counter = 0;
    private InputStream in;

    public CountingInputStream(InputStream inputStream) {
        this.in = inputStream;
    }

    @Override // java.io.InputStream
    public int read() {
        int read = this.in.read();
        if (read != -1) {
            this.counter++;
        }
        return read;
    }

    public int getCounter() {
        return this.counter;
    }

    public void resetCounter() {
        this.counter = 0;
    }
}
