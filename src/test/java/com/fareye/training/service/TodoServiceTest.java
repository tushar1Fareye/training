package com.fareye.training.service;

import com.fareye.training.model.Todo;
import com.fareye.training.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoServiceTest {

    @Mock
    UserService userService;

    @InjectMocks
    TodoService todoService;


    @Test
    void getShouldReturnEmptyArray() {
        todoService.userToTodosMap.put("test@gmail.com", new ArrayList<>());
        assertEquals(todoService.get("test@gmail.com"), new ArrayList<>());
    }

    @Test
    void addShouldReturnTrue() {
        todoService.userToTodosMap.put("test@gmail.com", new ArrayList<>());
        Todo todo = new Todo();
        todo.setUserEmail("test@gmail.com");
        todo.setTitle("testTitle");
        when(userService.getUser(any())).thenReturn(new User());
        assertEquals(todoService.add(todo), true);
    }

    @Test
    void addShouldReturnFalse() {
        Todo todo = new Todo();
        todo.setUserEmail("test1@gmail.com");
        assertEquals(todoService.add(todo), false);
    }

    @Test
    void deleteShouldReturnTrue() {
        Todo todo = new Todo();
        todo.setTitle("testTitle");
        todo.setUserEmail("test3@gmail.com");
        List<Todo> todoList = new ArrayList<>();
        todoList.add(todo);
        todoService.userToTodosMap.put("test3@gmail.com", todoList);
        assertEquals(todoService.delete("test3@gmail.com", "testTitle"), true);
    }

    @Test
    void deleteShouldReturnFalse() {
        assertEquals(todoService.delete("test4@gmail.com", "wrongTitle"), false);
    }

    @Test
    void updateShouldReturnTrue() throws InvocationTargetException, IllegalAccessException {
        Todo todo = new Todo();
        todo.setTitle("testTitle");
        todo.setUserEmail("test5@gmail.com");
        List<Todo> todoList = new ArrayList<>();
        todoList.add(todo);
        todoService.userToTodosMap.put("test5@gmail.com", todoList);
        Todo modificationTodo = new Todo();
        modificationTodo.setTitle("testTitle");
        modificationTodo.setUserEmail("test5@gmail.com");
        assertEquals(todoService.update(modificationTodo), true);
    }

    @Test
    void updateShouldReturnFalse() throws InvocationTargetException, IllegalAccessException {
        Todo modificationTodo = new Todo();
        modificationTodo.setTitle("testTitle");
        modificationTodo.setUserEmail("test6@gmail.com");
        assertEquals(todoService.update(modificationTodo), false);
    }


}