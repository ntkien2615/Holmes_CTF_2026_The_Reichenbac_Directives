package com.tw.core.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes.dex */
public class B implements Thread.UncaughtExceptionHandler {
    private static B fc;
    private Context fg;
    private Thread.UncaughtExceptionHandler fh;
    private Map ff = new HashMap();
    private DateFormat fe = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault());
    String fi = "/.crash/";
    String fd = "twcore_log.txt";

    private B() {
    }

    public static B ja() {
        if (fc == null) {
            synchronized (B.class) {
                fc = new B();
            }
        }
        return fc;
    }

    public void jb(Context context) {
        this.fg = context;
        this.fh = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        jj();
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        if (!ji(th) && this.fh != null) {
            this.fh.uncaughtException(thread, th);
        } else if (Looper.myLooper() == Looper.getMainLooper()) {
            je();
            C0034h.hs("CrashHandlerUtils", "kill self", new Object[0]);
            Process.killProcess(Process.myPid());
            System.exit(1);
        }
    }

    private void je() {
        try {
            String gt = C0029c.gt(this.fg, "KEY_CRASH_TIME", null);
            C0034h.hs("CrashHandlerUtils", "异常时间 %s", gt);
            long currentTimeMillis = System.currentTimeMillis();
            if (!TextUtils.isEmpty(gt)) {
                if (gt.contains(",")) {
                    String[] split = gt.split(",");
                    if (split.length > 2) {
                        long parseLong = Long.parseLong(split[0]);
                        long parseLong2 = Long.parseLong(split[1]);
                        long parseLong3 = Long.parseLong(split[2]);
                        C0034h.hr("CrashHandlerUtils", "两次异常的时间 %s --- %s 结果 --- %s", Long.valueOf(parseLong), Long.valueOf(parseLong3), Long.valueOf(parseLong3 - parseLong));
                        if (parseLong3 - parseLong <= 60000) {
                            C0034h.hs("CrashHandlerUtils", "pm clear %s", this.fg.getPackageName());
                            C0029c.gF(this.fg, "KEY_CRASH_TIME", null);
                            Runtime.getRuntime().exec("pm clear " + this.fg.getPackageName());
                        } else {
                            String str = parseLong2 + "," + parseLong3 + "," + currentTimeMillis;
                            C0029c.gF(this.fg, "KEY_CRASH_TIME", str);
                            C0034h.hs("CrashHandlerUtils", "replace third crash %s", str);
                        }
                    } else {
                        String str2 = gt + "," + currentTimeMillis;
                        C0029c.gF(this.fg, "KEY_CRASH_TIME", str2);
                        C0034h.hs("CrashHandlerUtils", "add third crash %s", str2);
                    }
                } else {
                    String str3 = gt + "," + currentTimeMillis;
                    C0029c.gF(this.fg, "KEY_CRASH_TIME", str3);
                    C0034h.hs("CrashHandlerUtils", "add second crash %s", str3);
                }
            } else {
                C0029c.gF(this.fg, "KEY_CRASH_TIME", String.valueOf(currentTimeMillis));
                C0034h.hs("CrashHandlerUtils", "add first crash %s", Long.valueOf(currentTimeMillis));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean ji(Throwable th) {
        if (th == null) {
            return false;
        }
        jf(this.fg);
        jd(th);
        return true;
    }

    public void jf(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 1);
            if (packageInfo != null) {
                String str = packageInfo.versionName == null ? "null" : packageInfo.versionName;
                String str2 = packageInfo.versionCode + "";
                String format = this.fe.format(new Date());
                this.ff.put("versionName", str);
                this.ff.put("versionCode", str2);
                this.ff.put("crashTime", format);
                this.ff.put("deviceID", com.tw.core.e.b.le());
            }
        } catch (Exception e) {
            C0034h.hs("CrashHandlerUtils", "an error occured when collect package info", e);
        }
        for (Field field : Build.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                this.ff.put(field.getName(), field.get(null).toString());
                C0034h.hs("CrashHandlerUtils", "%s : %s", field.getName(), field.get(null));
            } catch (Exception e2) {
                C0034h.hs("CrashHandlerUtils", "an error occured when collect crash info", e2);
            }
        }
    }

    public String jd(Throwable th) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry entry : this.ff.entrySet()) {
            stringBuffer.append(((String) entry.getKey()) + "=" + ((String) entry.getValue()) + "\n");
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        printWriter.close();
        stringBuffer.append(stringWriter.toString());
        C0034h.hs("CrashHandlerUtils", "异常日志 %s", stringBuffer.toString());
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                File file = new File(Environment.getExternalStorageDirectory() + this.fi);
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file, this.fd);
                C0034h.hs("CrashHandlerUtils", "文件保存路径 %s", file2.getCanonicalPath());
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file2, true);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                outputStreamWriter.write(stringBuffer.toString() + "\n");
                outputStreamWriter.flush();
                outputStreamWriter.close();
                fileOutputStream.close();
                C0034h.hs("CrashHandlerUtils", "写入成功", new Object[0]);
            }
            return this.fd;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private File jh() {
        String str = Environment.getExternalStorageDirectory() + this.fi + this.fd;
        C0034h.hs("CrashHandlerUtils", "文件读取路径 %s", str);
        File file = new File(str);
        if (!file.exists()) {
            C0034h.hs("CrashHandlerUtils", "文件不存在", new Object[0]);
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "utf8"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    C0034h.hs("CrashHandlerUtils", "文件内容 %s", sb.toString());
                    bufferedReader.close();
                    fileInputStream.close();
                    return file;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jg() {
        File file = new File(Environment.getExternalStorageDirectory() + this.fi + this.fd);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    private void jj() {
        File file = new File(Environment.getExternalStorageDirectory() + this.fi + this.fd);
        if (C0030d.gN(file) > 1048576) {
            file.delete();
        }
    }

    public void jc() {
        File jh = jh();
        if (jh != null && jh.exists() && jh.isFile()) {
            HashMap hashMap = new HashMap();
            String str = "twlog/" + com.tw.core.e.d.lA(this.fg) + MqttTopic.TOPIC_LEVEL_SEPARATOR + K.ke(System.currentTimeMillis(), "yyyy-MM-dd") + MqttTopic.TOPIC_LEVEL_SEPARATOR + com.tw.core.e.b.le() + MqttTopic.TOPIC_LEVEL_SEPARATOR;
            hashMap.put("key", str + System.currentTimeMillis() + jh.getName());
            HashMap hashMap2 = new HashMap();
            hashMap2.put("file", jh);
            C0034h.hr("CrashHandlerUtils", "错误日志上报 %s", str);
            C0032f.hm("http://dofun-log.oss-cn-shenzhen.aliyuncs.com", hashMap, hashMap2, new ae(this));
        }
    }
}
