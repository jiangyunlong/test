package com.jyl.test.jdk.string;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestReplace {
	
	@Test
	public void testt(){
		
		String str = "skgbds#kbgdsk%gbdskg#bdsjkbgdsjkg%bsdjk#gdsbkvdjf%vhbfgbsk#jgbdsjkfgbdgbdg";
		
		List<String> list = new ArrayList<String>();
		list.add("#");list.add("%");
		
		for(String key : list){
			if(str.indexOf(key) != -1){
				str = str.replaceAll(key, "<font color=\"red\">"+key+"</font>");
			}
		}
		System.out.println(str);
	}

}
