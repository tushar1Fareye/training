package com.fareye.training.controller;

import com.fareye.training.model.User;
import com.fareye.training.service.UserService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid User user) throws ParseException {

        if(userService.add(user)  == false) {
            return new ResponseEntity<>("User with given email already exist", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("user with given details created", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> fetch(@RequestParam String email) {

        User requestedUser = userService.get(email);

        if(requestedUser == null) {
            return new ResponseEntity<>("User with given email doesn't exist", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(requestedUser, HttpStatus.OK);

    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam String email) {

        if(userService.delete(email)  == false) {
            return new ResponseEntity<>("User with given email doesn't exist", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("user with given email deleted", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> modify(@RequestBody User user) throws InvocationTargetException,
            IllegalAccessException {

        if(userService.update(user) == false) {
            return new ResponseEntity<>("User with given email doesn't exist", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("user with given email modified", HttpStatus.OK);
    }

}
