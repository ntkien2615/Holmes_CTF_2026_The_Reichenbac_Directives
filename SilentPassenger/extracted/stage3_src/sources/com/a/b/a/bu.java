package com.a.b.a;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* loaded from: classes.dex */
public class bu extends bt {
    private static final String h = aq.a(",C@AJvsq$-,C>_41=a\u007f507\u0081`2-\u007f7\u0081_41=a\u007f507\u0081", 4);
    private static final String i = aq.a("*A>?htqo\"+0,*A?<\"keoraugs?3\"vvn?+", 2);
    private static final String j = aq.a("/FCD{ptlD051/FD'tz40", 7);
    private String[] k;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0135 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(java.lang.String r13, int r14) {
        /*
            Method dump skipped, instructions count: 346
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.b.a.bu.a(java.lang.String, int):java.lang.String");
    }

    private static String b(String str) {
        Process process;
        Throwable th;
        BufferedReader bufferedReader;
        Process process2;
        String str2;
        try {
            process = Runtime.getRuntime().exec(aq.a("rkpi\"/e\"3\"", 2) + str);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                str2 = "";
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        str2 = str2 + readLine;
                    } catch (Exception e) {
                        process2 = process;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e2) {
                            }
                        }
                        process2.destroy();
                        return str2;
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e3) {
                                throw th;
                            }
                        }
                        process.destroy();
                        throw th;
                    }
                }
                bufferedReader.close();
                process.waitFor();
                try {
                    bufferedReader.close();
                    process.destroy();
                } catch (Exception e4) {
                }
            } catch (Exception e5) {
                bufferedReader = null;
                str2 = "";
                process2 = process;
            } catch (Throwable th3) {
                bufferedReader = null;
                th = th3;
            }
        } catch (Exception e6) {
            bufferedReader = null;
            process2 = null;
            str2 = "";
        } catch (Throwable th4) {
            process = null;
            th = th4;
            bufferedReader = null;
        }
        return str2;
    }

    @Override // com.a.b.a.bt
    public final void a() {
        this.k = ((String) e(aq.a("jquv", 2))).split(aq.a("1", 5));
    }

    @Override // com.a.b.a.bt
    public final String b() {
        for (int i2 = 0; i2 < this.k.length; i2++) {
            bd.a(new bv(this, this.k[i2]));
        }
        return aq.a("8", 7);
    }
}
