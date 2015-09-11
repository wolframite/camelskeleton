package io.m18.skel.route;

import io.m18.skel.processor.DemoProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Wolfram Huesken <woh@m18.io>
 */
@Component
@ConfigurationProperties(prefix="route.DemoRoute")
public class DemoRoute extends RouteBuilder {

	private String wireTap;
	private String demoFolder;
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

	public String getWireTap() {
		return wireTap;
	}

	public void setWireTap(String wireTap) {
		this.wireTap = wireTap;
	}

	public String getDemoFolder() {
		return demoFolder;
	}

	public void setDemoFolder(String demoFolder) {
		this.demoFolder = demoFolder;
	}

	public String getDemoLogger() {
		return demoLogger;
	}

	public void setDemoLogger(String demoLogger) {
		this.demoLogger = demoLogger;
	}

}
