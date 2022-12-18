package com.example.userjob.controller;

import com.example.userjob.service.UserJobService;
import com.example.userjob.dto.UserDto;
import com.example.userjob.dto.UserJobInfoRequestResponse;
import com.example.userjob.exception.AlreadyPresentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class Controller {

    @Autowired
    private final UserJobService userJobService;

    public Controller(UserJobService userJobService) {
        this.userJobService = userJobService;
    }

    @PostMapping("create-userjob")
    ResponseEntity<Void> createUserJob(@RequestBody UserDto userDto) {
        try {
            userJobService.create(userDto);
        } catch (AlreadyPresentException exception) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("update-userjob")
    ResponseEntity<UserJobInfoRequestResponse> updateUserJob(
            @RequestBody UserJobInfoRequestResponse updateRequest) {
        try {
            return ResponseEntity.ok(userJobService.update(updateRequest));
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-userjob")
    ResponseEntity<UserJobInfoRequestResponse> getUserJob(
            Optional<UserJobInfoRequestResponse> query) {
        if (query.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            UserJobInfoRequestResponse response = userJobService.get(query.get());
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
