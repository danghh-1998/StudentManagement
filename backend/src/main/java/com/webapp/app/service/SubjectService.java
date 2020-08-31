package com.webapp.app.service;

import com.webapp.app.model.Subject;
import com.webapp.app.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> findAllSubjects() {
        return this.subjectRepository.findAll();
    }

    public Optional<Subject> findById(Integer id) {
        return this.subjectRepository.findById(id);
    }

    public void save(Subject subject) {
        this.subjectRepository.save(subject);
    }

    public void remove(Subject subject) {
        this.subjectRepository.delete(subject);
    }
}

