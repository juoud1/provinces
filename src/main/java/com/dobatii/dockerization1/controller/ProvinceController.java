package com.dobatii.dockerization1.controller;

import static com.dobatii.dockerization1.Utils.constant.ProvinceConstant.PROVINCES_PATH;
import static com.dobatii.dockerization1.Utils.constant.ProvinceConstant.PROVINCES_PROVINCE_PATH;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.dobatii.dockerization1.data.entity.Province;
import com.dobatii.dockerization1.hateoassupport.ProvinceRepresentationModelAssembler;
import com.dobatii.dockerization1.model.ProvinceModel;
import com.dobatii.dockerization1.service.ProvinceService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Composant d'intérection avec tout client de service-province
 * 
 * @author 9386-2142 Qc inc
 * @version 1.0 2019-05-09
 * 
 */

@RestController
@Slf4j
public class ProvinceController {

	private final ProvinceService provinceService;
	private final ProvinceRepresentationModelAssembler provinceAssembler;

	public ProvinceController(ProvinceService provinceService, ProvinceRepresentationModelAssembler provinceAssembler) {
		this.provinceService = provinceService;
		this.provinceAssembler = provinceAssembler;
		log.info("provinces and/or territories of Canada, web pesentation component is ready!");
	}

	@PostMapping(value = PROVINCES_PATH)
	public Mono<Province> addProvince(@RequestBody Mono<Province> newProvince) {
		log.info("Adding new province and/or territory of Canada!");
		return provinceService.persistProvince(newProvince);
	}

	@GetMapping(value = PROVINCES_PROVINCE_PATH)
	public Mono<ResponseEntity<ProvinceModel>> getProvince(@PathVariable String province, ServerWebExchange exchange) {
		log.info("Getting province and/or territory of Canada!".toUpperCase());
		return provinceService.getProvince(province).map(o -> {
			log.info("o %s ".toUpperCase(), o.toString());
			return provinceAssembler.entityToModel(o, exchange);
		}).map(m -> {
			log.info("m %s ".toUpperCase(), m.toString());
			return ok(m);
		}).defaultIfEmpty(notFound().build());
	}

	@GetMapping(value = PROVINCES_PATH)
	public Mono<ResponseEntity<Flux<ProvinceModel>>> getProvinces(ServerWebExchange exchange) {
		log.info("Getting all provinces and/or territories of Canada!");
		return Mono.just(ok(provinceAssembler.entitiesToListModels(provinceService.getAllProvinces(), exchange)));
	}

	/*
	 * @GetMapping(value=PROVINCES_PATH, produces =
	 * MediaType.APPLICATION_JSON_VALUE) public Flux<Province>
	 * getHardcodedProvinces(){ log.info(provinceService.getProvinces()
	 * .collectList().toString()); return provinceService.getProvinces(); }
	 */

}
