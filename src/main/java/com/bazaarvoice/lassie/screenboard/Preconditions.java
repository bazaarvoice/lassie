package com.bazaarvoice.lassie.screenboard;

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
        if (expression==false)
            throw new Exception();

    }
    public static void checkArgument(boolean expression, String message) throws Exception {
        if (expression==false)
            throw new Exception(message);
    }
}
