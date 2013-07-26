package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * The Location contains the x and y value for the Widget.
 * {@link Widget}
 */
public class Location {
    @JsonProperty("x")
    private final int _x;
    @JsonProperty("y")
    private final int _y;

    /**
     * The constructor for the Location class.
     *
     * @param x The x value of the Location.
     * @param y The y value of the Location.
     */
    public Location(int x, int y) {
        checkArgument(x >= 0, "x is less then zero");
        checkArgument(y >= 0, "y is less then zero");
        _x = x;
        _y = y;
    }

    /**
     * Plain Conditional format constructor, mainly used for Jackson serialization / deserialization.
     */
    private Location() {
        this(0, 0);
    }

    /**
     * The getter for the x value.
     *
     * @return The x value.
     */
    @JsonIgnore
    public int getX() {
        return _x;
    }

    /**
     * The getter for the y value
     *
     * @return The y value.
     */
    @JsonIgnore
    public int getY() {
        return _y;
    }

    /**
     * The toString override for the Dimensions of the Widget.
     *
     * @return The string containing all the values the Location class uses.
     */
    @JsonIgnore
    public String toString() {
        return "Location[x=" + _x + ", y=" + _y + "]";
    }
}
