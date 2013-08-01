Project Lassie
==============

Overview
--------
Project Lassie is a library of classes dedicated to accessing Datadog's API endpoints and creating screenboards. Lassie takes the Board Object from the user and converts it
to a JSON object and sends it to the Datadog servers where that JSON is then converted into a screenboard which can be accessed through either their site or an embed code.
Lassie has the ability to create, update, and remove screenboards. It also has the ability to fetch the specific id and embed code for specific screenboards.
Lassie operates in three parts.

* The Widgets package
* The Board Class
* The Client

The Widgets Package
-------------------
The widgets package serves as the nuts and bolts to Lassie. Every "widget" extends the widget class. Every widget has

+ Location
+ Dimension
+ Alignment

What makes the Widgets unique is the "type". The Type determines what additional elements are included in the eventual JSON object.
The types of widgets include

------

* EventStream
* EventTimeline
* Image
* Note
* QueryValue
* Text
* Timeseries
 
------

EventStream
>The Eventstream is a live ticker of events that will show the user any event that matches the query in real time.

EventTimeline
>The EventTimeline is a live bargraph ticker which shows the number of events at a specific time based on the user's query.

Image
>The Image is a literal Image which is linked to the screenboard through a url provided by the user.

Note
>The Note is a literal note which is added to the page which can be used as a standalone element or a tick (pointer) can
>be placed to denote reference to another element on the screenboard.

QueryValue
>The QueryValue is a widget used to show an aggragated value of a query. This Value can be a SUM, MAX, MIN, and AVG.
>The QueryValue can also compair the aggragated value to a user provided threshold using >, >=, <, <=.
>The color of the QueryValue can be assigned based on this comparison. The current color options are Green, Yellow, and Red.

Text
>The Test is a literal text element that can be added to the screenboard. It differs from the note by having no background and taking a hexadecimal color.

Timeseries
>The Timeseriese is a live linegraph ticker which will graph the value of the queried value. This value can be broken down by
>another parameter and then graphed by a selected tag.

The Board Class
------------------
The Board class is what holds the widgets. Let this be an important note, the Board class does not hold the screenboard ID or the embed code. Those elements are handled by
the Client class in private static response classes which are made for the purpose of seperating them from the returned JSON from the datadog servers.

The Client Class
----------------
The DataDogScreenboardClient class handles the http requests for lassie. Its here that the user will provide their application key and their api key.
The Client can create, update, delete, and get individual screenboards as well as get the public URL (embed code). The user can also provide their own  http client if they choose.

Testing
-------
There is an extensive test folder which mainly tests the creation of individual widgets, their serialization as well as their deserialization. Also included is an example screenboard response.
This JSON object contains both the id and the board object. The deserialization test does contain a replica of the the screenboard response class which is contained in the client.


Examples
--------

```java
// This is basic declaration and initialization of a Board object. Note the Title of the Board is "Example".

Board exampleBoard = new Board("Example");

// This will add an Image widget to the Board.

Image exampleImage = new Image(new Location(0, 0), new Dimensions(20, 20), "url");
exampleboard.getWidgets().add(exampleImage)

// The client takes in the user's datadog api key and application key.
// This creates the client which will send the exampleBoard containing exampleImage to the datadog servers.

DataDogScreenboardClient exampleScreenboardClient = new DataDogScreenboardClient(APPLICATION_KEY, API_KEY);

// The int exampleId is the id of the created Board.
// The client will sent the request and it will return an int.

int exampleId = exampleScreenboardClient.create(exampleBoard);
```


Links
-----
Lassie homepage
https://github.com/bazaarvoice/lassie

Datadog homepage
http://www.datadoghq.com/

GitHub link to the original bash scripts
https://gist.github.com/conorbranagan/6cf76cc9505430cde5d6