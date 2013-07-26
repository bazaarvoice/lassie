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
     * Constructor for the enum
     *
     * @param name The name of the Aggregator documented in the datadog API.
     */
    private Aggregator(String name) {
        _name = name;
    }

    /**
     * Gets the name of the Aggregator documented in the datadog API, which is not necessarily the same as the Aggregator
     *
     * @return The true name of the Aggregator
     */
    @JsonValue
    public String getName() {
        return _name;
    }

    /**
     * Getter for the Aggregator with a given name.
     *
     * @param name The Aggregator's name
     * @return The Aggregator matching the name.
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
