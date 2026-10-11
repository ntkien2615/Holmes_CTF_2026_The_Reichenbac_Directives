package com.c.j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: D:\hommie_Ctf\SilentPassenger\extracted\stage2_unzip\classes_fixed.dex */
public final class t {
    private static t a;

    private t() {
    }

    public static t a() {
        if (a == null) {
            a = new t();
        }
        return a;
    }

    /* JADX WARN: Removed duplicated region for block: B:144:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:157:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01ff A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01fa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean a(String str, String str2) {
        File file;
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        HttpURLConnection httpURLConnection;
        boolean z;
        File file2;
        File file3;
        FileOutputStream fileOutputStream2 = null;
        InputStream inputStream2 = null;
        HttpURLConnection httpURLConnection2 = null;
        File file4 = null;
        boolean z2 = false;
        try {
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection3.setConnectTimeout(10000);
                httpURLConnection3.setReadTimeout(10000);
                File file5 = new File(str2);
                if (!file5.exists() && !file5.mkdirs()) {
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    return false;
                }
                File file6 = new File(file5, m.c);
                file = new File(file5, m.c + a.a("7}vy", 9));
                try {
                    File file7 = new File(file5, m.c + a.a("0dcm", 2));
                    try {
                        if (file.exists() && !file.delete()) {
                            if (httpURLConnection3 != null) {
                                httpURLConnection3.disconnect();
                            }
                            if (file.exists()) {
                                file.delete();
                            }
                            if (file7.exists()) {
                                file7.renameTo(new File(str2, m.c));
                            }
                            return false;
                        }
                        if (file7.exists() && !file7.delete()) {
                            if (httpURLConnection3 != null) {
                                httpURLConnection3.disconnect();
                            }
                            if (file.exists()) {
                                file.delete();
                            }
                            if (file7.exists()) {
                                file7.renameTo(new File(str2, m.c));
                            }
                            return false;
                        }
                        InputStream inputStream3 = httpURLConnection3.getInputStream();
                        try {
                            fileOutputStream = new FileOutputStream(file);
                            try {
                                try {
                                    byte[] bArr = new byte[4096];
                                    while (true) {
                                        int read = inputStream3.read(bArr);
                                        if (read == -1) {
                                            break;
                                        }
                                        fileOutputStream.write(bArr, 0, read);
                                    }
                                    fileOutputStream.flush();
                                    fileOutputStream.close();
                                    fileOutputStream = null;
                                    try {
                                        inputStream3.close();
                                    } catch (Throwable th) {
                                        fileOutputStream2 = null;
                                        inputStream2 = inputStream3;
                                        file4 = file7;
                                        th = th;
                                        httpURLConnection2 = httpURLConnection3;
                                    }
                                } catch (Exception e) {
                                    z = false;
                                    file2 = file7;
                                    httpURLConnection = httpURLConnection3;
                                    e = e;
                                    inputStream = inputStream3;
                                    file3 = file;
                                }
                            } catch (Throwable th2) {
                                fileOutputStream2 = fileOutputStream;
                                inputStream2 = inputStream3;
                                file4 = file7;
                                th = th2;
                                httpURLConnection2 = httpURLConnection3;
                            }
                            try {
                                if (file.length() == 0) {
                                    if (httpURLConnection3 != null) {
                                        httpURLConnection3.disconnect();
                                    }
                                    if (file.exists()) {
                                        file.delete();
                                    }
                                    if (file7.exists()) {
                                        file7.renameTo(new File(str2, m.c));
                                    }
                                    return false;
                                }
                                if (file6.exists() && !file6.renameTo(file7)) {
                                    if (httpURLConnection3 != null) {
                                        httpURLConnection3.disconnect();
                                    }
                                    if (file.exists()) {
                                        file.delete();
                                    }
                                    if (file7.exists()) {
                                        file7.renameTo(new File(str2, m.c));
                                    }
                                    return false;
                                }
                                if (file.renameTo(file6)) {
                                    if (file7.exists()) {
                                        file7.delete();
                                    }
                                    if (httpURLConnection3 != null) {
                                        httpURLConnection3.disconnect();
                                    }
                                    if (file.exists()) {
                                        file.delete();
                                    }
                                    return true;
                                }
                                if (file7.exists()) {
                                    file7.renameTo(file6);
                                }
                                if (httpURLConnection3 != null) {
                                    httpURLConnection3.disconnect();
                                }
                                if (file.exists()) {
                                    file.delete();
                                }
                                if (file7.exists()) {
                                    file7.renameTo(new File(str2, m.c));
                                }
                                return false;
                            } catch (Exception e2) {
                                z = false;
                                file2 = file7;
                                httpURLConnection = httpURLConnection3;
                                e = e2;
                                inputStream = null;
                                file3 = file;
                                try {
                                    e.printStackTrace();
                                    if (fileOutputStream != null) {
                                        try {
                                            fileOutputStream.close();
                                        } catch (Exception e3) {
                                            e3.printStackTrace();
                                        }
                                    }
                                    if (inputStream != null) {
                                        try {
                                            inputStream.close();
                                        } catch (Exception e4) {
                                            e4.printStackTrace();
                                        }
                                    }
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                    }
                                    if (file3 != null && file3.exists()) {
                                        file3.delete();
                                    }
                                    if (!z && file2 != null && file2.exists()) {
                                        file2.renameTo(new File(str2, m.c));
                                    }
                                    return false;
                                } catch (Throwable th3) {
                                    th = th3;
                                    fileOutputStream2 = fileOutputStream;
                                    inputStream2 = inputStream;
                                    file = file3;
                                    file4 = file2;
                                    z2 = z;
                                    httpURLConnection2 = httpURLConnection;
                                    if (fileOutputStream2 != null) {
                                        try {
                                            fileOutputStream2.close();
                                        } catch (Exception e5) {
                                            e5.printStackTrace();
                                        }
                                    }
                                    if (inputStream2 != null) {
                                        try {
                                            inputStream2.close();
                                        } catch (Exception e6) {
                                            e6.printStackTrace();
                                        }
                                    }
                                    if (httpURLConnection2 != null) {
                                        httpURLConnection2.disconnect();
                                    }
                                    if (file != null && file.exists()) {
                                        file.delete();
                                    }
                                    if (!z2) {
                                        throw th;
                                    }
                                    if (file4 == null) {
                                        throw th;
                                    }
                                    if (!file4.exists()) {
                                        throw th;
                                    }
                                    file4.renameTo(new File(str2, m.c));
                                    throw th;
                                }
                            } catch (Throwable th4) {
                                fileOutputStream2 = null;
                                inputStream2 = null;
                                file4 = file7;
                                th = th4;
                                httpURLConnection2 = httpURLConnection3;
                                if (fileOutputStream2 != null) {
                                }
                                if (inputStream2 != null) {
                                }
                                if (httpURLConnection2 != null) {
                                }
                                if (file != null) {
                                    file.delete();
                                }
                                if (!z2) {
                                }
                            }
                        } catch (Exception e7) {
                            fileOutputStream = null;
                            file2 = file7;
                            httpURLConnection = httpURLConnection3;
                            e = e7;
                            z = false;
                            file3 = file;
                            inputStream = inputStream3;
                        } catch (Throwable th5) {
                            inputStream2 = inputStream3;
                            file4 = file7;
                            th = th5;
                            httpURLConnection2 = httpURLConnection3;
                        }
                    } catch (Exception e8) {
                        file3 = file;
                        inputStream = null;
                        fileOutputStream = null;
                        z = false;
                        file2 = file7;
                        httpURLConnection = httpURLConnection3;
                        e = e8;
                    } catch (Throwable th6) {
                        file4 = file7;
                        httpURLConnection2 = httpURLConnection3;
                        th = th6;
                    }
                } catch (Exception e9) {
                    httpURLConnection = httpURLConnection3;
                    e = e9;
                    z = false;
                    file2 = null;
                    file3 = file;
                    inputStream = null;
                    fileOutputStream = null;
                } catch (Throwable th7) {
                    httpURLConnection2 = httpURLConnection3;
                    th = th7;
                }
            } catch (Exception e10) {
                inputStream = null;
                fileOutputStream = null;
                file3 = null;
                httpURLConnection = httpURLConnection3;
                e = e10;
                z = false;
                file2 = null;
            } catch (Throwable th8) {
                file = null;
                httpURLConnection2 = httpURLConnection3;
                th = th8;
            }
        } catch (Exception e11) {
            e = e11;
            inputStream = null;
            fileOutputStream = null;
            httpURLConnection = null;
            z = false;
            file2 = null;
            file3 = null;
        } catch (Throwable th9) {
            th = th9;
            file = null;
        }
    }
}
