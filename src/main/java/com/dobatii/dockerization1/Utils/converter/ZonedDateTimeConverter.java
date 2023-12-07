package com.dobatii.dockerization1.Utils.converter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * Convert date in String format into ZonedDateTime If the source is null, it
 * return ZonedDateTime at now
 * 
 * @author juoud1
 * @version 1.0
 * @date 05-12-2023
 * 
 */
public class ZonedDateTimeConverter implements Converter<String, ZonedDateTime> {
	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.nnn VV");

	@Override
	public ZonedDateTime convert(String source) {

		return StringUtils.isBlank(source) ? ZonedDateTime.now() : ZonedDateTime.parse(source, dateFormat);
	}

}
