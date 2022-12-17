package com.example.userjob;

import com.example.userjob.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("api/v1/")

public class Controller {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("create-userjob")
    void createUserJob() { //todo requestbody

    }

    @PatchMapping("update-userjob")
    void updateUserJob() {

    }

    @GetMapping("get-userjob")
    List<User> getUserJob() {
        List<User> users = userRepository.findAll();

        return users;
    }
}
