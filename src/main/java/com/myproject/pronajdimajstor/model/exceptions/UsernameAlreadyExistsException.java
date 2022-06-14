package com.myproject.pronajdimajstor.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{


    public UsernameAlreadyExistsException(String userName){
        super(String.format("User with username: %s already exist!", userName));
    }
}
