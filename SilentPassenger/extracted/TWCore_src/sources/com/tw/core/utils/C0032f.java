package com.tw.core.utils;

import android.os.Looper;
import android.util.Log;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.tw.core.utils.f, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0032f {
    private static final String et = C0032f.class.getSimpleName();
    private static C0032f eu;

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized C0032f hf() {
        C0032f c0032f;
        synchronized (C0032f.class) {
            if (eu == null) {
                eu = new C0032f();
            }
            c0032f = eu;
        }
        return c0032f;
    }

    private C0032f() {
    }

    public static String hc(String str, InterfaceC0033g interfaceC0033g) {
        return hf().hd(str, null, interfaceC0033g);
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00a5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String hd(java.lang.String r8, java.util.Map r9, com.tw.core.utils.InterfaceC0033g r10) {
        /*
            Method dump skipped, instructions count: 272
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tw.core.utils.C0032f.hd(java.lang.String, java.util.Map, com.tw.core.utils.g):java.lang.String");
    }

    public static String gZ(Map map, String str, InterfaceC0033g interfaceC0033g) {
        if (C0042p.ic()) {
            C0042p.id().execute(new U(map, str, interfaceC0033g));
            return null;
        }
        return hj("application/json", new JSONObject(map).toString(), str, interfaceC0033g, false);
    }

    public static String hi(Map map, String str, InterfaceC0033g interfaceC0033g, boolean z) {
        if (C0042p.ic()) {
            C0042p.id().execute(new V(map, str, interfaceC0033g, z));
            return null;
        }
        return hf().hk("application/json", map, str, interfaceC0033g, z);
    }

    public static String hg(Map map, String str, InterfaceC0033g interfaceC0033g) {
        return hi(map, str, interfaceC0033g, false);
    }

    public static String hh(JSONArray jSONArray, String str, InterfaceC0033g interfaceC0033g) {
        return hj("application/json", jSONArray.toString(), str, interfaceC0033g, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0108 A[Catch: all -> 0x01e7, TRY_LEAVE, TryCatch #8 {all -> 0x01e7, blocks: (B:42:0x0100, B:44:0x0108), top: B:41:0x0100 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String hj(java.lang.String r10, java.lang.String r11, java.lang.String r12, com.tw.core.utils.InterfaceC0033g r13, boolean r14) {
        /*
            Method dump skipped, instructions count: 506
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tw.core.utils.C0032f.hj(java.lang.String, java.lang.String, java.lang.String, com.tw.core.utils.g, boolean):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String hk(String str, Map map, String str2, InterfaceC0033g interfaceC0033g, boolean z) {
        String he;
        if ("application/json".equals(str)) {
            he = new JSONObject(map).toString();
        } else {
            he = he(map);
        }
        return hj(str, he, str2, interfaceC0033g, z);
    }

    public static void hm(String str, Map map, Map map2, InterfaceC0033g interfaceC0033g) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            C0042p.id().execute(new W(str, map, map2, interfaceC0033g));
        } else {
            hl(str, map, map2, interfaceC0033g);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v59 */
    /* JADX WARN: Type inference failed for: r2v60 */
    /* JADX WARN: Type inference failed for: r2v7 */
    public static void hl(String str, Map map, Map map2, InterfaceC0033g interfaceC0033g) {
        HttpURLConnection httpURLConnection;
        String str2;
        String uuid = UUID.randomUUID().toString();
        ?? r2 = 0;
        HttpURLConnection httpURLConnection2 = null;
        try {
            try {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            httpURLConnection.setReadTimeout(300000);
            httpURLConnection.setChunkedStreamingMode(0);
            httpURLConnection.setConnectTimeout(300000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("connection", "keep-alive");
            httpURLConnection.setRequestProperty("Charsert", "UTF-8");
            httpURLConnection.setRequestProperty("Origin-Flag", "car");
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + uuid);
            StringBuilder sb = new StringBuilder();
            if (map != null) {
                for (Map.Entry entry : map.entrySet()) {
                    sb.append("--");
                    sb.append(uuid);
                    sb.append("\r\n");
                    sb.append("Content-Disposition: form-data; name=\"").append((String) entry.getKey()).append("\"").append("\r\n");
                    sb.append("Content-Type: text/plain; charset=").append("UTF-8").append("\r\n");
                    sb.append("Content-Transfer-Encoding: 8bit").append("\r\n");
                    sb.append("\r\n");
                    sb.append((String) entry.getValue());
                    sb.append("\r\n");
                }
            }
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(sb.toString().getBytes());
            if (map2 != null) {
                for (Map.Entry entry2 : map2.entrySet()) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("--");
                    sb2.append(uuid);
                    sb2.append("\r\n");
                    sb2.append("Content-Disposition: form-data; name=\"").append((String) entry2.getKey()).append("\"; filename=\"").append(((File) entry2.getValue()).getName()).append("\"").append("\r\n");
                    sb2.append("Content-Type: application/octet-stream; charset=").append("UTF-8").append("\r\n");
                    sb2.append("\r\n");
                    dataOutputStream.write(sb2.toString().getBytes());
                    FileInputStream fileInputStream = new FileInputStream((File) entry2.getValue());
                    byte[] bArr = new byte[1024];
                    int i = 0;
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read != -1) {
                            dataOutputStream.write(bArr, 0, read);
                            i += read;
                            Log.d(et, "当前进度 " + i);
                        }
                    }
                    C0034h.hr(et, "写流完成", new Object[0]);
                    fileInputStream.close();
                    dataOutputStream.write("\r\n".getBytes());
                }
            }
            dataOutputStream.write(("--" + uuid + "--\r\n").getBytes());
            dataOutputStream.flush();
            int responseCode = httpURLConnection.getResponseCode();
            C0034h.hr(et, "响应码 %s", Integer.valueOf(responseCode));
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb3 = new StringBuilder();
            if (responseCode == 200 || responseCode == 204) {
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else {
                        sb3.append(readLine);
                    }
                }
                String sb4 = sb3.toString();
                interfaceC0033g.bB(sb4);
                str2 = sb4;
            } else {
                String str3 = httpURLConnection.getResponseCode() + "错误";
                interfaceC0033g.bz(str3);
                str2 = str3;
            }
            dataOutputStream.close();
            inputStream.close();
            bufferedReader.close();
            inputStreamReader.close();
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            interfaceC0033g.bA();
            r2 = str2;
        } catch (Exception e2) {
            httpURLConnection2 = httpURLConnection;
            e = e2;
            e.printStackTrace();
            interfaceC0033g.bz("上传失败");
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            interfaceC0033g.bA();
            r2 = httpURLConnection2;
        } catch (Throwable th2) {
            r2 = httpURLConnection;
            th = th2;
            if (r2 != 0) {
                r2.disconnect();
            }
            interfaceC0033g.bA();
            throw th;
        }
    }

    public static boolean hb(String str, String str2, String str3) {
        return hf().ha(str, str2, str3);
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00af A[Catch: IOException -> 0x00b8, TryCatch #2 {IOException -> 0x00b8, blocks: (B:61:0x00aa, B:53:0x00af, B:55:0x00b4), top: B:60:0x00aa }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00b4 A[Catch: IOException -> 0x00b8, TRY_LEAVE, TryCatch #2 {IOException -> 0x00b8, blocks: (B:61:0x00aa, B:53:0x00af, B:55:0x00b4), top: B:60:0x00aa }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00aa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean ha(java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 228
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tw.core.utils.C0032f.ha(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    private String he(Map map) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry entry : map.entrySet()) {
            stringBuffer.append((String) entry.getKey()).append("=").append((String) entry.getValue()).append("&");
        }
        return stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1);
    }
}
