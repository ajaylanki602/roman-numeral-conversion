package com.ajaykumarl.romannumeral.errorHandling;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Value;

/**
 * @author ajayl
 *
 */
@Value
public class ApiExceptionResponse {

	private LocalDateTime timestamp;

    private HttpStatus status;

    private String message;


	public static ApiExceptionResponse of(HttpStatus httpStatus, String message) {

        return new ApiExceptionResponse(LocalDateTime.now(), httpStatus, message);
    }
}
