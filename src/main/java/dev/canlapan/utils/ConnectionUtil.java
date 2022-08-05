package dev.canlapan.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection createConnection(){

        try { //AZURE_SQL_DB
            Connection conn = DriverManager.getConnection(System.getenv("AZURE_SQL_DB"));
            return conn;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
