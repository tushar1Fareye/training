package com.fareye.training.service;

import com.fareye.training.model.Todo;
import com.fareye.training.repository.TodoRepository;
import com.fareye.training.repository.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    TodoRepository todoRepository;

    public List<Todo> get(String email) {

        return todoRepository.findByUserEmail(email);

    }

    public Boolean add(Todo todo) {

//        throw new CustomFieldException("Custom Exception Occurred", "Field1","Field1 not found");
        if(!userRepository.findByEmail(todo.getUserEmail()).isEmpty()) {
            todo.setCreatedDate(LocalDateTime.now()) ;
            todoRepository.save(todo);
            return true;
        }

        return false;
    }

    public Boolean delete(String email, String title) {

        List<Todo> todos = todoRepository.findByTitleAndUserEmail(title, email);

        if(!CollectionUtils.isEmpty(todos)) {

            todoRepository.deleteById(todos.get(0).getId());

            return true;

        }

        return false;
    }

    public Boolean update(Todo todoModifications) throws InvocationTargetException, IllegalAccessException {

        List<Todo> todos = todoRepository.findByTitleAndUserEmail(todoModifications.getTitle(),
                todoModifications.getUserEmail());

        if(!CollectionUtils.isEmpty(todos)) {
            Todo todo = todos.get(0);
            todo.setBody(todoModifications.getBody());
            todo.setStatus(todoModifications.getStatus());
            todo.setModifiedDate(LocalDateTime.now());

            todoRepository.save(todo);
            return true;
        }

        return false;
    }

}
