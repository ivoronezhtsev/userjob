package com.example.userjob;

import com.example.userjob.dto.UserDto;
import com.example.userjob.dto.UserJobInfoRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController("api/v1/")
public class Controller {

    @Autowired
    private UserJobService userJobService;

    public Controller(UserJobService userJobService) {
        this.userJobService = userJobService;
    }

    @PostMapping("create-userjob")
    ResponseEntity<Void> createUserJob(@RequestBody UserDto userDto) {
        try {
            userJobService.create(userDto);
        } catch (AllreadyPresentException exception) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("update-userjob")
    ResponseEntity<UserJobInfoRequestResponse> updateUserJob(@RequestBody UserJobInfoRequestResponse updateRequest) {
        try {
            return ResponseEntity.ok(userJobService.update(updateRequest));
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-userjob")
    ResponseEntity<UserJobInfoRequestResponse> getUserJob(@RequestBody(required = false) UserJobInfoRequestResponse query) {
        if (query == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserJobInfoRequestResponse response = userJobService.get(query);
        if (response.getUserDto() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(response);
    }
}
