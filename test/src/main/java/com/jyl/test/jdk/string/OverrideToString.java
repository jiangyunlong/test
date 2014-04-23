package com.jyl.test.jdk.string;

public class OverrideToString{
	
	private String sname;
	private int sage;
	
	public OverrideToString(String name,int age){
		sname = name;
		sage = age;
	}
	
	public String toString(){
		return "Name is : " + sname + "; Age is : " + sage;
	}
	
	public static void main(String[] args) {
		OverrideToString obj = new OverrideToString("kobe",24);
		System.out.println(obj.toString());
	}
}
