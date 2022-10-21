package com.fareye.training.service;

import com.fareye.training.exception.CustomFieldException;
import com.fareye.training.model.Todo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    public static HashMap<String, List<Todo>> userToTodosMap = new HashMap<>();

    @Autowired
    private UserService userService;

    public List<Todo> get(String email) {

        return userToTodosMap.get(email);

    }

    public Boolean add(Todo todo) {

        String email = todo.getUserEmail();
        List<Todo> todos = userToTodosMap.get(email);
//        throw new CustomFieldException("Custom Exception Occurred", "Field1","Field1 not found");
        if( todos != null) {
            todo.setUser(userService.getUser(email));
            todo.setCreatedDate(LocalDateTime.now()) ;
            todos.add(todo);
            return true;
        }

        return false;
    }

    public Boolean delete(String email, String title) {

        List<Todo> todos = get(email);

        if(!CollectionUtils.isEmpty(todos)) {

            List<Todo> filteredTodos = todos.stream().filter(Todo -> !title.equals(Todo.getTitle()))
                    .collect(Collectors.toList());

            if(todos.size() != filteredTodos.size()) {
                userToTodosMap.put(email, filteredTodos);
                return true;
            }
        }

        return false;
    }

    public Boolean update(Todo todoModifications) throws InvocationTargetException, IllegalAccessException {

        List<Todo> todos = get(todoModifications.getUserEmail());

        if(!CollectionUtils.isEmpty(todos)) {
            for(Todo todo: todos) {
                if(todo.getTitle().equals(todoModifications.getTitle())) {
                    todo.setBody(todoModifications.getBody());
                    todo.setStatus(todoModifications.getStatus());
                    todo.setModifiedDate(LocalDateTime.now());
                    return true;
                }
            }
        }

        return false;
    }

}
