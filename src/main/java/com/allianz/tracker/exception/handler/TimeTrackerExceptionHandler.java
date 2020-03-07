package com.allianz.tracker.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.allianz.tracker.exception.TimeTrackerAppException;
import com.allianz.tracker.exception.TimeTrackerBadDataException;

@RestControllerAdvice
public class TimeTrackerExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(TimeTrackerAppException.class)
	public ResponseEntity<Object> handleTimeTrackerApp(TimeTrackerAppException e, HttpServletRequest request) {
		return new ResponseEntity<>(e.getUserMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(TimeTrackerBadDataException.class)
	public ResponseEntity<Object> handleTimeTrackerBadData(TimeTrackerBadDataException e, HttpServletRequest request) {
		return new ResponseEntity<>(e.getUserMessage(), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(e.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(ex.getMostSpecificCause().getMessage(), HttpStatus.BAD_REQUEST);

	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}
}
