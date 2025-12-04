package com.hms.services;

import com.hms.utils.HashUtils;
import org.passay.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    // hash if needed (method to hash password only if it's not already hashed)
    public String hashIfNeeded(String password) {
        return switch (password) {
            case null -> null;
            case String s when HashUtils.isBcryptHash(s) -> s;
            default -> passwordEncoder.encode(password);
        };
    }
    
    // control if rawPass matches hashed one
    public boolean matches(String rawPassword, String encodedPassword) {
        if (rawPassword == null || encodedPassword == null) return false;
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * Validation avancée du mot de passe avec Passay
     * Critères : min 8 caractères, 1 majuscule, 1 minuscule, 1 chiffre, 1 spécial, aucun espace
     */
    public boolean isPasswordValidWithPassay(String password) {
        PasswordValidator validator = new PasswordValidator(
            new LengthRule(8, 128),
            new CharacterRule(EnglishCharacterData.UpperCase, 1),
            new CharacterRule(EnglishCharacterData.LowerCase, 1),
            new CharacterRule(EnglishCharacterData.Digit, 1),
            new CharacterRule(EnglishCharacterData.Special, 1),
            new WhitespaceRule() // rejette les espaces
        );
        RuleResult result = validator.validate(new PasswordData(password));
        return result.isValid();
    }

//    
//    // Checks if the password is strong (not NULL, no Space, min 8 chars,with upper, lower, digit, special char)
//    public boolean isStrong(String password) {
//        if (password == null || password.length() < 8) return false; //Reject if null or less than 8 characters
//        boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;
//        for (char c : password.toCharArray()) {
//            if (c == ' ') return false; // Reject if including a espace character
//            switch (Character.getType(c)) {
//                case Character.UPPERCASE_LETTER -> hasUpper = true;
//                case Character.LOWERCASE_LETTER -> hasLower = true;
//                case Character.DECIMAL_DIGIT_NUMBER -> hasDigit = true;
//                default -> {
//                    if (!Character.isLetterOrDigit(c)) hasSpecial = true;
//                }
//            }
//        }
//        return hasUpper && hasLower && hasDigit && hasSpecial;
//    }
    
}