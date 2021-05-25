package com.example.demo.domain;

import java.util.Date;

public class User {


    private String name;
    private Date birthdate;
    private String email;
    private String password;

    User() {
    }

    public User( String name, Date birthdate, String email, String password) {

        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

