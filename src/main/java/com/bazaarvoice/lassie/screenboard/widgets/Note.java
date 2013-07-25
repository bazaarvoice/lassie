/*
 * Copyright 2013 Bazaarvoice, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonValue;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

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

    public Note(Location location, Dimensions dimensions) {
        super(location, dimensions);
    }

    public Note(int x, int y, int width, int height) {
        this(new Location(x, y), new Dimensions(width, height));
    }

    public Note() {
        this(0, 0, 30, 15);
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
    public String getTickPosition() {
        return _tickPosition;
    }

    public void setTickPosition(String tickPosition) {
        _tickPosition = checkNotNull(tickPosition, "tickPosition is null");
    }

    @JsonIgnore
    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = checkNotNull(title, "titleText is null");
    }

    @JsonIgnore
    public BackgroundColor getBackgroundColor() {
        return _backgroundColor;
    }

    public void setBackgroundColor(BackgroundColor backgroundColor) {
        _backgroundColor = checkNotNull(backgroundColor, "backgroundColor is null");
    }

    @JsonIgnore
    public String getText() {
        return _text;
    }

    public void setText(String text) {
        _text = checkNotNull(text, "text is null");
    }

    @JsonIgnore
    public String getFontSize() {
        return _fontSize;
    }

    public void setFontSize(String fontSize) {
        _fontSize = checkNotNull(fontSize, "fontSize is null");
    }

    @JsonIgnore
    public boolean isTitleVisible() {
        return _titleVisible;
    }

    public void setTitleVisible(boolean titleVisible) {
        _titleVisible = titleVisible;
    }

    @JsonIgnore
    public Alignment getTitleAlignment() {
        return _titleAlignment;
    }

    public void setTitleAlignment(Alignment titleAlignment) {
        _titleAlignment = checkNotNull(titleAlignment, "titleAlignment is null");
    }

    @JsonIgnore
    public Edge getTickEdge() {
        return _tickEdge;
    }

    public void setTickEdge(Edge tickEdge) {
        _tickEdge = checkNotNull(tickEdge, "tickEdge is null");
    }

    @JsonIgnore
    public Alignment getTextAlignment() {
        return _textAlignment;
    }

    public void setTextAlignment(Alignment textAlignment) {
        _textAlignment = checkNotNull(textAlignment, "textAlignment is null");
    }

    @JsonIgnore
    public boolean isTickVisible() {
        return _tickVisible;
    }

    public void setTickVisible(boolean tickVisible) {
        _tickVisible = tickVisible;
    }

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

