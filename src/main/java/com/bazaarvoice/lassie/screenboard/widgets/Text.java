package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The Text class is a {@link Widget} used to place plain text onto the screenboard. It is unique because it has no background and
 * the color is a user defined hexadecimal value .
 */
public class Text extends Widget {
    @JsonProperty("title_size")
    private int _titleSize = 16;
    @JsonProperty("title")
    private boolean _titleVisible = true;
    @JsonProperty("color")
    private String _color = "#4d4d4d";
    @JsonProperty("text")
    private String _text = "body";
    @JsonProperty("title_align")
    private Alignment _titleAlignment = Alignment.LEFT;
    @JsonProperty("text_align")
    private Alignment _textAlignment = Alignment.LEFT;
    @JsonProperty("title_text")
    private String _title = "Title";
    @JsonProperty("font_size")
    private String _fontSize = "auto";

    /**
     * The constructor for the Text that takes in a location and dimension.
     *
     * @param location  The Text's Location.
     * @param dimension The Text's dimension.
     */
    public Text(Location location, Dimensions dimension) {
        super(location, dimension);
    }

    /**
     * The constructor for the Text that takes in a location and dimension.
     *
     * @param x      The Text's x value.
     * @param y      The Text's y value.
     * @param width  The Text's width value.
     * @param height The Text's height value.
     */
    public Text(int x, int y, int width, int height) {
        this(new Location(x, y), new Dimensions(width, height));
    }

    /**
     * Private constructor used for deserialization.
     * Set in the top left corner of the board with the default dimensions.
     */
    public Text() {
        this(0, 0, 30, 10);
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

    /**
     * The getter for the color of the Text widget. It takes in a hexadecimal color.
     *
     * @return
     */
    @JsonIgnore
    public String getColor() {
        return _color;
    }

    /**
     * The setter for the color of the Text widget. It takes in a hexadecimal color.
     *
     * @param color
     */
    public void setColor(String color) {
        checkNotNull(color, "color is null");
        checkArgument(color.matches("^#[a-fA-F0-9]{6}$"), "color must be hex");
        _color = color;
    }

    @JsonIgnore
    public String getText() {
        return _text;
    }

    public void setText(String text) {
        _text = checkNotNull(text, "text is null");
    }

    @JsonIgnore
    public Alignment getTitleAlignment() {
        return _titleAlignment;
    }

    public void setTitleAlignment(Alignment titleAlignment) {
        _titleAlignment = checkNotNull(titleAlignment, "title Alignment is null");
    }

    @JsonIgnore
    public Alignment getTextAlignment() {
        return _textAlignment;
    }

    public void setTextAlignment(Alignment textAlignment) {
        checkNotNull(textAlignment, "text alignment is null");
        checkArgument(textAlignment == Alignment.CENTER, "Not a valid alignment");
        _textAlignment = textAlignment;
    }

    @JsonIgnore
    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = checkNotNull(title, "title is null");
    }

    @JsonIgnore
    public String getFontSize() {
        return _fontSize;
    }

    public void setFontSize(String fontSize) {
        _fontSize = checkNotNull(fontSize, "font size is null");
    }

    @JsonIgnore
    public String toString() {
        return "Text[" +
                "title=" + getTitle() +
                ", titleAlignment=" + getTitleAlignment() +
                ", titleSize=" + getTitleSize() +
                ", titleVisible=" + isTitleVisible() +
                ", text=" + getText() +
                ", textAlignment=" + getTextAlignment() +
                ", fontSize=" + getFontSize() +
                ", color=" + getColor() +
                "]";
    }
}
