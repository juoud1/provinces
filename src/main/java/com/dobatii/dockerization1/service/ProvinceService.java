package com.dobatii.dockerization1.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dobatii.dockerization1.data.entity.Province;
import com.dobatii.dockerization1.data.repository.ProvinceRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author 9386-2142 Qc inc
 * @version 1.1
 * @date 2019-05-09; 2023-10-30
 * 
 */

@Slf4j
@Service
@Transactional
public class ProvinceService {

	private final ProvinceRepository provinceRepository;

	public ProvinceService(ProvinceRepository provinceRepository) {
		log.info("initializing province service in progress ...".toUpperCase());
		this.provinceRepository = provinceRepository;
		log.info("province service initialized with success!".toUpperCase());
	}

	/**
	 * This method calls the province's repository to get all provinces and
	 * territories
	 * 
	 * @return Flux of all existing provinces or territories
	 * 
	 */
	public Flux<Province> getProvinces() {
		log.info("getting all provinces and/or territories of Canada in progress ...!".toUpperCase());
		return provinceRepository.findAll()
				.log("all provinces and/or territories of Canada fetched with success!".toUpperCase());
	}

	public Mono<Province> getProvince(String province) {
		log.info("getting province and/or territory of Canada in progress ...".toUpperCase());
		return StringUtils.isNotBlank(province) && 2 == province.length()
				? provinceRepository.findByProvinceCode(province.toUpperCase())
						.log("province and/or territorie of Canada fetched with success!".toUpperCase())
				: provinceRepository.findByProvinceName(province)
						.log("province and/or territorie of Canada fetched with success!".toUpperCase());
	}

	public Mono<Province> persistProvince(Mono<Province> newProvince) {
		log.info("creating province and/or territory of Canada in progress ...".toUpperCase());
		return newProvince.flatMap(prov -> {
			Province province = Province.builder().provinceName(prov.getProvinceName())
					.provinceCode(prov.getProvinceCode().toUpperCase()).build();
			log.info("new province and/or territory of Canada processed!".toUpperCase());
			return provinceRepository.save(province)
					.log("new province and/or territory of Canada created with success!".toUpperCase());
		});
	}

	public Mono<Void> deleteProvinceByCode(String provinceCodeToDelete) {
		log.info("delete province and/or territory of Canada in progress ...".toUpperCase());
		return provinceRepository.deleteByProvinceCode(provinceCodeToDelete.toUpperCase())
				.log("province and/or territory of Canada deleted with success!".toUpperCase()).then();
	}

}
