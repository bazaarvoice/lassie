package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

public enum Comparator {
    GREATER(">"), GREATER_EQUALS(">="), LESS("<"), LESS_EQUALS("<=");

    private final String _name;

    private Comparator(String name) {
        _name = name;
    }

    @JsonValue
    public String getName() {
        return _name;
    }

    @JsonCreator
    public static Comparator fromName(String name) {
        if (">".equals(name)) {
            return GREATER;
        } else if (">=".equals(name)) {
            return GREATER_EQUALS;
        } else if ("<".equals(name)) {
            return LESS;
        } else if ("<=".equals(name)) {
            return LESS_EQUALS;
        }
        throw new IllegalArgumentException("Invalid comparator name: " + name);
    }
}
