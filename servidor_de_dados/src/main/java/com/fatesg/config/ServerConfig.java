package com.fatesg.config;

public record ServerConfig() {
    public static final String RMI_HOST = "localhost";
    public static final int RMI_PORT = System.getenv("FATESG_RMI_PORT") != null ? Integer.parseInt(System.getenv("FATESG_RMI_PORT")) : 1099;
}
