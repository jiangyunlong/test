package com.jyl.test.jdk.string;

import org.junit.Test;

public class TestSubstring {

	@Test
	public void testtt(){
		/*StringBuffer sb = new StringBuffer();
		sb.append("123,");
		sb.append("456,");
		if(sb.length() > 1){
			System.out.println(sb.substring(0, sb.length()-1));
		}*/
		
		String str = "2014-09-23T00:00:00";
		System.out.println(str.substring(0, 7));
	}
}
