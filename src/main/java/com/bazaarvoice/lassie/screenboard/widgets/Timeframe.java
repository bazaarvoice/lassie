package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

import static com.google.common.base.Preconditions.checkNotNull;

/** The Timeframe class is an enum that controls the amount of data collected by the query. */
public enum Timeframe {
    FIVE_MINUTES("5m"), TEN_MINUTES("10m"), ONE_HOUR("1h"), FOUR_HOURS("4h"), ONE_DAY("1d"), TWO_DAYS("2d"), ONE_WEEK("1w");

    private final String _name;

    /**
     * Constructor that sets the name of the enum as it is documented in the datadog API.
     *
     * @param name The name of the Timeframe enum
     */
    private Timeframe(String name) {
        _name = name;
    }

    /**
     * The getter for the name of the enum as it is documented in the datadog API.
     *
     * @return The name of the Timeframe.
     */
    @JsonValue
    public String getName() {
        return _name;
    }

    /**
     * The name of the expected enum as it is documented in the datadog API.
     *
     * @param name The name of the expected enum.
     * @return The Timeframe enum.
     */
    @JsonCreator
    public static Timeframe fromName(String name) {
        checkNotNull(name);
        if (name.equals("5m")) {
            return FIVE_MINUTES;
        } else if (name.equals("10m")) {
            return TEN_MINUTES;
        } else if (name.equals("1h")) {
            return ONE_HOUR;
        } else if (name.equals("4h")) {
            return FOUR_HOURS;
        } else if (name.equals("1d")) {
            return ONE_DAY;
        } else if (name.equals("2d")) {
            return TWO_DAYS;
        } else if (name.equals("1w")) {
            return ONE_WEEK;
        }
        throw new IllegalArgumentException("Invalid comparator name: " + name);
    }
}
