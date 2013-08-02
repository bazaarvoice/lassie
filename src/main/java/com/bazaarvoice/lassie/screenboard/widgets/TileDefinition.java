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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The TileDefinition class holds the events and requests that drives the {@link Timeseries} class.
 * It also holds a visualization.
 */
public class TileDefinition {
    @JsonProperty("viz")
    private Visualization _visualization = Visualization.TIMESERIES;
    @JsonProperty("requests")
    private List<Request> _requests = new ArrayList<Request>();
    @JsonProperty("events")
    private List<Query> _events = new ArrayList<Query>();

    /**
     * The full constructor for the TileDefinition.
     *
     * @param visualization How the data will be shown.
     * @param requests      The data that will be graphed.
     * @param events        The data that will be annotated to the graph as red lines.
     */
    public TileDefinition(Visualization visualization, Collection<Request> requests, Collection<Query> events) {
        _visualization = checkNotNull(visualization, "visualization is null");
        checkNotNull(requests, "requests are null");
        checkNotNull(events, "events are null");
        _requests.addAll(requests);
        _events.addAll(events);
    }

    /**
     * The partial constructor for the TileDefinition.
     *
     * @param visualization How the data will be shown.
     */
    public TileDefinition(Visualization visualization) {
        _visualization = checkNotNull(visualization, "visualization is null");
    }

    /** Private constructor used for deserialization. */
    private TileDefinition() {
    }

    @JsonIgnore
    public Visualization getVisualization() {
        return _visualization;
    }

    public void setVisualization(Visualization visualization) {
        _visualization = checkNotNull(visualization, "vizibility is null");
    }

    @JsonIgnore
    public List<Request> getRequests() {
        return _requests;
    }

    @JsonIgnore
    public List<Query> getEvents() {
        return _events;
    }

    public String toString() {
        return "TileDefinition[" +
                "visualization=" + getVisualization() +
                ", requests=" + getRequests() +
                ", events=" + getEvents() +
                "]";
    }
}
