package com.example.test.util;

import com.example.test.exception.ObjectMapperException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class Useful
{
    private Useful() {
        throw new IllegalAccessError("Classe Utiliataria");
    }

    public static <T> T filterDTO(String filtro, Class<T> clase) throws ObjectMapperException {
        ObjectMapper objectMapper = new ObjectMapper();
        T object;
        try {
            object = objectMapper.readValue(filtro, clase);
        } catch (IOException e) {
            String message = "Ocorreu uma falha ao converter o objeto da requisição (dtoFilter).";
            throw new ObjectMapperException(message, e);
        }
        return clase.cast(object);
    }

    public static String objectToJson(String objectName, String objetoStringValue) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put(objectName, objetoStringValue);
        return objectNode.toString();
    }
}
