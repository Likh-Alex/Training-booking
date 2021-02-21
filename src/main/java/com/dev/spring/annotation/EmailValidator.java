package com.dev.spring.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {
    public static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email != null && email.matches(EMAIL_PATTERN);
    }
}
