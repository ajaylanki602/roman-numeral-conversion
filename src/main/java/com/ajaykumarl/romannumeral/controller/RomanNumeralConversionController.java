package com.ajaykumarl.romannumeral.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ajaykumarl.romannumeral.model.RomanNumber;
import com.ajaykumarl.romannumeral.service.RomanNumeralConversionService;


/**
 * @author ajayl
 *
 */
@RestController
@Validated
public class RomanNumeralConversionController {

	Logger logger = LoggerFactory.getLogger(RomanNumeralConversionController.class);
	
	@Autowired
	private RomanNumeralConversionService romanNumeralConversionService;

	
	/**
	 * @param param - Queryparameter
	 * @return
	 */
	@GetMapping(value = "/romannumeral")
	public ResponseEntity<RomanNumber> toRomanNumber(@RequestParam("query")  @Valid @NotBlank String param){
		int number = Integer.parseInt(param);
		logger.debug("/romannumeral endpoint invoked with " + number);
		RomanNumber romanNumber = romanNumeralConversionService.toRomanNumber(number);
		logger.info("romanNumber conversion complete");
		return ResponseEntity.ok(romanNumber);
	}
	
}
