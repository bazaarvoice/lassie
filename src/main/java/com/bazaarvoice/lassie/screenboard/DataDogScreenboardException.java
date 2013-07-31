package com.bazaarvoice.lassie.screenboard;

import com.google.common.base.Joiner;

import java.util.List;

/** This class is an exception wrapper class. */
public class DataDogScreenboardException extends Exception {
    public DataDogScreenboardException() {
    }

    public DataDogScreenboardException(String message) {
        super(message);
    }

    public DataDogScreenboardException(List<String> errors) {
        super(Joiner.on('\n').join(errors));
    }

    public DataDogScreenboardException(List<String> errors, Exception cause) {
        super(Joiner.on('\n').join(errors), cause);
    }
}