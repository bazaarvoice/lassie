package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import static com.bazaarvoice.lassie.screenboard.Preconditions.checkArgument;
import static com.bazaarvoice.lassie.screenboard.Preconditions.checkNotNull;

/**
 * The QueryValue class is a {@link Widget} that aggregates a value based on a query. It relays on the {@link ConditionalFormat}
 * which determines the actual comparison.
 */
public class QueryValue extends Widget {
    @JsonProperty("title_size")
    private int _titleSize = 16;
    @JsonProperty("title")
    private boolean _titleVisible = true;
    @JsonProperty("aggregator")
    private Aggregator _aggregator = Aggregator.AVERAGE;
    @JsonProperty("conditional_formats")
    private List<ConditionalFormat> _conditionalFormats = new ArrayList<ConditionalFormat>();
    @JsonProperty("title_align")
    private Alignment _titleAlignment = Alignment.LEFT;
    @JsonProperty("text_align")
    private Alignment _textAlignment = Alignment.LEFT;
    @JsonProperty("title_text")
    private String _title = "Title";
    @JsonProperty("precision")
    private int _precision = 1;
    @JsonProperty("query")
    private String _query = "default.user.data";
    @JsonProperty("timeframe")
    private Timeframe _timeframe = Timeframe.ONE_WEEK;
    @JsonProperty("text_size")
    private String _textSize = "auto";
    @JsonProperty("unit")
    private String _unit = "auto";

    /**
     * The constructor for the QueryValue that takes in a location and dimension.
     *
     * @param location   The location of the QueryValue.
     * @param dimensions The dimensions of the QueryValue.
     */
    public QueryValue(Location location, Dimensions dimensions) {
        super(location, dimensions);
    }

    /**
     * The constructor for the QueryValue that takes in a x / y and width / height.
     *
     * @param x      The QueryValue's x value.
     * @param y      The QueryValue's y value.
     * @param width  The QueryValue's width value.
     * @param height The QueryValue's height value.
     */
    public QueryValue(int x, int y, int width, int height) throws Exception {
        this(new Location(x, y), new Dimensions(width, height));
    }

    /**
     * Private constructor used for deserialization.
     * Set in the top left corner of the board with the default dimensions.
     */
    public QueryValue() throws Exception {
        this(0, 0, 14, 4);
    }

    /**
     * This automatically adds three conditionalFormats that rank green, yellow and red.
     * <p/>
     * GREEN = aggregated value is greater then the threshold.
     * YELLOW = aggregated value is less then or equal too 2/3 the threshold.
     * RED = aggregated value is less then too 1/3 the threshold.
     *
     * @param threshold The number that the aggregated value will be compared to.
     */
    public void addThresholdFormatting(double threshold) throws Exception {
        addThresholdFormatting(threshold, threshold * 2 / 3, threshold / 3);
    }

    /**
     * Allows the user to define three conditional formats on the basis that green is the largest value and red is the smallest.
     * <p/>
     * GREEN >= YELLOW >= RED
     *
     * @param green  The largest value.
     * @param yellow The middle value
     * @param red    The smallest value
     */
    public void addThresholdFormatting(double green, double yellow, double red) throws Exception {
        checkArgument(green >= yellow, "green is not greater then yellow");
        checkArgument(yellow >= red, "yellow is not greater then red");

        _conditionalFormats.add(new ConditionalFormat(Color.WHITE_ON_GREEN, false, Comparator.GREATER, green));
        _conditionalFormats.add(new ConditionalFormat(Color.WHITE_ON_YELLOW, false, Comparator.LESS_EQUALS, yellow));
        _conditionalFormats.add(new ConditionalFormat(Color.WHITE_ON_RED, false, Comparator.LESS, red));
    }

    @JsonIgnore
    public int getTitleSize() {
        return _titleSize;
    }

    public void setTitleSize(int titleSize) throws Exception {
        checkArgument(titleSize > 0, "Title size is less then one");
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
    public Aggregator getAggregator() {
        return _aggregator;
    }

    public void setAggregator(Aggregator aggregator) {
        _aggregator = checkNotNull(aggregator, "aggregator is null");
    }

    /**
     * The getter for all conditional formats that will effect the aggregated value
     *
     * @return
     */
    @JsonIgnore
    public List<ConditionalFormat> getConditionalFormats() {
        return _conditionalFormats;
    }

    @JsonIgnore
    public Alignment getTitleAlignment() {
        return _titleAlignment;
    }

    public void setTitleAlignment(Alignment titleAlignment) {
        _titleAlignment = checkNotNull(titleAlignment, "titleAlignment is null");
    }

    @JsonIgnore
    public Alignment getTextAlignment() {
        return _textAlignment;
    }

    public void setTextAlignment(Alignment textAlignment) {
        _textAlignment = checkNotNull(textAlignment, "textAlignment is null");
    }

    @JsonIgnore
    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = checkNotNull(title, "title is null");
    }

    /** @return the number of decimal places the  Query value will use */
    @JsonIgnore
    public int getPrecision() {
        return _precision;
    }

    /** @param precision the number of decimal places the  Query value will use */
    public void setPrecision(int precision) throws Exception {
        checkArgument(precision >= 0, "precision is less then zero");
        _precision = precision;
    }

    @JsonIgnore
    public String getQuery() {
        return _query;
    }

    /**
     * Allows the user to freely build the query.
     *
     * @param query
     * @see #setQuery(Aggregator, String, String)
     */
    public void setQuery(String query) {
        _query = checkNotNull(query, "query is null");
    }

    /**
     * guides the user in building a query by providing parameters.
     *
     * @param aggregator How the data will be aggregated
     * @param query      The data that will be aggregated
     * @param over       A filter on the data.
     */
    public void setQuery(Aggregator aggregator, String query, String over) {
        checkNotNull(aggregator, "aggregator is null");
        checkNotNull(query, "query is null");
        checkNotNull(over, "over is null");
        setQuery(String.format("%s:%s{%s}", aggregator.getName(), query, over));
    }

    /** @return timeframe, how much of the processed data is used to aggregate the data */
    @JsonIgnore
    public Timeframe getTimeframe() {
        return _timeframe;
    }

    /** @param timeframe how much of the processed data is used to aggregate the data */
    public void setTimeframe(Timeframe timeframe) {
        checkNotNull(timeframe, "timeframe is null");
        _timeframe = timeframe;
    }

    @JsonIgnore
    public String getTextSize() {
        return _textSize;
    }

    public void setTextSize(String textSize) {
        _textSize = checkNotNull(textSize, "textSize is null");
    }

    /**
     * Getter for the user defined unit of measurement that the aggregated value will be denoted by.
     *
     * @return the unit of measurement
     */
    @JsonIgnore
    public String getUnit() {
        return _unit;
    }

    /**
     * Setter for the user defined unit of measurement that the aggregated value will be denoted by.
     *
     * @param unit
     */
    public void setUnit(String unit) {
        _unit = checkNotNull(unit, "unit is null");
    }

    @JsonIgnore
    public String toString() {
        return "QueryValue[" +
                "title=" + getTitle() +
                ", titleAlignment=" + getTitleAlignment() +
                ", titleSize=" + getTitleSize() +
                ", titleVisible=" + isTitleVisible() +
                ", textSize=" + getTextSize() +
                ", textAlignment=" + getTextAlignment() +
                ", aggregator=" + getAggregator() +
                ", precision=" + getPrecision() +
                ", timeframe=" + getTimeframe() +
                ", unit=" + getUnit() +
                ", query=" + getQuery() + "]";
    }
}