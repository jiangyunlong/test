package com.jyl.test.jdk.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegular {
	
	public static void main(String[] args) {
		
		/*String str = "sdfhf<a href=\"http://www.baidu.com/\">test</a>zdhgdtestfxhzsd<a href=\"http://ww" +
				"w.baidu.com/\">sdftest23</a>gzdxfg";
		
		Pattern pattern = Pattern.compile("<a [^>]*>[^<]*test([^<]*)</a>",Pattern.DOTALL);
		//Pattern pattern = Pattern.compile("([^>]*)test([^<]*)");
		
		Matcher matcher = pattern.matcher(str);
		
		while(matcher.find()){
			System.out.println(matcher.group());
		}*/
		
		String str = "42022219882301243X";
		String regex = "(^\\d{15}$)|(^\\d{17}([0-9]|X)$)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		boolean b = matcher.matches();
		System.out.println(b);
	}
}