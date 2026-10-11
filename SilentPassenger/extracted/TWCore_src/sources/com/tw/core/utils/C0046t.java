package com.tw.core.utils;

import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* renamed from: com.tw.core.utils.t, reason: case insensitive filesystem */
/* loaded from: classes.dex */
class C0046t implements InterfaceC0045s {
    private final String eQ;
    private final HashMap eR = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0046t(String str) {
        this.eQ = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void iu(String str, File file) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Name must not be empty");
        }
        try {
            this.eR.put(str, file.getCanonicalFile());
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to resolve canonical path for " + file, e);
        }
    }

    @Override // com.tw.core.utils.InterfaceC0045s
    public Uri it(File file) {
        Map.Entry entry;
        String substring;
        Map.Entry entry2 = null;
        try {
            String canonicalPath = file.getCanonicalPath();
            Iterator it = this.eR.entrySet().iterator();
            while (true) {
                entry = entry2;
                if (!it.hasNext()) {
                    break;
                }
                entry2 = (Map.Entry) it.next();
                String path = ((File) entry2.getValue()).getPath();
                if (!canonicalPath.startsWith(path) || (entry != null && path.length() <= ((File) entry.getValue()).getPath().length())) {
                    entry2 = entry;
                }
            }
            if (entry == null) {
                throw new IllegalArgumentException("Failed to find configured root that contains " + canonicalPath);
            }
            String path2 = ((File) entry.getValue()).getPath();
            if (path2.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                substring = canonicalPath.substring(path2.length());
            } else {
                substring = canonicalPath.substring(path2.length() + 1);
            }
            return new Uri.Builder().scheme("content").authority(this.eQ).encodedPath(Uri.encode((String) entry.getKey()) + '/' + Uri.encode(substring, MqttTopic.TOPIC_LEVEL_SEPARATOR)).build();
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to resolve canonical path for " + file);
        }
    }

    @Override // com.tw.core.utils.InterfaceC0045s
    public File is(Uri uri) {
        String encodedPath = uri.getEncodedPath();
        int indexOf = encodedPath.indexOf(47, 1);
        String decode = Uri.decode(encodedPath.substring(1, indexOf));
        String decode2 = Uri.decode(encodedPath.substring(indexOf + 1));
        File file = (File) this.eR.get(decode);
        if (file == null) {
            throw new IllegalArgumentException("Unable to find configured root for " + uri);
        }
        File file2 = new File(file, decode2);
        try {
            File canonicalFile = file2.getCanonicalFile();
            if (!canonicalFile.getPath().startsWith(file.getPath())) {
                throw new SecurityException("Resolved path jumped beyond configured root");
            }
            return canonicalFile;
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to resolve canonical path for " + file2);
        }
    }
}
