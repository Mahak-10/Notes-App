package com.notes.notes_service.service;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long userId) {
        super("Invalid userId:"+userId);
    }
}
