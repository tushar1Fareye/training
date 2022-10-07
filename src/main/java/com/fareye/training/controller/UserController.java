package com.fareye.training.controller;

import com.fareye.training.model.User;
import com.fareye.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public List<User> create(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping
    public User fetch(@RequestParam String email) {
        return userService.getUser(email);
    }
}
