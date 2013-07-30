package com.bazaarvoice.lassie.screenboard;

import com.bazaarvoice.lassie.screenboard.widgets.Widget;
import com.google.common.base.Joiner;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;

import static com.bazaarvoice.lassie.screenboard.Preconditions.checkNotNull;

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
                ", widgets=[" + Joiner.on(',').join(getWidgets()) +
                "]]";
    }
}
