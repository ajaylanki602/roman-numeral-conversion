package com.ajaykumarl.romannumeral.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.ajaykumarl.romannumeral.model.RomanNumber;
import com.ajaykumarl.romannumeral.service.RomanNumeralConversionService;

@SpringBootTest
@AutoConfigureMockMvc
public class RomanNumeralConversionControllerTest {
	


    @MockBean
    private RomanNumeralConversionService romanNumeralConversionService;

    @Autowired
    private MockMvc mockMvc;

	
	  @Test 
	  void whenValidNumberSent_thenResponseShouldBeSent() throws Exception {
	  
	  int input = 188;
	  RomanNumber expectedResponse = createRomanNumeralResponse(188);
	  System.out.println("ExpectedResponse:"+ expectedResponse); 
	  Mockito.doReturn(expectedResponse).when(romanNumeralConversionService).toRomanNumber(input);
	  
	  mockMvc.perform(get("/romannumeral")
			  .queryParam("query", "188"))
	  		  .andExpect(status().isOk()) 
	  		  .andExpect(jsonPath("$.input",equalTo(expectedResponse.getInput()))) 
	  		  .andExpect(jsonPath("$.output",equalTo(expectedResponse.getOutput()))); 
	  }
	  
	  @Test 
	  void whenInvalidNumberSent_thenErrorMessageIsSent() throws Exception {
		   
		  mockMvc.perform(get("/romannumeral")
				  .queryParam("query", "-a123"))
		  		  .andExpect(status().isBadRequest())
		  		  .andExpect(jsonPath("$.message").value("Number format error occured. Input entered is not a valid number"));
		  }
	  
	  @Test 
	  void whenInputisBlankOrEmpty_thenErrorMessageIsSent() throws Exception {
		   
		  mockMvc.perform(get("/romannumeral")
				  .queryParam("query", ""))
		  		  .andExpect(status().isBadRequest())
		  		  .andExpect(jsonPath("$.message").value("Query param cannot be empty"));
		  }


	private RomanNumber createRomanNumeralResponse(int num) {
		// TODO Auto-generated method stub
		return new RomanNumeralConversionService().toRomanNumber(num);
	}
	 

}
