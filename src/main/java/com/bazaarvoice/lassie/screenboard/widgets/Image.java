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

import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkNotNull;

public class Image extends Widget {
    @JsonProperty("url")
    private String _url;

    public Image(Location location, Dimensions dimensions, String url) {
        super(location, dimensions);
        _url = checkNotNull(url, "url is null");
    }

    public Image(int x, int y, int width, int height, String url) {
        this(new Location(x, y), new Dimensions(width, height), url);
    }

    public Image() {
        this(0, 0, 32, 20, "");
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String url) {
        _url = checkNotNull(url, "url is null");
    }
}
