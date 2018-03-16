package com.devopslam.configserverv15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigServerV15Application {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerV15Application.class, args);
	}
}
