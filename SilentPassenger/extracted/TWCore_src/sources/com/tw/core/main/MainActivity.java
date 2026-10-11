package com.tw.core.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;
import com.tw.core.R;
import com.tw.core.utils.C0032f;
import com.tw.core.utils.C0037k;
import com.tw.core.utils.K;
import java.io.File;
import java.util.HashMap;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes.dex */
public class MainActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_main_activity);
    }

    public void openGps(View view) {
        openGps();
    }

    public void closeGps(View view) {
        closeGps();
    }

    public void uploadLog(View view) {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/log/twcore.df");
        if (file.exists()) {
            HashMap hashMap = new HashMap();
            hashMap.put("file", file);
            HashMap hashMap2 = new HashMap();
            hashMap2.put("key", "applog/" + K.ke(System.currentTimeMillis(), K.fy) + MqttTopic.TOPIC_LEVEL_SEPARATOR + com.tw.core.e.b.le() + MqttTopic.TOPIC_LEVEL_SEPARATOR + K.ke(System.currentTimeMillis(), K.fz) + "-" + file.getName());
            C0032f.hm("http://dofun-log.oss-cn-shenzhen.aliyuncs.com", hashMap2, hashMap, new a(this));
            return;
        }
        Toast.makeText(this, "文件不存在 -- " + C0037k.ey.size(), 0).show();
    }

    private void closeGps() {
        Intent intent = new Intent("com.android.settings.location.MODE_CHANGING");
        intent.putExtra("NEW_MODE", 0);
        sendBroadcast(intent, "android.permission.WRITE_SECURE_SETTINGS");
        Settings.Secure.putInt(getContentResolver(), "location_mode", 0);
    }

    private void openGps() {
        int i = Settings.Secure.getInt(getContentResolver(), "location_mode", 0);
        if (i != 3 && i != 1) {
            Intent intent = new Intent("com.android.settings.location.MODE_CHANGING");
            intent.putExtra("NEW_MODE", 1);
            sendBroadcast(intent, "android.permission.WRITE_SECURE_SETTINGS");
            Settings.Secure.putInt(getContentResolver(), "location_mode", 1);
        }
    }
}
