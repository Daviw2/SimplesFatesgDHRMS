package com.fatesg.config;

public record RmiConfig() {
    public static final String RMI_HOST = System.getenv("FATESG_RMI_HOST") != null ? System.getenv("FATESG_RMI_HOST") : "localhost";
    public static final int RMI_PORT = System.getenv("FATESG_RMI_PORT") != null ? Integer.parseInt(System.getenv("FATESG_RMI_PORT")) : 1099;
    
    public static final String RMI_HOST_SECOND = System.getenv("FATESG_RMI_HOST_SECOND") != null ? System.getenv("FATESG_RMI_HOST_SECOND") : "localhost";
    public static final int RMI_PORT_SECOND = System.getenv("FATESG_RMI_PORT_SECOND") != null ? Integer.parseInt(System.getenv("FATESG_RMI_PORT_SECOND")) : 1098;
    
    public static final String RMI_SERVICE_NAME = System.getenv("FATESG_RMI_SERVICE_NAME") != null ? System.getenv("FATESG_RMI_SERVICE_NAME") : "ServidorDeDados";
}
