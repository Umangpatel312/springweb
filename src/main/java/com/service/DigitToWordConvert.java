package com.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bean.DigitWord;

@Service
public class DigitToWordConvert {

	private Map<Integer, String> map;

	@Autowired
	public DigitToWordConvert(Map<Integer, String> map) {
		// TODO Auto-generated constructor stub
		this.map = map;
	}

	public Map<Integer, String> getMap() {
		return this.map;
	}

	public int digitToWordConvert(int n) {

		int operationFlag = -1;
		if (map.containsKey(n)) {
			return 0;
		}
		int digit1 = n;
		String singleDigitstr[] = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
				"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
		String twoDigitStr[] = { "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety" };
		String postfixes[] = { "hundred", "thousand", "lac", "crore" };
		int count = 0;
		int digits[] = new int[20];
		while (n != 0) {
			digits[count++] = n % 10;
			n = n / 10;
		}
		if (count == 0) {
			map.put(digit1, "zero");
			operationFlag = 1;
		} else {
			String result[] = new String[count];
			String postfix = null;
			// System.out.println(count);
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
			// System.out.println(result[count-1]);
			map.put(digit1, result[count - 1]);
			// digitWordBean.setDigit(digit1);
			// digitWordBean.setWord(result[count - 1]);

			operationFlag = 1;
		}
		return operationFlag;
	}

	public DigitWord getDigit(int number) {

		if (!map.containsKey(number)) {
			return null;
		}
		DigitWord digitWordBean = new DigitWord();
		digitWordBean.setDigit(number);
		digitWordBean.setWord(map.get(number));
		return digitWordBean;
	}

	public boolean deleteDigit(int number) {
		boolean operationFlag = false;
		if (map.containsKey(number)) {
			map.remove(number);
			operationFlag = true;
		} else {
			operationFlag = false;
		}
		return operationFlag;
	}

	public void updateDigit(int number1, int number2) {
		if (map.containsKey(number1)) {
			map.remove(number1);
		}
		digitToWordConvert(number2);
	}
}
