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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

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

    public Text(Location location, Dimensions dimension) {
        super(location, dimension);
    }

    public Text(int x, int y, int width, int height) {
        this(new Location(x, y), new Dimensions(width, height));
    }

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

    @JsonIgnore
    public String getColor() {
        return _color;
    }

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
