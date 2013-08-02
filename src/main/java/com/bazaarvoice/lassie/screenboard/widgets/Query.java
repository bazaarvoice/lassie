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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.google.common.base.Preconditions.checkNotNull;

/** The Query class mainly serves as a string wrapper in the {@link TileDefinition} class. */
public class Query {
    @JsonProperty("q")
    final String _value;

    public Query(String value) {
        _value = checkNotNull(value, "query is null");
    }

    /** Private constructor used for deserialization. */
    private Query() {
        this("default.user.data{*}");
    }

    @JsonIgnore
    public String getValue() {
        return _value;
    }
}
