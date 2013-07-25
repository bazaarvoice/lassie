package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

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
