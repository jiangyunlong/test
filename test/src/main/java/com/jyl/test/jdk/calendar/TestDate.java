package com.jyl.test.jdk.calendar;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

public class TestDate {

	@Test
	public void testt() throws ParseException{
		/*Date date = new Date(System.currentTimeMillis()-7*24*60*60*1000);
		System.out.println(date);*/
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date()));*/
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date eDate = sdf.parse(sdf.format(new Date()));
		
		//Date eDate = DateUtils.toDate("", null);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(eDate);
		calendar.add(Calendar.DATE, -1);
		System.out.println(calendar.getTime());*/
		
		Date date = new Date();
		System.out.println(date.getTime());
		
		
	}
	
}
