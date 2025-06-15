package org.ComputerShop.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.ComputerShop.model.dto.RegisterDTO;
import org.ComputerShop.validation.annotation.PasswordMatches;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, RegisterDTO> {

    @Override
    public boolean isValid(RegisterDTO dto, ConstraintValidatorContext context) {
        if (dto.getPassword() == null || dto.getConfirmPassword() == null) {
            return false;
        }
        return dto.getPassword().equals(dto.getConfirmPassword());
    }
}