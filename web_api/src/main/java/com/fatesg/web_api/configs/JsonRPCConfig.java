package com.fatesg.web_api.configs;

public class JsonRPCConfig {
    public static final String JSON_RPC_VERSION = "2.0";
    public static final String JSON_RPC_SERVER_HOST = System.getenv("FATESG_JSON_RPC_SERVER_HOST") != null ? System.getenv("FATESG_JSON_RPC_SERVER_HOST") : "localhost";
    public static final short JSON_RPC_SERVER_PORT = System.getenv("FATESG_JSON_RPC_SERVER_PORT") != null ? Short.parseShort(System.getenv("FATESG_JSON_RPC_SERVER_PORT")) : 1198;
    
    public static final String JSON_RPC_SERVER_HOST2 = System.getenv("FATESG_JSON_RPC_SERVER_HOST2") != null ? System.getenv("FATESG_JSON_RPC_SERVER_HOST2") : "localhost";
    public static final short JSON_RPC_SERVER_PORT2 = System.getenv("FATESG_JSON_RPC_SERVER_PORT2") != null ? Short.parseShort(System.getenv("FATESG_JSON_RPC_SERVER_PORT2")) : 1199;
}
