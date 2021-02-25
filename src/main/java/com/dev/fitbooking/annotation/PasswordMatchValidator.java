package com.dev.fitbooking.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {
    private String password;
    private String repeatPassword;

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        password = constraintAnnotation.password();
        repeatPassword = constraintAnnotation.passwordMatch();
    }

    @Override
    public boolean isValid(Object dto, ConstraintValidatorContext context) {
        Object dtoPassword = new BeanWrapperImpl(dto).getPropertyValue(password);
        Object dtoPasswordRepeat = new BeanWrapperImpl(dto).getPropertyValue(repeatPassword);
        if (dtoPassword != null) {
            return dtoPassword.equals(dtoPasswordRepeat);
        } else {
            return repeatPassword == null;
        }
    }
}
