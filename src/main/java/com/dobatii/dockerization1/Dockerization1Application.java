package com.dobatii.dockerization1;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 9386-2142 Qc inc
 * @version 1.1
 * @date 2019-05-09, 2023-10-30
 * 
 */

@SpringBootApplication
@EnableHypermediaSupport(type = HypermediaType.HAL)
@Slf4j
public class Dockerization1Application implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(Dockerization1Application.class, args);
		/*
		 * SpringApplication reactiveApp = new
		 * SpringApplication(Dockerization1Application.class);
		 * reactiveApp.setWebApplicationType(WebApplicationType.REACTIVE);
		 * log.info("Province and territory App is ready!"); reactiveApp.run(args);
		 */
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Province and territory App is ready!");
	}

}
