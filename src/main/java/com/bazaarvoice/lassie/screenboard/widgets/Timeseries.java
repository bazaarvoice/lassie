package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The Timeseries {@link Widget} serves as a visual graph for the queried data. The user can view it in both a line and stacked line graph.
 * The queried data can be filtered and broken down by the parameters in the {@link TileDefinition} class as well as have events annotated to the graph.
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
     * The constructor for the Timeseries that takes in a location and dimension.
     *
     * @param location   The location of the Timeseries.
     * @param dimensions The dimensions of the Timeseries.
     */
    public Timeseries(Location location, Dimensions dimensions) {
        super(location, dimensions);
    }

    /**
     * The constructor for the Timeseries that takes in a x / y and width / height.
     *
     * @param x      The Timeseries x value.
     * @param y      The Timeseries y value.
     * @param width  The Timeseries width.
     * @param height The Timeseries height.
     */
    public Timeseries(int x, int y, int width, int height) {
        this(new Location(x, y), new Dimensions(width, height));
    }

    /**
     * Private constructor used for deserialization.
     * Set in the top left corner of the board with the default dimensions.
     */
    public Timeseries() {
        this(0, 0, 47, 13);
    }

    @JsonIgnore
    public int getTitleSize() {
        return _titleSize;
    }

    public void setTitleSize(int titleSize) {
        checkArgument(titleSize > 0, "titleSize is less then one");
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
        _title = checkNotNull(title, "titleText is null");
    }

    /**
     * Getter for the length of time the query will pull data from.
     *
     * @return
     */
    @JsonIgnore
    public Timeframe getTimeframe() {
        return _timeframe;
    }

    /**
     * Setter for the length of time the query will pull data from.
     *
     * @param timeframe
     */
    public void setTimeframe(Timeframe timeframe) {
        checkNotNull(timeframe, "time frame is null");
        _timeframe = timeframe;
    }

    /**
     * The getter for the TileDefinition which holds the actual request query and events.
     *
     * @return
     */
    @JsonIgnore
    public TileDefinition getTileDefinition() {
        return _tileDefinition;
    }

    /**
     * The getter for the TileDefinition which holds the actual request query and events.
     *
     * @param tileDefinition
     */
    public void setTileDefinition(TileDefinition tileDefinition) {
        _tileDefinition = checkNotNull(tileDefinition, "tileDefinition is null");
    }

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

