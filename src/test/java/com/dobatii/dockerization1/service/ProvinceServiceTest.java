/**
 * 
 */
package com.dobatii.dockerization1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dobatii.dockerization1.data.Province;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author 9386-2142 Qc inc
 * @version 1.0
 * 2019-12-13
 */

public class ProvinceServiceTest {
	
	private ProvinceService provinceService;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		provinceService = new ProvinceService();
	}

	@Test
	void testGetProvinces() {
		
		Flux<Province> reactiveProvinces = provinceService.getProvinces();
		
		StepVerifier.create(reactiveProvinces)
			.expectNext(Province.builder()
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
					.build())
			.verifyComplete();
	}

}
