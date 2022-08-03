package dev.canlapan.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection createConnection(){

        try { //AZURE_SQL_DB
            Connection conn = DriverManager.getConnection("jdbc:postgresql://canlapan-sql-server.postgres.database.azure.com:5432/project0db?user=Jaleckcn&password=Drumroll1993!&ssl=false");
            return conn;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
