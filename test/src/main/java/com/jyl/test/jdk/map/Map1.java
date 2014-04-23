package com.jyl.test.jdk.map;
import java.util.HashMap;
import java.util.Map;


public class Map1 {
	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		map.put(220180, null);
		map.put(220181, "马先生");
		
		System.out.println("get()方法的返回结果：");
		System.out.print("------ " + map.get(220180));
		System.out.print("    " + map.get(220181));
		System.out.println("    " + map.get(220182));
		System.out.println("containsKey()方法的返回结果：");
		System.out.print("------ " + map.containsKey(220180));
		System.out.print("    " + map.containsKey(220181));
		System.out.println("    " + map.containsKey(220182));
	}
}
