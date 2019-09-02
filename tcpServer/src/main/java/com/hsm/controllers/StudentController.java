package com.hsm.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.hsm.dbConnection.DBConnection;
import com.hsm.models.StudentDTO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentController {
    public static boolean addStudent(JsonObject object) throws SQLException, ClassNotFoundException, SQLException, IOException {
        ObjectMapper mapper=new ObjectMapper();
       StudentDTO student = mapper.readValue(object.toString(), StudentDTO.class);
        String sql = "insert into student values(?,?,?,?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, student.getNic());
        stm.setObject(2, student.getName());
        stm.setObject(3, student.getAddress());
        stm.setObject(4, student.getTel());

        return stm.executeUpdate() > 0;
    }
}
