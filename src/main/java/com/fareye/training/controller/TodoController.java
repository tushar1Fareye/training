package com.fareye.training.controller;

import com.fareye.training.model.Todo;
import com.fareye.training.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Todo todo) {

        if(todoService.add(todo)  == false) {
            return new ResponseEntity<>("user with given email doesn't exist", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("todo with given details created", HttpStatus.OK);

    }

    @GetMapping
    public  ResponseEntity<Object> fetch(@RequestParam String email) {

        List<Todo> todos = todoService.get(email);

        if(todos == null) {
            return new ResponseEntity<>("User with given email doesn't exist", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @DeleteMapping
    public  ResponseEntity<Object> delete(@RequestParam String email, @RequestParam String title) {

        if(todoService.delete(email, title)  == false) {
            return new ResponseEntity<>("may be todo with given title or user with given email doesn't exist",
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("todo with given title for user with given email deleted", HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<Object> modify(@RequestBody Todo todo) throws InvocationTargetException,
            IllegalAccessException {

        if(todoService.update(todo) == false) {
            return new ResponseEntity<>("may be todo with given title or user with given email doesn't exist",
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("todo with given title for user with given email modified", HttpStatus.OK);
    }


}
