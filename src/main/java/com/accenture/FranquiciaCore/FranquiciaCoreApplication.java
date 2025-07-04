package com.accenture.franquiciaCore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.accenture.franquiciaCore")
public class FranquiciaCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(FranquiciaCoreApplication.class, args);
	}

}
