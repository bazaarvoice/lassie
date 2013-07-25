package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
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
     *
     * @param location
     * @param dimension
     */
    public EventTimeline(Location location, Dimensions dimension) {
        super(location, dimension);
    }

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public EventTimeline(int x, int y, int width, int height) {
        this(new Location(x, y), new Dimensions(width, height));
    }

    /**
     *
     */
    public EventTimeline() {
        this(0, 0, 91, 9);
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public int getTitleSize() {
        return _titleSize;
    }

    /**
     *
     * @param titleSize
     */
    public void setTitleSize(int titleSize) {
        checkArgument(titleSize > 0, "size is less then 1");
        _titleSize = titleSize;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public boolean isTitleVisible() {
        return _titleVisible;
    }

    /**
     *
     * @param titleVisible
     */
    public void setTitleVisible(boolean titleVisible) {
        _titleVisible = titleVisible;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public Alignment getTitleAlignment() {
        return _titleAlignment;
    }

    /**
     *
     * @param titleAlignment
     */
    public void setTitleAlignment(Alignment titleAlignment) {
        _titleAlignment = checkNotNull(titleAlignment, "titleAlignment is null");
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public String getTitle() {
        return _title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        _title = checkNotNull(title, "title is null");
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public String getQuery() {
        return _query;
    }

    /**
     *
     * @param query
     */
    public void setQuery(String query) {
        _query = checkNotNull(query, "query is null");
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public Timeframe getTimeframe() {
        return _timeframe;
    }

    /**
     *
     * @param timeframe
     */
    public void setTimeframe(Timeframe timeframe) {
        checkNotNull(timeframe, "time frame is null");
        _timeframe = timeframe;
    }

    /**
     *
     * @return
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
