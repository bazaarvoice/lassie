package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

import static com.google.common.base.Preconditions.checkNotNull;

public enum Color {
    RED_ON_WHITE("red_on_white"), YELLOW_ON_WHITE("yellow_on_white"), GREEN_ON_WHITE("green_on_white"), WHITE_ON_RED("white_on_red"), WHITE_ON_YELLOW("white_on_yellow"), WHITE_ON_GREEN("white_on_green");

    private final String _name;

    private Color(String name) {
        _name = name;
    }

    @JsonValue
    public String getName() {
        return _name;
    }

    @JsonCreator
    public static Color fromName(String name) {
        checkNotNull(name);
        return Color.valueOf(name.toUpperCase());
    }
}
