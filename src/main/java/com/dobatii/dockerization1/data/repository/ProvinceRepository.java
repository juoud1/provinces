package com.dobatii.dockerization1.data.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.dobatii.dockerization1.data.entity.Province;

import reactor.core.publisher.Mono;

/**
 * Reactive DAO for mapping province object and table
 * 
 * @author juoud1
 * @version 1.1
 * @date 23-11-2020
 * 
 */

@Transactional
public interface ProvinceRepository extends ReactiveCrudRepository<Province, Long> {
	Mono<Province> findByProvinceCode(String provinceCode);

	Mono<Province> findByProvinceName(String provinceName);

	Mono<Province> findByProvinceCodeAndProvinceName(String ProvinceCode, String provinceName);

	Mono<Void> deleteByProvinceCode(String provinceCode);
}
