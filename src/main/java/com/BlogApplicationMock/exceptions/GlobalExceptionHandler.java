package com.BlogApplicationMock.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BlogException.class)
	public ResponseEntity<MyErrorDetails> BlogExceptionHandler(BlogException be, WebRequest web) {
		MyErrorDetails myerr = new MyErrorDetails();
		myerr.setTimestamp(LocalDateTime.now());
		myerr.setMessage(be.getMessage());
		myerr.setDetails(web.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(myerr, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CommentException.class)
	public ResponseEntity<MyErrorDetails> CommentExceptionHandler(CommentException be, WebRequest web) {
		MyErrorDetails myerr = new MyErrorDetails();
		myerr.setTimestamp(LocalDateTime.now());
		myerr.setMessage(be.getMessage());
		myerr.setDetails(web.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(myerr, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> UserExceptionHandler(UserException be, WebRequest web) {
		MyErrorDetails myerr = new MyErrorDetails();
		myerr.setTimestamp(LocalDateTime.now());
		myerr.setMessage(be.getMessage());
		myerr.setDetails(web.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(myerr, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> LoginExceptionHandler(LoginException be, WebRequest web) {
		MyErrorDetails myerr = new MyErrorDetails();
		myerr.setTimestamp(LocalDateTime.now());
		myerr.setMessage(be.getMessage());
		myerr.setDetails(web.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(myerr, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnauthorisedException.class)
	public ResponseEntity<MyErrorDetails> UnauthorisedExceptionHandler(UnauthorisedException be, WebRequest web) {
		MyErrorDetails myerr = new MyErrorDetails();
		myerr.setTimestamp(LocalDateTime.now());
		myerr.setMessage(be.getMessage());
		myerr.setDetails(web.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(myerr, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> myAnyExpHandler(Exception anyException, WebRequest wRequest) {

		MyErrorDetails err = new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(anyException.getMessage());
		err.setDetails(wRequest.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> mynotFoundHandler(NoHandlerFoundException nfe, WebRequest req) {

		MyErrorDetails err = new MyErrorDetails(nfe.getMessage(), req.getDescription(false), LocalDateTime.now());

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}
}
