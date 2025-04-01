package ru.ifmo.se.general.contract;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Static class for hashing data by SHA256. Support salt.
 *
 * @since 3.0
 * @author safarislava
 */
public class Hashing {
    private static final int KEY_SIZE = 6;

    /**
     * Generate salt.
     *
     * @return Value of salt
     */
    public static String getSalt() {
        String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();

        StringBuilder sb = new StringBuilder(KEY_SIZE);
        for (int i = 0; i < KEY_SIZE; i++)
            sb.append(alphabet.charAt(rnd.nextInt(alphabet.length())));

        return sb.toString();
    }

    /**
     * Hashing data
     *
     * @param data Value of data
     * @return Value of hash
     */
    public static String getHash(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] encodedHash = digest.digest(data.getBytes(StandardCharsets.UTF_8));

            return bytesToHex(encodedHash);
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convert byte array to hex-string
     *
     * @param hash Array of bytes
     * @return Value of hex-string
     */
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        
        return hexString.toString();
    }
}
