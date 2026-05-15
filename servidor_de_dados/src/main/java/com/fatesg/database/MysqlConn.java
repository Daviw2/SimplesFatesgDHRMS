package com.fatesg.database;

import java.sql.Connection;
import java.sql.SQLException;

import com.fatesg.config.DbConfig;

public class MysqlConn {
    private static MysqlConn instance = null;
    private Connection _conn = null;

    public MysqlConn() {
        try {
            Class.forName(DbConfig.DRIVER).getDeclaredConstructor().newInstance();
            _conn = java.sql.DriverManager.getConnection(DbConfig.URL, DbConfig.USERNAME, DbConfig.PASSWORD);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        if(instance == null) {
            instance = new MysqlConn();
            return instance._conn;
        }
        return instance._conn;
    }

    public static void close() {
        if(instance != null) {
            try {
                instance._conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
