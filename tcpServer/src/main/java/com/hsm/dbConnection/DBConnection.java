package com.hsm.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection conn;
    private String userName="root";
    private String password="mysql";
    private  String url="jdbc:mysql://localhost:3306/registration";

    private DBConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, userName,password);
    }

    public static synchronized DBConnection getDBConnection() throws SQLException, ClassNotFoundException {
        if (dbConnection == null) {
            dbConnection = new DBConnection();

        }
        return dbConnection;
    }

    public Connection getConnection() {

        return conn;
    }
}
