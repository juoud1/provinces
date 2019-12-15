/**
 * 
 */
package com.dobatii.dockerization1.controller;

import static org.mockito.Mockito.when;

import java.net.URISyntaxException;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.dobatii.dockerization1.data.model.Province;
import com.dobatii.dockerization1.service.ProvinceService;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author 9386-2142 Qc inc
 * @version 1.0
 * 2019-12-13
 */

public class ProvinceControllerTest {
	
	private ProvinceController provinceControler;
	private ProvinceService provinceSevice;
	
	private Province ontario, quebec;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		// Initiate provinces
		ontario = Province.builder()
		.provinceCode("ON")
		.provinceName("ONTARIO")
		.build();
		
		quebec = Province.builder()
		.provinceCode("QC")
		.provinceName("QUEBEC")
		.build();
		
		//Using a mock service to initiate the controller
		provinceSevice = Mockito.mock(ProvinceService.class);
		provinceControler = new ProvinceController(provinceSevice);
	}

	@Test
	void testGetProvinces() throws URISyntaxException {
				
		Flux<Province> reactiveProvinces = Flux.fromStream(Stream.of(ontario, quebec));
		
		when(provinceSevice.getProvinces()).thenReturn(reactiveProvinces);
		
		StepVerifier.create(provinceControler.getProvinces())
		.expectNext(ontario, quebec)
		.verifyComplete();
	}

}
