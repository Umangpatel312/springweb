package com.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.DigitWord;

@Service
public class DigitToWordConvert {
	private static String singleDigitstr[] = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
			"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
	private static String twoDigitStr[] = { "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety" };
	private static String postfixes[] = { "hundred", "thousand", "lac", "crore" };
	
	private Map<Integer, String> map;
	private DigitWord digitWordBean;
	@Autowired
	public DigitToWordConvert(Map<Integer, String> map,DigitWord digitWordBean) {
		// TODO Auto-generated constructor stub
		this.map = map;
		this.digitWordBean=digitWordBean;
	}

	public Map<Integer, String> getMap() {
		return this.map;
	}
	
	public DigitWord digitToWordConvert(int number) {
		String numberInStr=String.valueOf(number);
		//if number greater than crore it will not correct input for this program so return null
		//int number=parseInteger(numberInStr);
		//System.out.println(number);
		if(number<0 || numberInStr.length()>9 || map.containsKey(number))
			return null;
		DigitWord digitWordBean=null;
		
		int digit1 = number;
		int count=numberInStr.length();
		int digits[]=digitExtraciton(number,count);;
		//if number is zero
		//other go into else part
		if (digits.length == 0) {
			digitWordBean=createDigitWordBean(0, "zero");
			map.put(digit1, "zero");
		} 
		else {
			String result=convertDigitToString(digits);
			digitWordBean=createDigitWordBean(digit1,result);
			map.put(digit1, result);
		}
		return digitWordBean;
	}
	private DigitWord createDigitWordBean(int number,String numberInString) {
		//DigitWord digitWordBean=new DigitWord();
		digitWordBean.setDigit(number);
		digitWordBean.setWord(numberInString);
		return digitWordBean;
	}
	//extract the single digit from number
	public int[] digitExtraciton(int number,int count) {
		//int count=0;
		int i=0;
		int digits[] = new int[count];
		while (number != 0) {
			digits[i++] = number % 10;
			number = number / 10;
		}
		return digits;
	}
	
	//conversion of given to appropriate word
	private String convertDigitToString(int digits[]) {
		
		int count=digits.length;
		String result[] = new String[count];
		String postfix = null;
		int countForPostfix = -2, index = 0;
		for (int i = 0; i < count; i++, countForPostfix++) {
			if (digits[i] != 0) {
				if (countForPostfix == 0) {
					postfix = postfixes[index++];
				} 
				else if (countForPostfix >= 0) {
					if (countForPostfix % 2 == 1) {
						postfix = postfixes[index];
						index++;
					}
				}
				if (i == 0) {
					result[i] = singleDigitstr[digits[i] - 1];
				} 
				else if (i == 1 || i == 4 || i == 6 || i == 8) {
					if (digits[i] < 2) {
						result[i] = singleDigitstr[10 * digits[i] + digits[i - 1] - 1] + " "
								+ (postfix == null ? "" : postfix);
						if (i - 2 >= 0) {
							result[i] += " " + result[i - 2];
						}
						result[i - 1] = null;
					} 
					else {
						if (i < 4) {
							result[i] = twoDigitStr[digits[i] - 2] + " " + (postfix == null ? "" : postfix)
									+ (result[i - 1] == null ? "" : result[i - 1]);
						} else {
							result[i] = twoDigitStr[digits[i] - 2] + " "
									+ (result[i - 1] == null ? "" : result[i - 1]);
						}
					}
				} 
				else {
					result[i] = singleDigitstr[digits[i] - 1] + " " + (postfix == null ? "" : postfix + " ")
							+ (result[i - 1] == null ? "" : result[i - 1]);

				}
			}
		}
		return result[count-1];
	}
	
	
	public DigitWord getDigit(int number) {
		//int number=parseInteger(numberInStr);
		String numberInStr=String.valueOf(number);
		if (number<0 || numberInStr.length()>9 || !map.containsKey(number)) {
			return null;
		}
		DigitWord digitWordBean = new DigitWord();
		digitWordBean.setDigit(number);
		digitWordBean.setWord(map.get(number));
		return digitWordBean;
	}

	public boolean deleteDigit(int number) {
		//int number=parseInteger(numberInStr);
		//boolean operationFlag = false;
		String numberInStr=String.valueOf(number);
		if (number<0 || numberInStr.length()>9 || !map.containsKey(number)) {
			return false;
		} 
		map.remove(number);
		return true;
		
	}

	public boolean updateDigit(int number1, int number2) {
//		int number1=parseInteger(numberInStr1);
//		int number2=parseInteger(numberInStr2);
		String numberInStr1=String.valueOf(number1);
		String numberInStr2=String.valueOf(number2);
		boolean operationFlag=false;
		if(number1<0 || number2<0 || numberInStr1.length()>9 || numberInStr2.length()>9 || !map.containsKey(number1)) {
			return false;
		}
		map.remove(number1);
		digitToWordConvert(number2);
		return true;
	}
}
