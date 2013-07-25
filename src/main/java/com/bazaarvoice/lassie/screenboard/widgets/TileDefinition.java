package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class TileDefinition {
    @JsonProperty("viz")
    private Visualization _visualization = Visualization.TIMESERIES;
    @JsonProperty("requests")
    private List<Request> _requests = new ArrayList<Request>();
    @JsonProperty("events")
    private List<Query> _events = new ArrayList<Query>();

    public TileDefinition(Visualization visualization, Collection<Request> requests, Collection<Query> events) {
        _visualization = checkNotNull(visualization, "visualization is null");
        checkNotNull(requests, "requests are null");
        checkNotNull(events, "events are null");
        _requests.addAll(requests);
        _events.addAll(events);
    }

    public TileDefinition(Visualization visualization) {
        _visualization = checkNotNull(visualization, "visualization is null");
    }

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
