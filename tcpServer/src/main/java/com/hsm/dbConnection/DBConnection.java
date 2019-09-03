package com.hsm.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection conn;

    private DBConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration", "root", "mysql");
    }

    public static DBConnection getDBConnection() throws SQLException, ClassNotFoundException {
        if (dbConnection == null) {
            dbConnection = new DBConnection();

        }
        return dbConnection;
    }

    public Connection getConnection() {

        return conn;
    }
}
