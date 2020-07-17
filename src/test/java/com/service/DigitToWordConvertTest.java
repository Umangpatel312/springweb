package com.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DigitToWordConvertTest {
	@Test
	void chechMapIsNotNull() {
		assertNotNull(new DigitToWordConvert(new HashMap<Integer, String>()).getMap());
	}
	
}
