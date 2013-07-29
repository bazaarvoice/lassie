package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The TileDefination class holds the events and requests that drives the Timeseries class.
 * It also holds a visualization that currently only supports timeseries.
 * <p/>
 * {@link Timeseries}
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
     * @param events        The data that will be annotated to the graph as red lives.
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

    /** Plain TileDefinition constructor, mainly used for Jackson serialization / deserialization. */
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

    /**
     * The toString override for the TileDefinition class.
     *
     * @return The string containing all the values the TileDefinition class uses.
     */
    public String toString() {
        return "TileDefinition[" +
                "visualization=" + getVisualization() +
                ", requests=" + getRequests() +
                ", events=" + getEvents() +
                "]";
    }
}
