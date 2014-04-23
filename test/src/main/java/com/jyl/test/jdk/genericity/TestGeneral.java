package com.jyl.test.jdk.genericity;


	public class TestGeneral {
		public static <T> T getAbstractData(T t){
			System.out.println(t.getClass());
			return t;
		}
		
		public static void main(String[] args) {
			getAbstractData(User.class);
		}

}
