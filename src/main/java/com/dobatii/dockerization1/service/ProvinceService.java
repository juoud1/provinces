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
		this.provinceRepository = provinceRepository;
		log.info("initiate province repository".toUpperCase());
	}

	/**
	 * This method calls the province's repository to get all provinces and
	 * territories
	 * 
	 * @return Flux of all existing provinces or territories
	 * 
	 */
	public Flux<Province> getAllProvinces() {
		log.info("provinces and/or territories of Canada processed!".toUpperCase());
		return provinceRepository.findAll();
	}

	public Mono<Province> getProvince(String province) {
		log.info("province and/or territory of Canada processed!".toUpperCase());
		return StringUtils.isNotBlank(province) && 2 == province.length()
				? provinceRepository.findByProvinceCode(province)
				: provinceRepository.findByProvinceName(province);
	}

	public Mono<Province> persistProvince(Mono<Province> newProvince) {
		return newProvince.flatMap(prov -> {
			Province province = Province.builder().provinceName(prov.getProvinceName())
					.provinceCode(prov.getProvinceCode()).build();
			log.info("new province and/or territory of Canada processed!".toUpperCase());
			return provinceRepository.save(province);
		});
	}

}
