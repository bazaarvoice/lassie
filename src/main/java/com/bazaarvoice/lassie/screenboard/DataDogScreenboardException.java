package com.bazaarvoice.lassie.screenboard;

/** This class is an exception wrapper class. */
public class DataDogScreenboardException extends Exception {
    public DataDogScreenboardException() {
    }

    public DataDogScreenboardException(String message) {
        super(message);
    }
}