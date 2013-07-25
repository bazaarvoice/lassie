package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

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

    public EventStream(Location location, Dimensions dimensions) {
        super(location, dimensions);
    }

    public EventStream(int x, int y, int width, int height) {
        this(new Location(x, y), new Dimensions(width, height));
    }

    public EventStream() {
        this(0, 0, 59, 57);
    }

    @JsonIgnore
    public int getTitleSize() {
        return _titleSize;
    }

    public void setTitleSize(int titleSize) {
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

    @JsonIgnore
    public String getQuery() {
        return _query;
    }

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






