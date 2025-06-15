package org.ComputerShop.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.ComputerShop.service.impl.UserServiceImpl;
import org.ComputerShop.validation.annotation.UniqueUsername;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserServiceImpl userService;

    public UniqueUsernameValidator(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.userService.findUserByUsername(value) == null;
    }
}