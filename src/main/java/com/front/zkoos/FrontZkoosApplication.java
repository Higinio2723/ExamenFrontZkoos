package com.front.zkoos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.front.zkoos.*"})
public class FrontZkoosApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontZkoosApplication.class, args);
	}

}
