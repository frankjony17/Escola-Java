package com.example.test.controller;

import com.example.test.model.Person;
import com.example.test.model.dto.PersonDTO;
import com.example.test.repository.PersonRepository;
import com.example.test.service.PersonService;
import com.example.test.util.Useful;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "pessoa/", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired PersonService personService;

    @GetMapping(value = "list")
    public ResponseEntity<List<Person>> listPerson(@RequestParam int offset, @RequestParam int limit) {
        ResponseEntity responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(personService.list(offset, limit), HttpStatus.OK);
        } catch (Exception ie) {
            ie.printStackTrace();
        }
        return responseEntity;
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody PersonDTO personDTO) {
        ResponseEntity responseEntity;
        try {
            personService.save(personDTO);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = Useful.objectToJson("errorMessage", "UNIQUE-VALUES");
            responseEntity = new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("edit")
    public ResponseEntity<?> edit(@RequestBody PersonDTO personDTO) {
        ResponseEntity responseEntity;
        try {
            personService.edit(personDTO);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = Useful.objectToJson("errorMessage", "UNIQUE-VALUES");
            responseEntity = new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity remove(@PathVariable Long id) {
        personService.remove(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}




















