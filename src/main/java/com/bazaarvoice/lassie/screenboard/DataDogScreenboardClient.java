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

/** Client for accessing Datadog's screenboards. */
public class DataDogScreenboardClient {
    private final String _applicationKey;
    private final String _apiKey;
    private Client _httpClient;
    private URI _datadogApiUrl = URI.create("https://app.datadoghq.com/api/v1/screen");

    /**
     *
     * @param applicationKey
     * @param apiKey
     */
    public DataDogScreenboardClient(String applicationKey, String apiKey) {
        _applicationKey = checkNotNull(applicationKey, "application key is null");
        _apiKey = checkNotNull(apiKey, "apiKey is null");
    }

    /**
     *
     * @param board
     * @return
     */
    public int create(Board board) {
        return apiResource()
                .post(ScreenboardResponse.class, board)
                .getId();
    }

    /**
     *
     * @param screenboardID
     * @param board
     */
    public void update(int screenboardID, Board board) {
        apiResource("" + screenboardID)
                .put(ScreenboardResponse.class, board);
    }

    /**
     *
     * @param screenboardID
     * @return
     */
    public Board delete(int screenboardID) {
        return apiResource("" + screenboardID)
                .delete(ScreenboardResponse.class)
                .getBoard();
    }

    /**
     *
     * @param screenboardID
     * @return
     */
    public Board get(int screenboardID) {
        return apiResource("" + screenboardID)
                .get(Board.class);
    }

    /**
     *
     * @param screenboardID
     * @return
     */
    public String getPublicUrl(int screenboardID) {
        return apiResource("/share/" + screenboardID)
                .get(ScreenboardUrlResponse.class)
                .getUrl();
    }

    /**
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

    /**
     *
     * @return
     */
    public URI getDatadogApiUrl() {
        return _datadogApiUrl;
    }

    /**
     *
     * @param datadogApiUrl
     */
    public void setDatadogApiUrl(URI datadogApiUrl) {
        _datadogApiUrl = checkNotNull(datadogApiUrl, "datadog ApiUrl is null");
    }

    /**
     *
     * @return
     */
    public Client getHttpClient() {
        if (_httpClient == null) {
            DefaultClientConfig config = new DefaultClientConfig();
            config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, true);
            _httpClient = Client.create(config);
        }
        return _httpClient;
    }

    /**
     *
     * @param httpClient
     */
    public void setHttpClient(Client httpClient) {
        _httpClient = checkNotNull(httpClient);
    }

    /**
     *
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ScreenboardResponse {
        @JsonProperty("id")
        private int _id;
        @JsonProperty("board")
        private Board _board;

        /**
         *
         * @return
         */
        private Board getBoard() {
            return _board;
        }

        /**
         *
         * @param board
         */
        private void setBoard(Board board) {
            _board = board;
        }

        /**
         *
         * @return
         */
        private int getId() {
            return _id;
        }

        /**
         *
         * @param id
         */
        private void setId(int id) {
            _id = id;
        }
    }

    /**
     *
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ScreenboardUrlResponse {
        @JsonProperty("id")
        private int _id;
        @JsonProperty("public_url")
        private String _url;

        /**
         *
         * @return
         */
        private int getId() {
            return _id;
        }

        /**
         *
         * @return
         */
        private String getUrl() {
            return _url;
        }

        /**
         *
         * @param id
         */
        private void setId(int id) {
            _id = id;
        }

        /**
         *
         * @param url
         */
        private void setUrl(String url) {
            _url = checkNotNull(url, "url is null");
        }
    }
}
