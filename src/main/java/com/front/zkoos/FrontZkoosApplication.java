package com.front.zkoos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = {"com.front.zkoos.*"})
@PropertySource(value = "classpath:front.properties", encoding = "UTF-8")
public class FrontZkoosApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontZkoosApplication.class, args);
	}

}
