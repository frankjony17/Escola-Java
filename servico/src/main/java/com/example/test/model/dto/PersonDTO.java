package com.example.test.model.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

public class PersonDTO implements Serializable {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;
}
