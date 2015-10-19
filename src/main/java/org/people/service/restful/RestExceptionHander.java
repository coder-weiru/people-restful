package org.people.service.restful;

import javax.xml.bind.ValidationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHander {

	@ExceptionHandler({ ValidationException.class,
			HttpMessageNotReadableException.class })
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<RestError> handleRuntimeException(RuntimeException ex) {
		RestError restError = new RestError(HttpStatus.BAD_REQUEST,
				ex.getMessage());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<RestError>(restError, httpHeaders,
				HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<RestError> handleException(Exception ex) {
		RestError restError = new RestError(HttpStatus.INTERNAL_SERVER_ERROR,
				ex.getMessage());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<RestError>(restError, httpHeaders,
				HttpStatus.INTERNAL_SERVER_ERROR);

	}
}