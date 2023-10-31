package com.dobatii.dockerization1.config;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

import com.dobatii.dockerization1.data.entity.Province;
import com.dobatii.dockerization1.data.repository.ProvinceRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AppConfig {
	
//	@Bean
//	public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
//		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//		factory.addAdditionalTomcatConnectors(httpConnector());
//		factory.addContextCustomizers(securityCustomizer());
		
//		return factory;
//	}
//	
//	private TomcatContextCustomizer securityCustomizer() {
//		
//		return ctxt -> {
//			SecurityConstraint securityConstraint = new SecurityConstraint();
//			securityConstraint.setUserConstraint("CONFIDENTIAL");
//			
//			SecurityCollection securityCollection = new SecurityCollection();
//			securityCollection.addPattern("/*");
//			securityConstraint.addCollection(securityCollection);
//			
//			ctxt.addConstraint(securityConstraint);
//		};
//	}
	
//	private Connector httpConnector() {
//		Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//		connector.setScheme("http");
//		connector.setPort(7888);
//		connector.setSecure(false);
//		connector.setRedirectPort(8888);
//		
//		return connector;
//	}
	/*https://stackoverflow.com/questions/59124333/spring-boot-data-r2dbc-auto-create-tables
	 * @Bean
	public ConnectionFactoryInitializer initializeDbSchema() {
		
		return null;
	}*/
	
	@Bean
	public CommandLineRunner initDb(R2dbcEntityTemplate template, ProvinceRepository repository) {
		return arg -> {
			log.info("Initializing database ...");
			log.info("--- saving province ...");
			repository.saveAll(List.of(Province.builder().provinceCode("AB").provinceName("Alberta").build(),
					Province.builder().provinceCode("ON").provinceName("Ontario").build(),
					Province.builder().provinceCode("QC").provinceName("Québec").build(),
					Province.builder().provinceCode("BC").provinceName("Colombie-Britanique").build(),
					Province.builder().provinceCode("PE").provinceName("Ile-de-Prince-Édouard").build(),
					Province.builder().provinceCode("MB").provinceName("Manitoba").build(),
					Province.builder().provinceCode("NB").provinceName("Nouveau Brunswick").build(),
					Province.builder().provinceCode("NS").provinceName("Nouvelle Écosse").build(),
					Province.builder().provinceCode("SK").provinceName("Saskatchewan").build(),
					Province.builder().provinceCode("NT").provinceName("Térritoires du Nord-Oeust").build(),
					Province.builder().provinceCode("YT").provinceName("Yukon").build(),
					Province.builder().provinceCode("NU").provinceName("Nunavut").build()))
					.blockLast(Duration.ofSeconds(7));
			log.info("Provinces saved!");
			log.info("Database initialized!");
		};
	}
	
	/*public BeanPostProcessor addHttpConnectorProcessor() {
		return new BeanPostProcessor() {
			
			@Override
			public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//				if (bean instanceof TomcatServletWebServerFactory) {
//					TomcatServletWebServerFactory factory = (TomcatServletWebServerFactory) bean;
//					factory.addAdditionalTomcatConnectors(httpConnector());
//					
//					return factory;
//				}
//				
				return bean;
			}
		};
	}*/
}
