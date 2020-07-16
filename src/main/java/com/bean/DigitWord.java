package com.bean;

import org.springframework.stereotype.Component;

@Component
public class DigitWord {
	private int digit;
	private String word;
	
	public void setDigit(int digit) {
		this.digit = digit;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getDigit() {
		return digit;
	}
	public String getWord() {
		return word;
	}
	
}
