package com.bazaarvoice.lassie.screenboard;

/** This class serves as a utility to check many of the parameters in lassie to prevent the user from inputting null or invalid arguments. */
public class Preconditions {
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public static <T> T checkNotNull(T reference, String message) {
        if (reference == null) {
            throw new NullPointerException(message);
        }
        return reference;
    }

    public static void checkArgument(boolean expression) throws Exception {
        if (expression == false) {
            throw new Exception();
        }
    }

    public static void checkArgument(boolean expression, String message) throws Exception {
        if (expression == false) {
            throw new Exception(message);
        }
    }
}
