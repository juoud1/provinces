package com.dobatii.dockerization1.data.repositoryjpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

/**
 * Unit testing provinceRepository class using Mockito and reactor-test
 * 
 * @author 9386-2142 Qc inc
 * @version 1.0
 * 2019-12-15
 *
 */

@DataJpaTest
class ProvinceRepositoryTest {
	
	@Autowired
	private ProvinceRepository provinceRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testFindAll() {
		
		assertThat(provinceRepository.findAll()).isNotEmpty();
		assertThat(provinceRepository.findAll()).hasSize(13);
	}

}
