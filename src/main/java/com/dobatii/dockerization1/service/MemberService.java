package com.dobatii.dockerization1.service;

import java.util.Objects;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dobatii.dockerization1.data.entity.Member;
import com.dobatii.dockerization1.data.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Component to process member data from db
 * 
 * @author juoud1
 * @version 1.0
 * @date 02-11-2023
 * 
 */

@Service
@Transactional
@Slf4j
public class MemberService implements ReactiveUserDetailsService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		log.info("initializing member service in progress ...".toUpperCase());
		this.memberRepository = memberRepository;
		log.info("member service initialized with success!".toUpperCase());
	}

	@Override
	public Mono<UserDetails> findByUsername(String username) {

		Mono<Member> member = memberRepository.findByMemberUsername(username);

		if (!Objects.isNull(member)) {
			return member.map(m -> {
				log.info("Fetching Current user in progress ...".toUpperCase());
				return User.withUsername(username).password(m.getMemberPassword()).roles(m.getMemberRole()).build();
			}).log("Current user fetched with success!".toUpperCase());
		} else {
			throw new UsernameNotFoundException("Member not found!".toUpperCase());
		}
	}

}
