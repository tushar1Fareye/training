package com.fareye.training.controller;

import com.fareye.training.Annotation.TodoTitleConstraint;
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
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("todo")
    public List<Todo> createTodo(@RequestBody @Valid Todo todo) throws MethodArgumentNotValidException {
        return todoService.addTodo(todo);
    }

    @GetMapping("todos")
    public  List<Todo> fetchTodosByEmail(@RequestParam String email) throws InvocationTargetException,
            IllegalAccessException {

        return todoService.getTodosByEmail(email);
    }

//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> exception(MethodArgumentNotValidException exception) {
//        return new ResponseEntity<>("Request validation failed", HttpStatus.BAD_REQUEST);
//    }

}
