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

import static org.junit.Assert.assertEquals;

/** The TextTest is designed to test the serialization / deserialization of the {@link Text} class. */
public class TextTest {
    private ObjectMapper _json;

    @Before
    public void before() {
        _json = new ObjectMapper();
    }

    @Test
    public void textSerialization() throws Exception {
        Text testText = new Text();
        assertEquals("{\"type\":\"free_text\",\"height\":10,\"width\":30,\"x\":0,\"y\":0,\"title_size\":16,\"title\":true,\"color\":\"#4d4d4d\",\"text\":\"body\",\"title_align\":\"left\",\"text_align\":\"left\",\"title_text\":\"Title\",\"font_size\":\"auto\"}",
                _json.writeValueAsString(testText));
    }

    @Test
    public void textDeserialization() throws Exception {
        Text testText = (Text) _json.readValue("{\"type\":\"free_text\",\"height\":11,\"width\":31,\"x\":1,\"y\":1,\"title_size\":17,\"title\":false,\"color\":\"#4d4d4e\",\"text\":\"NotBody\",\"title_align\":\"right\",\"text_align\":\"right\",\"title_text\":\"NotTitle\",\"font_size\":\"auto\"}", Widget.class);
        assertEquals(11, testText.getDimensions().getHeight());
        assertEquals(31, testText.getDimensions().getWidth());
        assertEquals(1, testText.getLocation().getX());
        assertEquals(1, testText.getLocation().getY());
        assertEquals(17, testText.getTitleSize());
        assertEquals(false, testText.isTitleVisible());
        assertEquals(Widget.Alignment.RIGHT, testText.getTitleAlignment());
        assertEquals(Widget.Alignment.RIGHT, testText.getTextAlignment());
        assertEquals("NotTitle", testText.getTitle());
        assertEquals("#4d4d4e", testText.getColor());
        assertEquals("NotBody", testText.getText());
        assertEquals("auto", testText.getFontSize());
    }
}
