package io.m18.skel;

import org.apache.camel.CamelContext;
import org.springframework.context.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Wolfram Huesken <woh@m18.io>
 */
@Configuration
@ImportResource("classpath:camel-context.xml")
@SpringBootApplication
public class Start {

	@Autowired
	CamelContext camelContext;

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplication(Start.class).run(args);
	}

}
