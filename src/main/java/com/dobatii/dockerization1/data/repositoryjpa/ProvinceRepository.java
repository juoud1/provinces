package com.dobatii.dockerization1.data.repositoryjpa;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dobatii.dockerization1.data.model.Province;

@Repository
@Transactional
public interface ProvinceRepository extends CrudRepository<Province, String> {
	List<Province> findAll();
	Optional<Province> findById(String id);
	
	@SuppressWarnings("unchecked")
	Province save(Province province);
	
}
