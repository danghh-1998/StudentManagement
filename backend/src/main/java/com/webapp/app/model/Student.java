package com.webapp.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webapp.app.constant.GenderType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String name;

    @Enumerated(value = EnumType.STRING)
    private GenderType gender;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotNull
    private String className;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @JoinTable(name = "registration", joinColumns = @JoinColumn(columnDefinition = "userId"), inverseJoinColumns = @JoinColumn(columnDefinition = "subjectId"))
    private Collection<Subject> subjects;

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
