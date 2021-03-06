package org.adex.web.controllers;

import org.adex.utilities.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler
	public final ResponseEntity<Object> projectIdExceptionHandler(ProjectIdException ex, WebRequest request){
		return new ResponseEntity<Object>(new ProjectIdExceptionResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public final ResponseEntity<Object> projectNotFoundExceptionHandler(ProjectNotFoundException ex, WebRequest request){
		return new ResponseEntity<Object>(new ProjectNotFoundExceptionResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public final ResponseEntity<Object> taskNotFoundExceptionHandler(TaskNotFoundException ex, WebRequest request){
		return new ResponseEntity<Object>(new TaskNotFoundExceptionResponce(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public final ResponseEntity<Object> emailExistExceptionHandler(UsernameAlreadyExistsException ex, WebRequest request){
		return new ResponseEntity<Object>(new UsernameAlreadyExistsExceptionResponce(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

}
