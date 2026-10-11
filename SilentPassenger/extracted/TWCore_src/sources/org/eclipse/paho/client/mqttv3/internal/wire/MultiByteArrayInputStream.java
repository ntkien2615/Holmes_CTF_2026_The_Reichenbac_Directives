package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.InputStream;

/* loaded from: classes.dex */
public class MultiByteArrayInputStream extends InputStream {
    private byte[] bytesA;
    private byte[] bytesB;
    private int lengthA;
    private int lengthB;
    private int offsetA;
    private int offsetB;
    private int pos = 0;

    public MultiByteArrayInputStream(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        this.bytesA = bArr;
        this.bytesB = bArr2;
        this.offsetA = i;
        this.offsetB = i3;
        this.lengthA = i2;
        this.lengthB = i4;
    }

    @Override // java.io.InputStream
    public int read() {
        int i;
        if (this.pos < this.lengthA) {
            i = this.bytesA[this.offsetA + this.pos];
        } else {
            if (this.pos >= this.lengthA + this.lengthB) {
                return -1;
            }
            i = this.bytesB[(this.offsetB + this.pos) - this.lengthA];
        }
        if (i < 0) {
            i += 256;
        }
        this.pos++;
        return i;
    }
}
