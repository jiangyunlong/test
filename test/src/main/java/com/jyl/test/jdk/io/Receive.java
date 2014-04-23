package com.jyl.test.jdk.io;

import java.io.PipedInputStream;

public class Receive implements Runnable {
	
	private PipedInputStream input=null;  
    public Receive(){  
        this.input=new PipedInputStream();  
    }  
    public PipedInputStream getInput(){  
        return this.input;  
    }  
    public void run(){  
        byte[] b = new byte[1000];  
        int len = 0;  
        try{  
            len = this.input.read(b);  
        }catch (Exception e) {  
            e.printStackTrace();  
        }try{  
            input.close();  
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
        System.out.println("接受的内容为 "+(new String(b,0,len)));  
    }  

}
