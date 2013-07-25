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

    public DataDogScreenboardClient(String applicationKey, String apiKey) {
        _applicationKey = checkNotNull(applicationKey, "application key is null");
        _apiKey = checkNotNull(apiKey, "apiKey is null");
    }

    public int create(Board board) {
        return apiResource()
                .post(ScreenboardResponse.class, board)
                .getId();
    }

    public void update(int screenboardID, Board board) {
        apiResource("" + screenboardID)
                .put(ScreenboardResponse.class, board);
    }

    public Board delete(int screenboardID) {
        return apiResource("" + screenboardID)
                .delete(ScreenboardResponse.class)
                .getBoard();
    }

    public Board get(int screenboardID) {
        return apiResource("" + screenboardID)
                .get(Board.class);
    }

    public String getPublicUrl(int screenboardID) {
        return apiResource("/share/" + screenboardID)
                .get(ScreenboardUrlResponse.class)
                .getUrl();
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
