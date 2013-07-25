package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class Timeseries extends Widget {
    @JsonProperty("title_size")
    private int _titleSize = 16;
    @JsonProperty("title")
    private boolean _titleVisible = true;
    @JsonProperty("title_align")
    private Alignment _titleAlignment = Alignment.LEFT;
    @JsonProperty("title_text")
    private String _title = "Title";
    @JsonProperty("timeframe")
    private Timeframe _timeframe = Timeframe.ONE_WEEK;
    @JsonProperty("tile_def")
    private TileDefinition _tileDefinition = new TileDefinition(Visualization.TIMESERIES);

    /**
     *
     * @param location
     * @param dimensions
     */
    public Timeseries(Location location, Dimensions dimensions) {
        super(location, dimensions);
    }

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Timeseries(int x, int y, int width, int height) {
        this(new Location(x, y), new Dimensions(width, height));
    }

    /**
     *
     */
    public Timeseries() {
        this(0, 0, 47, 13);
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
        checkArgument(titleSize > 0, "titleSize is less then one");
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
        _title = checkNotNull(title, "titleText is null");
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
    public TileDefinition getTileDefinition() {
        return _tileDefinition;
    }

    /**
     *
     * @param tileDefinition
     */
    public void setTileDefinition(TileDefinition tileDefinition) {
        _tileDefinition = checkNotNull(tileDefinition, "tileDefinition is null");
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public String toString() {
        return "Timeseries[" +
                "title=" + getTitle() +
                ", titleAlignment=" + getTitleAlignment() +
                ", titleSize=" + getTitleSize() +
                ", titleVisible=" + isTitleVisible() +
                ", timeframe=" + getTimeframe() +
                ", tileDefinition=" + getTileDefinition() +
                "]";
    }
}

