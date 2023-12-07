package com.dobatii.dockerization1.config;

import static com.dobatii.dockerization1.Utils.constant.ProvinceConstant.DEFAULT_DATE_PATTERN;
import static com.dobatii.dockerization1.Utils.constant.ProvinceConstant.ZONED_DATE_PATTERN;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.Formatter;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import com.dobatii.dockerization1.Utils.converter.ZonedDateTimeConverter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@ComponentScan
//@EnableTransactionManagement
@Slf4j
public class AppConfig implements WebFluxConfigurer {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:messages");
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setFallbackToSystemLocale(true);
		messageSource.setCacheSeconds(0);
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean validatorFactoryBean(MessageSource messageSource) {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource(messageSource);
		return localValidatorFactoryBean;
	}

	// @Bean
	public ConversionServiceFactoryBean conversionService1() {
		var conversionServiceFactoryBean = new ConversionServiceFactoryBean();
		var converters = new HashSet<>();
		converters.add(new ZonedDateTimeConverter());

		conversionServiceFactoryBean.setConverters(converters);
		conversionServiceFactoryBean.afterPropertiesSet();
		log.info("{} converters.".toUpperCase(), converters.size());
		return conversionServiceFactoryBean;
	}

	// @Bean
	public FormattingConversionService conversionService2() {
		DefaultFormattingConversionService formattingConversionServiceBean = new DefaultFormattingConversionService(
				false);
		formattingConversionServiceBean.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());
		formattingConversionServiceBean.addFormatter(ZonedDateTimeFormatter());
		return formattingConversionServiceBean;
	}

	protected Formatter<ZonedDateTime> ZonedDateTimeFormatter() {
		return new Formatter<ZonedDateTime>() {

			@Override
			public ZonedDateTime parse(String text, Locale locale) throws ParseException {
				return ZonedDateTime.parse(text, DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN));// getDateTimeFormatter(locale));
			}

			@Override
			public String print(ZonedDateTime zonedDateTime, Locale locale) {
				return zonedDateTime.format(DateTimeFormatter.ofPattern(ZONED_DATE_PATTERN));// getDateTimeFormatter(locale));
			}

			protected DateTimeFormatter getDateTimeFormatter(Locale locale) {
				log.info(locale.getDisplayName().concat(" ").concat(locale.getCountry()));
				return DateTimeFormatter.ofPattern(ZONED_DATE_PATTERN, locale);
			}
		};
	}

	@Bean
	public FormattingConversionService conversionService() {
		// do not register defaults
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

		// ensure @NumberFormat is stille supported
		conversionService.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());

		// Register JSR-310 date conversion with a specific global format
		DateTimeFormatterRegistrar dateTimeFormatterRegistrar = new DateTimeFormatterRegistrar();
		dateTimeFormatterRegistrar.setDateFormatter(DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN));
		dateTimeFormatterRegistrar.registerFormatters(conversionService);

		// Register date conversion with a specific global format
		DateFormatterRegistrar dateFormatterRegistrar = new DateFormatterRegistrar();
		dateFormatterRegistrar.setFormatter(new DateFormatter(DEFAULT_DATE_PATTERN));
		dateFormatterRegistrar.registerFormatters(conversionService);

		return conversionService;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		WebFluxConfigurer.super.addCorsMappings(registry);
	}

	@Override
	public Validator getValidator() {
		return WebFluxConfigurer.super.getValidator();
	}

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
	/*
	 * https://stackoverflow.com/questions/59124333/spring-boot-data-r2dbc-auto-
	 * create-tables
	 * 
	 * @Bean public ConnectionFactoryInitializer initializeDbSchema() {
	 * 
	 * return null; }
	 */

	// @Bean
	/*
	 * public CommandLineRunner initDb(R2dbcEntityTemplate template,
	 * ProvinceRepository repository) { return arg -> {
	 * log.info("Initializing database ..."); log.info("--- saving province ...");
	 * repository
	 * .saveAll(List.of(Province.builder().provinceCode("AB").provinceName("Alberta"
	 * ).build(),
	 * Province.builder().provinceCode("ON").provinceName("Ontario").build(),
	 * Province.builder().provinceCode("QC").provinceName("Québec").build(),
	 * Province.builder().provinceCode("BC").provinceName("Colombie-Britanique").
	 * build(),
	 * Province.builder().provinceCode("PE").provinceName("Ile-de-Prince-Édouard").
	 * build(),
	 * Province.builder().provinceCode("MB").provinceName("Manitoba").build(),
	 * Province.builder().provinceCode("NB").provinceName("Nouveau Brunswick").build
	 * (),
	 * Province.builder().provinceCode("NS").provinceName("Nouvelle Écosse").build()
	 * , Province.builder().provinceCode("SK").provinceName("Saskatchewan").build(),
	 * Province.builder().provinceCode("NT").
	 * provinceName("Térritoires du Nord-Oeust").build(),
	 * Province.builder().provinceCode("YT").provinceName("Yukon").build(),
	 * Province.builder().provinceCode("NU").provinceName("Nunavut").build()))
	 * .blockLast(Duration.ofSeconds(7)); log.info("Provinces saved!");
	 * log.info("Database initialized!"); }; }
	 */

	/*
	 * public BeanPostProcessor addHttpConnectorProcessor() { return new
	 * BeanPostProcessor() {
	 * 
	 * @Override public Object postProcessBeforeInitialization(Object bean, String
	 * beanName) throws BeansException { // if (bean instanceof
	 * TomcatServletWebServerFactory) { // TomcatServletWebServerFactory factory =
	 * (TomcatServletWebServerFactory) bean; //
	 * factory.addAdditionalTomcatConnectors(httpConnector()); // // return factory;
	 * // } // return bean; } }; }
	 */
}
