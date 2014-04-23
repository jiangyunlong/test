package com.jyl.test.jdk.string;

import java.text.MessageFormat;

public class TestMessageFormat {
	
	public static void main(String[] args) {
		
		String str = MessageFormat.format("尊敬的客户，感谢你办理天翼业务，你的订单号是：{0}，查询进度：{1}", "YHJ201400055222","http://vmall.mini189.cn/xxx.html");
		System.out.println(str);
	}
}
