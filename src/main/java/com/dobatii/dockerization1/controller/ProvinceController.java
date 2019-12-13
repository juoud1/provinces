package com.dobatii.dockerization1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dobatii.dockerization1.data.Province;
import com.dobatii.dockerization1.service.ProvinceService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

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
	
	@GetMapping(value="/provinces")
	public Optional<List<Province>> getProvinces(){
		
		log.info(provinceService.getProvinces()
				.collectList()
				.block().toString());
		
		return provinceService.getProvinces()
				.collectList()
				.blockOptional();
	}
}
