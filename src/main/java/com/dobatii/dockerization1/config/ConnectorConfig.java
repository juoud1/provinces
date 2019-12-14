package com.dobatii.dockerization1.config;

import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectorConfig {
	
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
	
	private Connector httpConnector() {
		Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
		connector.setScheme("http");
		connector.setPort(7888);
		connector.setSecure(false);
		connector.setRedirectPort(8888);
		
		return connector;
	}
	
	@Bean
	public BeanPostProcessor addHttpConnectorProcessor() {
		return new BeanPostProcessor() {
			
			@Override
			public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
				if (bean instanceof TomcatServletWebServerFactory) {
					TomcatServletWebServerFactory factory = (TomcatServletWebServerFactory) bean;
					factory.addAdditionalTomcatConnectors(httpConnector());
					
					return factory;
				}
				
				return bean;
			}
		};
	}
}
