package com.hsm.repository;

import com.hsm.models.Student;

import java.io.IOException;
import java.sql.SQLException;

public interface StudentRepository {
    int save(Student student) throws SQLException, ClassNotFoundException, IOException;
}
