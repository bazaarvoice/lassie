package com.bazaarvoice.lassie.screenboard;

import com.bazaarvoice.lassie.screenboard.widgets.Aggregator;
import com.bazaarvoice.lassie.screenboard.widgets.Image;
import com.bazaarvoice.lassie.screenboard.widgets.Note;
import com.bazaarvoice.lassie.screenboard.widgets.Query;
import com.bazaarvoice.lassie.screenboard.widgets.QueryValue;
import com.bazaarvoice.lassie.screenboard.widgets.Request;
import com.bazaarvoice.lassie.screenboard.widgets.Timeseries;
import com.bazaarvoice.lassie.screenboard.widgets.Widget;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BoardTest {
    private Board _testBoard;
    private ObjectMapper _json;

    @Before
    public void before() {
        _testBoard = new Board("testBoard");
        _json = new ObjectMapper();
    }

    @Test
    public void singleWidgetSerialization() throws Exception {
        Image testImage = new Image();
        _testBoard.getWidgets().add(testImage);

        assertEquals("{\"board_title\":\"testBoard\",\"widgets\":[{\"type\":\"image\",\"height\":20,\"width\":32,\"x\":0,\"y\":0,\"url\":\"\"}]}",
                _json.writeValueAsString(_testBoard));
    }

    @Test
    public void multipleWidgetsSerialization() throws Exception {
        _testBoard.getWidgets().add(new Image());

        Timeseries testTimeseries = new Timeseries();
        testTimeseries.getTileDefinition().getEvents().add(new Query(""));
        testTimeseries.getTileDefinition().getRequests().add(Request.create(Aggregator.AVERAGE, "test.user.data"));

        _testBoard.getWidgets().add(testTimeseries);
        _testBoard.getWidgets().add(new Note());

        assertEquals("{\"board_title\":\"testBoard\"," +
                "\"widgets\":[" +
                "{\"type\":\"image\",\"height\":20,\"width\":32,\"x\":0,\"y\":0,\"url\":\"\"}," +
                "{\"type\":\"timeseries\",\"height\":13,\"width\":47,\"x\":0,\"y\":0,\"title_size\":16,\"title\":true,\"title_align\":\"left\",\"title_text\":\"Title\",\"timeframe\":\"1w\",\"tile_def\":{\"viz\":\"timeseries\",\"requests\":[{\"stacked\":false,\"q\":\"avg:test.user.data{*}\"}],\"events\":[{\"q\":\"\"}]}}," +
                "{\"type\":\"note\",\"height\":15,\"width\":30,\"x\":0,\"y\":0,\"title_size\":16,\"title\":true,\"tick_pos\":\"50%\",\"title_align\":\"left\",\"tick_edge\":\"right\",\"text_align\":\"left\",\"title_text\":\"Title\",\"bgcolor\":\"yellow\",\"html\":\"body\",\"font_size\":\"14\",\"tick\":true}" +
                "]}", _json.writeValueAsString(_testBoard));
    }

    @Test
    public void singleWidgetDeserialization() throws Exception {
        Board testBoard = _json.readValue("{\"board_title\":\"testBoard\",\"widgets\":[{\"type\":\"image\",\"height\":20,\"width\":32,\"x\":0,\"y\":0,\"url\":\"\"}]}", Board.class);
        ArrayList<Widget> testWidgets = new ArrayList<Widget>(testBoard.getWidgets());
        assertEquals(testWidgets.get(0).getClass(), Image.class);
        Image testImage = (Image) testWidgets.get(0);
        assertEquals(testBoard.getTitle(), "testBoard");
        assertEquals(testImage.getUrl(), "");
    }

    @Test
    public void multipleWidgetDeserialization() throws Exception {
        Board testBoard = _json.readValue("{\"board_title\":\"testBoard\"," +
                "\"widgets\":[" +
                "{\"type\":\"image\",\"height\":20,\"width\":32,\"x\":0,\"y\":0,\"url\":\"\"}," +
                "{\"type\":\"timeseries\",\"height\":13,\"width\":47,\"x\":0,\"y\":0,\"title_size\":16,\"title\":true,\"title_align\":\"left\",\"title_text\":\"Title\",\"timeframe\":\"1w\",\"tile_def\":{\"viz\":\"timeseries\",\"requests\":[{\"stacked\":false,\"q\":\"avg:test.user.data{*}\"}],\"events\":[{\"q\":\"\"}]}}," +
                "{\"type\":\"note\",\"height\":15,\"width\":30,\"x\":0,\"y\":0,\"title_size\":16,\"title\":true,\"tick_pos\":\"50%\",\"title_align\":\"left\",\"tick_edge\":\"right\",\"text_align\":\"left\",\"title_text\":\"Title\",\"bgcolor\":\"yellow\",\"html\":\"body\",\"font_size\":\"14\",\"tick\":true}" +
                "]}", Board.class);
        ArrayList<Widget> testWidgets = new ArrayList<Widget>(testBoard.getWidgets());
        assertEquals(testWidgets.get(0).getClass(), Image.class);
        assertEquals(testWidgets.get(1).getClass(), Timeseries.class);
        assertEquals(testWidgets.get(2).getClass(), Note.class);
    }

    @Test
    public void exampleBoardDeserialization() throws Exception {
        ScreenboardResponse testScreenboard = _json.readValue(Resources.toString(Resources.getResource(ScreenboardResponse.class, "dataDogResponseExample.json"), Charsets.UTF_8), ScreenboardResponse.class);

        assertEquals(123, testScreenboard.getId());

        Board testBoard = testScreenboard.getBoard();
        ArrayList<Widget> testWidgets = new ArrayList<Widget>(testBoard.getWidgets());

        assertEquals(4, testWidgets.size());
        assertEquals(Timeseries.class, testWidgets.get(0).getClass());
        assertEquals(QueryValue.class, testWidgets.get(1).getClass());
        assertEquals(QueryValue.class, testWidgets.get(2).getClass());
        assertEquals(QueryValue.class, testWidgets.get(3).getClass());
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ScreenboardResponse {
        @JsonProperty("id")
        private int _id;
        @JsonProperty("board")
        private Board _board;

        private Board getBoard() {
            return _board;
        }

        private void setBoard(Board board) {
            _board = board;
        }

        private int getId() {
            return _id;
        }

        private void setId(int id) {
            _id = id;
        }
    }
}
