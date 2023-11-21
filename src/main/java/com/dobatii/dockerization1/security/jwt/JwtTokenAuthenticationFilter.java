package com.dobatii.dockerization1.security.jwt;

import static com.dobatii.dockerization1.Utils.constant.ProvinceConstant.TOKEN_PREFIX;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.dobatii.dockerization1.service.JwtService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Component filter to handle authentication if there is a jwt token existed in
 * the http request headers
 * 
 * @author juoud1
 * @version 1.0
 * @date 16-11-2023
 * 
 */

@Slf4j
public class JwtTokenAuthenticationFilter implements WebFilter {
	private final JwtService jwtService;

	public JwtTokenAuthenticationFilter(JwtService jwtService) {
		log.info("initializing authentication filter in progress ...".toUpperCase());
		this.jwtService = jwtService;
		log.info("authentication filter in progress initialized with success ...".toUpperCase());
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		var token = resolveToken(exchange.getRequest());
		if (!StringUtils.isBlank(token) && this.jwtService.validateToken(token)) {
			return Mono.fromCallable(() -> this.jwtService.getAuthentication(token.trim()))
					.subscribeOn(Schedulers.boundedElastic()).flatMap(authentification -> {
						return chain.filter(exchange)
								.contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentification));
					});
		}
		return chain.filter(exchange);
	}

	private String resolveToken(ServerHttpRequest request) {
		var token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		if (!StringUtils.isAllBlank(token) && token.startsWith(TOKEN_PREFIX)) {
			log.info("RESOLVE TOKEN : {}", token.substring(7));
			return token.substring(7);
		}
		return null;
	}

}
