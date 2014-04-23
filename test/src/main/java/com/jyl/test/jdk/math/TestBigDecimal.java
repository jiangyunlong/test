package com.jyl.test.jdk.math;

import java.math.BigDecimal;

public class TestBigDecimal {
	
	public static void main(String[] args) {
		
		double i = 30;
		
		BigDecimal b = new BigDecimal(i/100);
		
		System.out.println(b);
		
	}

}
