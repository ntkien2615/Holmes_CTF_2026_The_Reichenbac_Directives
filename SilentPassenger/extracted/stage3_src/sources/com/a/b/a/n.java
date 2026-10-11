package com.a.b.a;

import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* loaded from: classes.dex */
public final class n {
    private static n b;
    private CookieManager c;
    final HostnameVerifier a = new o(this);
    private SSLSocketFactory d = c().getSocketFactory();

    private n() {
    }

    public static n a() {
        if (b == null) {
            b = new n();
        }
        return b;
    }

    private p a(String str, String[][] strArr, boolean z) {
        p pVar = new p();
        if (!str.startsWith(aq.a("nzzvy@55", 6))) {
            str = aq.a("jvvru<11", 2) + str;
        }
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
            httpsURLConnection.setRequestMethod(aq.a("LJY", 5));
            httpsURLConnection.setConnectTimeout(30000);
            httpsURLConnection.setReadTimeout(30000);
            httpsURLConnection.setInstanceFollowRedirects(z);
            httpsURLConnection.setDoInput(true);
            a(httpsURLConnection);
            httpsURLConnection.setSSLSocketFactory(this.d);
            httpsURLConnection.setHostnameVerifier(this.a);
            if (strArr != null && strArr.length > 0) {
                for (int i = 0; i < strArr.length; i++) {
                    httpsURLConnection.setRequestProperty(strArr[i][0], strArr[i][1]);
                }
            }
            Map<String, String> b2 = b(httpsURLConnection);
            pVar.a = httpsURLConnection.getResponseCode();
            pVar.c = b2;
            InputStream inputStream = httpsURLConnection.getInputStream();
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
            pVar.a(byteArrayOutputStream.toByteArray());
            inputStream.close();
            httpsURLConnection.disconnect();
        } catch (Exception e) {
            aq.a("ul{", 7);
            new StringBuilder().append(aq.a("n{{h", 9)).append(e.getLocalizedMessage());
        }
        return pVar;
    }

    private static void a(URLConnection uRLConnection) {
        uRLConnection.setRequestProperty(aq.a("Fhhjuy2Qfslzflj", 5), aq.a("\u0083q6LW", 9));
        uRLConnection.setRequestProperty(aq.a("Mrk|}o~", 10), aq.a("\\[M4?", 7));
        uRLConnection.setRequestProperty(aq.a("Myxxom~syx", 10), aq.a("uooz7kvs\u0080o", 10));
        uRLConnection.setConnectTimeout(20000);
        uRLConnection.setReadTimeout(60000);
        uRLConnection.setRequestProperty(aq.a("Fhhjuy", 5), aq.a("zk~z5nzsr2gvvroigzout5~nzsr1~sr2gvvroigzout5~srAwC64?2osgmk5g|ol2osgmk5}khv2osgmk5gvtm2050AwC64>2gvvroigzout5yomtkj3k~ingtmkA|Ch9AwC64?", 6));
        uRLConnection.setRequestProperty(aq.a("Bddfqu.Fodpejoh", 1), aq.a("p\u0083ry5)mnouj}n5)k{", 9));
        uRLConnection.setRequestProperty(aq.a("Xvhu0Djhqw", 3), aq.a("yur~~z9>8;:8:", 10));
    }

    public static n b() {
        n nVar = new n();
        nVar.c = new CookieManager();
        return nVar;
    }

    private p b(String str, String[][] strArr, boolean z) {
        p pVar = new p();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod(aq.a("HFU", 1));
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(30000);
            httpURLConnection.setInstanceFollowRedirects(z);
            httpURLConnection.setDoInput(true);
            a(httpURLConnection);
            if (strArr != null && strArr.length > 0) {
                for (int i = 0; i < strArr.length; i++) {
                    httpURLConnection.setRequestProperty(strArr[i][0], strArr[i][1]);
                }
            }
            httpURLConnection.connect();
            Map<String, String> b2 = b(httpURLConnection);
            pVar.a = httpURLConnection.getResponseCode();
            pVar.c = b2;
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
            pVar.a(byteArrayOutputStream.toByteArray());
            inputStream.close();
            httpURLConnection.disconnect();
        } catch (Exception e) {
            aq.a("wn}", 9);
            new StringBuilder().append(aq.a("kxxe", 6)).append(e.getLocalizedMessage());
        }
        return pVar;
    }

    private p b(String str, String[][] strArr, byte[] bArr) {
        p pVar = new p();
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
            httpsURLConnection.setRequestMethod(aq.a("XW[\\", 8));
            httpsURLConnection.setConnectTimeout(30000);
            httpsURLConnection.setReadTimeout(30000);
            httpsURLConnection.setInstanceFollowRedirects(true);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setDoInput(true);
            a(httpsURLConnection);
            httpsURLConnection.setHostnameVerifier(this.a);
            if (strArr != null && strArr.length > 0) {
                for (int i = 0; i < strArr.length; i++) {
                    httpsURLConnection.setRequestProperty(strArr[i][0], strArr[i][1]);
                }
            }
            httpsURLConnection.connect();
            if (bArr != null) {
                DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
                dataOutputStream.write(bArr);
                dataOutputStream.flush();
                dataOutputStream.close();
            }
            Map<String, String> b2 = b(httpsURLConnection);
            pVar.a = httpsURLConnection.getResponseCode();
            pVar.c = b2;
            InputStream inputStream = httpsURLConnection.getInputStream();
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
            byteArrayOutputStream.close();
            pVar.a(byteArrayOutputStream.toByteArray());
            inputStream.close();
            httpsURLConnection.disconnect();
        } catch (Exception e) {
            aq.a("ofu", 1);
            new StringBuilder().append(aq.a("kxxe", 6)).append(e.getLocalizedMessage());
        }
        return pVar;
    }

    private Map<String, String> b(URLConnection uRLConnection) {
        HashMap hashMap = new HashMap();
        try {
            Map<String, List<String>> headerFields = uRLConnection.getHeaderFields();
            if (this.c != null) {
                this.c.put(uRLConnection.getURL().toURI(), headerFields);
            }
            for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                if (value.size() != 0) {
                    if (key == null || !key.contains(aq.a("\\n}6Lxxtrn", 9))) {
                        hashMap.put(key, entry.getValue().get(0));
                    } else {
                        String str = "";
                        Iterator<String> it = value.iterator();
                        while (it.hasNext()) {
                            str = str + it.next() + aq.a("H+H", 8);
                        }
                        System.out.println(str);
                        hashMap.put(key, str);
                    }
                }
            }
        } catch (Throwable th) {
        }
        return hashMap;
    }

    private p c(String str, String[][] strArr, byte[] bArr) {
        p pVar = new p();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod(aq.a("SRVW", 3));
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(30000);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            a(httpURLConnection);
            if (strArr != null && strArr.length > 0) {
                for (int i = 0; i < strArr.length; i++) {
                    httpURLConnection.setRequestProperty(strArr[i][0], strArr[i][1]);
                }
            }
            httpURLConnection.connect();
            if (bArr != null) {
                DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                dataOutputStream.write(bArr);
                dataOutputStream.flush();
                dataOutputStream.close();
            }
            Map<String, String> b2 = b(httpURLConnection);
            pVar.a = httpURLConnection.getResponseCode();
            pVar.c = b2;
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
            byteArrayOutputStream.close();
            pVar.a(byteArrayOutputStream.toByteArray());
            inputStream.close();
            httpURLConnection.disconnect();
        } catch (Exception e) {
            aq.a("pgv", 2);
            new StringBuilder().append(aq.a("fss`", 1)).append(e.getLocalizedMessage());
        }
        return pVar;
    }

    private static SSLContext c() {
        SSLContext sSLContext = null;
        TrustManager[] trustManagerArr = {new q()};
        try {
            sSLContext = SSLContext.getInstance(aq.a("UUN", 2));
            sSLContext.init(null, trustManagerArr, null);
            return sSLContext;
        } catch (Exception e) {
            return sSLContext;
        }
    }

    public final p a(String str, String[][] strArr) {
        return str.toLowerCase().startsWith(aq.a("p||xB77", 8)) ? b(str, strArr, true) : a(str, strArr, true);
    }

    public final p a(String str, String[][] strArr, String str2) {
        try {
            return a(str, strArr, str2.getBytes(aq.a("\u007f~p7B", 10)));
        } catch (Exception e) {
            return null;
        }
    }

    public final p a(String str, String[][] strArr, byte[] bArr) {
        return str.toLowerCase().startsWith(aq.a("lxxt>33", 4)) ? c(str, strArr, bArr) : b(str, strArr, bArr);
    }

    public final String a(String str) {
        if (this.c != null && !TextUtils.isEmpty(str)) {
            try {
                List<String> list = this.c.get(URI.create(str), new HashMap()).get(aq.a("Frrnlh", 3));
                StringBuffer stringBuffer = new StringBuffer();
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    stringBuffer.append(aq.a("<", 1) + it.next());
                }
                return stringBuffer.substring(1);
            } catch (Exception e) {
            }
        }
        return null;
    }

    public final p b(String str, String[][] strArr) {
        return str.toLowerCase().startsWith(aq.a("jvvr<11", 2)) ? b(str, strArr, false) : a(str, strArr, false);
    }
}
