package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class QueryValueTest {
    private ObjectMapper _json;

    @Before
    public void before() {
        _json = new ObjectMapper();
    }

    @Test
    public void queryValueSerialization() throws Exception {
        QueryValue bla = new QueryValue();
        ConditionalFormat conFormatTest = new ConditionalFormat(Color.WHITE_ON_GREEN, false, Comparator.GREATER, 0);
        bla.getConditionalFormats().add(conFormatTest);
        assertEquals("{\"type\":\"query_value\",\"height\":4,\"width\":14,\"x\":0,\"y\":0,\"title_size\":16,\"title\":true,\"aggregator\":\"avg\",\"conditional_formats\":[" +
                "{\"color\":\"white_on_green\",\"invert\":false,\"comparator\":\">\",\"value\":0.0}],\"title_align\":\"left\",\"text_align\":\"left\",\"title_text\":\"Title\",\"precision\":1,\"query\":" +
                "\"default.user.data\",\"timeframe\":\"1w\",\"text_size\":\"auto\",\"unit\":\"auto\"}", _json.writeValueAsString(bla));
    }

    @Test
    public void definedQueryValueSerialization() throws Exception {
        QueryValue bla = new QueryValue(0, 0, 14, 4);
        bla.setQuery(Aggregator.AVERAGE, "default.user.data", "over");
        bla.addThresholdFormatting(0);
        assertEquals("{\"type\":\"query_value\",\"height\":4,\"width\":14,\"x\":0,\"y\":0,\"title_size\":16,\"title\":true,\"aggregator\":\"avg\",\"conditional_formats\":[" +
                "{\"color\":\"white_on_green\",\"invert\":false,\"comparator\":\">\",\"value\":0.0}," +
                "{\"color\":\"white_on_yellow\",\"invert\":false,\"comparator\":\"<=\",\"value\":0.0}," +
                "{\"color\":\"white_on_red\",\"invert\":false,\"comparator\":\"<\",\"value\":0.0}],\"title_align\":\"left\",\"text_align\":\"left\",\"title_text\":\"Title\",\"precision\":1,\"query\":" +
                "\"avg:default.user.data{over}\",\"timeframe\":\"1w\",\"text_size\":\"auto\",\"unit\":\"auto\"}", _json.writeValueAsString(bla));
    }

    @Test
    public void queryValueDeserialization() throws Exception {
        QueryValue testQueryValue = (QueryValue) _json.readValue(
                "{\"type\":\"query_value\",\"height\":5,\"width\":15,\"x\":1,\"y\":1,\"title_size\":17,\"title\":false,\"aggregator\":\"max\",\"conditional_formats\":[{" +
                        "\"value\":\"1\",\"color\":\"white_on_red\",\"comparator\":\"<\",\"invert\":true}],\"title_align\":\"right\",\"text_align\":\"right\",\"title_text\":\"NoTitle\",\"precision\":2,\"query\":" +
                        "\"query\",\"timeframe\":\"1d\",\"text_size\":\"10\",\"unit\":\"m/s\"}", Widget.class);

        assertEquals(5, testQueryValue.getDimensions().getHeight());
        assertEquals(15, testQueryValue.getDimensions().getWidth());
        assertEquals(1, testQueryValue.getLocation().getX());
        assertEquals(1, testQueryValue.getLocation().getY());
        assertEquals(17, testQueryValue.getTitleSize());
        assertEquals(false, testQueryValue.isTitleVisible());
        assertEquals(Aggregator.MAX, testQueryValue.getAggregator());

        List<ConditionalFormat> conFormats = testQueryValue.getConditionalFormats();
        ConditionalFormat testConFormat = conFormats.get(0);

        assertEquals(Color.WHITE_ON_RED, testConFormat.getColor());
        assertEquals(Comparator.LESS, testConFormat.getComparator());
        assertEquals(true, testConFormat.isInverted());

        assertEquals(Widget.Alignment.RIGHT, testQueryValue.getTitleAlignment());
        assertEquals(Widget.Alignment.RIGHT, testQueryValue.getTextAlignment());
        assertEquals("NoTitle", testQueryValue.getTitle());
        assertEquals(2, testQueryValue.getPrecision());
        assertEquals("query", testQueryValue.getQuery());
        assertEquals(Timeframe.ONE_DAY, testQueryValue.getTimeframe());
        assertEquals("10", testQueryValue.getTextSize());
        assertEquals("m/s", testQueryValue.getUnit());
    }
}
