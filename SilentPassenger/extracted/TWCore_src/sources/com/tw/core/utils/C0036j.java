package com.tw.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* renamed from: com.tw.core.utils.j, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0036j {
    private static final String ex = C0036j.class.getSimpleName();

    public static String hy(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(str.getBytes());
        gZIPOutputStream.close();
        return byteArrayOutputStream.toString("ISO-8859-1");
    }

    public static String hA(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(str.getBytes("ISO-8859-1")));
        byte[] bArr = new byte[256];
        while (true) {
            int read = gZIPInputStream.read(bArr);
            if (read >= 0) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                return byteArrayOutputStream.toString();
            }
        }
    }

    public static File hz(String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            C0034h.hs(ex, "文件不存在", new Object[0]);
            return null;
        }
        File file2 = new File(str2);
        try {
            long currentTimeMillis = System.currentTimeMillis();
            C0034h.hr(ex, "开始压缩", new Object[0]);
            ZipOutputStream zipOutputStream = new ZipOutputStream(new CheckedOutputStream(new FileOutputStream(file2), new CRC32()));
            hB(zipOutputStream, "DFLog", file);
            zipOutputStream.flush();
            zipOutputStream.close();
            C0034h.hr(ex, "结束压缩 耗时 %s", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            return file2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void hB(ZipOutputStream zipOutputStream, String str, File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            String str2 = str + MqttTopic.TOPIC_LEVEL_SEPARATOR;
            try {
                zipOutputStream.putNextEntry(new ZipEntry(str2));
                for (File file2 : listFiles) {
                    hB(zipOutputStream, str2 + file2.getName(), file2);
                }
                return;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        try {
            zipOutputStream.putNextEntry(new ZipEntry(str));
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    zipOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    return;
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
