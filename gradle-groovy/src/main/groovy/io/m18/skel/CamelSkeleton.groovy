package io.m18.skel

import groovy.util.logging.*
import org.springframework.context.support.ClassPathXmlApplicationContext

@Log4j
/**
 * @author Wolfram Huesken <woh@m18.io>
 */
class CamelSkeleton {

	def config

	public CamelSkeleton(ConfigObject config) {
		this.config = config
	}

	/**
	 * Init routes and add them to the context
	 * @see conf/skel.conf
	 */
	def launch() {
		def constructor
		def context = new ClassPathXmlApplicationContext('camel-context.xml').getBean('CamelSkeleton')

		config.route.name.each {
			try {
				constructor = Class.forName(config.route.routePackage + "." + it).getConstructor(ConfigObject.class)
				context.addRoutes(constructor.newInstance(config))
			} catch (Exception ex) {
				log.error(String.format("Route '%s' failed: %s", it, ex.getMessage()), ex)
			}
		}

		context.start()
	}

}
