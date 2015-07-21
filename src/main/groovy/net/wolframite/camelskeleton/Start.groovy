package net.wolframite.camelskeleton

import org.apache.log4j.*
import groovy.util.logging.*
import groovy.util.ConfigObject

@Log4j
/**
 * @author Wolfram Huesken <woh@wolframite.net>
 */
class Start {

	public static final String LOGGER_CONFIG_PATH = '.camelskeleton/logger.groovy'
	public static final String MAIN_CONFIG_PATH = '.camelskeleton/camelskeleton.groovy'

	static main(args) {
		initLogger()
		def mainConfig = getMainConfig()
		new CamelSkeleton(mainConfig).launch()
	}

	private static void initLogger() {
		try {
			def loggerConfig = new ConfigSlurper().parse(getLoggerConfigURL())
			PropertyConfigurator.configure(loggerConfig.toProperties())
		} catch (Exception ex) {
			println ex.getMessage()
			System.exit(1)
		}
	}

	private static URL getLoggerConfigURL() {
		def loggerConfigPath = new File(System.getProperty('user.home') + '/' + LOGGER_CONFIG_PATH)
		if (loggerConfigPath.exists()) {
			return loggerConfigPath.toURI().toURL()
		}

		getClass().getResource("/conf/logger.groovy")
	}

	private static ConfigObject getMainConfig() {
		try {
			return new ConfigSlurper().parse(getMainConfigURL())
		} catch (IOException ex) {
			log.fatal(ex.getMessage(), ex)
			System.exit(1)
		}
	}

	private static URL getMainConfigURL() {
		def mainConfigPath = new File(System.getProperty('user.home') + '/' + MAIN_CONFIG_PATH)
		if (mainConfigPath.exists()) {
			return mainConfigPath.toURI().toURL()
		}

		getClass().getResource('/conf/camelskeleton.groovy')
	}
}
