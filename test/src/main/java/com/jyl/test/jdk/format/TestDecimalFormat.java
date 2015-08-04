package com.jyl.test.jdk.format;

import java.text.DecimalFormat;

public class TestDecimalFormat {
	
	public static void main(String[] args) {
		
		DecimalFormat decimalFormat = new DecimalFormat("00000");
		
		String format = decimalFormat.format(123456789);
		
		System.out.println(format);
	}

}
