package com.dobatii.dockerization1.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.WebExchangeBindException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * Main component to handle exception in controllers
 * 
 * @author juoud1
 * @version 1.0
 * @date 22-11-2023
 * 
 */

@ControllerAdvice
@Slf4j
public class GlobalErrorHandler {
	private final MessageSource messageSource;

	public GlobalErrorHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(WebExchangeBindException.class)
	@ResponseBody
	public ResponseEntity<Flux<ErrorMessage>> handleValidationErrors(WebExchangeBindException exception) {
		log.error("{} ERRORS DETECTED!", exception.getFieldErrors().size());
		/*
		 * var currentErrors = exception.getBindingResult().getAllErrors().stream()
		 * .map(DefaultMessageSourceResolvable::getDefaultMessage)// //.map(e ->
		 * to).collect(Collectors.toList()); log.error("ERRORS : {}", currentErrors);
		 */
		log.error("DEFAULT LOCALE : {}", Locale.getDefault());
		return ResponseEntity.badRequest().body(Flux.fromIterable(exception.getFieldErrors()) //
				.map(f -> toErrorMessage(f, Locale.getDefault())));
	}

	private ErrorMessage toErrorMessage(FieldError fieldError, Locale locale) {
		log.error("ERROR, {} : {}", fieldError.getField(), fieldError.getDefaultMessage());
		return new ErrorMessage(fieldError.getField(),
				messageSource.getMessage(fieldError.getDefaultMessage(), fieldError.getArguments(), locale));
	}

	@NoArgsConstructor
	@AllArgsConstructor
	@Setter
	@Getter
	private class ErrorMessage {
		private String field;
		private String message;

	}
}
