package com.a.b.a;

import android.content.Context;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bn extends bt {
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private JSONArray o;
    private boolean p;
    private boolean q;
    private static final String s = aq.a("9Ny\u0081xvykn9vsl}", 10);
    private static final HashMap<String, Object> r = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(bn bnVar) {
        Class<?>[] clsArr;
        Object[] objArr = null;
        try {
            try {
                Object obj = r.get(bnVar.j);
                Class<?> cls = obj;
                if (obj == null) {
                    String str = bnVar.b.getFilesDir() + aq.a("76i76qw", 8);
                    String str2 = aq.a("5", 7) + bnVar.j;
                    File file = new File(str, str2);
                    if (!file.exists()) {
                        File file2 = new File(bnVar.f(), bnVar.h + aq.a("3", 5) + bnVar.j + aq.a("7sj{", 9));
                        if (file2.exists()) {
                            b(file2.getAbsolutePath(), file.getAbsolutePath());
                            file2.delete();
                        } else {
                            r.a();
                            r.a(bnVar.i, str, str2);
                        }
                    }
                    File file3 = new File(str, str2 + aq.a("5qhy", 7));
                    b(file.getAbsolutePath(), file3.getAbsolutePath());
                    if (file3.exists()) {
                        file3.setReadOnly();
                        Class loadClass = new DexClassLoader(file3.getAbsolutePath(), bnVar.f(), null, ClassLoader.getSystemClassLoader()).loadClass(bnVar.k);
                        Object obj2 = loadClass;
                        if (bnVar.n != null) {
                            obj2 = bnVar.n.equals(aq.a("pgy", 2)) ? loadClass.newInstance() : loadClass.getMethod(bnVar.n, new Class[0]).invoke(loadClass, new Object[0]);
                        }
                        file3.delete();
                        cls = obj2;
                    } else {
                        aq.a("qtfiqng", 5);
                        aq.a("qng%斌任丒孝圭", 5);
                        cls = null;
                    }
                }
                if (cls != null) {
                    if (bnVar.o != null) {
                        clsArr = new Class[bnVar.o.length()];
                        Object[] objArr2 = new Object[bnVar.o.length()];
                        for (int i = 0; i < bnVar.o.length(); i++) {
                            JSONObject optJSONObject = bnVar.o.optJSONObject(i);
                            String string = optJSONObject.getString(aq.a("y~uj", 5));
                            clsArr[i] = string.equals(aq.a("Htsyj}y", 5)) ? Context.class : string.equals(aq.a("\\}{rwp", 9)) ? String.class : string.equals(aq.a("mrx", 4)) ? Integer.TYPE : string.equals(aq.a("svun", 7)) ? Long.TYPE : null;
                            String string2 = optJSONObject.getString(aq.a("}\u0082yn", 9));
                            objArr2[i] = string2.equals(aq.a("Htsyj}y", 5)) ? bnVar.b : string2.equals(aq.a("\\}{rwp", 9)) ? optJSONObject.optString(aq.a("zepyi", 4)) : string2.equals(aq.a("jou", 1)) ? Integer.valueOf(optJSONObject.optInt(aq.a("xcnwg", 2))) : string2.equals(aq.a("mpoh", 1)) ? Long.valueOf(optJSONObject.optLong(aq.a("|gr{k", 6))) : null;
                        }
                        objArr = objArr2;
                    } else {
                        clsArr = null;
                    }
                    Method method = (cls instanceof Class ? cls : cls.getClass()).getMethod(bnVar.l, clsArr);
                    if (bnVar.p) {
                        method.invoke(cls, objArr);
                        r.put(bnVar.j, cls);
                    } else {
                        m.a().a(new bq(bnVar, method, cls, objArr));
                    }
                }
            } catch (Exception e) {
                aq.a("ordgole", 3);
                new StringBuilder().append(aq.a("twiltqj(mzzwz:(", 8)).append(e.getMessage());
            }
            bnVar.e();
        } catch (Exception e2) {
        }
    }

    private static void b(String str, String str2) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(str2);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            fileInputStream.close();
                            return;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                } catch (Throwable th) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } finally {
            }
        } catch (IOException e) {
        }
    }

    private void e() {
        if (this.m == null) {
            return;
        }
        for (String str : this.m.split(aq.a("/", 3))) {
            File[] listFiles = new File(f()).listFiles(new bp(this, str));
            for (File file : listFiles) {
                file.delete();
            }
        }
    }

    private String f() {
        return this.b.getFilesDir() + s;
    }

    @Override // com.a.b.a.bt
    public final void a() {
        this.h = (String) e(aq.a("reqi", 4));
        this.i = (String) e(aq.a("vsm", 1));
        this.j = (String) e(aq.a("wn?", 10));
        this.m = (String) e(aq.a("irkgx", 6));
        this.o = (JSONArray) e(aq.a("sdudpv", 3));
        this.k = (String) e(aq.a("kti{{Vium", 8));
        this.l = (String) e(aq.a("qixlsh", 4));
        this.n = (String) e(aq.a("dnfuipe", 1));
        this.p = ((Boolean) e(aq.a("znxkgj", 6))).booleanValue();
        Boolean bool = (Boolean) e(aq.a("sfmpbe", 1));
        this.q = bool == null ? false : bool.booleanValue();
    }

    @Override // com.a.b.a.bt
    public final String b() {
        if (!r.containsKey(this.j) || this.q) {
            bd.a(new bo(this));
            return aq.a(":", 9);
        }
        aq.a("twiltqj", 8);
        new StringBuilder().append(aq.a("rohy&", 6)).append(this.j).append(aq.a("&rugjkj", 6));
        return aq.a("9", 8);
    }
}
