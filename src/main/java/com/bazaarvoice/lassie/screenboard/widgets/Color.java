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

public enum Color {
    RED_ON_WHITE("red_on_white"), YELLOW_ON_WHITE("yellow_on_white"), GREEN_ON_WHITE("green_on_white"), WHITE_ON_RED("white_on_red"), WHITE_ON_YELLOW("white_on_yellow"), WHITE_ON_GREEN("white_on_green");

    private final String _name;

    private Color(String name) {
        _name = name;
    }

    @JsonValue
    public String getName() {
        return _name;
    }

    @JsonCreator
    public static Color fromName(String name) {
        checkNotNull(name);
        return Color.valueOf(name.toUpperCase());
    }
}
