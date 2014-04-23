package com.jyl.test.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.Properties;


public class ThreadRequest extends Thread{
	
	static class Daemon extends Thread{
		static DecimalFormat df = new DecimalFormat("##.##%");
		
		public void run(){
			while(true){
				int runThreads = 0;
				int stopThreads = 0;
				for(int i=0;i<ts.length;i++){
					if(ts[i].isAlive()){
						runThreads ++;
					}else{
						stopThreads ++;
					}
					
				}
				
				StringBuffer buff = new StringBuffer();
				buff.append("成功 ").append(success).append(", ");
				buff.append("成功率 ").append( 
						df.format(
								((double)success/(double)(success + error))
								) 
						).append(", ");
				buff.append("平均消耗时间 ").append((long)((double)successTimeMillis /(double)success)).append(" 微秒, ");
				buff.append("失败 ").append(error).append(", ");
				buff.append("失败率 ").append(
						df.format(
								((double)error/(double)(success + error))
								)
						).append(", ");
				buff.append("持续运行 ").append(((System.currentTimeMillis() - start)/1000)).append(" 秒, ");
				buff.append((success + error)).append("/").append((threads * runs)).append(" ");
				buff.append("(").append(df.format((double)(success + error)/(double)(threads * runs))).append(") ");
				buff.append("[").append(runThreads).append("/").append(stopThreads).append("] ");
				buff.append("找到字符串: " + foundReturnMessage);
				
				System.out.println(buff);
				
				
				if(runThreads == 0){
					break;
				}
				
				try{
					Thread.sleep(1000);
				}catch(Exception e){
					
				}
			}
		}
		
	}
	public static ThreadRequest[] ts;
	
	public static String spec = null;
	
	public static int threads = 1;
	public static int runs = 0;
	
	public static long success = 0;
	public static long error = 0;
	public static long foundReturnMessage = 0;
	
	public static long successTimeMillis = 0;
	
	public static long start = 0;
	
	public static long sleep = -1;
	
	public static String strFoundReturnMessage = null;
	
	public static void main(String[] args)throws Exception{
		
		if(args.length < 1){
			System.out.println("Usage: java ThreadRequest <u=url> <t=threads> <r=runs> <f=foundReturnMessage> <s=sleep>");
			return;
		}
		for(int i=0;i<args.length;i++){
			if(args[i].startsWith("u=")){
				spec = args[i].substring(2);
			}else
			if(args[i].startsWith("t=")){
				threads = Integer.parseInt(args[i].substring(2));
			}else
			if(args[i].startsWith("r=")){
				runs = Integer.parseInt(args[i].substring(2));
			}else
			if(args[i].startsWith("f=")){
				strFoundReturnMessage = args[i].substring(2);
			}else
			if(args[i].startsWith("s=")){
				sleep = Long.parseLong(args[i].substring(2));
			}
		}
		
		start = System.currentTimeMillis();
		
		File headersFile = new File("headers.ini");
		if(headersFile.exists()){
			InputStream headersInputStream = new FileInputStream(headersFile);
			headersProps.load(headersInputStream);
			headersInputStream.close();
		}
		
		ts = new ThreadRequest[threads];
		for(int i=0;i<threads;i++){
			ts[i] = new ThreadRequest();
			ts[i].start();
		}
		
		Daemon d = new Daemon();
		d.start();
	}
	
	static Properties headersProps = new Properties();
	
	public void run(){
		try{
			URL url = new URL(spec);
			for(int i=0;i<runs;i++){
				try{
					HttpURLConnection conn = (HttpURLConnection)url.openConnection();
					//conn.setRequestProperty("X_FORWARDED_FOR",  createIp());
					for(Enumeration<Object> e = headersProps.keys();e.hasMoreElements();){
						String key = String.valueOf(e.nextElement());
						conn.setRequestProperty(key, headersProps.getProperty(key));
						System.out.println("set " + key + ": " + headersProps.getProperty(key));
					}
					conn.setRequestMethod("GET");
					long start = System.currentTimeMillis();
					conn.getResponseMessage();
					
					if(strFoundReturnMessage != null){
						InputStream is = conn.getInputStream();
						byte[] buff = new byte[1024];
						StringBuffer sb = new StringBuffer();
						int length = 0;
						while((length = is.read(buff)) > 0){
							sb.append(new String(buff,0,length));
						}
						//System.out.println(sb);
						if(sb.toString().contains(strFoundReturnMessage)){
							foundReturnMessage ++;
						}
					}
					//
					ThreadRequest.success ++;
					long end = System.currentTimeMillis();
					successTimeMillis = successTimeMillis + end - start;
					conn.disconnect();
					conn = null;
					
					if(sleep > -1){
						Thread.sleep(sleep);
					}
				}catch(Exception e){
					ThreadRequest.error ++;
					//e.printStackTrace();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static int v = 11;
	static int x = 0;
	static int y = 0;
	static int z = 1;
	public static String createIp(){
		z ++;
		if(z == 256){
			z = 1;
			y ++;
		}
		if(y == 256){
			y = 1;
			x ++;
		}
		if(x == 256){
			x = 1;
			v ++;
		}
		
		return v + "." + x + "." + y + "." + z;
	}

}
