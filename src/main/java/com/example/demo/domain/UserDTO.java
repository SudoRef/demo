package com.example.demo.domain;

import java.util.Date;

public class UserDTO {

    private String name;
    private Date birthdate;
    private String email;

    public UserDTO() {
    }

    public UserDTO(String name, Date birthdate, String email) {
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
