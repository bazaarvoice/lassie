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

public class Dimensions {
    @JsonProperty("height")
    private final int _height;
    @JsonProperty("width")
    private final int _width;

    public Dimensions(int width, int height) {
        checkArgument(height > 0, "height is less then one");
        checkArgument(width > 0, "width is less then one");

        _height = height;
        _width = width;
    }

    private Dimensions() {
        _width = 50;
        _height = 50;
    }

    @JsonIgnore
    public int getHeight() {
        return _height;
    }

    @JsonIgnore
    public int getWidth() {
        return _width;
    }

    @JsonIgnore
    public String toString() {
        return "Dimensions[width=" + _width + ", height=" + _height + "]";
    }
}
