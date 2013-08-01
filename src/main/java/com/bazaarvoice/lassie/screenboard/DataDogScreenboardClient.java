/*
 * Copyright 2013 Bazaarvoice, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bazaarvoice.lassie.screenboard;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
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
    public int create(Board board) throws DataDogScreenboardException {
        try {
            return apiResource()
                    .post(ScreenboardResponse.class, board)
                    .getId();
        } catch (UniformInterfaceException ex) {
            ErrorResponse errorResponse = ex.getResponse().getEntity(ErrorResponse.class);
            throw new DataDogScreenboardException(errorResponse.getErrors(), ex);
        }
    }

    /**
     * Updates an existing Screenboard.
     *
     * @param screenboardID The ID of the screenboard to be updated.
     * @param board         The board that will replace the current board.
     */
    public void update(int screenboardID, Board board) throws DataDogScreenboardException, IOException {
        try {
            apiResource("" + screenboardID)
                    .put(board);
        } catch (UniformInterfaceException ex) {
            ErrorResponse errorResponse = ex.getResponse().getEntity(ErrorResponse.class);
            throw new DataDogScreenboardException(errorResponse.getErrors(), ex);
        }
    }

    /**
     * Deletes an existing Screenboard.
     *
     * @param screenboardID The ID of the screenboard to be deleted
     * @return The board that was deleted
     */
    public void delete(int screenboardID) throws DataDogScreenboardException, IOException {
        try {
            apiResource("" + screenboardID)
                    .delete(ScreenboardResponse.class);
        } catch (UniformInterfaceException ex) {
            if (ex.getResponse().getClientResponseStatus() != ClientResponse.Status.NOT_FOUND) {
                ErrorResponse errorResponse = ex.getResponse().getEntity(ErrorResponse.class);
                throw new DataDogScreenboardException(errorResponse.getErrors(), ex);
            }
        }
    }

    /**
     * Gets an existing Screenboard.
     *
     * @param screenboardID ID of the screenboard
     * @return The board matching the ID
     */
    public Board get(int screenboardID) throws DataDogScreenboardException, IOException {
        try {
            return apiResource("" + screenboardID)
                    .get(Board.class);
        } catch (UniformInterfaceException ex) {
            ErrorResponse errorResponse = ex.getResponse().getEntity(ErrorResponse.class);
            throw new DataDogScreenboardException(errorResponse.getErrors(), ex);
        }
    }

    /**
     * Gets a URL of an existing screenboard that can be used to publicly view the board in a browser.
     *
     * @param screenboardID ID of the screenboard
     * @return The URL of the screenboard
     */
    public String getPublicUrl(int screenboardID) throws DataDogScreenboardException {
        try {
            return apiResource("/share/" + screenboardID)
                    .get(ScreenboardUrlResponse.class)
                    .getUrl();
        } catch (UniformInterfaceException ex) {
            ErrorResponse errorResponse = ex.getResponse().getEntity(ErrorResponse.class);
            throw new DataDogScreenboardException(errorResponse.getErrors(), ex);
        }
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

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ErrorResponse {
        @JsonProperty("errors")
        private List<String> _errors;

        private List<String> getErrors() {
            return _errors;
        }
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
