package com.example.test.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

public class TeacherDTO implements Serializable {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String specialty;

    @Getter
    @Setter
    private String experience;

    @Getter
    @Setter
    private String dataEntry;

    @Getter
    @Setter
    private PersonDTO person;

    @Getter
    @Setter
    private List<StudentDTO> students;
}
