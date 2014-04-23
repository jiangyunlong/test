package com.jyl.test.jdk.string;


public class TestSplit {
	public static void main(String[] args) {
			
		/*String url = "http://www.p5w.net/kuaixun/201310/t20131017_339105.htm";
		
		String id = url.substring(url.indexOf("/t") + 1, url.indexOf(".htm"));
			
		System.out.println(id);*/
		
		String lzgh = "001002";
		String[] lzghArr = lzgh.split(",");
		for(String str : lzghArr){
			System.out.println(str);
		}
	}
}
