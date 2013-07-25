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

public class EventTimelineTest {
    private ObjectMapper _json;

    @Before
    public void before() {
        _json = new ObjectMapper();
    }

    @Test
    public void eventTimelineSerialization() throws Exception {
        EventTimeline testEventTimeline = new EventTimeline();
        assertEquals("{\"type\":\"event_timeline\",\"height\":9,\"width\":91,\"x\":0,\"y\":0,\"title_size\":16,\"title\":false,\"title_align\":\"left\",\"title_text\":\"Title\",\"query\":\"status:error\",\"timeframe\":\"1w\"}",
                _json.writeValueAsString(testEventTimeline));
    }

    @Test
    public void eventTimelineDeserialization() throws IOException {
        EventTimeline testEventTimeline = (EventTimeline) _json.readValue("{\"type\":\"event_timeline\",\"height\":100,\"width\":600,\"x\":5001,\"y\":500,\"title_size\":17,\"title\":true,\"title_align\":\"right\",\"title_text\":\"NotTitle\",\"query\":\"query1\",\"timeframe\":\"1d\"}", Widget.class);
        assertEquals(100, testEventTimeline.getDimensions().getHeight());
        assertEquals(600, testEventTimeline.getDimensions().getWidth());
        assertEquals(5001, testEventTimeline.getLocation().getX());
        assertEquals(500, testEventTimeline.getLocation().getY());
        assertEquals(17, testEventTimeline.getTitleSize());
        assertEquals(true, testEventTimeline.isTitleVisible());
        assertEquals(Widget.Alignment.RIGHT, testEventTimeline.getTitleAlignment());
        assertEquals("NotTitle", testEventTimeline.getTitle());
        assertEquals("query1", testEventTimeline.getQuery());
        assertEquals(Timeframe.ONE_DAY, testEventTimeline.getTimeframe());
    }
}
