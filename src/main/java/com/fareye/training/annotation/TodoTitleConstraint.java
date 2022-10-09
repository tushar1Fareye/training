package com.fareye.training.annotation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TodoTitleValidator.class)
@Target( { ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TodoTitleConstraint {
    String message() default "duplicate title for a user";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
