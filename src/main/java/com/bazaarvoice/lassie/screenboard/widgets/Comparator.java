package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/** The Comparator is used mainly in the {@link ConditionalFormat} class. It's used to compare the aggregated value to the threshold value. */
public enum Comparator {
    GREATER(">"), GREATER_EQUALS(">="), LESS("<"), LESS_EQUALS("<=");
    private final String _name;

    /**
     * Constructor to set the name for the Comparator as it is documented in the datadog API.
     *
     * @param name The name of the Comparator.
     */
    private Comparator(String name) {
        _name = name;
    }

    /**
     * Getter for the name of the Comparator. In this case it will return the symbol as it is documented in the datadog API.
     *
     * @return The name of the Comparator
     */
    @JsonValue
    public String getName() {
        return _name;
    }

    /**
     * Getter for the Comparator by its symbolic name as it is documented in the datadog API.
     *
     * @param name The Symbolic name of the Comparator
     * @return The expected Comparator
     */
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