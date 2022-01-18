package com.ajaykumarl.romannumeral.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.ajaykumarl.romannumeral.RomanNumeralConstants;
import com.ajaykumarl.romannumeral.errorHandling.NumberIsZeroException;
import com.ajaykumarl.romannumeral.errorHandling.NumberNotInRangeException;
import com.ajaykumarl.romannumeral.model.RomanNumber;

@ExtendWith(MockitoExtension.class)
public class RomanNumeralServiceTest {
	
	@InjectMocks
	private RomanNumeralConversionService romanNumeralConversionService = new RomanNumeralConversionService();
	
	@Test
	public void whenInputIsValid_thenReturnRomanNumber() {	
		
		RomanNumber romNum = romanNumeralConversionService.toRomanNumber(25);
		assertNotNull(romNum);
		Assertions.assertEquals(romNum.getOutput(), "XXV");
		
		
	}
	
	@Test
	public void WhenInputIsOutsideRange_testExceptionIsThrown() {
		Throwable exception = Assertions.assertThrows(NumberNotInRangeException.class, () -> {
	        throw new NumberNotInRangeException("The number entered must be between " + RomanNumeralConstants.MIN_INTEGER
					+ " and " + RomanNumeralConstants.MAX_INTEGER);
		});
		try {
			romanNumeralConversionService.toRomanNumber(4500);
		}
		catch(NumberNotInRangeException ex) {
			Assertions.assertEquals(ex.getMessage(), exception.getMessage());
		}
		
	}
	
	@Test
	public void WhenInputIsZero_testExceptionIsThrown() {
		Throwable exception = Assertions.assertThrows(NumberIsZeroException.class, () -> {
	        throw new NumberIsZeroException("Query parameter value is 0,Roman numerals start to count from one and had no symbol to represent 0.");
		});
		try {
			romanNumeralConversionService.toRomanNumber(0);
		}
		catch(NumberIsZeroException ex) {
			Assertions.assertEquals(ex.getMessage(), exception.getMessage());
		}
		
	}


}
