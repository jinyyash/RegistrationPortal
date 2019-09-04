package com.hsm.serviceImpl;

import com.hsm.models.Student;
import com.hsm.repository.StudentRepository;
import com.hsm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.SQLException;

@Transactional
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public int save(Student student) throws SQLException, ClassNotFoundException, IOException {

        return studentRepository.save(student);
    }
}
