package com.bazaarvoice.lassie.screenboard;

import java.util.List;

/** This class is an exception wrapper class. */
public class DataDogScreenboardException extends Exception {
    public DataDogScreenboardException() {
    }

    public DataDogScreenboardException(String message) {
        super(message);
    }

    public DataDogScreenboardException(List<String> errors) {
        super(errors.toString());
    }

    public DataDogScreenboardException(List<String> errors, Exception cause) {
        super(errors.toString(), cause);
    }
}