package com.dobatii.dockerization1.utils.schedule;

import java.time.ZonedDateTime;
import java.util.function.Predicate;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dobatii.dockerization1.data.model.Province;
import com.dobatii.dockerization1.service.ProvinceService;
import com.dobatii.dockerization1.utils.consts.ConstantTeritory;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * Special province that's gouverned by the federal 
 *  
 * @author 9386-2142 Qc inc
 * @version 1.0
 * 2019-12-17
 * 
 */

@Slf4j
@Component
public class TeritorySchedule {
	private final ProvinceService service;
	
	public TeritorySchedule(ProvinceService service) {
		this.service = service;
	}
	
	private Flux<Province> getTeritories(){
		Predicate<Province> predicateNu = p -> ConstantTeritory.CODE_NU.equalsIgnoreCase(p.getProvinceCode());
		Predicate<Province> predicateNt = p -> ConstantTeritory.CODE_NT.equalsIgnoreCase(p.getProvinceCode());
		Predicate<Province> predicateYt = p -> ConstantTeritory.CODE_YT.equalsIgnoreCase(p.getProvinceCode());	
		Predicate<Province> predicateTeritory = predicateNu.or(predicateNt.or(predicateYt));
		
		return service.getAllProvinces()
				.filter(predicateTeritory)
				.log();
	}
	
	/**
	 * This method displays the list of teritories evry 2009 milliseconds
	 * 
	 */
	@Scheduled(fixedRate= 2009)
	public void printCurrentTeritories() {
		
		try {
			Thread.sleep(1978);
			getTeritories().collectList()
				.block()
				.forEach(t -> log.info("{} at {}", t.toString(), ZonedDateTime.now().toInstant()));
			
		} catch (InterruptedException e) {
			log.error("Teritory {}", e.getLocalizedMessage());
		}
		
	}
	
	public Runnable printCurrentTeritoriesSize() {
		
		return () -> {
				try{
					Thread.sleep(2009);
					int teritorySize = getTeritories().collectList()
					.block()
					.size();
					
					log.info(" teritorySize : {}".toUpperCase(), teritorySize);
				} catch (InterruptedException e) {
					log.error("Teritory size {}", e.getLocalizedMessage());
				}
			};
	}
}
