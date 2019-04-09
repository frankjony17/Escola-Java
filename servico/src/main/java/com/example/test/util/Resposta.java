package com.example.test.util;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de Respostas
 *
 * @param <T>
 */

public class Resposta<T> {

    @Getter
    @Setter
    private T result;

    @Getter
    @Setter
    private List<String> error;

    @Getter
    @Setter
    private List<String> messages;

    @Getter
    @Setter
    private Long totalElements;

    @Getter
    @Setter
    private Integer totalPages;

    /**
     * Método construtor da classe
     */
    public Resposta() {
        messages = new ArrayList<>();
        error = new ArrayList<>();
    }
}
