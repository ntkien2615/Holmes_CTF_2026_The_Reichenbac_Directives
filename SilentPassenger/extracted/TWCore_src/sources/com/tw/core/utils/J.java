package com.tw.core.utils;

import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.Callable;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes.dex */
public class J implements Callable {
    private File fs;
    private long ft;
    public String fu;
    private File fv;
    private HttpURLConnection fw;

    public J(String str, File file) {
        this.fu = str;
        this.fv = file;
    }

    private FileOutputStream kc(URL url) {
        String headerField = this.fw.getHeaderField("Accept-Ranges");
        C0034h.hr("Downloader", "Accept-Ranges %s", headerField);
        if (TextUtils.isEmpty(headerField) || (!headerField.startsWith("bytes"))) {
            this.fs.delete();
            jX();
        }
        return new FileOutputStream(this.fs, true);
    }

    private void kb() {
        this.fw.setRequestProperty("Content-Type", "text/html; charset=UTF-8");
        this.fw.setRequestMethod("GET");
        this.fw.setRequestProperty("Connection", "Keep-Alive");
        this.fw.setRequestProperty("Accept-Encoding", "identity");
        this.fw.setConnectTimeout(10000);
    }

    private void jX() {
        this.fs = jY(String.format("%s_%s", this.fv.getAbsolutePath(), "bak"));
    }

    public File jY(String str) {
        File file = new File(str);
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            file.createNewFile();
        }
        return file;
    }

    private void ka() {
        this.fv.delete();
        this.fs.renameTo(this.fv);
    }

    private synchronized void jZ() {
    }

    @Override // java.util.concurrent.Callable
    public Void call() {
        FileOutputStream fileOutputStream;
        try {
        } catch (Exception e) {
            e = e;
            fileOutputStream = null;
        }
        if (this.fv.exists()) {
            C0034h.hr("Downloader", "本地下载好的文件名称 %s", this.fv.getName());
            return null;
        }
        jX();
        long length = this.fs.length();
        C0034h.hr("Downloader", "本地下载好的长度 %s", Long.valueOf(length));
        String replaceAll = URLEncoder.encode(this.fu, "UTF-8").replaceAll("\\+", "%20").replaceAll("%3A", ":").replaceAll("%2F", MqttTopic.TOPIC_LEVEL_SEPARATOR);
        if (C0038l.ez) {
            C0038l.hG("Downloader", "mUrl =  " + this.fu + "\nencodeurl " + replaceAll);
        }
        URL url = new URL(replaceAll);
        this.fw = (HttpURLConnection) url.openConnection();
        kb();
        if (length > 0) {
            this.fw.setRequestProperty("Range", "bytes=" + length + "-");
        }
        this.fw.connect();
        int responseCode = this.fw.getResponseCode();
        if (responseCode < 200 || responseCode >= 300) {
            this.fw.disconnect();
            return null;
        }
        this.ft = this.fw.getContentLength();
        if (C0034h.ev) {
            C0034h.hr("Downloader", "本次下载长度 %s 文件总长度 %s", Long.valueOf(this.ft), Long.valueOf(this.ft + length));
        }
        fileOutputStream = kc(url);
        try {
            InputStream inputStream = this.fw.getInputStream();
            byte[] bArr = new byte[8192];
            long currentTimeMillis = System.currentTimeMillis();
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
                length += read;
                if (System.currentTimeMillis() - currentTimeMillis > 50) {
                    currentTimeMillis = System.currentTimeMillis();
                }
                if (C0034h.ev) {
                    C0034h.hr("Downloader", "下载进度 " + length, new Object[0]);
                }
            }
            this.fw.disconnect();
            fileOutputStream.close();
            this.fw = null;
            ka();
            if (C0038l.ez) {
                C0038l.hG("Downloader", "下载完成 " + this.fv);
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            if (this.fw != null) {
                this.fw.disconnect();
                this.fw = null;
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            jZ();
            return null;
        }
        jZ();
        return null;
    }
}
