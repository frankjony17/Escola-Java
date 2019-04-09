package com.example.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="student")
public class Student implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nota")
    private Float nota;

    @Column(name="list_number")
    private int listNumber;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="person_id", nullable=false)
    private Person person;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="group_id", nullable=false)
    @JsonIgnore
    private Group group;

    @ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="student_teacher",
            joinColumns={@JoinColumn(name="student_id")},
            inverseJoinColumns={@JoinColumn(name="teacher_id")})
    private List<Teacher> teachers;
}
