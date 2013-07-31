package com.bazaarvoice.lassie.screenboard;

import com.bazaarvoice.lassie.screenboard.widgets.Widget;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Client for accessing Datadog's screenboards.
 * Both the application key and the api key are obtained from the datadog site.
 */
public class DataDogScreenboardClient {
    private final String _applicationKey;
    private final String _apiKey;
    private Client _httpClient;
    private URI _datadogApiUrl = URI.create("https://app.datadoghq.com/api/v1/screen");

    public DataDogScreenboardClient(String applicationKey, String apiKey) {
        _applicationKey = checkNotNull(applicationKey, "application key is null");
        _apiKey = checkNotNull(apiKey, "apiKey is null");
    }

    /**
     * Creates a Screenboard.
     *
     * @param board The screenboard to be created.
     * @return The id of the created board.
     */
    public int create(Board board) {
        return apiResource()
                .post(ScreenboardResponse.class, board)
                .getId();
    }

    /**
     * Updates an existing Screenboard.
     *
     * @param screenboardID The ID of the screenboard to be updated.
     * @param board         The board that will replace the current board.
     */
    public void update(int screenboardID, Board board) throws DataDogScreenboardException {
        ScreenboardResponse response = apiResource("" + screenboardID)
                .put(ScreenboardResponse.class, board);
        if (response.getErrors().size() > 0) {
            throw new DataDogScreenboardException(response.getErrors());
        }
    }

    /**
     * Deletes an existing Screenboard.
     *
     * @param screenboardID The ID of the screenboard to be deleted
     * @return The board that was deleted
     */
    public void delete(int screenboardID) throws DataDogScreenboardException {
        ScreenboardResponse response = apiResource("" + screenboardID)
                .delete(ScreenboardResponse.class);
        if (response.getErrors().size() > 0) {
            throw new DataDogScreenboardException(response.getErrors());
        }
    }

    /**
     * Gets an existing Screenboard.
     *
     * @param screenboardID ID of the screenboard
     * @return The board matching the ID
     */
    public Board get(int screenboardID) throws DataDogScreenboardException, IOException {
        ObjectMapper _json = new ObjectMapper();
        BoardResponse response = apiResource("" + screenboardID)
                .get(BoardResponse.class);
        if (response.getErrors() != null) {
            throw new DataDogScreenboardException(response.getErrors());
        }
        return response;
    }

    /**
     * Gets a URL of an existing screenboard that can be used to publicly view the board in a browser.
     *
     * @param screenboardID ID of the screenboard
     * @return The URL of the screenboard
     */
    public String getPublicUrl(int screenboardID) throws DataDogScreenboardException {
        ScreenboardUrlResponse response = apiResource("/share/" + screenboardID)
                .get(ScreenboardUrlResponse.class);
        if (response.getErrors().size() > 0) {
            throw new DataDogScreenboardException(response.getErrors());
        }
        return response.getUrl();
    }

    private WebResource.Builder apiResource(String... path) {
        UriBuilder resourceUrl = UriBuilder.fromUri(_datadogApiUrl);

        for (String p : path) {
            resourceUrl.path(p);
        }

        return getHttpClient().resource(resourceUrl.build())
                .queryParam("api_key", _apiKey)
                .queryParam("application_key", _applicationKey)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE);
    }

    public URI getDatadogApiUrl() {
        return _datadogApiUrl;
    }

    public void setDatadogApiUrl(URI datadogApiUrl) {
        _datadogApiUrl = checkNotNull(datadogApiUrl, "datadog ApiUrl is null");
    }

    public Client getHttpClient() {
        if (_httpClient == null) {
            DefaultClientConfig config = new DefaultClientConfig();
            config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, true);
            _httpClient = Client.create(config);
        }
        return _httpClient;
    }

    public void setHttpClient(Client httpClient) {
        _httpClient = checkNotNull(httpClient);
    }

    /**
     * mainly used for Jackson deserialization of responses from datadog.
     * The error parameter is used to catch the error response from Datadog.
     * It should only have data when there is a server side error
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ScreenboardResponse {
        @JsonProperty("id")
        private int _id;
        @JsonProperty("board")
        private Board _board;
        @JsonProperty("errors")
        private List<String> _errors = new ArrayList<String>();

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

        private List<String> getErrors() {
            return _errors;
        }
    }

    /** mainly used for Jackson deserialization of responses from datadog. */
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ScreenboardUrlResponse {
        @JsonProperty("id")
        private int _id;
        @JsonProperty("public_url")
        private String _url;
        @JsonProperty("errors")
        private List<String> _errors = new ArrayList<String>();

        private int getId() {
            return _id;
        }

        private String getUrl() {
            return _url;
        }

        private void setId(int id) {
            _id = id;
        }

        private void setUrl(String url) {
            _url = checkNotNull(url, "url is null");
        }

        private List<String> getErrors() {
            return _errors;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class BoardResponse extends Board {
        @JsonProperty("errors")
        private List<String> _errors = null;

        public BoardResponse(final String title, final Collection<Widget> widgets) {
            super(title, widgets);
        }

        public BoardResponse(final String title) {
            super(title);
        }

        public BoardResponse() {
            super("Title");
        }

        private List<String> getErrors() {
            return _errors;
        }
    }
}