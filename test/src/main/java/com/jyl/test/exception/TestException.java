package com.jyl.test.exception;

import org.junit.Test;

public class TestException {
	
	@Test
	public void tete(){
		
		try {
			TestException.sss();
		} catch (GeneralException e) {
			System.out.println("11111111111111111111");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("2222222222222");
	}
	
	public static void sss() throws GeneralException {
		
		TestException.xxx();
	}
	
	public static void xxx() throws GeneralException {
		throw new GeneralException("xxxxxxxxxxxxx");
	}
	
}
