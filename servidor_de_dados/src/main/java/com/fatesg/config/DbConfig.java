package com.fatesg.config;

public record DbConfig() {
        // TODO: Mover para um arquivo de configuração
        public static final String URL = "jdbc:mysql://localhost:3306/employees";
        public static final String USERNAME = "root";
        public static final String PASSWORD = "20202290882"; 
        public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
        public static final int DEFAULT_LIMIT = 15;
}