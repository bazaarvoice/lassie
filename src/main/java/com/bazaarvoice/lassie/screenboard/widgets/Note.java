package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonValue;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class Note extends Widget {
    @JsonProperty("title_size")
    private int _titleSize = 16;
    @JsonProperty("title")
    private boolean _titleVisible = true;
    @JsonProperty("tick_pos")
    private String _tickPosition = "50%";
    @JsonProperty("title_align")
    private Alignment _titleAlignment = Alignment.LEFT;
    @JsonProperty("tick_edge")
    private Edge _tickEdge = Edge.RIGHT;
    @JsonProperty("text_align")
    private Alignment _textAlignment = Alignment.LEFT;
    @JsonProperty("title_text")
    private String _title = "Title";
    @JsonProperty("bgcolor")
    private BackgroundColor _backgroundColor = BackgroundColor.YELLOW;
    @JsonProperty("html")
    private String _text = "body";
    @JsonProperty("font_size")
    private String _fontSize = "14";
    @JsonProperty("tick")
    private boolean _tickVisible = true;

    /**
     *
     */
    public enum Edge {
        LEFT("left"), TOP("top"), RIGHT("right"), BOTTOM("bottom");

        private final String _name;

        private Edge(String name) {
            _name = name;
        }

        @JsonValue
        public String getName() {
            return _name;
        }

        @JsonCreator
        public static Edge fromName(String name) {
            checkNotNull(name);
            return Edge.valueOf(name.toUpperCase());
        }
    }

    /**
     *
     */
    public enum BackgroundColor {
        YELLOW("yellow"), BLUE("blue"), PINK("pink"), GRAY("gray"), WHITE("white");

        private final String _name;

        private BackgroundColor(String name) {
            _name = name;
        }

        @JsonValue
        public String getName() {
            return _name;
        }

        @JsonCreator
        public static BackgroundColor fromName(String name) {
            checkNotNull(name);
            return BackgroundColor.valueOf(name.toUpperCase());
        }
    }

    /**
     *
     * @param location
     * @param dimensions
     */
    public Note(Location location, Dimensions dimensions) {
        super(location, dimensions);
    }

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Note(int x, int y, int width, int height) {
        this(new Location(x, y), new Dimensions(width, height));
    }

    /**
     *
     */
    public Note() {
        this(0, 0, 30, 15);
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
    public String getTickPosition() {
        return _tickPosition;
    }

    /**
     *
     * @param tickPosition
     */
    public void setTickPosition(String tickPosition) {
        _tickPosition = checkNotNull(tickPosition, "tickPosition is null");
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
        _title = checkNotNull(title, "titleText is null");
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public BackgroundColor getBackgroundColor() {
        return _backgroundColor;
    }

    /**
     *
     * @param backgroundColor
     */
    public void setBackgroundColor(BackgroundColor backgroundColor) {
        _backgroundColor = checkNotNull(backgroundColor, "backgroundColor is null");
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
    public String getFontSize() {
        return _fontSize;
    }

    /**
     *
     * @param fontSize
     */
    public void setFontSize(String fontSize) {
        _fontSize = checkNotNull(fontSize, "fontSize is null");
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
    public Alignment getTitleAlignment() {
        return _titleAlignment;
    }

    /**
     *
     * @param titleAlignment
     */
    public void setTitleAlignment(Alignment titleAlignment) {
        _titleAlignment = checkNotNull(titleAlignment, "titleAlignment is null");
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public Edge getTickEdge() {
        return _tickEdge;
    }

    /**
     *
     * @param tickEdge
     */
    public void setTickEdge(Edge tickEdge) {
        _tickEdge = checkNotNull(tickEdge, "tickEdge is null");
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
        _textAlignment = checkNotNull(textAlignment, "textAlignment is null");
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public boolean isTickVisible() {
        return _tickVisible;
    }

    /**
     *
     * @param tickVisible
     */
    public void setTickVisible(boolean tickVisible) {
        _tickVisible = tickVisible;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public String toString() {
        return "Note[" +
                "title=" + getTitle() +
                ", titleAlignment=" + getTitleAlignment() +
                ", titleSize=" + getTitleSize() +
                ", titleVisible=" + isTitleVisible() +
                ", text=" + getText() +
                ", textAlignment=" + getTextAlignment() +
                ", tickEdge=" + getTickEdge() +
                ", fontSize=" + getFontSize() +
                ", tickVisible=" + isTickVisible() +
                "]";
    }
}

