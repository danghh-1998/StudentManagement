package com.webapp.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "subjects")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Student> students;

    @NotNull
    @Positive
    private int numLessons;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private Date createdAt;

    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}