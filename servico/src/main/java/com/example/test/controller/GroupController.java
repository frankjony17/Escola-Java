package com.example.test.controller;

import com.example.test.model.Group;
import com.example.test.model.dto.GroupDTO;
import com.example.test.repository.GroupRepository;
import com.example.test.repository.GroupRepository;
import com.example.test.service.GroupService;
import com.example.test.util.Resposta;
import com.example.test.util.Useful;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "grupo/", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    GroupService groupService;

    @GetMapping(value = "list")
    public ResponseEntity<List<Group>> listGroup(@RequestParam int offset, @RequestParam int limit) {
        ResponseEntity responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(groupService.list(offset, limit), HttpStatus.OK);
        } catch (Exception ie) {
            ie.printStackTrace();
        }
        return responseEntity;
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody GroupDTO groupDTO) {
        ResponseEntity responseEntity;
        try {
            groupService.save(groupDTO);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = Useful.objectToJson("errorMessage", "UNIQUE-VALUES");
            responseEntity = new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("edit")
    public ResponseEntity<?> edit(@RequestBody GroupDTO groupDTO) {
        ResponseEntity responseEntity;
        try {
            groupService.edit(groupDTO);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = Useful.objectToJson("errorMessage", "UNIQUE-VALUES");
            responseEntity = new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity remove(@PathVariable Long id) {
        groupService.remove(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
