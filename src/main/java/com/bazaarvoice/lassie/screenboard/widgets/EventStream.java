package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The EventStream class is a Widget that acts as a live ticker showing all the the events that match the user's query.
 */
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
     * @param location The location of the EventStream.
     * @param dimensions The dimensions of the EventStream.
     */
    public EventStream(Location location, Dimensions dimensions) {
        super(location, dimensions);
    }

    /**
     * The constructor for the EventStream that takes in a x / y and width / height
     * @param x The EventStream's x value.
     * @param y The EventStream's y value.
     * @param width The EventStream's width.
     * @param height The EventStream's height.
     */
    public EventStream(int x, int y, int width, int height) {
        this(new Location(x, y), new Dimensions(width, height));
    }

    /**
     * Plain Conditional format constructor, mainly used for Jackson serialization / deserialization.
     * Set in the top left corner of the board with the default dimensions.
     */
    public EventStream() {
        this(0, 0, 59, 57);
    }

    /**
     * The getter for the EventStream's title size.
     * @return the value of the title.
     */
    @JsonIgnore
    public int getTitleSize() {
        return _titleSize;
    }

    /**
     * The setter for the EventStream's title size.
     * @param titleSize the value of the title.
     */
    public void setTitleSize(int titleSize) {
        checkArgument(titleSize > 0, "size less then 1");
        _titleSize = titleSize;
    }

    /**
     * The getter for the visibility of the EventStream's title.
     * @return The visibility of the EventStream's title.
     */
    @JsonIgnore
    public boolean isTitleVisible() {
        return _titleVisible;
    }

    /**
     * The setter for the visibility of the EventStream's title.
     * @param titleVisible The visibility of the EventStream's title.
     */
    public void setTitleVisible(boolean titleVisible) {
        _titleVisible = titleVisible;
    }

    /**
     * The getter for the Alignment enum that the EventStream uses.
     * @return Alignment enum that the EventStream uses.
     */
    @JsonIgnore
    public Alignment getTitleAlignment() {
        return _titleAlignment;
    }

    /**
     * The setter for the Alignment enum that the EventStream uses.
     * @param titleAlignment Alignment enum that the EventStream uses.
     */
    public void setTitleAlignment(Alignment titleAlignment) {
        _titleAlignment = checkNotNull(titleAlignment, "titleAlignment is null");
    }

    /**
     * The getter for the Title of the EventStream.
     * @return The title of the EventStream.
     */
    @JsonIgnore
    public String getTitle() {
        return _title;
    }

    /**
     * The setter for the Title of the EventStream.
     * @param title The title of the EventStream.
     */
    public void setTitle(String title) {
        _title = checkNotNull(title, "title is null");
    }

    /**
     * The getter for the EventStream's query.
     * @return The EventStream's query.
     */
    @JsonIgnore
    public String getQuery() {
        return _query;
    }

    /**
     * The setter for the EventStream's query.
     * @param query The EventStream's query.
     */
    public void setQuery(String query) {
        _query = checkNotNull(query, "query is null");
    }

    /**
     * The getter for the Timeframe enum that the EventStream uses.
     * @return The Timeframe that the EventStream uses.
     */
    @JsonIgnore
    public Timeframe getTimeframe() {
        return _timeframe;
    }

    /**
     * The setter for the Timeframe enum that the EventStream uses.
     * @param timeframe The Timeframe that the EventStream will use.
     */
    public void setTimeframe(Timeframe timeframe) {
        checkNotNull(timeframe, "time frame is null");
        _timeframe = timeframe;
    }

    /**
     * The toString override for the EventStream class.
     * @return The string containing all the values the EventStream class uses.
     */
    @JsonIgnore
    public String toString() {
        return "EventStream[title=" + getTitle() + ", titleAlignment=" + getTitleAlignment() + ", titleSize=" + getTitleSize() + ", titleVisible=" + isTitleVisible() + "]";
    }
}