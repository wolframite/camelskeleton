package io.m18.skel.processor

import groovy.json.*
import org.apache.camel.*
import groovy.util.logging.*

@Log4j
/**
 * @author Wolfram Huesken <woh@wolframite.net>
 */
class DemoProcessor implements Processor {

	def config

	public DemoProcessor(ConfigObject config) {
		this.config = config
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		def payload = exchange.getIn().getBody(String.class)
		def data = [:]

		data['content'] = payload
		data['timestamp'] = System.currentTimeMillis()

		exchange.getIn().setHeader(Exchange.CONTENT_TYPE, 'application/json')
		exchange.getIn().setBody(JsonOutput.toJson(data))
	}

}
