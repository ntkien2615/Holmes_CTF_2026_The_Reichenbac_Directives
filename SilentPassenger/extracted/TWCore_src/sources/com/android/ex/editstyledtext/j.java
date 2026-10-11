package com.android.ex.editstyledtext;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.util.Log;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.logging.Logger;

/* loaded from: classes.dex */
public class j extends CharacterStyle {
    private int kD;
    private int kE;

    public void pr(int i) {
        this.kE = pq(this.kD, i);
    }

    private int pq(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int alpha = Color.alpha(i2);
        int red = Color.red(i2);
        int green = Color.green(i2);
        int blue = Color.blue(i2);
        if (alpha == 0) {
            alpha = 128;
        }
        switch (i) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT /* 0 */:
                if (red > 128) {
                    i5 = red / 2;
                } else {
                    i5 = (255 - red) / 2;
                }
                i4 = i5;
                i3 = green;
                break;
            case Logger.SEVERE /* 1 */:
                if (green > 128) {
                    i3 = green / 2;
                } else {
                    i3 = (255 - green) / 2;
                }
                i4 = red;
                break;
            case Logger.WARNING /* 2 */:
                return 16777215;
            default:
                Log.e("EditStyledText", "--- getMarqueeColor: got illigal marquee ID.");
                return 16777215;
        }
        return Color.argb(alpha, i4, i3, blue);
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        textPaint.bgColor = this.kE;
    }
}
