package com.a.b.a;

import android.content.Context;
import android.text.TextUtils;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class an {
    private static String a() {
        InputStream inputStream;
        DataOutputStream dataOutputStream;
        DataInputStream dataInputStream;
        try {
            Socket socket = new Socket(aq.a("45:131314", 3), 12101);
            inputStream = socket.getInputStream();
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(inputStream);
            dataOutputStream.writeInt(25746);
            dataOutputStream.writeByte(101);
            byte[] bytes = aq.a("{}ql4}ql", 8).getBytes();
            dataOutputStream.writeInt(bytes.length);
            dataOutputStream.write(bytes);
            dataOutputStream.flush();
        } catch (IOException e) {
        }
        if (dataInputStream.readInt() != 25746) {
            dataInputStream.close();
            dataOutputStream.close();
            return null;
        }
        byte[] bArr = new byte[dataInputStream.readInt()];
        inputStream.read(bArr);
        String str = new String(bArr);
        dataInputStream.close();
        dataOutputStream.close();
        return str;
    }

    public static String a(Context context) {
        String a = a(a());
        if (!TextUtils.isEmpty(a)) {
            v.a(context, aq.a("[NP;", 9), aq.a("|oq", 10));
            context.getSharedPreferences(aq.a("|~rm", 9), 0).edit().putString(aq.a("xlg", 3), a).commit();
        }
        return a;
    }

    private static String a(String str) {
        String str2 = TextUtils.isEmpty(str) ? "" : aq.a("H~rmF", 9) + str;
        for (int i = 0; i < 3; i++) {
            p a = n.a().a(am.a(i) + am.e + str2, null);
            if (a.a == 200) {
                try {
                    return new JSONObject(a.a()).optString(aq.a("ifyf", 5));
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

    public static String b(Context context) {
        return TextUtils.isEmpty(v.a(context, aq.a("XKM8", 6))) ? a(context) : a();
    }
}
