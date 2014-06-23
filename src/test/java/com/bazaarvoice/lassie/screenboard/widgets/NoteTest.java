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

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** The NoteTest is designed to test the serialization / deserialization of the {@link Note} class. */
public class NoteTest {
    private ObjectMapper _json;

    @Before
    public void before() {
        _json = new ObjectMapper();
    }

    @Test
    public void noteSerialization() throws Exception {
        Note testNote = new Note();

        assertEquals("{\"type\":\"note\",\"height\":15,\"width\":30,\"x\":0,\"y\":0,\"title_size\":16,\"title\":true,\"tick_pos\":\"50%\",\"title_align\":\"left\",\"tick_edge\":\"right\",\"text_align\":\"left\",\"title_text\":\"Title\",\"bgcolor\":\"yellow\",\"html\":\"body\",\"font_size\":\"14\",\"tick\":true,\"auto_refresh\":false}",
                _json.writeValueAsString(testNote));
    }

    @Test
    public void noteDeserialization() throws Exception {
        Note testNote = (Note) _json.readValue("{\"type\":\"note\",\"height\":16,\"width\":31,\"x\":1,\"y\":1,\"title_size\":17,\"title\":false,\"tick_pos\":\"50%\",\"title_align\":\"right\",\"tick_edge\":\"left\",\"text_align\":\"right\",\"title_text\":\"NotTitle\",\"bgcolor\":\"pink\",\"html\":\"NotBody\",\"font_size\":\"14\",\"tick\":true}", Widget.class);
        assertEquals(16, testNote.getDimensions().getHeight());
        assertEquals(31, testNote.getDimensions().getWidth());
        assertEquals(1, testNote.getLocation().getX());
        assertEquals(1, testNote.getLocation().getY());
        assertEquals(17, testNote.getTitleSize());
        assertEquals(false, testNote.isTitleVisible());
        assertEquals(Widget.Alignment.RIGHT, testNote.getTitleAlignment());
        assertEquals(Note.Edge.LEFT, testNote.getTickEdge());
        assertEquals(Widget.Alignment.RIGHT, testNote.getTextAlignment());
        assertEquals("NotTitle", testNote.getTitle());
        assertEquals(Note.BackgroundColor.PINK, testNote.getBackgroundColor());
        assertEquals("NotBody", testNote.getText());
    }
}
