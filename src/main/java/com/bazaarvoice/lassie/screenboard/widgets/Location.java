package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkArgument;

public class Location {
    @JsonProperty("x")
    private final int _x;
    @JsonProperty("y")
    private final int _y;

    public Location(int x, int y) {
        checkArgument(x >= 0, "x is less then zero");
        checkArgument(y >= 0, "y is less then zero");
        _x = x;
        _y = y;
    }

    private Location() {
        this(0, 0);
    }

    @JsonIgnore
    public int getX() {
        return _x;
    }

    @JsonIgnore
    public int getY() {
        return _y;
    }

    @JsonIgnore
    public String toString() {
        return "Location[x=" + _x + ", y=" + _y + "]";
    }
}
