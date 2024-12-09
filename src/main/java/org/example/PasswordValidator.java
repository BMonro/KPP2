package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PasswordValidator {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,20}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    private static boolean isValid(String password) {
        if (password == null) {
            return false;
        }
        return pattern.matcher(password).matches();
    }

    public static List<String> findCandidates(String text) {
        List<String> candidates = new ArrayList<>();

        String[] words = text.split("[\\s,.:]+");

        for (String word : words) {
            if (isValid(word)) {
                candidates.add(word);
            }
        }

        return candidates;
    }
}


