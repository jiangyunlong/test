package com.jyl.test.util;

public class TestIp2Long {

	private static long ip2Long(String strIp) 
	//将127.0.0.1 形式的ip地址转换成10进制整数，这里没有进行任何错误处理 
	{ 
		long [] ip=new long[4]; 
		int position1=strIp.indexOf("."); 
		int position2=strIp.indexOf(".",position1+1); 
		int position3=strIp.indexOf(".",position2+1); 
		
		ip[0]=Long.parseLong(strIp.substring(0,position1)); 
		ip[1]=Long.parseLong(strIp.substring(position1+1,position2)); 
		ip[2]=Long.parseLong(strIp.substring(position2+1,position3)); 
		ip[3]=Long.parseLong(strIp.substring(position3+1)); 
		return (ip[0]<<24)+(ip[1]<<16)+(ip[2]<<8)+ip[3]; //ip1*256*256*256+ip2*256*256+ip3*256+ip4 
	} 
	
	public static void main(String[] args) {
		System.out.println(ip2Long("1.184.118.156"));
	}
}
