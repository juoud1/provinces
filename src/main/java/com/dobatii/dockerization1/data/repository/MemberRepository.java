package com.dobatii.dockerization1.data.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.dobatii.dockerization1.data.entity.Member;

import reactor.core.publisher.Mono;

/**
 * Member DAO
 * 
 * @author juoud1
 * @version 1.0
 * @date 02-11-2023
 * 
 */

@Transactional
public interface MemberRepository extends ReactiveCrudRepository<Member, Long> {
	Mono<Member> findByMemberUsername(String memberUsername);
}
