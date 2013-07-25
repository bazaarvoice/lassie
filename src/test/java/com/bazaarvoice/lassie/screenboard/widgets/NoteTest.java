package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class NoteTest {
    private ObjectMapper _json;

    /**
     *
     */
    @Before
    public void before() {
        _json = new ObjectMapper();
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void noteSerialization() throws Exception {
        Note testNote = new Note();

        assertEquals("{\"type\":\"note\",\"height\":15,\"width\":30,\"x\":0,\"y\":0,\"title_size\":16,\"title\":true,\"tick_pos\":\"50%\",\"title_align\":\"left\",\"tick_edge\":\"right\",\"text_align\":\"left\",\"title_text\":\"Title\",\"bgcolor\":\"yellow\",\"html\":\"body\",\"font_size\":\"14\",\"tick\":true}",
                _json.writeValueAsString(testNote));
    }

    /**
     *
     * @throws Exception
     */
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
