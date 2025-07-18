package me.pajic.affogatotweaks.shaderlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class SHA256 {

    private static final Logger LOGGER = LoggerFactory.getLogger("ShaderLock");

    public static String calculateSHA256(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buf = new byte[4096];
            int len;

            while ((len = fis.read(buf)) != -1) {
                md.update(buf, 0, len);
            }
        } catch (IOException e) {
            LOGGER.error("Failed to open file for hash calculation", e);
        }

        var digest = md.digest();
        return HexFormat.of().formatHex(digest);
    }
}