package com.hms.utils;

public class HashUtils {
    public static boolean isBcryptHash(String s) {
        return s != null && (s.startsWith("$2a$") || s.startsWith("$2b$") || s.startsWith("$2y$"));
    }
}
