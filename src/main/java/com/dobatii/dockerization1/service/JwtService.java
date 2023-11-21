package com.dobatii.dockerization1.service;

import static com.dobatii.dockerization1.Utils.constant.ProvinceConstant.AUTHORITY_PREFIX;
import static com.dobatii.dockerization1.Utils.constant.ProvinceConstant.EXPIRATION_TIME;
import static com.dobatii.dockerization1.Utils.constant.ProvinceConstant.TOKEN_PREFIX;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * Component to create, valide and provide token
 * 
 * @author juoud1
 * @version 1.0
 * @date 16-11-2023
 * 
 */

@Component
@Slf4j
public class JwtService {

	private Key key;

	@PostConstruct
	public void init() {
		this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		log.info("SECRET KEY GENERATED : {}", this.key.toString());
	}

	public String createToken(String username) {
		var now = new Date();
		var validity = new Date(now.getTime() + EXPIRATION_TIME); // new Date(System.currentTimeMillis() +
																	// EXPIRATION_TIME)
		String token = Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(validity).signWith(key)
				.compact();
		log.info("TOKEN : {}", token);

		return token;
	}

	public String createToken(Authentication authentication) {
		var username = authentication.getName(); // Principal's name
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		log.info("USERNAME : {}", username);
		log.info("AUTHORITIES : {}", authorities);

		Claims claims = Jwts.claims().setSubject(username);
		if (!CollectionUtils.isEmpty(authorities)) {
			claims.put(AUTHORITY_PREFIX,
					authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")));
		}

		var now = new Date();
		var validity = new Date(now.getTime() + EXPIRATION_TIME);

		return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity).signWith(key).compact();
	}

	public Authentication getAuthentication(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token).getBody();

		var authoritiesClaim = claims.get(AUTHORITY_PREFIX);

		Collection<? extends GrantedAuthority> authorities = Objects.nonNull(authoritiesClaim)
				? AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesClaim.toString())
				: AuthorityUtils.NO_AUTHORITIES;

		User principal = new User(claims.getSubject(), "", authorities);
		log.info("PRINCIPAL : {}", principal);

		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}

	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			log.info("EXPIRATION DATE : {}", claims.getBody().getExpiration());
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
			log.trace("Invalid JWT token trace.", e);
		}
		return false;
	}

	public String getAuthUser(ServerHttpRequest request) {
		var token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		if (!StringUtils.isAllBlank(token)) {
			var user = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody().getSubject();
			log.info("USER : {}", user);

			if (!StringUtils.isBlank(user)) {
				return user;
			}

		}
		return null;
	}
}
