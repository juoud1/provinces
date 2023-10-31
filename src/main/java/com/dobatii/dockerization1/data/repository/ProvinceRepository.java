package com.dobatii.dockerization1.data.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.dobatii.dockerization1.data.entity.Province;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Transactional
public interface ProvinceRepository extends ReactiveCrudRepository<Province, Long> {
	@Override
	Flux<Province> findAll();

	@Override
	Mono<Province> findById(Long id);

	Mono<Province> findByProvinceCode(String provinceCode);

	Mono<Province> findByProvinceName(String provinceName);

	@Override
	Mono<Province> save(Province province);

}
