package com.jyl.test.jdk.calendar;

import java.util.Date;

import org.junit.Test;

public class TestDate {

	@Test
	public void testt(){
		Date date = new Date(System.currentTimeMillis()-7*24*60*60*1000);
		System.out.println(date);
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date()));*/
		
	}
	
}
