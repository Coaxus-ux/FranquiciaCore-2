package com.accenture.FranquiciaCore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.accenture.FranquiciaCore")
public class FranquiciaCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(FranquiciaCoreApplication.class, args);
	}

}
