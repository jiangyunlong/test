package com.jyl.test.jdk.text;

import java.text.DecimalFormat;

public class TestDecimalFormat {

	public static void main(String[] args) {
		
		DecimalFormat decimalFormat = new DecimalFormat("000000");
		String str = decimalFormat.format(24);
		System.out.println(str);
	}
	
}
