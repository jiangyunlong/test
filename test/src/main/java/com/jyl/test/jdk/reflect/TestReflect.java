package com.jyl.test.jdk.reflect;

import java.lang.reflect.Field;

public abstract class TestReflect<T> {
	
	private Class<T> clazz;
	 
    public TestReflect(Class<T> clazz) {
        this.clazz = clazz;
    }
 
    public T getClassType() {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
	public void ttttt() {
		Field field[] = clazz.getDeclaredFields();  
        for (Field f : field) {
        	System.out.println("xxxxxxxxxxx");
            System.out.println(f.getName());  
        }  
	}

}
