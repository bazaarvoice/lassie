package com.bazaarvoice.lassie.screenboard;

/** This class is an exception wrapper class. */
public class DataDogScreenboardException extends Exception {
    public DataDogScreenboardException() {
    }

    public DataDogScreenboardException(Object message) {
        super(String.valueOf(message));
    }
}