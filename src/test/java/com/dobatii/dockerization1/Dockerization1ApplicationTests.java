package com.dobatii.dockerization1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Dockerization1ApplicationTests {

	@Test
	public void contextLoads() {
	
	}
	
	@Test
	public void testFlux() {
		
		List<Integer> integers = new ArrayList<>();
		
		Flux.just(1,2,3,4)
			.filter(n -> n % 2 == 0)
			.log()   
			.map(n -> n* 2)
			.log()
			.subscribe(n -> integers.add(n));
		
		log.info("integers : {}", integers);
		
		assertThat(integers).containsExactly(4, 8);
		
	}
}
