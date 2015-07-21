package net.wolframite.camelskeleton.route

import net.wolframite.camelskeleton.processor.*
import net.wolframite.camel.ConfigRouteBuilder

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
		from(config.route.Demo.from).routeId(getClass().getSimpleName())
			.convertBodyTo(String.class)
			.wireTap(config.route.Demo.wireTap)
			.process(demoProcessor).id(config.route.Demo.names.demoProcessor)
			.to(config.route.Demo.to).id(config.route.Demo.names.to)
	}
}
