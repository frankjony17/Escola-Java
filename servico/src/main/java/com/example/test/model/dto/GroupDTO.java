package com.example.test.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class GroupDTO implements Serializable {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private String location;
}
