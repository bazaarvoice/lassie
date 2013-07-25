package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Aggregator enum which is used to denote how the query will be aggregated.
 */
public enum Aggregator {
    AVERAGE("avg"), MAX("max"), MIN("min"), SUM("sum");

    private final String _name;

    /**
     * Getter for the Aggregator enum
     * @param name The name of the Aggregator
     */
    private Aggregator(String name) {
        _name = name;
    }

    /**
     * Gets the name of the Aggregator, which is not necessarily the same as the Aggregator
     * @return The true name of the Aggregator
     */
    @JsonValue
    public String getName() {
        return _name;
    }

    /**
     * Getter  for the Aggregator's name based on a String
     * @param name The Aggregator's name
     * @return the Aggregator matching the name.
     */
    @JsonCreator
    public static Aggregator fromName(String name) {
        checkNotNull(name);
        if (name.equals("avg")) {
            return AVERAGE;
        }
        return Aggregator.valueOf(name.toUpperCase());
    }
}
