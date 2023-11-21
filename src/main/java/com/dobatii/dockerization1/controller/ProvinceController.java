package com.dobatii.dockerization1.controller;

import static com.dobatii.dockerization1.Utils.constant.ProvinceConstant.OLIBILL_API_PROVINCES_PATH;
import static com.dobatii.dockerization1.Utils.constant.ProvinceConstant.PROVINCE_PATH;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
 * Composant d'interaction avec tout client de service-province
 * 
 * @author 9386-2142 Qc inc
 * @version 1.0 2019-05-09
 * 
 */

@RestController
@RequestMapping(OLIBILL_API_PROVINCES_PATH)
@Slf4j
public class ProvinceController {

	private final ProvinceService provinceService;
	private final ProvinceRepresentationModelAssembler provinceAssembler;

	public ProvinceController(ProvinceService provinceService, ProvinceRepresentationModelAssembler provinceAssembler) {
		this.provinceService = provinceService;
		this.provinceAssembler = provinceAssembler;
		log.info("provinces and/or territories of Canada, web pesentation component initialized with success!"
				.toUpperCase());
	}

	// @PreAuthorize("hasRole('ADMIN')")
	@PostMapping // (path = PROVINCES_PATH)
	public Mono<ResponseEntity<ProvinceModel>> addProvince(@RequestBody Mono<Province> newProvince,
			ServerWebExchange exchange) {
		log.info("request for creating new province and/or territory of Canada!".toUpperCase());
		return provinceService.persistProvince(newProvince).map(p -> provinceAssembler.entityToModel(p, exchange))
				.map(m -> status(HttpStatus.CREATED).body(m));
	}

	@GetMapping(PROVINCE_PATH)
	public Mono<ResponseEntity<ProvinceModel>> getProvince(@PathVariable String province, ServerWebExchange exchange) {
		log.info("request for getting province and/or territory of Canada!".toUpperCase());
		return provinceService.getProvince(province).map(o -> provinceAssembler.entityToModel(o, exchange))
				.map(m -> ok(m)).defaultIfEmpty(notFound().build());
	}

	@GetMapping // (PROVINCES_PATH)
	public Mono<ResponseEntity<Flux<ProvinceModel>>> getProvinces(ServerWebExchange exchange) {
		log.info("request for getting all provinces and/or territories of Canada!");
		return Mono.just(ok(provinceAssembler.entitiesToListModels(provinceService.getProvinces(), exchange)));
	}

	@DeleteMapping(PROVINCE_PATH)
	public Mono<ResponseEntity<Void>> deleteProvince(@PathVariable String province, ServerWebExchange exchange) {
		log.info("request for deleting province and/or territory of Canada!".toUpperCase());
		return provinceService.getProvince(province)
				.flatMap(p -> provinceService.deleteProvinceByCode(p.getProvinceCode())
						.then(Mono.just(status(HttpStatus.ACCEPTED).<Void>build())))
				.switchIfEmpty(Mono.just(notFound().build()));
	}
}
