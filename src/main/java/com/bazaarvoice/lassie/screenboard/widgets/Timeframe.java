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

public enum Timeframe {
    FIVE_MINUTES("5m"), TEN_MINUTES("10m"), ONE_HOUR("1h"), FOUR_HOURS("4h"), ONE_DAY("1d"), TWO_DAYS("2d"), ONE_WEEK("1w");

    private final String _name;

    private Timeframe(String name) {
        _name = name;
    }

    @JsonValue
    public String getName() {
        return _name;
    }

    @JsonCreator
    public static Timeframe fromName(String name) {
        checkNotNull(name);
        if (name.equals("5m")) {
            return FIVE_MINUTES;
        } else if (name.equals("10m")) {
            return TEN_MINUTES;
        } else if (name.equals("1h")) {
            return ONE_HOUR;
        } else if (name.equals("4h")) {
            return FOUR_HOURS;
        } else if (name.equals("1d")) {
            return ONE_DAY;
        } else if (name.equals("2d")) {
            return TWO_DAYS;
        } else if (name.equals("1w")) {
            return ONE_WEEK;
        }
        throw new IllegalArgumentException("Invalid comparator name: " + name);
    }
}
