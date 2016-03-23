package io.m18.skel;

import org.springframework.context.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Wolfram Huesken <woh@m18.io>
 */
@SpringBootApplication
@ImportResource("classpath:camel-context.xml")
public class Start {

    public static void main(String[] args) {
		SpringApplication.run(Start.class, args);
	}

}
