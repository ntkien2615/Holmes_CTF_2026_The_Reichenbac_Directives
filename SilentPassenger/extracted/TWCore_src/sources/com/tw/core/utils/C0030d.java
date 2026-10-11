package com.tw.core.utils;

import android.os.Environment;
import com.tw.core.app.DoFunApplication;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.tw.core.utils.d, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0030d {
    private static final String eq = C0030d.class.getSimpleName();
    private String ep = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;

    public static List gR(File file, FileFilter fileFilter) {
        ArrayList arrayList = new ArrayList();
        if (!gO(file)) {
            return arrayList;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (File file2 : listFiles) {
                if (fileFilter == null || fileFilter.accept(file2)) {
                    arrayList.add(file2);
                }
            }
        }
        return arrayList;
    }

    public static long gM(String str) {
        FileInputStream fileInputStream;
        if (gQ(str)) {
            Long.valueOf(0L);
            try {
                try {
                    fileInputStream = new FileInputStream(new File(str));
                    try {
                        long longValue = Long.valueOf(fileInputStream.available()).longValue();
                        H.jx(fileInputStream);
                        return longValue;
                    } catch (Exception e) {
                        e = e;
                        e.printStackTrace();
                        H.jx(fileInputStream);
                        return 0L;
                    }
                } catch (Throwable th) {
                    th = th;
                    H.jx(null);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                fileInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                H.jx(null);
                throw th;
            }
        }
        return 0L;
    }

    public static boolean gP(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        return file.isFile();
    }

    public static boolean gQ(String str) {
        return gP(new File(str));
    }

    public static boolean gO(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        return file.isDirectory();
    }

    public static boolean gL(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static void gV(String str, String str2, boolean z) {
        OutputStreamWriter outputStreamWriter;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(gI(str), z);
            try {
                outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                try {
                    outputStreamWriter.write(str2 + "\n");
                    outputStreamWriter.flush();
                    fileOutputStream.getFD().sync();
                    H.jx(fileOutputStream);
                    H.jx(outputStreamWriter);
                } catch (IOException e) {
                    e = e;
                    fileOutputStream2 = fileOutputStream;
                    try {
                        e.printStackTrace();
                        H.jx(fileOutputStream2);
                        H.jx(outputStreamWriter);
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        H.jx(fileOutputStream);
                        H.jx(outputStreamWriter);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    H.jx(fileOutputStream);
                    H.jx(outputStreamWriter);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                outputStreamWriter = null;
                fileOutputStream2 = fileOutputStream;
            } catch (Throwable th3) {
                th = th3;
                outputStreamWriter = null;
            }
        } catch (IOException e3) {
            e = e3;
            outputStreamWriter = null;
        } catch (Throwable th4) {
            th = th4;
            outputStreamWriter = null;
            fileOutputStream = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x005a A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0070  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String gT(java.lang.String r6) {
        /*
            r2 = 0
            java.lang.String r0 = ""
            java.io.File r0 = new java.io.File
            r0.<init>(r6)
            boolean r1 = r0.exists()
            if (r1 == 0) goto L54
            boolean r1 = r0.isFile()
            if (r1 == 0) goto L54
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L7d
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L7d
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L81
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L81
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L81
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L81
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L87
            r4.<init>()     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L87
        L29:
            java.lang.String r0 = r1.readLine()     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L78
            if (r0 == 0) goto L5e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L78
            r2.<init>()     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L78
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L78
            java.lang.String r2 = "\n"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L78
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L78
            r4.append(r0)     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L78
            goto L29
        L47:
            r0 = move-exception
            r2 = r3
            r3 = r4
        L4a:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L7a
            com.tw.core.utils.H.jx(r2)
            com.tw.core.utils.H.jx(r1)
            r2 = r3
        L54:
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 == 0) goto L70
            java.lang.String r0 = ""
        L5d:
            return r0
        L5e:
            com.tw.core.utils.H.jx(r3)
            com.tw.core.utils.H.jx(r1)
            r2 = r4
            goto L54
        L66:
            r0 = move-exception
            r1 = r2
            r3 = r2
        L69:
            com.tw.core.utils.H.jx(r3)
            com.tw.core.utils.H.jx(r1)
            throw r0
        L70:
            java.lang.String r0 = r2.toString()
            goto L5d
        L75:
            r0 = move-exception
            r1 = r2
            goto L69
        L78:
            r0 = move-exception
            goto L69
        L7a:
            r0 = move-exception
            r3 = r2
            goto L69
        L7d:
            r0 = move-exception
            r1 = r2
            r3 = r2
            goto L4a
        L81:
            r0 = move-exception
            r1 = r2
            r5 = r3
            r3 = r2
            r2 = r5
            goto L4a
        L87:
            r0 = move-exception
            r5 = r3
            r3 = r2
            r2 = r5
            goto L4a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tw.core.utils.C0030d.gT(java.lang.String):java.lang.String");
    }

    public static File gI(String str) {
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

    public static boolean gH(File file) {
        if (file != null) {
            return file.exists() ? file.isDirectory() : file.mkdirs();
        }
        return false;
    }

    public static boolean gU(File file, String str) {
        if (file == null || !file.exists()) {
            return false;
        }
        if (str.equals(file.getName())) {
            return true;
        }
        File file2 = new File(file.getParent() + File.separator + str);
        if (file2.exists()) {
            return false;
        }
        return file.renameTo(file2);
    }

    public static void gK(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    for (File file2 : listFiles) {
                        gK(file2);
                    }
                }
                file.delete();
                return;
            }
            if (file.isFile()) {
                file.delete();
            }
        }
    }

    public static long gN(File file) {
        if (file != null && file.exists() && file.isFile()) {
            Long.valueOf(0L);
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                Long valueOf = Long.valueOf(fileInputStream.available());
                fileInputStream.close();
                return valueOf.longValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0L;
    }

    public static File gJ(String str, String str2) {
        File file = new File(gS(str), str2);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public static File gS(String str) {
        File file;
        try {
            file = new File(gG() + str);
            try {
                if (!file.exists()) {
                    file.mkdirs();
                }
            } catch (Exception e) {
                e = e;
                C0038l.hF(eq, e);
                return file;
            }
        } catch (Exception e2) {
            e = e2;
            file = null;
        }
        return file;
    }

    public static String gG() {
        File externalCacheDir;
        String str = null;
        if (("mounted".equals(Environment.getExternalStorageState()) || (!Environment.isExternalStorageRemovable())) && (externalCacheDir = DoFunApplication.nF().getExternalCacheDir()) != null) {
            str = externalCacheDir.getPath();
        }
        if (str == null) {
            return DoFunApplication.nF().getFilesDir().getPath();
        }
        return str;
    }
}
