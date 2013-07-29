package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

/**
 * The EventTimelineTest is designed to test the serialization / deserialization of the EventTimeline class.
 * {@link EventTimeline}
 */
public class EventTimelineTest {
    private ObjectMapper _json;

    @Before
    public void before() {
        _json = new ObjectMapper();
    }

    /** @throws Exception  */
    @Test
    public void eventTimelineSerialization() throws Exception {
        EventTimeline testEventTimeline = new EventTimeline();
        assertEquals("{\"type\":\"event_timeline\",\"height\":9,\"width\":91,\"x\":0,\"y\":0,\"title_size\":16,\"title\":false,\"title_align\":\"left\",\"title_text\":\"Title\",\"query\":\"status:error\",\"timeframe\":\"1w\"}",
                _json.writeValueAsString(testEventTimeline));
    }

    /** @throws IOException  */
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
