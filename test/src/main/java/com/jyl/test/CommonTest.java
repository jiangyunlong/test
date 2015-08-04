package com.jyl.test;

public class CommonTest {
	
	public static void main(String[] args) {
		/*String str = "aaaaaaaaaaasssdddd";
		
		System.out.println(str.indexOf("ss"));*/
		
		String str = "123234345";
		String[] arr = str.split(",");
		for(String s : arr){
			System.out.println(s);
		}
	}

}
