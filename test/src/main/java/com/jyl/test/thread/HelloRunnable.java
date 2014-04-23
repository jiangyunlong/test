package com.jyl.test.thread;

public class HelloRunnable implements Runnable {

private String name;
	
	public HelloRunnable() {
		 
    }
 
    public HelloRunnable(String name) {
        this.name = name;
    }
 
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行     " + i);
        }
    }
 
    public static void main(String[] args) {
    	HelloRunnable h1=new HelloRunnable("线程A");
    	Thread demo= new Thread(h1);
    	HelloRunnable h2=new HelloRunnable("线程Ｂ");
    	Thread demo1=new Thread(h2);
    	demo.start();
    	demo1.start();
    }
}
