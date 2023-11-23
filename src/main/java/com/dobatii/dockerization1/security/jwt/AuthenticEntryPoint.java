package com.dobatii.dockerization1.security.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Component uses to handle exception during authentication
 * 
 * @author juoud1
 * @version 1.0
 * @date 22-11-2023
 * 
 */

//@Component
@Slf4j
public class AuthenticEntryPoint implements ServerAuthenticationEntryPoint {

	private final ServerWebExchangeMatcher serverWebExchangeMatcher;
	private final HttpStatusServerEntryPoint httpStatusEntryPoint;

	AuthenticEntryPoint(ServerWebExchangeMatcher serverWebExchangeMatcher,
			HttpStatusServerEntryPoint httpStatusEntryPoint) {
		log.info("initializing authentication entry point in progress ...".toUpperCase());
		this.serverWebExchangeMatcher = serverWebExchangeMatcher;
		this.httpStatusEntryPoint = new HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED);
		log.info("authentication entry point initialized with success ...".toUpperCase());
	}

	@Override
	public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {

		return this.serverWebExchangeMatcher.matches(exchange)//
				.map(result -> {
					return result.isMatch() ? this.httpStatusEntryPoint
							: new HttpStatusServerEntryPoint(HttpStatus.ALREADY_REPORTED);
				}).flatMap(e -> e.commence(exchange, ex))//
				.then();
	}

}
