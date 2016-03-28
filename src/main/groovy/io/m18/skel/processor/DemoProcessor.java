package io.m18.skel.processor;

import java.util.*;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.*;

/**
 * Put payload from file and timestamp in a map
 * @author Wolfram Huesken <woh@m18.io>
 */
@Component
public class DemoProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String payload = exchange.getIn().getBody(String.class);
        Map<String, String> data = new HashMap<>();
        data.put("content", payload);
        data.put("timestamp", new Date().toString());
        exchange.getIn().setBody(data, Map.class);
    }

}
