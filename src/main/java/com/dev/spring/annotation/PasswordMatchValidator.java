package com.dev.spring.annotation;

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
    public boolean isValid(Object pass, ConstraintValidatorContext context) {
        Object dtoPassword = new BeanWrapperImpl(pass).getPropertyValue(password);
        Object dtoPasswordRepeat = new BeanWrapperImpl(pass).getPropertyValue(repeatPassword);
        if (dtoPassword != null) {
            return dtoPassword.equals(dtoPasswordRepeat);
        } else {
            return repeatPassword == null;
        }
    }
}
