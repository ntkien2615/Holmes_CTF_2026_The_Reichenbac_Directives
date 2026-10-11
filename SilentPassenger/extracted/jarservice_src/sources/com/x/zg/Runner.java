package com.x.zg;

import java.io.File;
import java.security.MessageDigest;
import java.nio.file.Files;

public class Runner {
    public static void main(String[] args) throws Exception {
        m res = n.a("reconstructed_stage.bin");
        System.out.println("b (version): " + res.b);
        System.out.println("c: " + res.c);
        System.out.println("d (entry class): " + res.d);
        System.out.println("e (entry method): " + res.e);
        System.out.println("file: " + res.a.getAbsolutePath());
        
        byte[] data = Files.readAllBytes(new File("reconstructed_stage.bin").toPath());
        System.out.println("Reconstructed size: " + data.length);
        
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] hash = sha256.digest(data);
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        System.out.println("SHA-256: " + sb.toString());
    }
}
