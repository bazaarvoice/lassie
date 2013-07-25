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

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ImageTest {
    private ObjectMapper _json;

    @Before
    public void before() {
        _json = new ObjectMapper();
    }

    @Test
    public void imageSerialization() throws Exception {
        Image testImage = new Image();
        assertEquals("{\"type\":\"image\",\"height\":20,\"width\":32,\"x\":0,\"y\":0,\"url\":\"\"}", _json.writeValueAsString(testImage));
    }

    @Test
    public void imageDeserialization() throws IOException {
        Image testImage = (Image) _json.readValue("{\"type\":\"image\",\"height\":21,\"width\":33,\"x\":1,\"y\":1,\"url\":\"NotUrl\"}", Widget.class);
        assertEquals(21, testImage.getDimensions().getHeight());
        assertEquals(33, testImage.getDimensions().getWidth());
        assertEquals(1, testImage.getLocation().getX());
        assertEquals(1, testImage.getLocation().getY());
        assertEquals("NotUrl", testImage.getUrl());
    }
}
