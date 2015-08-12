package net.wolframite.camelskeleton

import org.apache.log4j.*
import groovy.util.logging.*
import groovy.util.ConfigObject
import org.springframework.context.support.ClassPathXmlApplicationContext

@Log4j
/**
 * @author Wolfram Huesken <woh@wolframite.net>
 */
class CamelSkeleton {

	def config

	public CamelSkeleton(ConfigObject config) {
		this.config = config
	}

	/**
	 * Init routes and add them to the context
	 * @see conf/camelskeleton.conf
	 */
	def launch() {
		def constructor
		def context = new ClassPathXmlApplicationContext('camel-context.xml').getBean('CamelSkeleton')

		// The registry is encapsulated in a weird wrapper... Free the Registry!
		context.setRegistry(context.getRegistry().getRegistry())

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
