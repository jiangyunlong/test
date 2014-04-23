package com.jyl.test.jdk.string;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.junit.Test;

public class URLEncode {
	
	@Test
	public void testt() throws Exception{
		String str = "是多少V大房顶上方便适度腐败";
		
		String urlEncodeStr = URLEncoder.encode(str, "UTF-8");
		System.out.println(urlEncodeStr);
		
		String urlDecodeStr = URLDecoder.decode(urlEncodeStr, "UTF-8");
		System.out.println(urlDecodeStr);
	}

}
