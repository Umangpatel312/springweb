package com.controller;

import java.net.http.HttpResponse;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bean.Digit;
import com.bean.DigitWord;
import com.bean.TwoDigit;
import com.service.DigitToWordConvert;

@RestController
public class DigitToWordController {

	@Autowired
	DigitToWordConvert digitService;

	@PostMapping(value = "/digits/", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public DigitWord  digitToWord(@RequestBody Digit digit,HttpServletResponse response) {
		System.out.println("digit:"+digit);
		System.out.println(digit.getDigit());
		System.out.println(digitService);
		DigitWord digitWordBean = digitService.digitToWordConvert(digit.getDigit());
		if (digitWordBean==null) {
			
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;	
		}
		response.setStatus(HttpServletResponse.SC_CREATED);
		return digitWordBean;
	}

	@GetMapping(value = "/digits",produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<Integer, String> getAllDigit(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_OK);
		return digitService.getMap();
	}

	@GetMapping(value = "/digits/{digit}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getDigit(@PathVariable int digit,HttpServletResponse response) {
		System.out.println(digit);
		System.out.println(digitService.getMap().size());
		DigitWord bean = digitService.getDigit(digit);
		if (bean == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return new ResponseEntity<>("data not exist...", HttpStatus.BAD_REQUEST);
		}
		response.setStatus(HttpServletResponse.SC_OK);
		return bean;
	}

	@PutMapping(value = "/digits", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateDigit(@RequestBody TwoDigit twodigit,HttpServletResponse response) {
		digitService.updateDigit(twodigit.getDigit1(), twodigit.getDigit2());
		response.setStatus(HttpServletResponse.SC_OK);
		return "updated successful...";
	}

	@DeleteMapping(value = "/digits/{digit}")
	public void deleteDigit(@PathVariable int digit,HttpServletResponse response) {
		boolean operationFlag = digitService.deleteDigit(digit);
		if (operationFlag)
			response.setStatus(HttpServletResponse.SC_OK);
		else
			response.setStatus(HttpServletResponse.SC_OK);
	}
}
