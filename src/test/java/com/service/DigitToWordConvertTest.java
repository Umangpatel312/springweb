package com.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.junit.Test;

import com.bean.DigitWord;

public class DigitToWordConvertTest {
	@Test
	public void chechMapIsNotNull() {
		assertNotNull(new DigitToWordConvert(new HashMap<Integer, String>(),new DigitWord()).getMap());
	}
	
	/*
	 * Check covertDigitToStringReturnCorrectString() method
	 */
	
	@Test
	public void digitToWordConvertTestCorrectBean() {
		assertEquals(new DigitWord(12,"twelve "), new DigitToWordConvert(new HashMap<Integer, String>(),new DigitWord()).digitToWordConvert(12));
	}
	
	@Test
	public void digitToWordConvertTestForNegativeValue() {
		assertEquals(null, new DigitToWordConvert(new HashMap<Integer, String>(),new DigitWord()).digitToWordConvert(-12));
	}
	
	
}
