# Camel Skeleton

## Prerequisites

- Java 1.7+
- Linux / OS X / Windows
- Maven

##tl;dr

- `git clone git@github.com:Lunatic666/camelskeleton.git`
- `cd camelskeleton/maven-java`
- `mvn compile exec:java`

I don't really have much experience with Maven (Actually I prefer Gradle a lot more...),
so if you get any errors, better start freaking out and running in circles. If everything
runs smoothly, try the following:
- `cd camelskeleton/maven-java/demo`
- `head -n1 /proc/meminfo > test.txt`

You should see something like this in the 1st console window:

`2015-09-12 21:50:06.505  INFO 24141 --- [0 - file://demo] io.m18.skel.DemoRoute  : Exchange[ExchangePattern: InOnly, BodyType: byte[], Body: {"content":"MemTotal: 16341180 kB\n","timestamp":"Sat Sep 12 21:50:06 SGT 2015"}]`

## Structure of the skeleton

It's a lot less code than in the gradle-groovy example:

In the resources folder you find the camel context, which doesn't do anything interesting and the application.properties,
which holds the route configuration.

In the Start.java I use some annotations and I start the Spring Application, which does the rest pretty
much automatically.

The demo route is annotated as component, so spring will come around and see what's going on. The
String member variables are auto-assigned from the config file by using the `@ConfigurationProperties`
annotation. What's not so nice is that you still need getters and setters, which blow up the class.
Using groovy for that or hiding this stuff in a bean, could make this a bit cleaner.

The processor is annotated as a component, too, that's why it's instantiated automatically by annotating
the variable with `@Autowired`

If you want to use this skeleton for your own projects, you can either delete the route and the processor or
if you want to keep the code, remove the annotations.

## The route

I left the ids and wireTap out for better overview:

```Java
from("file:demo?autoCreate=true&delete=true")
    .convertBodyTo(String.class)
    .process(demoProcessor)
    .marshal().json(JsonLibrary.Gson)
    .setHeader("content-type", constant("application/json"))
    .to("log:io.m18.skel.DemoRoute?level=INFO");
```

- Listen on the demo folder (Create it, if it doesn't exist)
- Send the file to the demo processor which creates a map and adds a timestamp
- Convert the map to JSON using GSON
- Setting a content type header
- Send the whole message to the logger instance

Java doesn't have an inbuilt class for JSON handling, so I first tried xstream which is Camel's default,
but the result was not really convincing. GSON however did the job as expected.

## Add your own routes and processors

- Add a route file where ever you want
- Annotate it with component
- Add a new processor (optional)
- Annotate it, too (optional)
