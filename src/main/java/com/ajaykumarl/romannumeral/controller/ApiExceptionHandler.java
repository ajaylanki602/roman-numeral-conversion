package com.ajaykumarl.romannumeral.controller;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ajaykumarl.romannumeral.errorHandling.ApiExceptionResponse;
import com.ajaykumarl.romannumeral.errorHandling.NumberIsZeroException;
import com.ajaykumarl.romannumeral.errorHandling.NumberNotInRangeException;

/**
 * @author ajayl
 *
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	/**
	 * Exception handler to handle NumberFormatException
	 */
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<Object> handleNumberFormatException(NumberFormatException ex) {
		
		ApiExceptionResponse response = ApiExceptionResponse.of(HttpStatus.BAD_REQUEST, "Number format error occured. Input entered is not a valid number");
		logger.error("Number is not an Integer", ex);
		return createResponseEntity(response);
		

	}
	
	/**
	 * Exception handler to handle ConstraintViolationException
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	  public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
		
	    ApiExceptionResponse response = ApiExceptionResponse.of(HttpStatus.BAD_REQUEST, "Query param cannot be empty");
		logger.error("Input should not be blank or empty", ex);
	    return createResponseEntity(response);
	    
	  }
    
    @ExceptionHandler(NumberIsZeroException.class)
    public final ResponseEntity<Object> handleNumberIsZeroException(NumberIsZeroException ex) {

        ApiExceptionResponse response = ApiExceptionResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
        logger.error("Input cannot be zero.", ex);
        return createResponseEntity(response);
    }
    
    /**
	 * Exception handler to handle NumberNotInRangeException
	 */
    @ExceptionHandler(NumberNotInRangeException.class)
    public final ResponseEntity<Object> handleNumberNotInRangeException(NumberNotInRangeException ex) {

        ApiExceptionResponse response = ApiExceptionResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
        logger.error("Input should be between 1 and 3999", ex);
        return createResponseEntity(response);
    }
    
    private ResponseEntity<Object> createResponseEntity(ApiExceptionResponse response) {
        return new ResponseEntity<>(response, response.getStatus());
    }

}
