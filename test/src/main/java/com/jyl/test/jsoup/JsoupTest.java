package com.jyl.test.jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class JsoupTest {
	
	public static void main(String[] args) throws IOException {
		
		//第一步
		String url = "http://gd.189.cn/transaction/operApply1.jsp?operCode=DGCFPT&latn_id=0769";
		Document stemp1 = Jsoup.connect(url)
				.header("accept", "*/*")
				.header("accept-language", "zh-cn")
				.header("user-agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.04506.648; CIBA)")
				.header("accept-encoding", "gzip, deflate")
				.get();
		
		Element stemp1form = stemp1.select("form").first();
		String applyId = stemp1form.select("input").select("[name=ApplyId]").attr("value");
		System.out.println("applyId: " + applyId);
		
		Map<String, String> stemp1ParamsMap = new HashMap<String, String>();
		stemp1ParamsMap.put("operCode", "DGCFPT");
		stemp1ParamsMap.put("latn_id", "0769");
		stemp1ParamsMap.put("ApplyId", applyId);
		stemp1ParamsMap.put("number", "");
		stemp1ParamsMap.put("fromPage", "first");
		stemp1ParamsMap.put("toPage", "second");
		stemp1ParamsMap.put("targetChk", "targetChk");
		System.out.println("stemp1ParamsMap: " + stemp1ParamsMap);
		
		//第二步
		Jsoup.connect("http://gd.189.cn/OperationInitAction2.do?OperCode=DGCFPT&Latn_id=0769")
				  .data(stemp1ParamsMap)
				  .header("accept", "*/*")
				  .header("accept-language", "zh-cn")
				  .header("user-agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.04506.648; CIBA)")
				  .header("accept-encoding", "gzip, deflate")
				  .timeout(3000)
				  .post();

		Map<String, String> stemp2ParamsMap = new HashMap<String, String>();
		stemp2ParamsMap.put("operCode", "DGCFPT");
		stemp2ParamsMap.put("latnID", "0769");
		stemp2ParamsMap.put("fromPage", "second");
		stemp2ParamsMap.put("toPage", "second");
		stemp2ParamsMap.put("ApplyId", applyId);
		stemp2ParamsMap.put("applyLatn", "");
		stemp2ParamsMap.put("applyNum", "");
		stemp2ParamsMap.put("applyBusiID", "");
		stemp2ParamsMap.put("number", "");
		stemp2ParamsMap.put("mailing_post_code", "");
		stemp2ParamsMap.put("code", "null");
		stemp2ParamsMap.put("printView", "");
		stemp2ParamsMap.put("curPageId", "0");
		stemp2ParamsMap.put("sel_disc_id", "");
		stemp2ParamsMap.put("action_type", "");
		System.out.println("stemp2ParamsMap: " + stemp2ParamsMap);
		
		//第三步
		Document stemp3 = Jsoup.connect("http://gd.189.cn/OperationConfirmAction2.do?OperCode=DGCFPT&Latn_id=0769")
				  .data(stemp2ParamsMap)
				  .header("accept", "*/*")
				  .header("accept-language", "zh-cn")
				  .header("user-agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.04506.648; CIBA)")
				  .header("accept-encoding", "gzip, deflate")
				  .timeout(3000)
				  .post();
		
		Element stemp3form = stemp3.select("form").first();
		String token = stemp3form.select("input").select("[name=org.apache.struts.taglib.html.TOKEN]").attr("value");
		System.out.println("token: " + token);
		
		Map<String, String> stemp3ParamsMap = new HashMap<String, String>();
		stemp3ParamsMap.put("operCode", "DGCFPT");
		stemp3ParamsMap.put("latnID", "0769");
		stemp3ParamsMap.put("fromPage", "third");
		stemp3ParamsMap.put("ApplyId", applyId);
		stemp3ParamsMap.put("applyLatn", "");
		stemp3ParamsMap.put("applyNum", "");
		stemp3ParamsMap.put("applyBusiID", "");
		stemp3ParamsMap.put("number", "");
		stemp3ParamsMap.put("mailing_post_code", "");
		stemp3ParamsMap.put("code", "null");
		stemp3ParamsMap.put("printView", "");
		stemp3ParamsMap.put("curPageId", "0");
		stemp3ParamsMap.put("sel_disc_id", "");
		stemp3ParamsMap.put("action_type", "");
		stemp3ParamsMap.put("org.apache.struts.taglib.html.TOKEN", token);
		
		//stemp3ParamsMap.put("userAction", "next");			
		//产品名称
		/*stemp3ParamsMap.put("product_name", "测试宽带产品");			
		//产品编码
		stemp3ParamsMap.put("product_code", "DGCF2014112600580");		
		//基本费用
		stemp3ParamsMap.put("price", "990");			
		//modem
		stemp3ParamsMap.put("modem", "自备");					
		//安装调试费用
		stemp3ParamsMap.put("test_price", "100");		
		//是否有固话
		stemp3ParamsMap.put("telephone", "未有固话");			
		//固话号码
		stemp3ParamsMap.put("telephone_number", "");		
		//客户名称
		stemp3ParamsMap.put("customer_name", "测试");		
		//联系电话
		stemp3ParamsMap.put("contact_phone", "17701956520");		
		//装机地址
		stemp3ParamsMap.put("contact_address", "广州天河");		
		//证件类型
		stemp3ParamsMap.put("id_type", "身份证");				
		//证件号码
		stemp3ParamsMap.put("id_number", "400000000000000000");			
		//支付方式
		stemp3ParamsMap.put("pay_type", "pos机支付");				
		//备注
		stemp3ParamsMap.put("remark", "测试备注，请勿受理！");*/					
		
		System.out.println("stemp3ParamsMap: " + stemp3ParamsMap.toString());
		
		//第四步
		Document stemp4 = Jsoup.connect("http://gd.189.cn/OperationDoneAction2.do")
				  .data(stemp3ParamsMap)
				  .header("accept", "*/*")
				  .header("accept-language", "zh-cn")
				  .header("user-agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.04506.648; CIBA)")
				  .header("accept-encoding", "gzip, deflate")
				  .timeout(3000)
				  .post();
		
		System.out.println(stemp4.html());
		
	}
	
	public static Map<String,String> getInputValueMapByName(Element e, String... keys){
		
		Map<String,String> map = new HashMap<String,String>();
		for(String key : keys){
			map.put(key, e.select("input").select("[name="+key+"]").attr("value"));
		}
		return map;
	}
	
}
