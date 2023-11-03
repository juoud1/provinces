package com.dobatii.dockerization1.data.repositoryjpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.dobatii.dockerization1.data.repository.ProvinceRepository;

/**
 * Unit testing provinceRepository class using Mockito and reactor-test
 * 
 * @author 9386-2142 Qc inc
 * @version 1.0 2019-12-15
 *
 */

@DataJpaTest
class ProvinceRepositoryTest {

	@Autowired
	private ProvinceRepository provinceRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@BeforeEach
	@Disabled
	void setUp() throws Exception {
	}

	/*
	 * @Test
	 * 
	 * @Disabled void testFindAll() {
	 * 
	 * assertThat(provinceRepository.findAll()).isNotEmpty();
	 * assertThat(provinceRepository.findAll()).hasSize(13); }
	 */

}
