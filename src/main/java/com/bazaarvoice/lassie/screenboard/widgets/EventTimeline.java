package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The EventTimeline is a live ticker that shows the number of events as a bargraph.
 */
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
     * @param location The location of the EventTimeline.
     * @param dimension The dimension of the EventTimeline.
     */
    public EventTimeline(Location location, Dimensions dimension) {
        super(location, dimension);
    }

    /**
     * The constructor for the EventStream that takes in a x / y and width / height.
     *
     * @param x The x value of the EventTimeline.
     * @param y The y value of the EventTimeline.
     * @param width The width of the EventTimeline.
     * @param height The height of the EventTimeline.
     */
    public EventTimeline(int x, int y, int width, int height) {
        this(new Location(x, y), new Dimensions(width, height));
    }

    /**
     * Plain Conditional format constructor, mainly used for Jackson serialization / deserialization.
     *
     * Set in the top left corner of the board with the default dimensions.
     */
    public EventTimeline() {
        this(0, 0, 91, 9);
    }

    /**
     * The getter for the EventTimeline's title size.
     *
     * @return The value of the title.
     */
    @JsonIgnore
    public int getTitleSize() {
        return _titleSize;
    }

    /**
     * The setter for the EventTimeline's title size.
     *
     * @param titleSize The value of the title.
     */
    public void setTitleSize(int titleSize) {
        checkArgument(titleSize > 0, "size is less then 1");
        _titleSize = titleSize;
    }

    /**
     * The getter for the visibility of the EventTimeline's title.
     *
     * @return The visibility of the EventTimelines's title.
     */
    @JsonIgnore
    public boolean isTitleVisible() {
        return _titleVisible;
    }

    /**
     * The setter for the visibility of the EventTimeline's title.
     *
     * @param titleVisible The visibility of the EventStream's title.
     */
    public void setTitleVisible(boolean titleVisible) {
        _titleVisible = titleVisible;
    }

    /**
     * The getter for the Alignment enum that the EventTimeline uses.
     *
     * @return Alignment enum that the EventTimeline uses.
     */
    @JsonIgnore
    public Alignment getTitleAlignment() {
        return _titleAlignment;
    }

    /**
     * The setter for the Alignment enum that the EventTimeline uses.
     *
     * @param titleAlignment Alignment enum that the EventTimeline uses.
     */
    public void setTitleAlignment(Alignment titleAlignment) {
        _titleAlignment = checkNotNull(titleAlignment, "titleAlignment is null");
    }

    /**
     * The getter for the Title of the EventTimeline.
     *
     * @return The title of the EventTimeline.
     */
    @JsonIgnore
    public String getTitle() {
        return _title;
    }

    /**
     * The setter for the Title of the EventTimeline.
     *
     * @param title The title of the EventTimeline.
     */
    public void setTitle(String title) {
        _title = checkNotNull(title, "title is null");
    }

    /**
     * The getter for the EventTimeline's query.
     *
     * @return The EventTimeline's query.
     */
    @JsonIgnore
    public String getQuery() {
        return _query;
    }

    /**
     * The setter for the EventTimeline's query.
     *
     * @param query The EventTimeline's query.
     */
    public void setQuery(String query) {
        _query = checkNotNull(query, "query is null");
    }

    /**
     * The getter for the Timeframe enum that the EventTimeline uses.
     *
     * @return The Timeframe that the EventTimeline uses.
     */
    @JsonIgnore
    public Timeframe getTimeframe() {
        return _timeframe;
    }

    /**
     * The setter for the Timeframe enum that the EventTimeline uses.
     *
     * @param timeframe The Timeframe that the EventTimeline will use.
     */
    public void setTimeframe(Timeframe timeframe) {
        checkNotNull(timeframe, "time frame is null");
        _timeframe = timeframe;
    }

    /**
     * The toString override for the EventTimeline class.
     *
     * @return The string containing all the values the EventTimeline class uses.
     */
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
