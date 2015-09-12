package io.m18.camel

import groovy.util.ConfigObject
import org.apache.camel.builder.RouteBuilder

/**
 * @author Wolfram Huesken <woh@wolframite.net>
 * @see conf/skel*/
abstract class ConfigRouteBuilder extends RouteBuilder {

	def config

	public ConfigRouteBuilder(ConfigObject config) {
		super(null)
		this.config = config
	}

}
