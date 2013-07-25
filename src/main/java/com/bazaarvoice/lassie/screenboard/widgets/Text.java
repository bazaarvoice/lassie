package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
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
     *
     * @param location
     * @param dimension
     */
    public Text(Location location, Dimensions dimension) {
        super(location, dimension);
    }

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Text(int x, int y, int width, int height) {
        this(new Location(x, y), new Dimensions(width, height));
    }

    /**
     *
     */
    public Text() {
        this(0, 0, 30, 10);
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public int getTitleSize() {
        return _titleSize;
    }

    /**
     *
     * @param titleSize
     */
    public void setTitleSize(int titleSize) {
        checkArgument(titleSize > 0, "titleSize is less then one");
        _titleSize = titleSize;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public boolean isTitleVisible() {
        return _titleVisible;
    }

    /**
     *
     * @param titleVisible
     */
    public void setTitleVisible(boolean titleVisible) {
        _titleVisible = titleVisible;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public String getColor() {
        return _color;
    }

    /**
     *
     * @param color
     */
    public void setColor(String color) {
        checkNotNull(color, "color is null");
        checkArgument(color.matches("^#[a-fA-F0-9]{6}$"), "color must be hex");
        _color = color;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public String getText() {
        return _text;
    }

    /**
     *
     * @param text
     */
    public void setText(String text) {
        _text = checkNotNull(text, "text is null");
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public Alignment getTitleAlignment() {
        return _titleAlignment;
    }

    /**
     *
     * @param titleAlignment
     */
    public void setTitleAlignment(Alignment titleAlignment) {
        _titleAlignment = checkNotNull(titleAlignment, "title Alignment is null");
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public Alignment getTextAlignment() {
        return _textAlignment;
    }

    /**
     *
     * @param textAlignment
     */
    public void setTextAlignment(Alignment textAlignment) {
        checkNotNull(textAlignment, "text alignment is null");
        checkArgument(textAlignment == Alignment.CENTER, "Not a valid alignment");
        _textAlignment = textAlignment;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public String getTitle() {
        return _title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        _title = checkNotNull(title, "title is null");
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public String getFontSize() {
        return _fontSize;
    }

    /**
     *
     * @param fontSize
     */
    public void setFontSize(String fontSize) {
        _fontSize = checkNotNull(fontSize, "font size is null");
    }

    /**
     *
     * @return
     */
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
