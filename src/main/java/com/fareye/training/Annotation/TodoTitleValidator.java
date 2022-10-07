package com.fareye.training.Annotation;

import com.fareye.training.model.Todo;
import com.fareye.training.service.TodoService;
import com.fareye.training.utility.EncryptionUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class TodoTitleValidator implements ConstraintValidator<TodoTitleConstraint, Todo> {

    @Override
    public void initialize(TodoTitleConstraint TodoTitle) {
    }

    @Override
    public boolean isValid(Todo todo,
                           ConstraintValidatorContext cxt) {

        List<Todo> todos = TodoService.userToTodosMap.get(todo.getUser().getEmail());

        if(todos == null) {
            return true;
        }

        for(Todo todo1: todos) {
            if(todo.getTitle().equals(todo1.getTitle())) {
                System.out.println("yes");
                return false;
            }
        }

        return true;

    }
}
