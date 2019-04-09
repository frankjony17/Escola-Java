package com.example.test.controller;

import com.example.test.model.Teacher;
import com.example.test.model.dto.TeacherDTO;
import com.example.test.repository.TeacherRepository;
import com.example.test.service.TeacherService;
import com.example.test.util.Useful;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "professor/", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired TeacherService teacherService;

    @GetMapping(value = "list")
    public ResponseEntity<List<Teacher>> listTeacher(@RequestParam int offset, @RequestParam int limit) {
        ResponseEntity responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(teacherService.list(offset, limit), HttpStatus.OK);
        } catch (Exception ie) {
            ie.printStackTrace();
        }
        return responseEntity;
    }

//    @PostMapping("save")
//    public ResponseEntity<?> save(@RequestBody TeacherDTO teacherDTO) {
//        ResponseEntity responseEntity;
//        try {
//            teacherService.save(teacherDTO);
//            responseEntity = new ResponseEntity<>(HttpStatus.OK);
//        } catch (DataIntegrityViolationException e) {
//            String errorMessage = Useful.objectToJson("errorMessage", "UNIQUE-VALUES");
//            responseEntity = new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return responseEntity;
//    }
//
//    @PostMapping("edit")
//    public ResponseEntity<?> edit(@RequestBody TeacherDTO teacherDTO) {
//        ResponseEntity responseEntity;
//        try {
//            teacherService.edit(teacherDTO);
//            responseEntity = new ResponseEntity<>(HttpStatus.OK);
//        } catch (DataIntegrityViolationException e) {
//            String errorMessage = Useful.objectToJson("errorMessage", "UNIQUE-VALUES");
//            responseEntity = new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return responseEntity;
//    }
//
//    @DeleteMapping("remove/{id}")
//    public ResponseEntity remove(@PathVariable Long id) {
//        teacherService.remove(id);
//        return new ResponseEntity(HttpStatus.OK);
//    }
}




















