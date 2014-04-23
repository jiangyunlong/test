package com.jyl.test.thread;

public class HelloThread extends Thread {
	
	/*private String name;
	
	public HelloThread() {
		 
    }
 
    public HelloThread(String name) {
        this.name = name;
    }
 
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行     " + i);
        }
    }
 
    public static void main(String[] args) {
    	HelloThread h1=new HelloThread("A");
    	HelloThread h2=new HelloThread("B");
        h1.start();
        h2.start();
    }*/
	
	public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
 
    public static void main(String[] args) {
    	HelloThread he = new HelloThread();
        new Thread(he,"A").start();
        new Thread(he,"B").start();
        new Thread(he).start();
    }
}
