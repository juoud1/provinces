package com.dobatii.dockerization1.utils.async;

import java.util.function.Predicate;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.dobatii.dockerization1.data.model.Province;
import com.dobatii.dockerization1.service.ProvinceService;
import com.dobatii.dockerization1.utils.consts.ConstantTeritory;

import lombok.extern.slf4j.Slf4j;

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
public class TeritoryAsync {
	private final ProvinceService service;
	
	public TeritoryAsync(ProvinceService service) {
		this.service = service;
	}
	
	/**
	 * This method displays asychonously the list of teritories
	 */
	@Async
	public void printCurrentTeritories() {
		
		Predicate<Province> predicateNu = p -> ConstantTeritory.CODE_NU.equalsIgnoreCase(p.getProvinceCode());
		Predicate<Province> predicateNt = p -> ConstantTeritory.CODE_NT.equalsIgnoreCase(p.getProvinceCode());
		Predicate<Province> predicateYt = p -> ConstantTeritory.CODE_YT.equalsIgnoreCase(p.getProvinceCode());	
		Predicate<Province> predicateTeritory = predicateNu.or(predicateNt.or(predicateYt));
		
		try {
			Thread.sleep(1978);
			service.getAllProvinces().filter(predicateTeritory)
				.log()
				
				.collectList()
				.block()
				.forEach(t -> log.info(t.toString()));
			
		} catch (InterruptedException e) {
			log.error("Teritory {}", e.getLocalizedMessage());
		}
		
	}
}
