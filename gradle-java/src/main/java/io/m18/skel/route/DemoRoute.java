package io.m18.skel.route;

import io.m18.skel.processor.DemoProcessor;
import lombok.Getter;
import lombok.Setter;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Wolfram Huesken <woh@m18.io>
 */
@Component
@ConfigurationProperties(prefix="route.DemoRoute")
public class DemoRoute extends RouteBuilder {

    @Getter @Setter
	private String wireTap;

    @Getter @Setter
	private String demoFolder;

    @Getter @Setter
	private String demoLogger;

	@Autowired
	private DemoProcessor demoProcessor;

	@Override
	public void configure() {
		from(demoFolder).routeId(getClass().getSimpleName())
			.convertBodyTo(String.class)
			.wireTap(wireTap)
			.process(demoProcessor).id("MyProcessor")
			.marshal().json(JsonLibrary.Gson)
			.setHeader("content-type", constant("application/json"))
			.to(demoLogger);
	}

}
