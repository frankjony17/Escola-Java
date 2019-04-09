package com.example.test.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="teacher")
public class Teacher implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="specialty")
    private String specialty;

    @Column(name="experience")
    private String experience;

    @Temporal(TemporalType.DATE)
    @Column(name="data_entry")
    private Date dataEntry;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="person_id", nullable=false)
    private Person person;

    @ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="teachers")
    private List<Student> students;

}
