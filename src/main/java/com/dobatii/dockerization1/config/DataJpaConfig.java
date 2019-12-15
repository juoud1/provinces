package com.dobatii.dockerization1.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuring spring data jpa for the province service
 * 
 * @author 9386-2142 Qc inc
 * @version 1.0
 * 2019-12-15
 * 
 */

@Configuration
@EntityScan({"com.dobatii.dockerization1.data.model",
	"com.dobatii.dockerization1.data.repositoryjpa"})
public class DataJpaConfig {

}
