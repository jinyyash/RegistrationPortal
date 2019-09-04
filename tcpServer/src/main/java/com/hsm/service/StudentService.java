package com.hsm.service;

import com.hsm.models.Student;

import java.io.IOException;
import java.sql.SQLException;

public interface StudentService {
    int save(Student student) throws SQLException, ClassNotFoundException, IOException;
}
