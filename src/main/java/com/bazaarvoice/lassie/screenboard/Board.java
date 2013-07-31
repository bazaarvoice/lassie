package com.bazaarvoice.lassie.screenboard;

import com.bazaarvoice.lassie.screenboard.widgets.Widget;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.ArrayList;
import java.util.Collection;

import static com.google.common.base.Preconditions.checkNotNull;

/** should only be used when creating a new Screenboard */
@JsonSerialize(
        include=JsonSerialize.Inclusion.NON_NULL
)
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

    /** Private constructor used for deserialization. */
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
                ", widgets=" + getWidgets() +
                "]";
    }
}
