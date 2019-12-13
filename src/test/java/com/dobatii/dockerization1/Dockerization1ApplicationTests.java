package com.dobatii.dockerization1;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Dockerization1ApplicationTests {

	@Test
	public void contextLoads() {
	
	}
	
	@Test
	public void testFlux() {
		
		List<Integer> integers = new ArrayList<>();
		
		Flux.just(1,2,3,4)
			.filter(n -> n % 2 == 0)
			.log()
			.map(n -> n* 2)
			.log()
			.subscribe(n -> integers.add(n));
		
		log.info("integers : {}", integers);
		
		assertThat(integers).containsExactly(4, 8);
		
	}
	
	@Test
	public void testNewDateTime() {
		// Working with Instant and Duration
		InstantAndDurationDummyUtils.workingWithInstantAndDuration();
		
		// Working with LocalDate, Period and Duration
		LocalDateDummyUtils.workingWithLocalDate();
		
		// Working with ZonedDateTime, Period and Duration
		ZonedTimeDummyUtils.workingWithZonedDateTime();
		
		// Working with formatter and parser
		FormattingParsingDummyUtils.workingWithFormatter();
		FormattingParsingDummyUtils.workingWithParser();
		
		
	}
	
	/**
	 * Dummy test class for formatting and parsing date/time value
	 * 
	 * @author 9386-2142 Qc inc
	 *
	 */
	private static class FormattingParsingDummyUtils {
		private static ZonedTimeDummyUtils zonedTimeDummyUtils;
		
		private static LocalDateTime qzarLocalDateTime;
		private static ZonedDateTime qzarZonedDateTime;
		
		private FormattingParsingDummyUtils() {
			
		}
		
		private static void workingWithFormatter() {
			// Qzar datetime
			createDateTime();
			
			// Working with standard formatters
			String localFormattedIsoDate = DateTimeFormatter.ISO_DATE.format(qzarLocalDateTime);
			log.info("localFormattedIsoDate : {}".toUpperCase(), localFormattedIsoDate);
			
			String localFormattedIsoDatetime = DateTimeFormatter.ISO_DATE_TIME.format(qzarLocalDateTime);
			log.info("localFormattedIsoDatetime : {}".toUpperCase(), localFormattedIsoDatetime);
			
//			String formattedIsoinstant = DateTimeFormatter.ISO_INSTANT.format(qzarLocalDateTime.toInstant(null));
//			log.info("formattedIsoinstant : {}".toUpperCase(), formattedIsoinstant);
			
			String localFormattedIsoLocalDate = DateTimeFormatter.ISO_LOCAL_DATE.format(qzarLocalDateTime);
			log.info("localFormattedIsoLocalDate : {}".toUpperCase(), localFormattedIsoLocalDate);
			
			String localFormattedIsoLocalDatetime = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(qzarLocalDateTime);
			log.info("localFormattedIsoLocalDatetime : {}".toUpperCase(), localFormattedIsoLocalDatetime);
			log.info("\n");
			
			////
			String zonedFormattedIsoDate = DateTimeFormatter.ISO_DATE.format(qzarZonedDateTime);
			log.info("zonedFormattedIsoDate : {}".toUpperCase(), zonedFormattedIsoDate);
			
			String zonedFormattedIsoDateTime = DateTimeFormatter.ISO_DATE_TIME.format(qzarZonedDateTime);
			log.info("zonedFormattedIsoDateTime : {}".toUpperCase(), zonedFormattedIsoDateTime);		
					
			String zonedFormattedIsoInstant = DateTimeFormatter.ISO_INSTANT.format(qzarZonedDateTime.toInstant());
			log.info("zonedFormattedIsoInstant : {}".toUpperCase(), zonedFormattedIsoInstant);
			
			String zonedFormattedIsoLocalDate = DateTimeFormatter.ISO_LOCAL_DATE.format(qzarZonedDateTime);
			log.info("zonedFormattedIsoLocalDate : {}".toUpperCase(), zonedFormattedIsoLocalDate);
			
			String zonedFormattedIsoLocalDateTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(qzarZonedDateTime);
			log.info("zonedFormattedIsoLocalDateTime : {}".toUpperCase(), zonedFormattedIsoLocalDateTime);
			
			String zonedFormattedIsoOffSetDate = DateTimeFormatter.ISO_OFFSET_DATE.format(qzarZonedDateTime);
			log.info("zonedFormattedIsoOffSetDate : {}".toUpperCase(), zonedFormattedIsoOffSetDate);
			
			String zonedFormattedIsoOffSetDateTime = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(qzarZonedDateTime);
			log.info("zonedFormattedIsoOffSetDateTime : {}".toUpperCase(), zonedFormattedIsoOffSetDateTime);
			
			String zonedFormattedRfcDateTime = DateTimeFormatter.RFC_1123_DATE_TIME.withLocale(Locale.CANADA_FRENCH).format(qzarZonedDateTime);
			log.info("zonedFormattedRfcDateTime : {}".toUpperCase(), zonedFormattedRfcDateTime);
			log.info("\n");
			
			// Working with Locale-specific formatters
			String localFormattedlocalisedDateFull = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
														.withLocale(Locale.CANADA_FRENCH).format(qzarLocalDateTime);
			log.info("localFormattedlocalisedDateFull : {}".toUpperCase(), localFormattedlocalisedDateFull);
			
//			String localFormattedlocalisedDatetimeFull = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)
//															.format(qzarLocalDateTime);
//			log.info("localFormattedlocalisedDatetimeFull : {}".toUpperCase(), localFormattedlocalisedDatetimeFull);
			
			String localFormattedlocalisedDateLong = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
														.withLocale(Locale.CANADA_FRENCH).format(qzarLocalDateTime);
			log.info("localFormattedlocalisedDateLong : {}".toUpperCase(), localFormattedlocalisedDateLong);
			
//			String localFormattedlocalisedDateTimeLong = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)
//															.format(qzarLocalDateTime);
//			log.info("localFormattedlocalisedDateTimeLong : {}".toUpperCase(), localFormattedlocalisedDateTimeLong);
			
			String localFormattedlocalisedDateMedium = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
																.withLocale(Locale.CANADA_FRENCH).format(qzarLocalDateTime);
			log.info("localFormattedlocalisedDateMedium : {}".toUpperCase(), localFormattedlocalisedDateMedium);
			
			String localFormattedlocalisedDateTimeMedium = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
																.withLocale(Locale.CANADA_FRENCH).format(qzarLocalDateTime);
			log.info("localFormattedlocalisedDateTimeMedium : {}".toUpperCase(), localFormattedlocalisedDateTimeMedium);
			
			// Working with custom patterns
			
		}
		
		private static void workingWithParser() {
			// Qzar datetime
			createDateTime();
		}
		
		private static void createDateTime() {
			if (null == qzarLocalDateTime) {
				qzarLocalDateTime =  LocalDateTime.of(LocalDate.of(2009, Month.JANUARY, 27), LocalTime.of(3, 45));
				log.info("qzarLocalDateTime : {}".toUpperCase(), qzarLocalDateTime);
			}
			
			if (null != qzarLocalDateTime && null == qzarZonedDateTime) {
				qzarZonedDateTime = qzarLocalDateTime.atZone(ZoneId.of(zonedTimeDummyUtils.ZONEID_CLIENT));
				log.info("qzarZonedDateTime : {}".toUpperCase(), qzarZonedDateTime);
			}
			log.info("\n");
		}
	}
	
	/**
	 * Dummy test class for java.time.ZonedDateTime's method
	 * 
	 * @author 9386-2142 Qc inc
	 *
	 */
	private static class ZonedTimeDummyUtils{
		private static final String PREFIX_AFRICA_ZONEID = "Africa";
		
		private static final String ZONEID_CLIENT = "Africa/Bangui";
		private static final String ZONEID_UTC = "UTC";
		
		private static void workingWithZonedDateTime() {
			// List all available time zones ID
			log.info("All available time zones :".toUpperCase());
			ZoneId.getAvailableZoneIds().forEach(z -> System.out.println(z));
			log.info("\n");
			
			// Sorted list of available time zones ID in Africa 
			log.info("Africa available time zones :".toUpperCase());
			ZoneId.getAvailableZoneIds().stream()
			.filter(z -> z.startsWith(PREFIX_AFRICA_ZONEID))
			.sorted((a, b) -> a.compareTo(b))
			.forEach(a -> System.out.println(a));
			log.info("\n");
			
			// Working with ZonedDateTime and ZoneId
			/// Using ZoneId to turn LocalDatetime to ZonedDateTime
			LocalTime localNoon = LocalTime.NOON;
			LocalDate localDate = LocalDate.now();
			LocalDateTime localDateTime = LocalDateTime.of(localDate, localNoon);
			log.info("localDateTime of the date {} at {} is {}".toUpperCase(), localDate, localNoon, localDateTime);
			
			ZonedDateTime banguiDateTime = localDateTime.atZone(ZoneId.of(ZONEID_CLIENT));
			log.info("Bangui datetime equivalent of the local datetime {} is {}".toUpperCase(), localDateTime, banguiDateTime);
			log.info("Instant in bangui zonedDateTime is {}".toUpperCase(), banguiDateTime.toInstant());
			
			ZonedDateTime utcDateTime = localDateTime.atZone(ZoneId.of(ZONEID_UTC));
			log.info("UTC datetime equivalent of the local datetime {} is {}".toUpperCase(), localDateTime, utcDateTime);
			log.info("Instant in UTC zonedDateTime is {}".toUpperCase(), utcDateTime.toInstant());
			log.info("\n");
			
			// Look at an ambiguous 
			ZonedDateTime bgDateTime = ZonedDateTime.of(LocalDate.of(2009,Month.JANUARY, 27),
					LocalTime.of(4, 30),
					ZoneId.of(ZONEID_CLIENT));
			log.info("bgDateTime {} and the Instant is {}".toUpperCase(), bgDateTime, bgDateTime.toInstant());
			ZonedDateTime bgDateTimePlus1 = bgDateTime.plusHours(1);
			log.info("bgDateTimePlus1 {} and the Instant is {}".toUpperCase(), bgDateTimePlus1, bgDateTimePlus1.toInstant());
			log.info("\n");
			
			
		}
	}
	
	/**
	 * Dummy test class for java.time.LocalDate's method
	 * 
	 * @author 9386-2142 Qc inc
	 *
	 */
	private static class LocalDateDummyUtils{
		private static void workingWithLocalDate() {
			
			log.info("LocalDate.MIN : {}".toUpperCase(), LocalDate.MIN);
			log.info("LocalDate.MAX : {}".toUpperCase(), LocalDate.MAX);
			
			// Construct LocalDate with statics methods now() and of()
			LocalDate localToDay = LocalDate.now();
			log.info("localToDay : {}".toUpperCase(), localToDay);
			
			LocalDate localQzarDateOfBirth = LocalDate.of(2009, 1, 27);
			log.info("localQzarDateOfBirth : {}".toUpperCase(), localQzarDateOfBirth);
			log.info("localQzarDateOfBirth.isLeapYear : {}".toUpperCase(), localQzarDateOfBirth.isLeapYear());
			
			LocalDate localJodDateOfBirth = LocalDate.of(1978, Month.JANUARY, 30);
			log.info("localJodDateofBirth : {}".toUpperCase(), localJodDateOfBirth);
			log.info("localJodDateofBirth.isLeapYear : {}".toUpperCase(), localJodDateOfBirth.isLeapYear());
			System.out.println();
			
			// Some manipulations
			DayOfWeek  localDayOfWeekQzarDateOfBirth = localQzarDateOfBirth.getDayOfWeek();
			log.info("localDayOfWeekQzarDateOfBirth : {}", localDayOfWeekQzarDateOfBirth);
			log.info("localNameDayOfWeekQzarDateOfBirth : {}", localDayOfWeekQzarDateOfBirth.name());
			
			DayOfWeek  localDayOfWeekJodDateOfBirth = localJodDateOfBirth.getDayOfWeek();
			log.info("localDayOfWeekJodDateOfBirth : {}", localDayOfWeekJodDateOfBirth);
			log.info("localNameDayOfWeekJodDateOfBirth : {}", localDayOfWeekJodDateOfBirth.name());
			log.info("\n");
			
			// Period
			Period periodUntilJodToQzar = localJodDateOfBirth.until(localQzarDateOfBirth);
			log.info("periodUntilJodToQzar : {}".toUpperCase(), periodUntilJodToQzar);
			
			Period periodQzarAndJod = localJodDateOfBirth.isBefore(localQzarDateOfBirth) ? Period.between(localJodDateOfBirth, localQzarDateOfBirth) : Period.ZERO;
			log.info("periodQzarAndJod : {}".toUpperCase(), periodQzarAndJod);
			log.info("\n");
			log.info("periodQzarAndJod.getYears : {}".toUpperCase(), periodQzarAndJod.getYears());
			log.info("periodQzarAndJod.getMonths : {}".toUpperCase(), periodQzarAndJod.getMonths());
			log.info("periodQzarAndJod.getDays : {}".toUpperCase(), periodQzarAndJod.getDays());
			log.info("periodQzarAndJod.getUnits : {}".toUpperCase(), periodQzarAndJod.getUnits());
			log.info("periodQzarAndJod.getChronology : {}".toUpperCase(), periodQzarAndJod.getChronology());
			log.info("periodQzarAndJod.toTotalMonths : {}".toUpperCase(), periodQzarAndJod.toTotalMonths());
			log.info("\n");
			log.info("\n");
			
			// LocalTime
			log.info("LocalTime.MIN : {}".toUpperCase(), LocalTime.MIN);
			log.info("LocalTime.MAX : {}".toUpperCase(), LocalTime.MAX);
			log.info("LocalTime.NOON : {}".toUpperCase(), LocalTime.NOON);
			log.info("LocalTime.MIDNIGHT : {}".toUpperCase(), LocalTime.MIDNIGHT);
			
			// Construct LocalTime
			LocalTime localTimeNow = LocalTime.now();
			log.info("localTimeNow : {}".toUpperCase(), localTimeNow);
			
			LocalTime localTimeOf = LocalTime.of(3, 33);
			log.info("localTimeOf : {}".toUpperCase(), localTimeOf);
			log.info("\n");
					
			// Duration
			Duration durationUntilNowtoOf = localTimeNow.isBefore(localTimeOf) ? Duration.between(localTimeNow, localTimeOf) : Duration.ZERO;
			log.info("durationUntilNowtoOf : {}".toUpperCase(), durationUntilNowtoOf);
			log.info("\n");
			
		}
	}
	
	/**
	 * Dummy test class for java.time.Instant's method  
	 * 
	 * @author 9386-2142 Qc inc
	 *
	 */
	private static class InstantAndDurationDummyUtils{
		
		private static void workingWithInstantAndDuration() {
			log.info("Instant.EPOCH : {}", Instant.EPOCH);
			log.info("Instant.MIN : {}", Instant.MIN);
			log.info("Instant.MAX : {}", Instant.MAX);
			
			Instant startInstant = Instant.now();
			log.info("startInstant : {}".toUpperCase(), startInstant);
			
			// Do some task
			try {
				Thread.sleep(2009l);
			} catch (InterruptedException e) {
				log.error("error_Thread.sleep : {} \n {}".toUpperCase(), e.getMessage(), e.getCause());
			}
			
			Instant endInstant = Instant.now();
			log.info("endInstant : {}".toUpperCase(), endInstant);
			
			// Compute the duration between start and end
			Duration duration = Duration.between(startInstant, endInstant);
			log.info("duration : {}".toUpperCase(), duration);
			
			// Get the length of duration in conventional unit (second, ns, ...)
			long milliSeconds = duration.toMillis();
			log.info("duration in millisecond : {}".toUpperCase(), milliSeconds);
			log.info("duration.getSeconds : {}".toUpperCase(), duration.getSeconds());
			log.info("duration.getNano : {}".toUpperCase(), duration.getNano());
			System.out.println();
		}
		
	}
	
}
