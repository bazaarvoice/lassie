package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

import static com.google.common.base.Preconditions.checkNotNull;

/** Visualization enum which is used mainly in the QueryValue class */
public enum Visualization {
    TIMESERIES("timeseries");

    private final String _name;

    /**
     * Constructor that sets the name of the enum as it is documented in the datadog API.
     *
     * @param name The name of the Visualization enum.
     */
    private Visualization(String name) {
        _name = name;
    }

    /**
     * The getter for the name of the enum as it is documented in the datadog API.
     *
     * @return The name of the Visualization.
     */
    @JsonValue
    public String getName() {
        return _name;
    }

    /**
     * The name of the expected enum as it is documented in the datadog API.
     *
     * @param name The name of the expected enum.
     * @return The Visualization enum.
     */
    @JsonCreator
    public static Visualization fromName(String name) {
        checkNotNull(name);
        return Visualization.valueOf(name.toUpperCase());
    }
}
