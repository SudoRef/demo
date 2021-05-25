package com.example.demo.service;

import com.example.demo.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

@Component
public class UserValidator implements Validator {

    private static final int ADULTHOOD = 15;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if (!isAgeValid(user)) {
            errors.reject("","User is underage");
        }

    }

    private boolean isAgeValid(User user) {
        return Period.between(user.getBirthdate()
                .toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears() >= ADULTHOOD;
    }
}
