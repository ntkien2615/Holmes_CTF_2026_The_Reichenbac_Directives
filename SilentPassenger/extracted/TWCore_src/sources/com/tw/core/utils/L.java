package com.tw.core.utils;

import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.logging.Logger;

/* loaded from: classes.dex */
public class L {
    private static int[] fF = new int[256];
    private static int fG;

    static {
        for (int i = 0; i < 256; i++) {
            int i2 = i;
            for (int i3 = 0; i3 < 8; i3++) {
                if ((i2 & 1) != 0) {
                    i2 = (i2 >>> 1) ^ (-306674912);
                } else {
                    i2 >>>= 1;
                }
            }
            fF[i] = i2;
        }
        fG = -1;
    }

    public static String kg(String str) {
        File file = new File(str);
        if (!file.exists()) {
            Log.e("VerifyFile", "文件不存在！" + str);
            return null;
        }
        return kf(file);
    }

    public static String kf(File file) {
        FileInputStream fileInputStream;
        int read;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                byte[] bArr = new byte[10240];
                do {
                    read = fileInputStream.read(bArr);
                    if (read > 0) {
                        messageDigest.update(bArr, 0, read);
                    }
                } while (read >= 10240);
                String ki = ki(kh(messageDigest.digest()), 0);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                    }
                }
                return ki;
            } catch (FileNotFoundException e2) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                    }
                }
                return null;
            } catch (IOException e4) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e5) {
                    }
                }
                return null;
            } catch (NoSuchAlgorithmException e6) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e7) {
                    }
                }
                return null;
            } catch (Throwable th) {
                fileInputStream2 = fileInputStream;
                th = th;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e8) {
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException e9) {
            fileInputStream = null;
        } catch (IOException e10) {
            fileInputStream = null;
        } catch (NoSuchAlgorithmException e11) {
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static String kh(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static String ki(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            switch (i) {
                case MqttConnectOptions.MQTT_VERSION_DEFAULT /* 0 */:
                    return str.toLowerCase();
                case Logger.SEVERE /* 1 */:
                    return str.toUpperCase();
                default:
                    return str;
            }
        } catch (Exception e) {
            return str;
        }
    }
}
