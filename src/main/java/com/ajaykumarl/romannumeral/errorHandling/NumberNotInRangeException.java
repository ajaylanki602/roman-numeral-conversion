package com.ajaykumarl.romannumeral.errorHandling;

/**
 * @author ajayl
 *
 */
public class NumberNotInRangeException extends RuntimeException {
	
	private static final long serialVersionUID = -6206960627597278285L;

	public NumberNotInRangeException() {
        super();
    }

    public NumberNotInRangeException(String message) {
        super(message);
    }

    public NumberNotInRangeException(String message, Throwable cause) {
        super(message, cause);
    }

}
