package com.webapp.app.controller;

import com.webapp.app.exception.AlreadyRegisteredException;
import com.webapp.app.exception.DuplicatedException;
import com.webapp.app.exception.NotFoundException;
import com.webapp.app.exception.NotregisteredException;
import com.webapp.app.model.Student;
import com.webapp.app.model.Subject;
import com.webapp.app.service.StudentService;
import com.webapp.app.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/students")
public class StudentController {
    private final StudentService studentService;
    private final SubjectService subjectService;

    @Autowired
    public StudentController(StudentService studentService, SubjectService subjectService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Student>> listStudents() {
        return new ResponseEntity<>(this.studentService.findAllStudents(), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        List<String> emails = this.studentService.findAllStudents().stream().map(Student::getEmail).collect(Collectors.toList());
        if (emails.contains(student.getEmail())) {
            throw new DuplicatedException("email", student.getEmail());
        }
        this.studentService.save(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") Integer id) {
        Optional<Student> student = this.studentService.findById(id);
        if (student.isEmpty()) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") Integer id) {
        Optional<Student> currentStudent = this.studentService.findById(id);
        if (currentStudent.isEmpty()) {
            throw new NotFoundException();
        }
        List<String> emails = this.studentService.findAllStudents().stream().map(Student::getEmail).collect(Collectors.toList());
        if (emails.contains(student.getEmail()) && !currentStudent.get().getEmail().equals(student.getEmail())) {
            throw new DuplicatedException("email", student.getEmail());
        }
        currentStudent.get().setName(student.getName());
        currentStudent.get().setEmail(student.getEmail());
        currentStudent.get().setBirthday(student.getBirthday());
        currentStudent.get().setGender(student.getGender());
        this.studentService.save(currentStudent.get());
        return new ResponseEntity<>(currentStudent.get(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer id) {
        Optional<Student> student = this.studentService.findById(id);
        if (student.isEmpty()) {
            throw new NotFoundException();
        }
        this.studentService.remove(student.get());
        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/register/{subjectId}")
    public ResponseEntity<Student> registerStudent(@PathVariable("id") Integer id, @PathVariable("subjectId") Integer subjectId) {
        Optional<Student> student = this.studentService.findById(id);
        if (student.isEmpty()) {
            throw new NotFoundException();
        }
        Optional<Subject> subject = this.subjectService.findById(subjectId);
        if (subject.isEmpty()) {
            throw new NotFoundException();
        }
        if (student.get().getSubjects().contains(subject.get())) {
            throw new AlreadyRegisteredException(student.get().getName(), subject.get().getName());
        }
        student.get().getSubjects().add(subject.get());
        this.studentService.save(student.get());
        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/register/{subjectId}")
    public ResponseEntity<Student> cancelRegisterStudent(@PathVariable("id") Integer id, @PathVariable("subjectId") Integer subjectId) {
        Optional<Student> student = this.studentService.findById(id);
        if (student.isEmpty()) {
            throw new NotFoundException();
        }
        Optional<Subject> subject = this.subjectService.findById(subjectId);
        if (subject.isEmpty()) {
            throw new NotFoundException();
        }
        if (!student.get().getSubjects().contains(subject.get())) {
            throw new NotregisteredException(student.get().getName(), subject.get().getName());
        }
        student.get().getSubjects().remove(subject.get());
        this.studentService.save(student.get());
        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/subjects")
    public ResponseEntity<List<Subject>> listRegisteredSubject(@PathVariable("id") Integer id) {
        Optional<Student> student = this.studentService.findById(id);
        if (student.isEmpty()) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>((List<Subject>) student.get().getSubjects(), HttpStatus.OK);
    }
}
