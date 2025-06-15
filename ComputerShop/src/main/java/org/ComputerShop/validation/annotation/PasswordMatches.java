package org.ComputerShop.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.ComputerShop.validation.PasswordMatchesValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatches {
    String message() default "Passwords do not match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
