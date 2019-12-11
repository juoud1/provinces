package com.dobatii.dockerization1;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.time.ZoneId;
import java.time.temporal.TemporalUnit;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;

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
		
	}
	
	/**
	 * Dummy test class for java.time.ZonedDateTime's method
	 * 
	 * @author 9386-2142 Qc inc
	 *
	 */
	private static class ZonedTimeDummyUtils{
		private static void workingWithZonedDateTime() {
			// List all available time zones
			ZoneId.getAvailableZoneIds().forEach(z -> System.out.println(z));
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
