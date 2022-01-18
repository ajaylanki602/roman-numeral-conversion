package com.ajaykumarl.romannumeral.errorHandling;

/**
 * @author ajayl
 *
 */
public class NumberIsZeroException extends RuntimeException {

	private static final long serialVersionUID = -4418174261330108811L;

	public NumberIsZeroException() {
        super();
    }

    public NumberIsZeroException(String message) {
        super(message);
    }

    public NumberIsZeroException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
