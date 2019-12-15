package com.dobatii.dockerization1.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dobatii.dockerization1.data.model.Province;
import com.dobatii.dockerization1.service.ProvinceService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * Composant d'intérection avec tout client de service-province
 * 
 * @author 9386-2142 Qc inc
 * @version 1.0
 * 2019-05-09
 * 
 */

@RestController
@Slf4j
public class ProvinceController {
	
	private final ProvinceService provinceService;
	
	public ProvinceController(ProvinceService provinceService) {
		this.provinceService = provinceService;
	}
	
	@GetMapping(value="/provinces", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Province> getProvinces(){
		
		log.info(provinceService.getAllProvinces()
				.collectList().toString());
		
		return provinceService.getAllProvinces();
	}
	
	@GetMapping(value="/provincesHardcoded", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Province> getHardcodedProvinces(){
		
		log.info(provinceService.getProvinces()
				.collectList().toString());
		
		return provinceService.getProvinces();
	}
	
}
