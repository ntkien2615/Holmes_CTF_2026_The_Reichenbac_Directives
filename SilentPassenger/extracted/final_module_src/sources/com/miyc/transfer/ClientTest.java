package com.miyc.transfer;

import java.io.DataInputStream;

/* loaded from: classes.dex */
public class ClientTest {
    private static String pingIP(String str) {
        try {
            Process exec = Runtime.getRuntime().exec(com.miyc.transfer.a.a.a("5y\u007fyzks5hot5votm&3i&9&", 6) + str);
            exec.waitFor();
            return readInput(exec);
        } catch (Exception e) {
            return "";
        }
    }

    public static String readInput(Process process) {
        DataInputStream dataInputStream = new DataInputStream(process.getInputStream());
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = dataInputStream.readLine();
            if (readLine == null) {
                return sb.toString();
            }
            sb.append(readLine).append("\n");
        }
    }

    public static void start(t tVar, String str, String str2, int i, int i2, int i3, int i4) {
        if (!str2.equals(com.miyc.transfer.a.a.a("MNK^P", 9))) {
            new Thread(new c(tVar, i3)).start();
        }
        new Thread(new d(str2, tVar, str, i, i2, i4)).start();
    }

    public static void start(String str, String str2, int i, int i2, int i3, int i4) {
        start(new k(), str, str2, i, i2, i3, i4);
    }
}
