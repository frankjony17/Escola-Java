package com.example.test.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class StudentDTO implements Serializable {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Float nota;

    @Getter
    @Setter
    private String personFirstName;

    @Getter
    @Setter
    private String personLastName;

    @Getter
    @Setter
    private String groupNome;

    @Getter
    @Setter
    private String groupLocation;
}
