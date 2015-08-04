package com.jyl.test.httpclient;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jyl.test.httpclient.webClient189.DefaultWebClient;
import com.jyl.test.httpclient.webClient189.WebClient;


public class HttpclientTest {
	
	public static void main(String[] args) {
		WebClient wc = new DefaultWebClient();
		wc.setDefaultCharSet("GBK");
		wc.setDebugMode(true);
		wc.setOutputMode(WebClient.OUTPUT_MODE_OUT_PRINT);
		wc.addHeader("accept", "*/*");
		wc.setHttpPostCharset("GBK");
		wc.addHeader("accept-language", "zh-cn");
		wc.addHeader("user-agent",
						"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.04506.648; CIBA)");
		wc.addHeader("accept-encoding", "gzip, deflate");

		String firstPage = wc.httpGet("http://gd.189.cn/transaction/operApply1.jsp?operCode=DGCFPT&latn_id=0769");
		
		Pattern step1Parse1 = Pattern.compile("<input type=\"hidden\" name=\"ApplyId\" value=\"([^\"]*)\" >",Pattern.MULTILINE);

		Pattern step3Parse1 = Pattern.compile("<input type=\"hidden\" name=\"org.apache.struts.taglib.html.TOKEN\" value=\"([^\"]*)\" />",Pattern.MULTILINE);
		
		Pattern step4Parse1 = Pattern.compile("您的业务办理订单号是：</span><span class=\"[^\"]*\">([^<]*)。</span>",Pattern.MULTILINE);
		
		Matcher step1M1 = step1Parse1.matcher(firstPage);

		String appId = null;
		while (step1M1.find()) {
			appId = step1M1.group(1);
		}
		
		Map<String, Object> step1Map1 = new HashMap<String, Object>();
		step1Map1.put("operCode", "DGCFPT");
		step1Map1.put("latn_id", "0769");
		step1Map1.put("ApplyId", appId);
		step1Map1.put("number", "");
		step1Map1.put("fromPage", "first");
		step1Map1.put("toPage", "second");
		step1Map1.put("targetChk", "targetChk");
		
		wc.httpPost("http://gd.189.cn/OperationInitAction2.do?OperCode=DGCFPT&Latn_id=0769",step1Map1);
		
		
		Map<String, Object> step3Map1 = new HashMap<String, Object>();
		step3Map1.put("operCode", "DGCFPT");
		step3Map1.put("latnID", "0769");
		step3Map1.put("fromPage", "second");
		step3Map1.put("toPage", "second");
		step3Map1.put("ApplyId", appId);
		step3Map1.put("applyLatn", "");
		step3Map1.put("applyNum", "");
		step3Map1.put("applyBusiID", "");
		step3Map1.put("number", "");
		step3Map1.put("mailing_post_code", "");
		step3Map1.put("code", "null");
		step3Map1.put("printView", "");
		step3Map1.put("curPageId", "0");
		step3Map1.put("sel_disc_id", "");
		step3Map1.put("action_type", "");
		
		String step3Page = wc.httpPost("http://gd.189.cn/OperationConfirmAction2.do?OperCode=DGCFPT&Latn_id=0769",step3Map1);
		Matcher step3M1 = step3Parse1.matcher(step3Page);		 
		String token = null;
		while (step3M1.find()) {
			token = step3M1.group(1);
		}
		
		Map<String, Object> step4Map1 = new HashMap<String, Object>();
		step4Map1.put("operCode", "DGCFPT");
		step4Map1.put("latnID", "0769");
		step4Map1.put("fromPage", "third");
		step4Map1.put("ApplyId", appId);
		step4Map1.put("applyLatn", "");
		step4Map1.put("applyNum", "");
		step4Map1.put("applyBusiID", "");
		step4Map1.put("number", "");
		step4Map1.put("mailing_post_code", "");
		step4Map1.put("code", "null");
		step4Map1.put("printView", "");
		step4Map1.put("curPageId", "0");
		step4Map1.put("sel_disc_id", "");
		step4Map1.put("action_type", "");
		step4Map1.put("org.apache.struts.taglib.html.TOKEN", token);
		//产品名称
		step4Map1.put("product_name", "测试宽带产品");			
		//产品编码
		step4Map1.put("product_code", "DGCF2014112600580");		
		//基本费用
		step4Map1.put("price", "990");			
		//modem
		step4Map1.put("modem", "自备");					
		//安装调试费用
		step4Map1.put("test_price", "100");		
		//是否有固话
		step4Map1.put("telephone", "未有固话");			
		//固话号码
		step4Map1.put("telephone_number", "");		
		//客户名称
		step4Map1.put("customer_name", "测试");		
		//联系电话
		step4Map1.put("contact_phone", "17701956520");		
		//装机地址
		step4Map1.put("contact_address", "广州天河");		
		//证件类型
		step4Map1.put("id_type", "身份证");				
		//证件号码
		step4Map1.put("id_number", "400000000000000000");			
		//支付方式
		step4Map1.put("pay_type", "pos机支付");				
		//备注
		step4Map1.put("remark", "测试备注，请勿受理！");			
		
		// 关闭浏览器
		String successPage = wc.httpPost("http://gd.189.cn/OperationDoneAction2.do", step4Map1);
		System.out.println("......"+successPage);
		Matcher step4M1 = step4Parse1.matcher(successPage);
		
		String ddh = "";
		while (step4M1.find()) {
			ddh = step4M1.group(1);
		}
		System.out.println(ddh);
		wc.shutDown();
	}
}
