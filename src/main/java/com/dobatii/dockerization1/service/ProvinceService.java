package com.dobatii.dockerization1.service;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.dobatii.dockerization1.data.Province;

import reactor.core.publisher.Flux;

/**
 * 
 * @author 9386-2142 Qc inc
 * @version 1.0
 * 2019-05-09
 * 
 */

@Service
public class ProvinceService {
	
	public Flux<Province> getProvinces(){
		
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
