package com.hsm.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.hsm.models.Student;
import com.hsm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;
    public  boolean addStudent(JsonObject object) throws SQLException, ClassNotFoundException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        Student student = mapper.readValue(object.toString(), Student.class);
        int result=studentService.save(student);
        if(result>0){
            return true;
        }else{
            return false;
        }
    }
}
