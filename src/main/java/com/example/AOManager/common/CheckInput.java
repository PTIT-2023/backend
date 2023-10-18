package com.example.AOManager.common;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckInput {

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

    public static boolean isDateValid(long timestamp) {
        try {
            Instant instant = Instant.ofEpochMilli(timestamp);
            LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            return true; // Nếu không có lỗi, thì timestamp hợp lệ
        } catch (Exception e) {
            return false; // Nếu có lỗi, thì timestamp không hợp lệ
        }
    }

    public static boolean isValidDateForPriceDetail(long timestamp) {
        try {
            Instant instant = Instant.ofEpochMilli(timestamp);
            LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now(); // Lấy thời điểm hiện tại

            if (date.isAfter(currentDate)) {
                return true; // Kiểm tra ngày hợp lệ và từ ngày hiện tại trở đi
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
