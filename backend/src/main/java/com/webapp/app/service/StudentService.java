package com.webapp.app.service;

import com.webapp.app.model.Student;
import com.webapp.app.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAllStudents() {
        return this.studentRepository.findAll();
    }

    public Optional<Student> findById(Integer id) {
        return this.studentRepository.findById(id);
    }

    public void save(Student student) {
        this.studentRepository.save(student);
    }

    public void remove(Student student) {
        this.studentRepository.delete(student);
    }
}
