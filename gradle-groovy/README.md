# Camel Skeleton

## Prerequisites

- Java 1.7+
- Linux / OS X / Windows

##tl;dr

- `git clone git@github.com:Lunatic666/camelskeleton.git`
- `cd camelskeleton/gradle-groovy`
- `./gradlew run`

Now wait 2h until the download of the dependencies is finished, then open a new shell:

- `cd camelskeleton/gradle-groovy/demo`
- `head -n1 /proc/meminfo > demo/memory.txt`

You should see something like this in the 1st console window: 

`49928 [Camel (CamelSkeleton) thread #2 - file://demo] INFO  io.m18.skel.Demo - Exchange[ExchangePattern: InOnly, BodyType: String, Body: {"content":"MemTotal:       16341180 kB\n","timestamp":1442067682937}]`

## Structure of the skeleton

Everything starts in Start.groovy, where I...

- Init the logger
- Read the configuration files with the routes
- Create a new instance of the CamelSkeleton class

In the CamelSkeleton class I create a new context which includes the camel-context.xml, which gives you another option
how to implement your routes. I prefer the programmatic approach, others may like to write them in an XML file.

There are packages for processors and routes (`io.m18.skel.processor` + `io.m18.skel.route`); they're the
starting point for you to implement your own routes.

## The route

I left the ids and wireTap out for better overview:

```Java
from(file:///demo?autoCreate=true&delete=true)
    .convertBodyTo(String.class)
    .process(demoProcessor)
    .to("log:io.m18.skel.Demo?level=INFO");
```

- Listen on the demo folder (Create it, if it doesn't exist)
- Send the file to the demo processor which creates a small map and converts it to JSON
- Send the whole message to the logger instance

## Add your own routes and processors

- Add a route file to `io.m18.skel.route`
- Add the class name to `route.name` in `conf/skel.groovy`
- Add a new processor to `io.m18.skel.processor` (optional)
- Add the route components to `conf/skel.groovy` (optional)
