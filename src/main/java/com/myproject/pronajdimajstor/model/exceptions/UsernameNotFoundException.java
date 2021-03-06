package com.myproject.pronajdimajstor.model.exceptions;

public class UsernameNotFoundException extends RuntimeException{

    public UsernameNotFoundException(String username){
        super(String.format("User with username: %s was not found", username));
    }
}
