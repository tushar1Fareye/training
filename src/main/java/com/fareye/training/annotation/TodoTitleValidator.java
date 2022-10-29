package com.fareye.training.annotation;

import com.fareye.training.model.Todo;
import com.fareye.training.repository.TodoRepository;
import com.fareye.training.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class TodoTitleValidator implements ConstraintValidator<TodoTitleConstraint, Todo> {

    @Autowired
    TodoRepository todoRepository;

    @Override
    public void initialize(TodoTitleConstraint TodoTitle) {
    }

    @Override
    public boolean isValid(Todo todo,
                           ConstraintValidatorContext cxt) {

        if(todoRepository ==null) {
            return true;
        }

        if (!todoRepository.findByTitleAndUserEmail(todo.getTitle(), todo.getUserEmail()).isEmpty()) {
            return false;
        }

        return true;

    }
}
