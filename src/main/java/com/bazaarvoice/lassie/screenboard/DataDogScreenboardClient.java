package com.bazaarvoice.lassie.screenboard;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Client for accessing Datadog's screenboards.
 * Both the application key and the api key are obtained from the datadog site.
 * The datadog servers respond with a screenboard JSON Object which contains both an id and a board.
 * The client will then take that response and pass either the ID,URL, or the Board to the user.
 */
public class DataDogScreenboardClient {
    private final String _applicationKey;
    private final String _apiKey;
    private Client _httpClient;
    private URI _datadogApiUrl = URI.create("https://app.datadoghq.com/api/v1/screen");

    /**
     * The constructor for the DataDogScreenboardClient that takes in a application key and api key.
     *
     * @param applicationKey
     * @param apiKey
     */
    public DataDogScreenboardClient(String applicationKey, String apiKey) {
        _applicationKey = checkNotNull(applicationKey, "application key is null");
        _apiKey = checkNotNull(apiKey, "apiKey is null");
    }

    /**
     * Creates a Screenboard".
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
    public void update(int screenboardID, Board board) {
        apiResource("" + screenboardID)
                .put(ScreenboardResponse.class, board);
    }

    /**
     * Deletes an existing Screenboard.
     *
     * @param screenboardID The ID of the screenboard to be deleted
     * @return The board that was deleted
     */
    public Board delete(int screenboardID) {
        return apiResource("" + screenboardID)
                .delete(ScreenboardResponse.class)
                .getBoard();
    }

    /**
     * Gets an existing Screenboard.
     *
     * @param screenboardID ID of the screenboard
     * @return The board matching the ID
     */
    public Board get(int screenboardID) {
        return apiResource("" + screenboardID)
                .get(Board.class);
    }

    /**
     * Gets a URL of an existing screenboard.
     *
     * @param screenboardID ID of the screenboard
     * @return The URL of the screenboard
     */
    public String getPublicUrl(int screenboardID) {
        return apiResource("/share/" + screenboardID)
                .get(ScreenboardUrlResponse.class)
                .getUrl();
    }

    /**
     * Builds the url containing both the api and application key.
     *
     * @param path
     * @return
     */
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

    /** mainly used for Jackson deserialization of responses from datadog. */
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

    /** mainly used for Jackson deserialization of responses from datadog. */
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ScreenboardUrlResponse {
        @JsonProperty("id")
        private int _id;
        @JsonProperty("public_url")
        private String _url;

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
    }
}
