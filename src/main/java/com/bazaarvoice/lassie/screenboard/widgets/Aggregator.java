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
import org.codehaus.jackson.annotate.JsonValue;

import static com.google.common.base.Preconditions.checkNotNull;

/** Defines how the results of a query will be aggregated. */
public enum Aggregator {
    AVERAGE("avg"), MAX("max"), MIN("min"), SUM("sum");

    private final String _name;

    /**
     * Constructor for the Aggregator.
     *
     * @param name The name of the aggregator documented in the datadog API.
     */
    private Aggregator(String name) {
        _name = name;
    }

    /**
     * Gets the name of the aggregator documented in the datadog API, which is not necessarily the same as the {@link Aggregator}.
     *
     * @return The true name of the aggregator.
     */
    @JsonValue
    public String getName() {
        return _name;
    }

    /**
     * Parse an aggregator name into the corresponding {@link Aggregator} value.
     *
     * @param name The aggregator's name.
     * @return The aggregator matching the name.
     */
    @JsonCreator
    public static Aggregator fromName(String name) {
        checkNotNull(name);
        if (name.equals("avg")) {
            return AVERAGE;
        }
        return Aggregator.valueOf(name.toUpperCase());
    }
}
