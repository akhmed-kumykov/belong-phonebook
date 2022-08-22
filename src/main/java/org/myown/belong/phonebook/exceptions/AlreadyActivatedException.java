package org.myown.belong.phonebook.exceptions;

public class AlreadyActivatedException extends RuntimeException {
    public AlreadyActivatedException(String message) {
        super(message);
    }
}
