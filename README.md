# Camel Skeleton

It took me quite a while to get started with Camel, especially to setup the environment around it.
I want to share my skeleton app to help other people, that they can focus on working with Camel and trying
out the examples on the page, rather than searching for boilerplate-snippets on the web.

## Prerequisites

- Java 1.8
- Linux / OS X / Windows
- Maven

##tl;dr

```
$ git clone git@github.com:Lunatic666/camelskeleton.git
$ cd camelskeleton
$ cp src/main/resources/application.yml.dev src/main/resources/application.yml
$ gradle run
```

Open a new shell and create some content in the demo folder:

```
$ cd camelskeleton/maven-java/demo
$ head -n1 /proc/meminfo > test.txt
```

You should see something like this in the 1st console window:

`2016-03-23 19:27:19.012  INFO 2769 --- [2 - file://demo] io.m18.skel.DemoRoute: Exchange[ExchangePattern: InOnly, BodyType: String, Body: {"content":"MemTotal: 7741044 kB\n","timestamp":"Wed Mar 23 19:27:18 SGT 2016"}]`

## Structure of the skeleton

In the resources folder you find the camel context, which holds a configuration to access a demo database and shows how to define a Slack bean. The folder also contains the application.yml.dev, where you can change the db config credentials, the slack web hook and of course the route parameters

In the Start.java I use some annotations and I start the Spring Application, which does the rest pretty
much automatically.

The demo route is annotated as component, so spring will come around and see what's going on. The
String member variables are auto-assigned from the config file by using the `@ConfigurationProperties`
annotation. To hide the getters and setters, I use lombok to not clutter up the file. You might have to install an extension in your IDE, to make it stop complaining.

The processor is annotated as a component, too, that's why it's instantiated automatically by annotating
the variable with `@Autowired`

If you want to use this skeleton for your own projects, simply move the Start.java to your own package, delete the rest of the code and remove the routeBuilder tag in the camel context: `<routeBuilder ref="demoRoute" />` or replace it with your own route name.

I want to completely get rid of the Start class, so currently I'm experimenting with the Main classes which come with Spring and Camel. So far no real success, but I'm sure I can make it work. I only need some time :D

## The route

I left the ids and wireTap out for better overview:

```Java
from("file:demo?autoCreate=true&delete=true")
    .convertBodyTo(String.class)
    .process(demoProcessor)
    .marshal().json(JsonLibrary.Gson)
    .convertBodyTo(String.class)
    .to("log:io.m18.skel.DemoRoute?level=INFO");
```

- Listen on the demo folder (Create it, if it doesn't exist)
- Send the file to the demo processor which creates a map and adds a timestamp
- Convert the map to JSON using GSON
- Setting a content type header
- Send the whole message to the logger instance

Java doesn't have an inbuilt class for JSON handling, so I first tried xstream which is Camel's default,
but the result was not really convincing. GSON however did the job as expected.

## Send me pull requests!

I'm pretty new to Camel / Groovy / Gradle / Maven, so if you have improvements, let me know!
