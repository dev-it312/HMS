package com.hms.services;

import com.hms.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String hashIfNeeded(String password) {
        return switch (password) {
            case null -> null;
            case String s when HashUtils.isBcryptHash(s) -> s;
            default -> passwordEncoder.encode(password);
        };
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        if (rawPassword == null || encodedPassword == null) return false;
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}