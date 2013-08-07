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
package com.bazaarvoice.lassie.screenboard;

import java.util.List;

/** Exception thrown when a screenboard does not exist. */
public class ScreenboardNotFoundException extends DataDogScreenboardException {
    public ScreenboardNotFoundException() {
    }

    public ScreenboardNotFoundException(String message) {
        super(message);
    }

    public ScreenboardNotFoundException(List<String> errors) {
        super(errors);
    }

    public ScreenboardNotFoundException(List<String> errors, Exception cause) {
        super(errors, cause);
    }
}
