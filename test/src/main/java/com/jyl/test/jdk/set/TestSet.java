package com.jyl.test.jdk.set;

import java.util.HashSet;
import java.util.Set;

public class TestSet {
	
	public static void main(String[] args) {
		
		/*Set<String> set1 = new HashSet<String>();
		set1.add("a");
		set1.add("b");
		set1.add("c");
		System.out.println(set1);
		
		Set<String> set2 = new HashSet<String>();
		//set2.add("c");
		set2.add("d");
		set2.add("e");
		System.out.println(set2);
		
		set1.removeAll(set2);
		System.out.println(set1);*/
		
		Set<String> set = new HashSet<String>();
		for(int i=0;i<3;i++){
			String str = new String("String");
			set.add(str);
		}
		for(String user : set){
			System.out.println(user);
		}
	}

}
