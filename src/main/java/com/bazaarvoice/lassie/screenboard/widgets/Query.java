package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class Query {
    @JsonProperty("q")
    final String _value;

    /**
     *
     * @param value
     */
    public Query(String value) {
        _value = checkNotNull(value, "query is null");
    }

    /**
     *
     */
    private Query() {
        this("default.user.data{*}");
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public String getValue() {
        return _value;
    }
}
