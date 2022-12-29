package com.pil.group4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ComponentScan({"com.server", "com.server.config"})
public class Group4Application {

	public static void main(String[] args) {
		SpringApplication.run(Group4Application.class, args);
	}

}
