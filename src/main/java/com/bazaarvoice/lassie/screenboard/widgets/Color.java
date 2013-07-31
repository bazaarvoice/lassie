package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

import static com.google.common.base.Preconditions.checkNotNull;

public enum Color {
    RED_ON_WHITE("red_on_white"), YELLOW_ON_WHITE("yellow_on_white"), GREEN_ON_WHITE("green_on_white"), WHITE_ON_RED("white_on_red"), WHITE_ON_YELLOW("white_on_yellow"), WHITE_ON_GREEN("white_on_green");

    private final String _name;

    /**
     * Constructor that sets the name of the Color as it is documented in the datadog API.
     *
     * @param name name of the Color.
     */
    private Color(String name) {
        _name = name;
    }

    /**
     * Gets the name of the Color as it is documented in the datadog API.
     *
     * @return The name of the Color.
     */
    @JsonValue
    public String getName() {
        return _name;
    }

    /**
     * Parse Color name into the corresponding {@link Color} value.
     *
     * @param name The name of the expected enum.
     * @return The Color enum.
     */
    @JsonCreator
    public static Color fromName(String name) {
        checkNotNull(name);
        return Color.valueOf(name.toUpperCase());
    }
}