package com.example.AOManager.common;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Function {

    public static long toLongFromTimeStamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime).getTime();
    }
}
