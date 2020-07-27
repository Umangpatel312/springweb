package com.bean;

import org.springframework.stereotype.Component;

@Component
public class DigitWord {
	private int digit;
	private String word;
	public DigitWord() {}
	public DigitWord(int digit, String word) {
		super();
		this.digit = digit;
		this.word = word;
		//System.out.println(word+"..");
	}
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DigitWord other = (DigitWord) obj;
		if (digit != other.digit)
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}
	
}
