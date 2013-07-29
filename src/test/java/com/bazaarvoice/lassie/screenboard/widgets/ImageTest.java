package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class ImageTest {
    private ObjectMapper _json;

    /**
     *
     */
    @Before
    public void before() {
        _json = new ObjectMapper();
    }

    /** @throws Exception  */
    @Test
    public void imageSerialization() throws Exception {
        Image testImage = new Image();
        assertEquals("{\"type\":\"image\",\"height\":20,\"width\":32,\"x\":0,\"y\":0,\"url\":\"\"}", _json.writeValueAsString(testImage));
    }

    /** @throws IOException  */
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
