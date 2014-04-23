package com.jyl.test.jdk.list;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestListRemove {

	@Test
	public void testt(){
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("a");
		list.add("b");
		list.add("c");
		
		System.out.println(list);
		
		/*List<String> tmpList = new ArrayList<String>();
		for(String str : list){
			if("a".equals(str)){
				tmpList.add(str);
			}
		}
		list.removeAll(tmpList);*/
		
		System.out.println(list);
	}
}
