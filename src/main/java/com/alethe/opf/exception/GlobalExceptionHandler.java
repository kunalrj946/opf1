package com.alethe.opf.exception;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

/**
 * Created by Kunal Kumar
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DataAccessResourceFailureException.class)
	public ResponseEntity<?> jdbcHandleDataAccessResourceFailureException(DataAccessResourceFailureException e,
			WebRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Database not connected !", Boolean.TRUE,
				request.getDescription(false), 100000);
		map.clear();
		map.put("content", errorDetails);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@ExceptionHandler(JpaSystemException.class)
	public ResponseEntity<?> jdbcHandleJpaSystemException(JpaSystemException e, WebRequest request) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.toString(), Boolean.TRUE,
				request.getDescription(false), 100002);
		map.clear();
		map.put("content", errorDetails);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		ErrorDetails errorDetails = new ErrorDetails(new Date(), "invalid username !", Boolean.TRUE,
				request.getDescription(false), 100003);
		map.clear();
		map.put("content", errorDetails);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "wrong password !", Boolean.TRUE,
				request.getDescription(false), 100004);
		map.clear();
		map.put("content", errorDetails);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<?> handleMyExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "token_expired", Boolean.TRUE,
				request.getDescription(false), 100005);
		map.clear();
		map.put("content", errorDetails);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> handleaccessDeniedException(AccessDeniedException ex, WebRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.toString(), Boolean.TRUE,
				request.getDescription(false), 100006);
		map.clear();
		map.put("content", errorDetails);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.toString(), Boolean.TRUE,
				request.getDescription(false), 100007);

		map.clear();
		map.put("content", errorDetails);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.toString(), Boolean.TRUE,
				request.getDescription(false), 100008);
		map.clear();
		map.put("content", errorDetails);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.toString(), Boolean.TRUE,
				request.getDescription(false), 100009);
		map.clear();
		map.put("content", errorDetails);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<?> handleMaxSizeException(MaxUploadSizeExceededException ex, WebRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.toString(), Boolean.TRUE,
				request.getDescription(false), 110001);
		map.clear();
		map.put("content", errorDetails);

		return ResponseEntity.status(HttpStatus.OK).body(map);
	}

	@ExceptionHandler(SQLException.class)
	public ResponseEntity<?> sqlHandle(SQLException e, WebRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), Boolean.TRUE,
				request.getDescription(false), 110002);
		map.clear();
		map.put("content", errorDetails);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@ExceptionHandler(MalformedJwtException.class)
	public ResponseEntity<?> handleMalFormedException(MalformedJwtException ex, WebRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), Boolean.TRUE, request.getDescription(false),
				110003);
		map.clear();
		map.put("content", errorDetails);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException e,
			HttpHeaders headers, HttpStatus status, WebRequest req) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ErrorDetails response = new ErrorDetails(new Date(), e.getMessage(), Boolean.TRUE, req.getDescription(false),
				110004);

		map.put("content", response);

		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
}
