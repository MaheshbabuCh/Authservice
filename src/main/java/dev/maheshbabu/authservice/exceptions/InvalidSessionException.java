package dev.maheshbabu.authservice.exceptions;

public class InvalidSessionException extends Exception{
    public InvalidSessionException(String message) {
        super(message);
    }
}
