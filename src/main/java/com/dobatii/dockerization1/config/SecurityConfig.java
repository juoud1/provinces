package com.dobatii.dockerization1.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.dobatii.dockerization1.service.MemberService;

import lombok.extern.slf4j.Slf4j;

/**
 * Config for app security
 * 
 * @author juoud1
 * @version 1.0
 * @date 01/11/2023
 * 
 */

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Slf4j
public class SecurityConfig {

	private final MemberService reactiveUserDetailsService;

	public SecurityConfig(MemberService reactiveUserDetailsService) {
		log.info("Initializing province security configuration in progress ...".toUpperCase());
		this.reactiveUserDetailsService = reactiveUserDetailsService;
		log.info("nitializing province security configuration done!".toUpperCase());
	}

	@Bean
	public ReactiveAuthenticationManager reactiveAuthenticationManager() {
		log.info("Configuring province reactive authentication manager in progress ...".toUpperCase());
		var reactiveAuthManager = new UserDetailsRepositoryReactiveAuthenticationManager(reactiveUserDetailsService);
		reactiveAuthManager.setPasswordEncoder(passwordEncoder());
		log.info(passwordEncoder().encode("dongongo"));
		log.info("province reactive authentication manager configured with success!".toUpperCase());
		return reactiveAuthManager;
	}

	@Bean
	public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
		log.info("Security config done!".toUpperCase());

		return null;
		/*
		 * return http .authorizeExchange(exchanges ->
		 * exchanges.pathMatchers("/provinces/OP").hasAuthority("ROLE_ADMIN")
		 * .anyExchange().permitAll()) .formLogin(formLogin ->
		 * formLogin.loginPage("/login")).csrf(csrf -> csrf.disable()).build();
		 */

		/*
		 * return http.httpBasic().and().csrf().ignoringAntMatchers("/",
		 * H2_URL_PREFIX).and().headers() // (API_URL_PREFIX, //
		 * H2_URL_PREFIX).and().headers() .frameOptions().sameOrigin() // for H2 Console
		 * .and().cors().and().authorizeRequests().antMatchers(HttpMethod.POST,
		 * TOKEN_PREFIX).permitAll() .antMatchers(HttpMethod.DELETE,
		 * OLIBILL_TOKEN_URL).permitAll() .antMatchers(HttpMethod.POST,
		 * OLIBILL_SIGNUP_URL).permitAll() .antMatchers(HttpMethod.POST,
		 * OLIBILL_REFRESH_URL).permitAll() .antMatchers(HttpMethod.GET,
		 * PROVINCES_PATH).permitAll().antMatchers(H2_URL_PREFIX).permitAll() //
		 * .mvcMatchers(HttpMethod.POST, //
		 * PROVINCES_PATH).hasAuthority(RoleEnum.ADMIN.getAuthority()).anyRequest()
		 * .authenticated() // .and() .build();
		 */
	}

	/*
	 * @Bean public MapReactiveUserDetailsService userDetailsRepository() { var
	 * dongongo =
	 * User.withUsername("dongongo").password("dongongo").roles("USER").build(); var
	 * admin =
	 * User.withUsername("admin").password("dongongo").roles("ADMIN").build();
	 * return new MapReactiveUserDetailsService(dongongo, admin); }
	 */

	// @Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH"));
		// configuration.setAllowCredentials(true);
		// For CORS response headers
		configuration.addAllowedOrigin("*");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
