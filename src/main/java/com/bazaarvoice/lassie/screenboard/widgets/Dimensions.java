package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkArgument;

/**
 *The Width and Height of the Widget.
 *
 * {@link Widget}
 */
public class Dimensions {
    @JsonProperty("height")
    private final int _height;
    @JsonProperty("width")
    private final int _width;

    /**
     * Constructor for the Dimension class.
     *
     * @param width The width of the widget.
     * @param height The height of the widget.
     */
    public Dimensions(int width, int height) {
        checkArgument(height > 0, "height is less then one");
        checkArgument(width > 0, "width is less then one");

        _height = height;
        _width = width;
    }

    /**
     * Plain Conditional format constructor, mainly used for Jackson serialization / deserialization
     */
    private Dimensions() {
        _width = 50;
        _height = 50;
    }

    /**
     * The getter for the height of the Widget
     *
     * @return The height of the Widget
     */
    @JsonIgnore
    public int getHeight() {
        return _height;
    }

    /**
     * The getter for the width of the Widget
     *
     * @return the width of the Widget
     */
    @JsonIgnore
    public int getWidth() {
        return _width;
    }

    /**
     * The toString override for the Dimensions of the Widget.
     *
     * @return The string containing all the values the Dimensions class uses.
     */
    @JsonIgnore
    public String toString() {
        return "Dimensions[width=" + _width + ", height=" + _height + "]";
    }
}