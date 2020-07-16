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
		// int n=sc.nextInt();
		// ApplicationContext context=new
		// ClassPathXmlApplicationContext("/WEB-INF/spring.xml");
		// DigitWord bean=context.getBean(DigitWord.class);
		int flag = -1;
		DigitWord bean = new DigitWord();
		if (map.containsKey(n)) {
			return 0;
		}
		int digit1 = n;
		String s[] = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven",
				"twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
		String s2[] = { "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety" };
		String s3[] = { "hundred", "thousand", "lac", "crore" };
		int cnt = 0;
		int digit[] = new int[20];
		while (n != 0) {
			digit[cnt++] = n % 10;
			n = n / 10;
		}
		if (cnt == 0) {
			// System.out.println("zero");
			map.put(digit1, "zero");
			flag = 1;
		} else {
			String result[] = new String[cnt];
			String postfix = null;
			// System.out.println(cnt);
			int p = -2, index = 0;
			for (int i = 0; i < cnt; i++, p++) {
				if (digit[i] != 0) {
					// postfix=null;
					if (p == 0) {
						postfix = s3[index++];
					} else if (p >= 0) {
						if (p % 2 == 1) {
							postfix = s3[index];
							index++;
						}
					}
					// System.out.println("p: "+p+"i:"+index+" posix "+postfix);
					if (i == 0) {
						result[i] = s[digit[i] - 1] + " ";
					} else if (i == 1 || i == 4 || i == 6 || i == 8) {
						if (digit[i] < 2) {
							result[i] = s[10 * digit[i] + digit[i - 1] - 1] + " " + (postfix == null ? "" : postfix);
							if (i - 2 >= 0) {
								result[i] += " " + result[i - 2];
							}
							result[i - 1] = null;
						} else {
							if (i < 4) {
								result[i] = s2[digit[i] - 2] + " " + (postfix == null ? "" : postfix)
										+ (result[i - 1] == null ? "" : result[i - 1]);
							} else {
								result[i] = s2[digit[i] - 2] + " " + (result[i - 1] == null ? "" : result[i - 1]);
							}
						}
					} else {
						result[i] = s[digit[i] - 1] + " " + (postfix == null ? "" : postfix + " ")
								+ (result[i - 1] == null ? "" : result[i - 1]);

					}
				}
			}
			// System.out.println(result[cnt-1]);
			map.put(digit1, result[cnt - 1]);
			// bean.setDigit(digit1);
			// bean.setWord(result[cnt - 1]);

			flag = 1;
		}
		return flag;
	}

	public DigitWord getDigit(int number) {

		if (!map.containsKey(number)) {
			return null;
		}
		DigitWord bean = new DigitWord();
		bean.setDigit(number);
		bean.setWord(map.get(number));
		return bean;
	}

	public int deleteDigit(int number) {
		int flag = 0;
		if (map.containsKey(number)) {
			map.remove(number);
			flag = 1;
		} else {
			flag = -1;
		}
		return flag;
	}

	public void updateDigit(int number1, int number2) {
		if (map.containsKey(number1)) {
			map.remove(number1);
		}
		digitToWordConvert(number2);
	}
}
