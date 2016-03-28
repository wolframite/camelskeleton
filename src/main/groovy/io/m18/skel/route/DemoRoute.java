package io.m18.skel.route;

import io.m18.skel.processor.DemoProcessorGroovy;
import lombok.*;
import io.m18.skel.processor.DemoProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.context.annotation.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Workflow:
 * - Watch the demoFolder
 * - Read content from new files
 * - Send the data async to the wireTap endpoint
 * - The processor prepares for the JSON conversion by putting the data in a map
 * - Json conversion via GSON
 * - Output to demoLogger
 *
 * @author Wolfram Huesken <woh@m18.io>
 */
@Component
@Profile({"dev"})
@ConfigurationProperties(prefix="routes.DemoRoute")
public class DemoRoute extends RouteBuilder {

    @Getter @Setter
    private String wireTap;

    @Getter @Setter
    private String demoFolder;

    @Getter @Setter
    private String demoLogger;

    @Getter @Setter
    private String webserver;

    @Autowired
    private DemoProcessorGroovy demoProcessorGroovy;

    @Override
    public void configure() {
        from(demoFolder).routeId(getClass().getSimpleName()).startupOrder(10)
            .convertBodyTo(String.class)
            .wireTap(wireTap)
            .process(demoProcessorGroovy)
            .marshal().json(JsonLibrary.Gson)
            .convertBodyTo(String.class)
            .to(demoLogger);

        from(webserver).startupOrder(21)
            .convertBodyTo(String.class)
            .to("file:demo");

    }

}
