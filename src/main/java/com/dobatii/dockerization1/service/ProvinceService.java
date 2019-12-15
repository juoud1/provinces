package com.dobatii.dockerization1.service;

import java.util.List;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.dobatii.dockerization1.data.model.Province;
import com.dobatii.dockerization1.data.repositoryjpa.ProvinceRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * 
 * @author 9386-2142 Qc inc
 * @version 1.0
 * 2019-05-09
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
	 * This method calls the province's repository to get all provinces and territories 
	 * 
	 * @return Flux of all existing provinces or territories
	 * 
	 */
	public Flux<Province> getAllProvinces(){
		
		List<Province> provinces = provinceRepository.findAll();
		
		if (!provinces.isEmpty()) {
			log.info("Get all {} existing provinces and territories.".toUpperCase(), provinces.size());
			return Flux.fromStream(provinces.stream());
		}
		
		log.info("No existing provinces and territories.".toUpperCase());
		return Flux.empty();
	}
	
	public Flux<Province> getProvinces(){
		log.info("Get all hard coded provinces.".toUpperCase());
		Stream<Province> streamProvince = Stream.of(Province.builder()
				.provinceCode("BC")
				.provinceName("British Colombia")
				.build(),
				Province.builder()
				.provinceCode("QC")
				.provinceName("Quebec")
				.build(),
				Province.builder()
				.provinceCode("ON")
				.provinceName("Ontario")
				.build());
		
		return Flux.fromStream(streamProvince);
	}
}
