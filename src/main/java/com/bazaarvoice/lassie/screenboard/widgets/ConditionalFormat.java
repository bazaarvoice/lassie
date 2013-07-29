package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The ConditionalFormat class is mainly used in the QueryValue class.
 * It holds The Color that the widget will be once the conditions met by the aggregated value,
 * the comparator, and the threshold value.
 * </p>
 * {@link QueryValue}
 */
public class ConditionalFormat {
    @JsonProperty("color")
    private Color _color = Color.WHITE_ON_GREEN;
    @JsonProperty("invert")
    private boolean _inverted = false;
    @JsonProperty("comparator")
    private Comparator _comparator = Comparator.GREATER;
    @JsonProperty("value")
    private double _value = 0;

    /**
     * Conditional format constructor that sets the  Color based on the  aggregated value versus
     * the value using the comparator.
     *
     * @param color      Color of the conditional format.
     * @param inverted   Compares the aggregated value to the (threshold) value.
     * @param comparator The Comparator used in the comparison between the aggregated value and the (threshold) value.
     * @param value      The threshold value compared to the aggregated value.
     */
    public ConditionalFormat(Color color, boolean inverted, Comparator comparator, double value) {
        _color = checkNotNull(color, "color is null");
        _inverted = inverted;
        _comparator = checkNotNull(comparator, "comparator is null");
        _value = checkNotNull(value, "value is null");
    }

    /** Plain ConditionalFormat constructor, mainly used for Jackson serialization / deserialization */
    private ConditionalFormat() {
    }

    /**
     * The getter for the Color of the conditional format.
     *
     * @return The Color enum of the conditional format.
     */
    @JsonIgnore
    public Color getColor() {
        return _color;
    }

    /**
     * The setter for the Color of the conditionalFormat.
     *
     * @param color The Color enum that will be used to set the color for the conditionalFormat.
     */
    public void setColor(Color color) {
        _color = checkNotNull(color, "color is null");
    }

    /**
     * The getter for the inverted boolean for the conditionalFormat.
     *
     * @return The value of the inverted parameter.
     */
    @JsonIgnore
    public boolean isInverted() {
        return _inverted;
    }

    /**
     * The setter for the inverted boolean for the conditionalFormat.
     *
     * @param inverted The value of the inverted parameter.
     */
    public void setInverted(boolean inverted) {
        _inverted = inverted;
    }

    /**
     * The getter for the Comparator enum that the conditionalFormat uses.
     *
     * @return The Comparator enum that the conditionalFormat uses.
     */
    @JsonIgnore
    public Comparator getComparator() {
        return _comparator;
    }

    /**
     * The setter for the Comparator enum that the conditionalFormat uses.
     *
     * @param comparator The Comparator enum that the conditionalFormat uses
     */
    public void setComparator(Comparator comparator) {
        _comparator = checkNotNull(comparator, "comparator is null");
    }

    /**
     * The getter for the value that will be used to compare against the aggregated value.
     *
     * @return The value that will be used to compare against the aggregated value.
     */
    @JsonIgnore
    public double getValue() {
        return _value;
    }

    /**
     * The setter for the value that will be used to compare against the aggregated value.
     *
     * @param value The value that will be used to compare against the aggregated value.
     */
    public void setValue(double value) {
        _value = value;
    }

    public String toString() {
        return "ConditionalFormat[" +
                "color=" + getColor() +
                ", inverted=" + isInverted() +
                ", comparator=" + getComparator() +
                ", value=" + getValue() +
                "]";
    }
}
