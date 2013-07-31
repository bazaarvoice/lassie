package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/** The EventStream class is a {@link Widget} that acts as a live ticker showing all the the events that match the user's query. */
public class EventStream extends Widget {
    @JsonProperty("title_size")
    private int _titleSize = 16;
    @JsonProperty("title")
    private boolean _titleVisible = false;
    @JsonProperty("title_align")
    private Alignment _titleAlignment = Alignment.LEFT;
    @JsonProperty("title_text")
    private String _title = "Title";
    @JsonProperty("query")
    private String _query = "*";
    @JsonProperty("timeframe")
    private Timeframe _timeframe = Timeframe.ONE_WEEK;

    /**
     * The constructor for the EventStream that takes in a location and dimension.
     *
     * @param location   The location of the EventStream.
     * @param dimensions The dimensions of the EventStream.
     */
    public EventStream(Location location, Dimensions dimensions) {
        super(location, dimensions);
    }

    /**
     * The constructor for the EventStream that takes in a x / y and width / height.
     *
     * @param x      The EventStream's x value.
     * @param y      The EventStream's y value.
     * @param width  The EventStream's width.
     * @param height The EventStream's height.
     */
    public EventStream(int x, int y, int width, int height) throws Exception {
        this(new Location(x, y), new Dimensions(width, height));
    }

    /**
     * Private constructor used for deserialization.
     * Set in the top left corner of the board with the default dimensions.
     */
    public EventStream() throws Exception {
        this(0, 0, 59, 57);
    }

    @JsonIgnore
    public int getTitleSize() {
        return _titleSize;
    }

    public void setTitleSize(int titleSize) throws Exception {
        checkArgument(titleSize > 0, "size less then 1");
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
     * The getter for the EventStream's query which will fuel the EventStream.
     *
     * @return The EventStream's query.
     */
    @JsonIgnore
    public String getQuery() {
        return _query;
    }

    /**
     * The setter for the EventStream's query which will fuel the EventStream.
     *
     * @param query The EventStream's query.
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
        return "EventStream[title=" + getTitle() + ", titleAlignment=" + getTitleAlignment() + ", titleSize=" + getTitleSize() + ", titleVisible=" + isTitleVisible() + "]";
    }
}