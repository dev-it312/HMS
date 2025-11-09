package com.hms.services;

import java.util.regex.Pattern;
import org.springframework.stereotype.Service;
import com.hms.exceptions.InvalidEmailFormatException;

@Service
public class EmailValidatorService {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public void validate(String email) {
        if (email == null || !Pattern.matches(EMAIL_REGEX, email)) {
            throw new InvalidEmailFormatException("Format email invalide");
        }
    }
}
