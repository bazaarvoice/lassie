package com.bazaarvoice.lassie.screenboard;

import java.util.List;

/** Exception thrown when a screenboard does not exist. */
public class ScreenboardNotFoundException extends DataDogScreenboardException {
    public ScreenboardNotFoundException() {
    }

    public ScreenboardNotFoundException(String message) {
        super(message);
    }

    public ScreenboardNotFoundException(List<String> errors) {
        super(errors);
    }

    public ScreenboardNotFoundException(List<String> errors, Exception cause) {
        super(errors, cause);
    }
}
