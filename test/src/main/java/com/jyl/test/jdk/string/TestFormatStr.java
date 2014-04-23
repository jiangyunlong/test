package com.jyl.test.jdk.string;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestFormatStr {
	
	@Test
	public void testt(){
		String str = "Hello,{username},welcome to {location}!";

		Map<String,String> replaceMap = new HashMap<String,String>();
		replaceMap.put("{username}", "Kobe Bryant");
		replaceMap.put("{location}", "GuangZhou");
		str = formatStr(str, replaceMap);
		System.out.println("str: " + str);
	}
	
	public String formatStr(String str, Map<String,String> replacements){
		for(String target : replacements.keySet()){
			String replacement = replacements.get(target);
			if(replacement == null){
				continue;
			}
			str = str.replace(target, replacement);
		}
		return str;
	}

}
