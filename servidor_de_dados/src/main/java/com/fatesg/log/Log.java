package com.fatesg.log;

public class Log {
    public static void info(String message) {
        var tempo = java.time.LocalDateTime.now();
        System.out.println("[INFO] " + tempo + " - " + message);
    }

    public static void error(String message) {
        var tempo = java.time.LocalDateTime.now();
        System.err.println("[ERROR] " + tempo + " - " + message);
    }
}
