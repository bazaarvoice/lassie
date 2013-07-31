package com.bazaarvoice.lassie.screenboard.widgets;

import org.codehaus.jackson.annotate.JsonProperty;

import static com.bazaarvoice.lassie.screenboard.Preconditions.checkNotNull;

/** The Image is a {@link Widget} that places an image linked by URL onto the screenboard. */
public class Image extends Widget {
    @JsonProperty("url")
    private String _url;

    /**
     * The constructor for the Image that takes in a location and dimension.
     *
     * @param location   The location of the Image.
     * @param dimensions The dimension of the Image.
     * @param url        The url of the linked image.
     */
    public Image(Location location, Dimensions dimensions, String url) {
        super(location, dimensions);
        _url = checkNotNull(url, "url is null");
    }

    /**
     * The constructor for the EventStream that takes in a x / y and width / height.
     *
     * @param x      The Image's x value.
     * @param y      The Image's y value.
     * @param width  The Image's width.
     * @param height The Image's height.
     * @param url    The Image's url.
     */
    public Image(int x, int y, int width, int height, String url) throws Exception {
        this(new Location(x, y), new Dimensions(width, height), url);
    }

    /**
     * Private constructor used for deserialization.
     * Set in the top left corner of the board with the default dimensions and empty url.
     */
    public Image() throws Exception {
        this(0, 0, 32, 20, "");
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String url) {
        _url = checkNotNull(url, "url is null");
    }
}