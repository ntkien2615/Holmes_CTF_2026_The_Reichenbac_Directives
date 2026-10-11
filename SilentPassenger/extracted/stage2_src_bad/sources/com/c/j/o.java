package com.c.j;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* loaded from: D:\hommie_Ctf\SilentPassenger\extracted\stage2_unzip\classes_fixed.dex */
public final class o {
    private static SSLSocketFactory b = a().getSocketFactory();
    static final HostnameVerifier a = new p();

    public static q a(String str) {
        q qVar = new q();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod(a.a("IGV", 2));
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(30000);
            httpURLConnection.setDoInput(true);
            if (httpURLConnection instanceof HttpsURLConnection) {
                ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(b);
                ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(a);
            }
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            qVar.b = byteArrayOutputStream.toByteArray();
            inputStream.close();
            httpURLConnection.disconnect();
            qVar.a = 200;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qVar;
    }

    public static q a(String str, byte[] bArr) {
        q qVar = new q();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod(a.a("RQUV", 2));
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(30000);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            if (httpURLConnection instanceof HttpsURLConnection) {
                ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(b);
                ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(a);
            }
            httpURLConnection.connect();
            if (bArr != null) {
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.flush();
                outputStream.close();
            }
            InputStream inputStream = httpURLConnection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr2);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr2, 0, read);
            }
            byteArrayOutputStream.flush();
            qVar.b = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            inputStream.close();
            httpURLConnection.disconnect();
            qVar.a = 200;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qVar;
    }

    private static SSLContext a() {
        SSLContext sSLContext;
        NoSuchAlgorithmException e;
        KeyManagementException e2;
        TrustManager[] trustManagerArr = {new u()};
        try {
            sSLContext = SSLContext.getInstance(a.a("]]V", 10));
            try {
                sSLContext.init(null, trustManagerArr, null);
            } catch (KeyManagementException e3) {
                e2 = e3;
                e2.printStackTrace();
                return sSLContext;
            } catch (NoSuchAlgorithmException e4) {
                e = e4;
                e.printStackTrace();
                return sSLContext;
            }
        } catch (KeyManagementException e5) {
            sSLContext = null;
            e2 = e5;
        } catch (NoSuchAlgorithmException e6) {
            sSLContext = null;
            e = e6;
        }
        return sSLContext;
    }
}
