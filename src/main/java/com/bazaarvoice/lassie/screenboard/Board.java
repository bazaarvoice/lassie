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
package com.bazaarvoice.lassie.screenboard;

import com.bazaarvoice.lassie.screenboard.widgets.Widget;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.Collection;

import static com.google.common.base.Preconditions.checkNotNull;

/** should only be used when creating a new Screenboard */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Board {
    @JsonProperty("board_title")
    private String _title = "Title";
    @JsonProperty("widgets")
    private final Collection<Widget> _widgets = new ArrayList<Widget>();

    public Board(String title, Collection<Widget> widgets) {
        this(title);

        checkNotNull(widgets, "widgets are null");
        _widgets.addAll(widgets);
    }

    public Board(String title) {
        _title = checkNotNull(title, "Board title is null");
    }

    private Board() {
    }

    @JsonIgnore
    public Collection<Widget> getWidgets() {
        return _widgets;
    }

    @JsonIgnore
    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = checkNotNull(title, "title is null");
    }

    public String toString() {
        return "Board[" +
                "title=" + getTitle() +
                ", widgets=[" + Joiner.on(',').join(getWidgets()) +
                "]]";
    }
}
