package com.dobatii.dockerization1;

import java.io.IOException;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dobatii.dockerization1.utils.async.TeritoryAsync;

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
		
		log.info("Press [ENTER] to quit :".toUpperCase());
		try {
			System.in.read();
		} catch (IOException e) {
			log.error("input error : {}".toUpperCase(), e.getLocalizedMessage());
		}
	}

	@Bean(name="teritoryRunner")
	public ApplicationRunner teritoryRunner(TeritoryAsync teritory) {
		
		return v -> teritory.printCurrentTeritories(); 
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Allo - Olibill province and territory service!");	
	}
}
