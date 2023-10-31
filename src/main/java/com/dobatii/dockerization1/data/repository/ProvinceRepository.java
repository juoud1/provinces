package com.dobatii.dockerization1.data.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.dobatii.dockerization1.data.entity.Province;

import reactor.core.publisher.Mono;

@Transactional
public interface ProvinceRepository extends ReactiveCrudRepository<Province, Long> {

	Mono<Province> findByProvinceCode(String provinceCode);

	Mono<Province> findByProvinceName(String provinceName);

	Mono<Void> deleteByProvinceCode(String provinceCode);
}
