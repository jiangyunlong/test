package com.jyl.test.jdk.map;

import java.util.HashMap;
import java.util.Map;

public class MapCalculate {
	
	public static void main(String[] args) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", 0);

		System.out.println(Integer.valueOf(map.get("count").toString()) + 1);
	}

}
