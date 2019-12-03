package com.dobatii.dockerization1;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 9386-2142 Qc inc
 * @version 1.0
 * 2019-05-09
 * 
 */

@SpringBootApplication
@Slf4j
public class Dockerization1Application implements ApplicationRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(Dockerization1Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Allo - Olibill province and territory service!");
	}

}
