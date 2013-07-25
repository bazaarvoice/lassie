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

public enum Comparator {
    GREATER(">"), GREATER_EQUALS(">="), LESS("<"), LESS_EQUALS("<=");

    private final String _name;

    private Comparator(String name) {
        _name = name;
    }

    @JsonValue
    public String getName() {
        return _name;
    }

    @JsonCreator
    public static Comparator fromName(String name) {
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
