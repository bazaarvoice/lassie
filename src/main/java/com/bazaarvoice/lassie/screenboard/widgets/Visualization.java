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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static com.google.common.base.Preconditions.checkNotNull;

/** Visualization enum which is used mainly in the QueryValue class */
public enum Visualization {
    TIMESERIES("timeseries");

    private final String _name;

    /**
     * Constructor that sets the name of the enum as it is documented in the datadog API.
     *
     * @param name The name of the Visualization enum.
     */
    private Visualization(String name) {
        _name = name;
    }

    /**
     * The getter for the name of the enum as it is documented in the datadog API.
     *
     * @return The name of the Visualization.
     */
    @JsonValue
    public String getName() {
        return _name;
    }

    /**
     * The name of the expected enum as it is documented in the datadog API.
     *
     * @param name The name of the expected enum.
     * @return The Visualization enum.
     */
    @JsonCreator
    public static Visualization fromName(String name) {
        checkNotNull(name);
        return Visualization.valueOf(name.toUpperCase());
    }
}
