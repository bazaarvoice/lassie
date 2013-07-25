package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public enum Visualization {
    TIMESERIES("timeseries");

    private final String _name;

    /**
     *
     * @param name
     */
    private Visualization(String name) {
        _name = name;
    }

    /**
     *
     * @return
     */
    @JsonValue
    public String getName() {
        return _name;
    }

    /**
     *
     * @param name
     * @return
     */
    @JsonCreator
    public static Visualization fromName(String name) {
        checkNotNull(name);
        return Visualization.valueOf(name.toUpperCase());
    }
}
