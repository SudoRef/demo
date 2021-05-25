package com.example.demo.exceptions;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserParameterException extends RuntimeException {
    public static final String MESSAGE_TEMPLATE = "Mandatory parameter {%s} is invalid";

    List<String>messages;

    public UserParameterException(Collection<String> paramNames) {
        super(String.format(MESSAGE_TEMPLATE, paramNames));
        messages = paramNames.stream()
                .map(param -> String.format(MESSAGE_TEMPLATE, param)).collect(Collectors.toList());
    }

    @Override
    public String getMessage() {
        return String.join(", ", messages);
    }
}
