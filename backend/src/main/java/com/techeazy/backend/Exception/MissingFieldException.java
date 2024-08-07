package com.techeazy.backend.Exception;

public class MissingFieldException extends RuntimeException{
    public MissingFieldException(String message) {
        super(message);
    }
}
