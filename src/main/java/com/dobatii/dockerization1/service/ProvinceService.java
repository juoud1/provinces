package com.dobatii.dockerization1.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dobatii.dockerization1.data.entity.Province;
import com.dobatii.dockerization1.data.repository.ProvinceRepository;
import com.dobatii.dockerization1.model.ProvinceModel;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Component for processing province or territory data
 * 
 * @author 9386-2142 Qc inc
 * @version 1.3
 * @date 2019-05-09; 2023-10-30; 2023-11-30, 2023-12-06
 * 
 */

@Service
@Transactional
@Slf4j
public class ProvinceService {

	private final ProvinceRepository provinceRepository;

	public ProvinceService(ProvinceRepository provinceRepository) {
		log.info("initializing province service in progress ...".toUpperCase());
		this.provinceRepository = provinceRepository;
		log.info("province service initialized with success!".toUpperCase());
	}

	/**
	 * This method calls the province's repository to get all provinces and
	 * territories
	 * 
	 * @return Flux of all existing provinces or territories
	 * 
	 */
	public Flux<Province> getProvinces() {
		log.info("getting all provinces and/or territories of Canada in progress ...!".toUpperCase());
		return provinceRepository.findAll()
				.log("all provinces and/or territories of Canada fetched with success!".toUpperCase());
	}

	public Mono<Province> getProvince(String province) {
		log.info("getting province and/or territory of Canada in progress ...".toUpperCase());
		return StringUtils.isNotBlank(province) && 2 == province.length()
				? provinceRepository.findByProvinceCode(province.toUpperCase())
						.log("province and/or territorie of Canada fetched with success!".toUpperCase())
				: provinceRepository.findByProvinceName(province)
						.log("province and/or territorie of Canada fetched with success!".toUpperCase());
	}

	public Mono<Province> persistProvince(Mono<ProvinceModel> newProvince, final String username) {
		log.info("creating province and/or territory of Canada in progress ...".toUpperCase());

		log.info("username : {}".toUpperCase(), username);
		if (StringUtils.isBlank(username)) {
			throw new RuntimeException("unknow user does not delete!".toUpperCase());
		}

		return newProvince.flatMap(prov -> {
			ZonedDateTime zdt = ZonedDateTime.now(ZoneId.systemDefault());

			Province provinceToSave = Province.builder() //
					.provinceCode(prov.getProvinceCode()) //
					.provinceName(prov.getProvinceName()) //
					.provinceCreated(zdt) //
					.provinceCreatedBy(username) //
					.provinceLastUpdated(zdt) //
					.provinceLastUpdatedBy(username)//
					.build();

			return provinceRepository.save(provinceToSave)
					.log("new province and/or territory of Canada created with success!".toUpperCase())
					.onErrorMap(e -> new Exception("Could not save province or territory!" + e.getMessage()));
		});
	}

	public Mono<Void> deleteProvinceByCode(String provinceToDeleteCode, final String username) {
		log.info("delete province and/or territory of Canada in progress ...".toUpperCase());

		log.info("username : {}".toUpperCase(), username);
		if (StringUtils.isBlank(username)) {
			throw new RuntimeException("unknow user does not delete!".toUpperCase());
		}

		return provinceRepository.deleteByProvinceCode(provinceToDeleteCode.toUpperCase())
				.log("province and/or territory of Canada deleted with success!".toUpperCase()).then();
	}

	public Mono<Province> updateProvinceByCode(String provinceToUpdateCode, ProvinceModel provinceToUpdate,
			final String username) {
		log.info("update province and/or territory of Canada in progress ...".toUpperCase());

		log.info("username : {}".toUpperCase(), username);
		if (StringUtils.isBlank(username)) {
			throw new RuntimeException("unknow user does not delete!".toUpperCase());
		}

		return provinceRepository.findByProvinceCode(provinceToUpdate.getProvinceCode())//
				.flatMap(prov -> {
					ZonedDateTime zdt = ZonedDateTime.now(ZoneId.systemDefault());
					prov.setProvinceCode(provinceToUpdate.getProvinceCode());
					prov.setProvinceName(provinceToUpdate.getProvinceName());
					prov.setProvinceLastUpdated(zdt);
					prov.setProvinceLastUpdatedBy(username);
					return provinceRepository.save(prov)//
							.log("province and/or territory of Canada updated with success!".toUpperCase())
							.onErrorMap(e -> new Exception("Could not update province or territory!" + e.getMessage()));
				});

	}

}
