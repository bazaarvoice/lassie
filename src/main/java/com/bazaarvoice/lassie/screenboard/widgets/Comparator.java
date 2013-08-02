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

/** The Comparator is used mainly in the {@link ConditionalFormat} class. It's used to compare the aggregated value to the threshold value. */
public enum Comparator {
    GREATER(">"), GREATER_EQUALS(">="), LESS("<"), LESS_EQUALS("<=");
    private final String _name;

    /**
     * Constructor to set the name for the Comparator as it is documented in the datadog API.
     *
     * @param name The name of the Comparator.
     */
    private Comparator(String name) {
        _name = name;
    }

    /**
     * Getter for the name of the Comparator. In this case it will return the symbol as it is documented in the datadog API.
     *
     * @return The name of the Comparator
     */
    @JsonValue
    public String getName() {
        return _name;
    }

    /**
     * Getter for the Comparator by its symbolic name as it is documented in the datadog API.
     *
     * @param name The Symbolic name of the Comparator
     * @return The expected Comparator
     */
    @JsonCreator
    public static Comparator fromName(String name) {
        checkNotNull(name);
        if (">".equals(name)) {
            return GREATER;
        } else if (">=".equals(name)) {
            return GREATER_EQUALS;
        } else if ("<".equals(name)) {
            return LESS;
        } else if ("<=".equals(name)) {
            return LESS_EQUALS;
        }
        throw new IllegalArgumentException("Invalid comparator name: " + name);
    }
}
