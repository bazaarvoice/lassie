package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkArgument;

/**
 *
 */
public class Dimensions {
    @JsonProperty("height")
    private final int _height;
    @JsonProperty("width")
    private final int _width;

    /**
     *
     * @param width
     * @param height
     */
    public Dimensions(int width, int height) {
        checkArgument(height > 0, "height is less then one");
        checkArgument(width > 0, "width is less then one");

        _height = height;
        _width = width;
    }

    /**
     *
     */
    private Dimensions() {
        _width = 50;
        _height = 50;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public int getHeight() {
        return _height;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public int getWidth() {
        return _width;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public String toString() {
        return "Dimensions[width=" + _width + ", height=" + _height + "]";
    }
}
