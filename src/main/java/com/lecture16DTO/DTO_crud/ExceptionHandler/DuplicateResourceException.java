package com.lecture16DTO.DTO_crud.ExceptionHandler;

public class DuplicateResourceException extends RuntimeException{

    public DuplicateResourceException(String message) {
        super(message);
    }
}
