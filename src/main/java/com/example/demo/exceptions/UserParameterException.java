package com.example.demo.exceptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserParameterException extends RuntimeException {

    List<String>messages;

    public UserParameterException(Collection<String> paramNames) {
        super(String.format("parameter", paramNames));
        messages = new ArrayList<>(paramNames);
    }

    @Override
    public String getMessage() {
        return String.join(", ", messages);
    }
}
