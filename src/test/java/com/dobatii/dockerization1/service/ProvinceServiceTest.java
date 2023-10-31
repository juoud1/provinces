/**
 * 
 */
package com.dobatii.dockerization1.service;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.dobatii.dockerization1.data.entity.Province;
import com.dobatii.dockerization1.data.repository.ProvinceRepository;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Unit testing provinceService class using Mockito and reactor-test
 * 
 * @author 9386-2142 Qc inc
 * @version 1.0
 * 2019-12-13
 */

public class ProvinceServiceTest {
	
	private ProvinceService provinceService;
	private ProvinceRepository provinceRepository;
	
	private Province ontario, quebec;
	private List<Province> provinces;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		// Initiate province's instance and the list of provinces for unit testing
		ontario = Province.builder()
		.provinceCode("ON")
		.provinceName("ONTARIO")
		.build();
		
		quebec = Province.builder()
		.provinceCode("QC")
		.provinceName("QUEBEC")
		.build();
		
		provinces = Arrays.asList(ontario, quebec);
		
		//Using a mock repository to initiate the service
		provinceRepository = Mockito.mock(ProvinceRepository.class);
		provinceService = new ProvinceService(provinceRepository);
	}
	
	@Test
	void testGetAllProvinces() {
		
		/*when(provinceRepository.findAll()).thenReturn(Flux.just(provinces));
		
		StepVerifier.create(provinceService.getAllProvinces())
			.expectNext(ontario, quebec)
			.verifyComplete();*/
	}
	
	@Disabled
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
