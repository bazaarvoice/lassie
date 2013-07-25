package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkNotNull;

public class Request {
    @JsonProperty("stacked")
    private final boolean _stacked;
    @JsonProperty("q")
    private final String _query;

    public Request(String query, boolean stacked) {
        _query = checkNotNull(query, "query is null");
        _stacked = stacked;
    }

    public Request(String query) {
        this(query, false);
    }

    private Request() {
        _query = "default.user.data";
        _stacked = false;
    }

    //Full breakdown
    public static Request create(Aggregator aggregator, String query, String over, String breakdown, boolean stacked) {
        String value = aggregator.getName() + ":" + query + "{" + over + "}" + " by {" + breakdown + "}";
        return new Request(value, stacked);
    }

    public static Request create(Aggregator aggregator, String query, String breakdown, boolean stacked) {
        return create(aggregator, query, "*", breakdown, stacked);
    }

    public static Request create(Aggregator aggregator, String query) {
        String value = aggregator.getName() + ":" + query + "{*}";
        return new Request(value, false);
    }

    @JsonIgnore
    public String getQuery() {
        return _query;
    }

    @JsonIgnore
    public boolean isStacked() {
        return _stacked;
    }

    public String toString() {
        return "Request[" +
                "stacked=" + isStacked() +
                ", query=" + getQuery() +
                "]";
    }
}
