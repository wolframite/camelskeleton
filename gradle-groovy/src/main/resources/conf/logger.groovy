log4j {
	rootLogger="info,stdout"

	appender.stdout = "org.apache.log4j.ConsoleAppender"
	appender."stdout.layout" = "org.apache.log4j.PatternLayout"
	appender."stdout.layout.ConversionPattern" = "%r [%t] %-5p %c - %m%n"
}
