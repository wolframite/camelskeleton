package io.m18.skel.processor

import org.apache.camel.*
import org.springframework.stereotype.Component

/**
 * Put payload from file and timestamp in a map
 * @author Wolfram Huesken <woh@m18.io>
 */
@Component
public class DemoProcessorGroovy implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        def data = [
            'content': exchange.getIn().getBody(),
            'timestamp': new Date().toString()
        ]
        exchange.getIn().setBody(data, Map.class)
    }

}
