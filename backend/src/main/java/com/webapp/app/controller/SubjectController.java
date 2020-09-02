package com.webapp.app.controller;

import com.webapp.app.exception.DuplicatedException;
import com.webapp.app.exception.NotFoundException;
import com.webapp.app.model.Subject;
import com.webapp.app.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Subject>> listSubjects() {
        return new ResponseEntity<>(this.subjectService.findAllSubjects(), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        List<String> names = this.subjectService.findAllSubjects().stream().map(Subject::getName).collect(Collectors.toList());
        if (names.contains(subject.getName())) {
            throw new DuplicatedException("name", subject.getName());
        }
        this.subjectService.save(subject);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable("id") Integer id) {
        Optional<Subject> subject = this.subjectService.findById(id);
        if (subject.isEmpty()) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(subject.get(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Subject> updateSubject(@RequestBody Subject subject, @PathVariable Integer id) {
        Optional<Subject> currentSubject = this.subjectService.findById(id);
        if (currentSubject.isEmpty()) {
            throw new NotFoundException();
        }
        List<String> names = this.subjectService.findAllSubjects().stream().map(Subject::getName).collect(Collectors.toList());
        if (names.contains(subject.getName()) && !currentSubject.get().getName().equals(subject.getName())) {
            throw new DuplicatedException("name", subject.getName());
        }
        currentSubject.get().setName(subject.getName());
        currentSubject.get().setNumLessons(subject.getNumLessons());
        currentSubject.get().setStartDate(subject.getStartDate());
        currentSubject.get().setEndDate(subject.getEndDate());
        this.subjectService.save(currentSubject.get());
        return new ResponseEntity<>(currentSubject.get(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Subject> deleteSubject(@PathVariable Integer id) {
        Optional<Subject> subject = this.subjectService.findById(id);
        if (subject.isEmpty()) {
            throw new NotFoundException();
        }
        this.subjectService.remove(subject.get());
        return new ResponseEntity<>(subject.get(), HttpStatus.OK);
    }
}
