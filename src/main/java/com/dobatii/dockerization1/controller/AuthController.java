package com.dobatii.dockerization1.controller;

import static com.dobatii.dockerization1.Utils.constant.ProvinceConstant.ACCESS_TOKEN;
import static com.dobatii.dockerization1.Utils.constant.ProvinceConstant.AUTHORIZATION;
import static com.dobatii.dockerization1.Utils.constant.ProvinceConstant.LOGIN_PATH;
import static com.dobatii.dockerization1.Utils.constant.ProvinceConstant.OLIBILL_API_AUTH_PATH;
import static com.dobatii.dockerization1.Utils.constant.ProvinceConstant.TOKEN_PREFIX;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dobatii.dockerization1.data.security.AccountCredential;
import com.dobatii.dockerization1.service.JwtService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 
 * Composant web d'authentification
 * 
 * @author juoud1
 * @version 1.0
 * @date 16-11-2023
 * 
 */

@RestController
@RequestMapping(OLIBILL_API_AUTH_PATH)
@Slf4j
public class AuthController {
	private final JwtService jwtService;
	private final ReactiveAuthenticationManager authenticationManager;

	public AuthController(JwtService jwtService, ReactiveAuthenticationManager authenticationManager) {
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
		log.info("Authentication web pesentation component initialized with success!".toUpperCase());
	}

	@PostMapping(LOGIN_PATH)
	public Mono<ResponseEntity<?>> getToken(@RequestBody Mono<AccountCredential> accountCredential) {

		return accountCredential.flatMap(account -> this.authenticationManager //
				.authenticate(new UsernamePasswordAuthenticationToken(account.getMemberUsername(),
						account.getMemberPassword()))
				.map(a -> {
					log.info("a.getCredentials() : {}", a.getCredentials().toString());
					log.info("a.getPrincipal() : {}", a.getPrincipal().toString());
					// log.info("a.getDetails() : {}", a.getDetails().toString());
					log.info("a.getName() : {}", a.getName());
					return this.jwtService.createToken(a);// .getToken(a.getName());
				})) //
				.map(jwt -> {
					log.info("JWT : {}", jwt);
					log.info("TOKEN_PREFIX JWT : {}", String.join("", TOKEN_PREFIX, jwt));

					HttpHeaders httpHeaders = new HttpHeaders();
					httpHeaders.add(HttpHeaders.AUTHORIZATION, String.join("", TOKEN_PREFIX, jwt));
					httpHeaders.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, AUTHORIZATION);

					var httpBodyToken = Map.of(ACCESS_TOKEN, jwt);

					return new ResponseEntity<>(httpBodyToken, httpHeaders, HttpStatus.OK);
				});
		/*
		 * ResponseEntity.ok() // .header(HttpHeaders.AUTHORIZATION, String.join("",
		 * TOKEN_PREFIX, jwt))// .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,
		 * AUTHORIZATION)// .build();
		 */

		/*
		 * .flatMap(account -> this.authenticationManager .authenticate(new
		 * UsernamePasswordAuthenticationToken(account.getMemberUsername(),
		 * account.getMemberPassword())) .map(this.jwtService::createToken)) // .map(jwt
		 * -> { log.info("JWT : {}", jwt); HttpHeaders httpHeaders = new HttpHeaders();
		 * httpHeaders.add(HttpHeaders.AUTHORIZATION, String.join("", TOKEN_PREFIX,
		 * jwt)); httpHeaders.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,
		 * AUTHORIZATION); var httpBodyToken = Map.of(ACCESS_TOKEN, jwt); return new
		 * ResponseEntity<>(httpBodyToken, httpHeaders, HttpStatus.OK); });
		 */
	}
}
