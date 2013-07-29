package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The Request class is used in the TileDefinition class. It is the crux of how the Timeseries class works
 * as it is the graphed value of the Timeseriese widget.
 */
public class Request {
    @JsonProperty("stacked")
    private final boolean _stacked;
    @JsonProperty("q")
    private final String _query;

    /**
     * Constructor of the Request class that takes in a query an a boolean that determines
     * if it will show up as a line or a stacked graph.
     *
     * @param query   The query of the Request.
     * @param stacked The boolean that denotes if the graph of the query will be a line or stacked graph.
     */
    public Request(String query, boolean stacked) {
        _query = checkNotNull(query, "query is null");
        _stacked = stacked;
    }

    /**
     * Constructor of the Request class that takes in a query.
     *
     * @param query The query of the Request.
     */
    public Request(String query) {
        this(query, false);
    }

    /** Plain Request constructor, mainly used for Jackson serialization / deserialization. */
    private Request() {
        _query = "default.user.data";
        _stacked = false;
    }

    /**
     * This factory method creates a Request with a specific aggregator, query, what tag the data is graphed over,
     * and what tags the data can be broken down by.
     * <p/>
     * FULL REQUEST
     *
     * @param aggregator How the data is collected (AVG,MAX,MIN,SUM)
     * @param query      What comprises the data aggregation.
     * @param over       What the criteria the data is viewed over
     * @param breakdown  What tags the data can be broken down by.
     * @param stacked    If the data will be shown by a line graph or a stacked line graph
     * @return A fully created Request object.
     */
    public static Request create(Aggregator aggregator, String query, String over, String breakdown, boolean stacked) {
        String value = aggregator.getName() + ":" + query + "{" + over + "}" + " by {" + breakdown + "}";
        return new Request(value, stacked);
    }

    /**
     * This factory method creates a Request with a specific aggregator, query,
     * and what tags the data can be broken down by.
     * <p/>
     * PARTIAL REQUEST
     *
     * @param aggregator How the data is collected (AVG,MAX,MIN,SUM)
     * @param query      What comprises the data aggregation.
     * @param breakdown  What tags the data can be broken down by.
     * @param stacked    If the data will be shown by a line graph or a stacked line graph
     * @return A partially created Request object.
     */
    public static Request create(Aggregator aggregator, String query, String breakdown, boolean stacked) {
        return create(aggregator, query, "*", breakdown, stacked);
    }

    /**
     * This factory method creates a Request with a specific aggregator, query.
     * <p/>
     * MINIMAL REQUEST
     *
     * @param aggregator How the data is collected (AVG,MAX,MIN,SUM)
     * @param query      What comprises the data aggregation.
     * @return A minimally created Request object.
     */
    public static Request create(Aggregator aggregator, String query) {
        String value = aggregator.getName() + ":" + query + "{*}";
        return new Request(value, false);
    }

    /** @return  */
    @JsonIgnore
    public String getQuery() {
        return _query;
    }

    /** @return  */
    @JsonIgnore
    public boolean isStacked() {
        return _stacked;
    }

    /** @return  */
    public String toString() {
        return "Request[" +
                "stacked=" + isStacked() +
                ", query=" + getQuery() +
                "]";
    }
}
