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

/** The EventStreamTest is designed to test the serialization / deserialization of the {@link EventStream} class. */
public class EventStreamTest {
    private ObjectMapper _json;

    @Before
    public void before() {
        _json = new ObjectMapper();
    }

    @Test
    public void eventStreamSerialization() throws Exception {
        EventStream testEventStream = new EventStream();
        assertEquals("{\"type\":\"event_stream\",\"height\":57,\"width\":59,\"x\":0,\"y\":0,\"title_size\":16,\"title\":false,\"title_align\":\"left\",\"title_text\":\"Title\",\"query\":\"*\",\"timeframe\":\"1w\"}",
                _json.writeValueAsString(testEventStream));
    }

    @Test
    public void eventStreamDeserialization() throws IOException {
        EventStream testEventStream = (EventStream) _json.readValue("{\"type\":\"event_stream\",\"height\":100,\"width\":600,\"x\":5001,\"y\":500,\"title_size\":17,\"title\":true,\"title_align\":\"right\",\"title_text\":\"NotTitle\",\"query\":\"query1\",\"timeframe\":\"1d\"}", Widget.class);
        assertEquals(100, testEventStream.getDimensions().getHeight());
        assertEquals(600, testEventStream.getDimensions().getWidth());
        assertEquals(5001, testEventStream.getLocation().getX());
        assertEquals(500, testEventStream.getLocation().getY());
        assertEquals(17, testEventStream.getTitleSize());
        assertEquals(true, testEventStream.isTitleVisible());
        assertEquals(Widget.Alignment.RIGHT, testEventStream.getTitleAlignment());
        assertEquals("NotTitle", testEventStream.getTitle());
        assertEquals("query1", testEventStream.getQuery());
        assertEquals(Timeframe.ONE_DAY, testEventStream.getTimeframe());
    }
}
