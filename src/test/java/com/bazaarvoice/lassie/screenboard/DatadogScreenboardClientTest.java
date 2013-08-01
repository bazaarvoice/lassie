package com.bazaarvoice.lassie.screenboard;

import com.xebialabs.restito.server.StubServer;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.semantics.Action.contentType;
import static com.xebialabs.restito.semantics.Action.status;
import static com.xebialabs.restito.semantics.Action.stringContent;
import static com.xebialabs.restito.semantics.Condition.delete;
import static com.xebialabs.restito.semantics.Condition.get;
import static com.xebialabs.restito.semantics.Condition.parameter;
import static com.xebialabs.restito.semantics.Condition.post;
import static com.xebialabs.restito.semantics.Condition.put;
import static com.xebialabs.restito.semantics.Condition.withPostBodyContaining;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/** The DatadogScreenboardClientTest mainly tests the url path generation and serialization of boards through the {@link DataDogScreenboardClient}. */
public class DatadogScreenboardClientTest {
    private ObjectMapper _json;
    private StubServer _stubServer;

    private static final String APPLICATION_KEY = "APPLICATION_KEY";
    private static final String API_KEY = "API_KEY";

    private DataDogScreenboardClient _testScreenboardClient;
    private Board _testBoard;

    @Before
    public void before() {
        _json = new ObjectMapper();
        _stubServer = new StubServer().run();
        _testBoard = new Board("Title");
        _testScreenboardClient = new DataDogScreenboardClient(APPLICATION_KEY, API_KEY);
        _testScreenboardClient.setDatadogApiUrl(URI.create("http://localhost:" + _stubServer.getPort()));
    }

    @After
    public void after() {
        _stubServer.stop();
    }

    @Test
    public void createTest() throws Exception {
        int id = 15;
        whenHttp(_stubServer)
                .match(
                        post("?api_key=" + API_KEY + "&application_key=" + APPLICATION_KEY),
                        withPostBodyContaining(_json.writeValueAsString(_testBoard)))
                .then(
                        contentType("application/json"),
                        status(HttpStatus.OK_200),
                        stringContent("{\"id\":" + id + ", \"board\":" + _json.writeValueAsString(_testBoard) + "}"));
        assertEquals(id, _testScreenboardClient.create(_testBoard));
    }

    @Test
    public void getTest() throws Exception {
        int id = 527;
        whenHttp(_stubServer)
                .match(
                        get("/" + id),
                        parameter("api_key", API_KEY),
                        parameter("application_key", APPLICATION_KEY))
                .then(
                        contentType("application/json"),
                        status(HttpStatus.OK_200),
                        stringContent("{\"id\":" + id + ", \"board\":" + _json.writeValueAsString(_testBoard) + "}"));
        assertEquals(_json.writeValueAsString(_testBoard), _json.writeValueAsString(_testScreenboardClient.get(id)));
    }

    @Test
    public void getPublicURLTest() throws Exception {
        int id = 527;
        whenHttp(_stubServer)
                .match(
                        get("/share/" + id),
                        parameter("api_key", API_KEY),
                        parameter("application_key", APPLICATION_KEY))
                .then(
                        contentType("application/json"),
                        status(HttpStatus.OK_200),
                        stringContent("{\"id\":" + id + ",  \"public_url\": \"https://p.datadoghq.com/sb/TEST\"}"));
        assertEquals("https://p.datadoghq.com/sb/TEST", _testScreenboardClient.getPublicUrl(id));
    }

    @Test
    public void updateTest() throws Exception {
        int id = 15;
        whenHttp(_stubServer)
                .match(
                        put("/" + id),
                        parameter("api_key", API_KEY),
                        parameter("application_key", APPLICATION_KEY),
                        withPostBodyContaining(_json.writeValueAsString(_testBoard)))
                .then(
                        contentType("application/json"),
                        status(HttpStatus.OK_200),
                        stringContent("{\"id\":" + id + ", \"board\":" + _json.writeValueAsString(_testBoard) + "}"));
        _testScreenboardClient.update(id, _testBoard);
    }

    @Test
    public void deleteTest() throws Exception {
        int id = 225;
        whenHttp(_stubServer)
                .match(
                        delete("/" + id),
                        parameter("api_key", API_KEY),
                        parameter("application_key", APPLICATION_KEY))
                .then(
                        contentType("application/json"),
                        status(HttpStatus.OK_200),
                        stringContent("{\"id\":" + id + ", \"board\":" + _json.writeValueAsString(_testBoard) + "}"));
        _testScreenboardClient.delete(id);
    }

    @Test
    public void errorTest() throws Exception {
        int id = 225;
        whenHttp(_stubServer)
                .match(
                        delete("/" + id),
                        parameter("api_key", API_KEY),
                        parameter("application_key", APPLICATION_KEY))
                .then(
                        contentType("application/json"),
                        status(HttpStatus.NOT_FOUND_404),
                        stringContent("{\"errors\": [\"Unable to find Screenboard for id 0\"]}"));
        _testScreenboardClient.delete(id);
    }
}