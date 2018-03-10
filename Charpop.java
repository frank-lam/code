package com.lk.example;

import java.util.Scanner;

/**
 * @author qing
 * @version 创建时间：2018年1月10日 下午9:16:21 类说明
 */
public class Charpop {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String b = null;
		while (scanner.hasNext()) {
			b = scanner.nextLine();
			char[] ch = b.toCharArray();
			System.out.println(pop(ch));
		}
	}

	public static String pop(char[] s) {
		for (int j = 0; j < s.length; j++) {
			for (int i = 1; i < s.length - j; i++) {
				char temp;
				if (Character.isLowerCase(s[i]) && Character.isUpperCase(s[i - 1])) {
					temp = s[i - 1];
					s[i - 1] = s[i];
					s[i] = temp;
				}
			}

		}
		return new String(s, 0, s.length);
	}
}
