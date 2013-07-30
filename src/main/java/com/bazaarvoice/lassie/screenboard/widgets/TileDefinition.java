package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

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
