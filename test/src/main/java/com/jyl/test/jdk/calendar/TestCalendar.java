package com.jyl.test.jdk.calendar;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class TestCalendar {
	
	@Test
	public void testt(){
		/*Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)-1);
		Date lastHourDate =  calendar.getTime();
		System.out.println(lastHourDate);
		
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		System.out.println(sdf.format(new Date()));
		
		Date time=null;
		try {
		   time= sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
		   e.printStackTrace();
		}
		System.out.println(time);*/
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date()));*/
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		System.out.println(calendar.get(Calendar.MONTH));
		System.out.println(calendar.get(Calendar.YEAR));
	}
}
