/**
 * 
 */
package com.dobatii.dockerization1.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.dobatii.dockerization1.data.entity.Province;
import com.dobatii.dockerization1.hateoassupport.ProvinceRepresentationModelAssembler;
import com.dobatii.dockerization1.service.JwtService;
import com.dobatii.dockerization1.service.ProvinceService;

import reactor.core.publisher.Flux;

/**
 * Unit testing provinceController class using Mockito and reactor-test
 * 
 * @author 9386-2142 Qc inc
 * @version 1.2
 * @date 2019-05-09, 2023-12-06
 */

public class ProvinceControllerTest {

	private ProvinceController provinceControler;
	private ProvinceService provinceSevice;
	private ProvinceRepresentationModelAssembler provinceAssembler;
	private JwtService jwtService;

	private Province ontario, quebec;
	private List<Province> provinces;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		// Initiate provinces
		ontario = Province.builder().provinceCode("ON").provinceName("ONTARIO").build();

		quebec = Province.builder().provinceCode("QC").provinceName("QUEBEC").build();

		provinces = Arrays.asList(ontario, quebec);

		// Using a mock service to initiate the controller
		provinceSevice = Mockito.mock(ProvinceService.class);
		provinceAssembler = Mockito.mock(ProvinceRepresentationModelAssembler.class);
		jwtService = Mockito.mock(JwtService.class);
		provinceControler = new ProvinceController(provinceSevice, provinceAssembler, jwtService);

	}

	@Test
	@Disabled
	void testGetProvinces() {

		Flux<Province> reactiveProvinces = Flux.fromStream(provinces.stream());
		when(provinceSevice.getProvinces()).thenReturn(reactiveProvinces);

		// StepVerifier.create(provinceControler.getProvinces()).expectNext(ontario,
		// quebec).verifyComplete();
	}

	@Test
	@Disabled
	void testHardcodedProvinces() {

		/*
		 * Flux<Province> reactiveProvinces = Flux.fromStream(Stream.of(ontario,
		 * quebec)); when(provinceSevice.getProvinces()).thenReturn(reactiveProvinces);
		 * 
		 * StepVerifier.create(provinceControler.getHardcodedProvinces())
		 * .expectNext(ontario, quebec) .verifyComplete();
		 */
	}

}
