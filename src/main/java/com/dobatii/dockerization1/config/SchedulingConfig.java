package com.dobatii.dockerization1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.dobatii.dockerization1.utils.schedule.TeritorySchedule;

/**
 * Enabling scheduling process support 
 * 
 * @author 9386-2142 Qc inc
 * @version 1.0
 * 2019-12-17
 * 
 */

@Configuration
@EnableScheduling
public class SchedulingConfig implements SchedulingConfigurer{
	
	@Autowired
	private TeritorySchedule teritorySchedule;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.addFixedRateTask(() -> teritorySchedule.printCurrentTeritoriesSize(), 1978);
	}

}
