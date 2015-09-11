# Camel Skeleton

## Prerequisites

- Java 1.7+
- Linux / OS X

It should also work on Windows, but I don't have a machine to test it. I also don't know if the folder listener works properly. If you experience any problems and know how to fix it, let me know!

##tl;dr

- `git clone git@github.com:Lunatic666/camelskeleton.git`
- `cd camelskeleton`
- `./gradlew run`

Now wait 2h until the download of the dependencies is finished, then open a new shell:

- `cd camelskeleton/demo`
- `echo 'Hello Camel!' >> test.txt`

You should see something like this in the 1st console window: 

`66897 [Camel (CamelSkeleton) thread #2 - file:///Users/whuesken/Documents/ggts-workspace/CamelSkeleton/demo] INFO net.wolframite.camelskeleton.Demo - Exchange[ExchangePattern: InOnly, BodyType: String, Body: {"content":"Hello Camel!\n","timestamp":1437452465232}]`

## Structure of the skeleton

Everything starts in Start.groovy, where I...

- Init the logger
- Read the configuration files with the routes
- Create a new instance of the CamelSkeleton class

In the CamelSkeleton class I create a new context which includes the camel-context.xml, which gives you another option
how to implement your routes. I prefer the programmatic approach, others may like to write them in an XML file.

There are packages for processors and routes (`net.wolframite.camelskeleton.processor` + `net.wolframite.camelskeleton.route`); they're the
starting point for you to implement your own routes.

## The route

I left the ids and wireTap out for better overview:

```Java
from(file:///demo?autoCreate=true&delete=true)
    .convertBodyTo(String.class)
    .process(demoProcessor)
    .to("log:net.wolframite.camelskeleton.Demo?level=INFO");
```

- Listen on the demo folder (Create it, if it doesn't exist)
- Send the file to the demo processor which creates a small map and converts it to JSON
- Send the whole message to the logger instance

## Add your own routes and processors

- Add a route file to `net.wolframite.camelskeleton.route`
- Add the class name to `route.name` in `conf/camelskeleton.groovy`
- Add a new processor to `net.wolframite.camelskeleton.processor` (optional)
- Add the route components to `conf/camelskeleton.groovy` (optional)

## What is this camel?

I think Amr Mostafa already gave the best answer to this question on [Stackoverflow](http://stackoverflow.com/questions/8845186/what-exactly-is-apache-camel#answer-11540451)

## Send me pull requests!

I'm pretty new to Camel / Groovy / Gradle, so if you have improvements, let me know!
