package com.example.AOManager.common;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckString {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    public static boolean stringIsNullOrEmpty (String string) {
        return null == string || string.equals("");
    }

    public static boolean isEmail(String string) {
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean isValidUUID(String uuidString) {
        try {
            UUID uuid = UUID.fromString(uuidString);
            return true; // Nếu không có ngoại lệ, chuỗi là UUID hợp lệ
        } catch (IllegalArgumentException e) {
            return false; // Nếu có ngoại lệ, chuỗi không phải là UUID hợp lệ
        }
    }
}
