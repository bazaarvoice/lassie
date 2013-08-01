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
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonUnwrapped;
import org.codehaus.jackson.annotate.JsonValue;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * abstract for all widgets. Each widget will have a dimensions class which
 * determines it's placement and size as well as a get type which will report back it's type.
 * the enum is for widgets that use text alignment
 * there is no set type method because the type of the widget should never be changed
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Timeseries.class, name = "timeseries"),
        @JsonSubTypes.Type(value = EventStream.class, name = "event_stream"),
        @JsonSubTypes.Type(value = Image.class, name = "image"),
        @JsonSubTypes.Type(value = Note.class, name = "note"),
        @JsonSubTypes.Type(value = QueryValue.class, name = "query_value"),
        @JsonSubTypes.Type(value = Timeseries.class, name = "timeseries"),
        @JsonSubTypes.Type(value = EventTimeline.class, name = "event_timeline"),
        @JsonSubTypes.Type(value = Text.class, name = "free_text")
})
public abstract class Widget {
    public enum Alignment {
        LEFT("left"), CENTER("center"), RIGHT("right");

        private final String _name;

        private Alignment(String name) {
            _name = name;
        }

        @JsonValue
        public String getName() {
            return _name;
        }

        @JsonCreator
        public static Alignment fromName(String name) {
            checkNotNull(name);
            return Alignment.valueOf(name.toUpperCase());
        }
    }

    @JsonUnwrapped
    @JsonProperty
    private Dimensions _dimensions;

    @JsonUnwrapped
    @JsonProperty
    private Location _location;

    @JsonIgnore
    public Widget(Location location, Dimensions dimension) {
        _dimensions = checkNotNull(dimension);
        _location = checkNotNull(location);
    }

    @JsonIgnore
    public Dimensions getDimensions() {
        return _dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        _dimensions = checkNotNull(dimensions);
    }

    @JsonIgnore
    public Location getLocation() {
        return _location;
    }

    public void setLocation(Location location) {
        _location = checkNotNull(location);
    }
}
