package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkNotNull;

/** The Query class mainly serves as a string wrapper in the {@link TileDefinition} class. */
public class Query {
    @JsonProperty("q")
    final String _value;

    public Query(String value) {
        _value = checkNotNull(value, "query is null");
    }

    /** Private constructor used for deserialization. */
    private Query() {
        this("default.user.data{*}");
    }

    @JsonIgnore
    public String getValue() {
        return _value;
    }
}
