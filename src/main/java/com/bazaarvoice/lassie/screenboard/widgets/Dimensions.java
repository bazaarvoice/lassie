package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * The Width and Height of the Widget.
 * <p/>
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
     * @param width  The width of the widget.
     * @param height The height of the widget.
     */
    public Dimensions(int width, int height) {
        checkArgument(height > 0, "height is less then one");
        checkArgument(width > 0, "width is less then one");

        _height = height;
        _width = width;
    }

    /** Private constructor used for deserialization. */
    private Dimensions() {
        _width = 50;
        _height = 50;
    }

    @JsonIgnore
    public int getHeight() {
        return _height;
    }

    @JsonIgnore
    public int getWidth() {
        return _width;
    }

    @JsonIgnore
    public String toString() {
        return "Dimensions[width=" + _width + ", height=" + _height + "]";
    }
}