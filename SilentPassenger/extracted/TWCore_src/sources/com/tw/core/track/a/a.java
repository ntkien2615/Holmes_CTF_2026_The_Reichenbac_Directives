package com.tw.core.track.a;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;

/* loaded from: classes.dex */
public class a {
    public static boolean s = false;

    public static String j(String str, String str2, String str3) {
        try {
            HttpURLConnection f = s ? f(str, str2, str3) : e(str, str2, str3);
            if (str3.equals("POST")) {
                return i(f, str2);
            }
            if (str3.equals("GET")) {
                return h(f);
            }
            return null;
        } catch (IOException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("{\"status\":").append(-1).append(",").append("\"message\":").append("\"").append(com.tw.core.track.b.b.aG(e)).append("\"}");
            return sb.toString();
        }
    }

    private static HttpsURLConnection f(String str, String str2, String str3) {
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) g("https://yingyan.baidu.com/api/v3/" + str, str2, str3).openConnection();
        d(httpsURLConnection, str3);
        httpsURLConnection.setHostnameVerifier(new c());
        return httpsURLConnection;
    }

    private static HttpURLConnection e(String str, String str2, String str3) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) g("http://yingyan.baidu.com/api/v3/" + str, str2, str3).openConnection();
        d(httpURLConnection, str3);
        return httpURLConnection;
    }

    private static URL g(String str, String str2, String str3) {
        if ("GET".equalsIgnoreCase(str3)) {
            return new URL(str + "?" + str2);
        }
        return new URL(str);
    }

    private static void d(HttpURLConnection httpURLConnection, String str) {
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        httpURLConnection.setRequestMethod(str);
        httpURLConnection.setConnectTimeout(DisconnectedBufferOptions.DISCONNECTED_BUFFER_SIZE_DEFAULT);
        httpURLConnection.setReadTimeout(DisconnectedBufferOptions.DISCONNECTED_BUFFER_SIZE_DEFAULT);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
    }

    private static String h(HttpURLConnection httpURLConnection) {
        InputStream inputStream = null;
        try {
            try {
                int responseCode = httpURLConnection.getResponseCode();
                if (200 == responseCode) {
                    InputStream inputStream2 = httpURLConnection.getInputStream();
                    String c = c(inputStream2);
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return c;
                }
                return a(responseCode, httpURLConnection.getResponseMessage());
            } catch (IOException e2) {
                String b = b(e2);
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                return b;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static String i(HttpURLConnection httpURLConnection, String str) {
        OutputStream outputStream;
        InputStream inputStream = null;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        try {
            if (httpURLConnection == null) {
                return "";
            }
            try {
                outputStream = httpURLConnection.getOutputStream();
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    bufferedWriter.write(str);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    httpURLConnection.connect();
                    int responseCode = httpURLConnection.getResponseCode();
                    if (200 != responseCode) {
                        String a = a(responseCode, httpURLConnection.getResponseMessage());
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        return a;
                    }
                    InputStream inputStream2 = httpURLConnection.getInputStream();
                    String c = c(inputStream2);
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    return c;
                } catch (IOException e3) {
                    e = e3;
                    String b = b(e);
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                            return b;
                        }
                    }
                    if (0 != 0) {
                        (objArr2 == true ? 1 : 0).close();
                    }
                    return b;
                }
            } catch (IOException e5) {
                e = e5;
                outputStream = null;
            } catch (Throwable th) {
                th = th;
                if (0 != 0) {
                    try {
                        (objArr == true ? 1 : 0).close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                        throw th;
                    }
                }
                if (0 != 0) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static String c(InputStream inputStream) {
        StringBuffer stringBuffer = new StringBuffer("");
        if (inputStream != null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                try {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuffer.append(readLine);
                    } catch (Throwable th) {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                                throw th;
                            }
                        }
                        throw th;
                    }
                } catch (IOException e2) {
                    String b = b(e2);
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                            return b;
                        }
                    }
                    return b;
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
        }
        return stringBuffer.toString();
    }

    private static String b(IOException iOException) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"status\":").append(-1).append(",").append("\"message\":").append("\"").append(com.tw.core.track.b.b.aG(iOException)).append("\"}");
        return sb.toString();
    }

    private static String a(int i, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"status\":").append(String.valueOf(i)).append(",").append("\"message\":").append("\"").append(str).append("\"}");
        return sb.toString();
    }
}
