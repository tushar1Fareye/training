package com.fareye.training.service;

import com.fareye.training.model.Todo;
import com.fareye.training.utility.EncryptionUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TodoService {

    public static HashMap<String, List<Todo>> userToTodosMap = new HashMap<>();

    public List<Todo> getTodosByEmail(String email) throws InvocationTargetException, IllegalAccessException {
        List<Todo> todos = userToTodosMap.get(email);
        List<Todo> encryptedPasswordTodos = new ArrayList<>();

        for(Todo todo: todos) {
            Todo encryptedPasswordTodo = new Todo();
            BeanUtils.copyProperties(encryptedPasswordTodo, todo);
            encryptedPasswordTodo.getUser().setPassword(EncryptionUtil.decodeString(todo.getUser().getPassword()));
            encryptedPasswordTodos.add(encryptedPasswordTodo);
        }

        return encryptedPasswordTodos;
    }

    public List<Todo> addTodo(Todo todo) {

        todo.getUser().setPassword(EncryptionUtil.encodeString(todo.getUser().getPassword()));

        List<Todo> todos = userToTodosMap.get(todo.getUser().getEmail());

        if(todos == null) {
            todos = new ArrayList<>();
        }

        todos.add(todo);
        userToTodosMap.put(todo.getUser().getEmail(), todos);
        return todos;
    }
}
