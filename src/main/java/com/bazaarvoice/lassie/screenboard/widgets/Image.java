package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class Image extends Widget {
    @JsonProperty("url")
    private String _url;

    /**
     *
     * @param location
     * @param dimensions
     * @param url
     */
    public Image(Location location, Dimensions dimensions, String url) {
        super(location, dimensions);
        _url = checkNotNull(url, "url is null");
    }

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param url
     */
    public Image(int x, int y, int width, int height, String url) {
        this(new Location(x, y), new Dimensions(width, height), url);
    }

    /**
     *
     */
    public Image() {
        this(0, 0, 32, 20, "");
    }

    /**
     *
     * @return
     */
    public String getUrl() {
        return _url;
    }

    /**
     *
     * @param url
     */
    public void setUrl(String url) {
        _url = checkNotNull(url, "url is null");
    }
}
