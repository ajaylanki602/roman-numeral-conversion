package com.ajaykumarl.romannumeral.service;

import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ajaykumarl.romannumeral.RomanNumeralConstants;
import com.ajaykumarl.romannumeral.errorHandling.NumberIsZeroException;
import com.ajaykumarl.romannumeral.errorHandling.NumberNotInRangeException;
import com.ajaykumarl.romannumeral.model.RomanNumber;


/**
 * @author ajayl
 *
 */
@Service
public class RomanNumeralConversionService {

	Logger logger = LoggerFactory.getLogger(RomanNumeralConversionService.class);


	
	/**
	 * service method to convert integer to roman number
	 * @param inputNumber
	 * @return RomanNumber object
	 */
	public RomanNumber toRomanNumber(Integer inputNumber) {
		
		logger.info("Inside RomanNumeralConversionService");
		checkIfNumberInRange(inputNumber);
		RomanNumber romanNumber = new RomanNumber();
		romanNumber.setInput(inputNumber);
		StringBuilder result = new StringBuilder();
		
		while (inputNumber != 0) {
			
			Entry<Integer, String> entry = RomanNumeralConstants.ROMAN_NUMERALS.floorEntry(inputNumber);
			result.append(entry.getValue());
			inputNumber -= entry.getKey();

		}
		
		romanNumber.setOutput(result.toString());
		logger.debug("{} created ", romanNumber);
		return romanNumber;
	}

	
	/**
	 * Utility method to check if the input provided is within the range supported by the API
	 * @param number
	 */
	private void checkIfNumberInRange(int number) {
		
		if (number == 0) {
			throw new NumberIsZeroException("Query parameter value is 0,"
					+ "Roman numerals start to count from one and had no symbol to represent 0.");
		}
		if (number < RomanNumeralConstants.MIN_INTEGER || number > RomanNumeralConstants.MAX_INTEGER) {
			throw new NumberNotInRangeException("The number entered must be between "
					+ RomanNumeralConstants.MIN_INTEGER + " and " + RomanNumeralConstants.MAX_INTEGER);
		}
	}

}
	

