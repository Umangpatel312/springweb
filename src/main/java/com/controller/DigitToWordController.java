package com.controller;

import java.util.Map;

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

	@PostMapping(value = "/digits/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> digitToWord(@RequestBody Digit digit) {
		// int num=Integer.parseInt(digit);
		int flag = digitService.digitToWordConvert(digit.getDigit());
		if (flag == 1)
			return new ResponseEntity<>("successfully added...", HttpStatus.CREATED);
		else if (flag == 0) {
			return new ResponseEntity<>("data already exist...", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("something might be wrong...", HttpStatus.ALREADY_REPORTED);
		}
	}

	@GetMapping(value = "/digits")
	public Map<Integer, String> getAllDigit() {
		return digitService.getMap();
	}

	@GetMapping(value = "/digits/{digit}")
	public Object getDigit(@PathVariable int digit) {
		DigitWord bean = digitService.getDigit(digit);
		if (bean == null) {
			return new ResponseEntity<>("data not exist...", HttpStatus.BAD_REQUEST);
		}
		return bean;
	}

	@PutMapping(value = "/digits", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object updateDigit(@RequestBody TwoDigit twodigit) {
		digitService.updateDigit(twodigit.getDigit1(), twodigit.getDigit2());
		return new ResponseEntity<String>("updated successful...", HttpStatus.OK);
	}

	@DeleteMapping(value = "/digits/{digit}")
	public Object deleteDigit(@PathVariable int digit) {
		boolean operationFlag = digitService.deleteDigit(digit);
		if (operationFlag)
			return new ResponseEntity<>( HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
