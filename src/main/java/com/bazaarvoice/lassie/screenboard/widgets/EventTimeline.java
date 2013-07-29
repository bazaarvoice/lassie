package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/** The EventTimeline {@link Widget} is a live ticker that shows the number of events as a bargraph. */
public class EventTimeline extends Widget {
    @JsonProperty("title_size")
    private int _titleSize = 16;
    @JsonProperty("title")
    private boolean _titleVisible = false;
    @JsonProperty("title_align")
    private Alignment _titleAlignment = Alignment.LEFT;
    @JsonProperty("title_text")
    private String _title = "Title";
    @JsonProperty("query")
    private String _query = "status:error";
    @JsonProperty("timeframe")
    private Timeframe _timeframe = Timeframe.ONE_WEEK;

    /**
     * The constructor for the EventTimeline that takes in a location and dimension.
     *
     * @param location  The location of the EventTimeline.
     * @param dimension The dimension of the EventTimeline.
     */
    public EventTimeline(Location location, Dimensions dimension) {
        super(location, dimension);
    }

    /**
     * The constructor for the EventStream that takes in a x / y and width / height.
     *
     * @param x      The x value of the EventTimeline.
     * @param y      The y value of the EventTimeline.
     * @param width  The width of the EventTimeline.
     * @param height The height of the EventTimeline.
     */
    public EventTimeline(int x, int y, int width, int height) {
        this(new Location(x, y), new Dimensions(width, height));
    }

    /**
     * Private constructor used for deserialization.
     * Set in the top left corner of the board with the default dimensions.
     */
    public EventTimeline() {
        this(0, 0, 91, 9);
    }

    @JsonIgnore
    public int getTitleSize() {
        return _titleSize;
    }

    public void setTitleSize(int titleSize) {
        checkArgument(titleSize > 0, "size is less then 1");
        _titleSize = titleSize;
    }

    @JsonIgnore
    public boolean isTitleVisible() {
        return _titleVisible;
    }

    public void setTitleVisible(boolean titleVisible) {
        _titleVisible = titleVisible;
    }

    @JsonIgnore
    public Alignment getTitleAlignment() {
        return _titleAlignment;
    }

    public void setTitleAlignment(Alignment titleAlignment) {
        _titleAlignment = checkNotNull(titleAlignment, "titleAlignment is null");
    }

    @JsonIgnore
    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = checkNotNull(title, "title is null");
    }

    /**
     * The getter for the EventTimeline's query which will fuel the EventTimeline..
     *
     * @return The EventTimeline's query.
     */
    @JsonIgnore
    public String getQuery() {
        return _query;
    }

    /**
     * The setter for the EventTimeline's query which will fuel the EventStream..
     *
     * @param query The EventTimeline's query.
     */
    public void setQuery(String query) {
        _query = checkNotNull(query, "query is null");
    }

    @JsonIgnore
    public Timeframe getTimeframe() {
        return _timeframe;
    }

    public void setTimeframe(Timeframe timeframe) {
        checkNotNull(timeframe, "time frame is null");
        _timeframe = timeframe;
    }

    @JsonIgnore
    public String toString() {
        return "EventTimeline[" +
                "title=" + getTitle() +
                ", titleAlignment=" + getTitleAlignment() +
                ", titleSize=" + getTitleSize() +
                ", titleVisible=" + isTitleVisible() +
                ", query=" + getQuery() +
                ", timeframe=" + getTimeframe() +
                "]";
    }
}
