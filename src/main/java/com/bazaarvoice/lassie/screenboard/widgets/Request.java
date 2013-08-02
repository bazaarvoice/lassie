/*
 * Copyright 2013 Bazaarvoice, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bazaarvoice.lassie.screenboard.widgets;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The Request class is used in the {@link TileDefinition} class.
 * It is the graphed value of the {@link Timeseries} widget.
 */
public class Request {
    @JsonProperty("stacked")
    private final boolean _stacked;
    @JsonProperty("q")
    private final String _query;

    /**
     * Constructor of the Request class that takes in a query and a boolean that determines
     * if it will show up as a line or a stacked line graph.
     *
     * @param query   The query of the Request.
     * @param stacked The boolean that denotes if the graph of the query will be a line or stacked graph.
     */
    public Request(String query, boolean stacked) {
        _query = checkNotNull(query, "query is null");
        _stacked = stacked;
    }

    /**
     * Constructor of the Request class that takes in a query and generates a line graph.
     *
     * @param query The query of the Request.
     */
    public Request(String query) {
        this(query, false);
    }

    /** Private constructor used for deserialization. */
    private Request() {
        _query = "default.user.data";
        _stacked = false;
    }

    /**
     * This factory method creates a Request with a specific aggregator, query, what tag the data is graphed over,
     * and what tags the data can be broken down by.
     * <p/>
     * FULL REQUEST
     * The user is able to set every parameter that will define the query over user specified tags.
     *
     * @param aggregator How the data is collected.
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
     * The user is able to set most parameters that will define the query over all tags.
     *
     * @param aggregator How the data is collected.
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
     * The user is able to set the minimum parameters that will define the query over all tags.
     *
     * @param aggregator How the data is collected.
     * @param query      What comprises the data aggregation.
     * @return A minimally created Request object.
     */
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
