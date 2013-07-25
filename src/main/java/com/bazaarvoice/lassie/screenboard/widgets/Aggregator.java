package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

import static com.google.common.base.Preconditions.checkNotNull;

public enum Aggregator {
    AVERAGE("avg"), MAX("max"), MIN("min"), SUM("sum");

    private final String _name;

    private Aggregator(String name) {
        _name = name;
    }

    @JsonValue
    public String getName() {
        return _name;
    }

    @JsonCreator
    public static Aggregator fromName(String name) {
        checkNotNull(name);
        if (name.equals("avg")) {
            return AVERAGE;
        }
        return Aggregator.valueOf(name.toUpperCase());
    }
}
