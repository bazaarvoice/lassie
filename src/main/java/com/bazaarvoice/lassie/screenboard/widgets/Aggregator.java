package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

import static com.bazaarvoice.lassie.screenboard.Preconditions.checkNotNull;

/** Defines how the results of a query will be aggregated. */
public enum Aggregator {
    AVERAGE("avg"), MAX("max"), MIN("min"), SUM("sum");
    private final String _name;

    /**
     * Constructor for the Aggregator.
     *
     * @param name The name of the aggregator documented in the datadog API.
     */
    private Aggregator(String name) {
        _name = name;
    }

    /**
     * Gets the name of the aggregator documented in the datadog API, which is not necessarily the same as the {@link Aggregator}.
     *
     * @return The true name of the aggregator.
     */
    @JsonValue
    public String getName() {
        return _name;
    }

    /**
     * Parse an aggregator name into the corresponding {@link Aggregator} value.
     *
     * @param name The aggregator's name.
     * @return The aggregator matching the name.
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