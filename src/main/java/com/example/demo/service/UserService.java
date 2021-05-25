package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.exceptions.UserParameterException;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator validator;

    public UserService(UserRepository userRepository, UserValidator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    public User getUserByEmail(String email) {
        return userRepository.selectByEmail(email);
    }

    public void createUser(User user) {
        validateUser(user);
        userRepository.insertUser(user);
    }

    private User validateUser(User user) {
        Errors errors = new BeanPropertyBindingResult(user, User.class.getName());
        validator.validate(user, errors);
        if (errors.hasErrors()) {
            throw new UserParameterException(errors.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        return user;
    }

    public void deleteUser(String email){
        userRepository.deleteUser(email);
    }
}
