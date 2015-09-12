package io.m18.skel.route

import io.m18.camel.ConfigRouteBuilder
import io.m18.skel.processor.DemoProcessor

/**
 * @author Wolfram Huesken <woh@wolframite.net>
 */
class DemoRoute extends ConfigRouteBuilder {

	def demoProcessor

	public DemoRoute(ConfigObject config) {
		super(config)
		demoProcessor = new DemoProcessor()
	}

	@Override
	public void configure() {
		from(config.route.Demo.from)
			.routeId(getClass().getSimpleName())
			.convertBodyTo(String.class)
			.wireTap(config.route.Demo.wireTap)
			.process(demoProcessor).id("MyProcessor")
			.to(config.route.Demo.to).id("Logger")
	}
}
