package com.jyl.test.jackson;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jyl.test.jdk.genericity.User;

public class Bean2JsonStr {
	
	
	public void bean2Json(){
        User bean = new User();
        bean.setName("xxxxxxxxxxx");
        bean.setBirth(new Date());
        
        ObjectMapper om = new ObjectMapper();  
        Writer w = new StringWriter();  
        String json = null;  
        try {  
        om.writeValue(w, bean);  
            json = w.toString();  
            w.close();  
        } catch (IOException e) {  
            // 错误处理  
        }  
        System.out.println(json);  
    }
	
	@Test
	public void json2Bean(){
		
		String json = "{\"id\":0,\"name\":\"xxxxxxxxxxx\",\"birth\":1416536489986}";
		
		User user = null;  
	    try{  
	    	user = new ObjectMapper().readValue(json, User.class);  
	    } catch (IOException e){  
	        // 处理异常  
	    }
	    
	    
	    System.out.println(user.getName());
	    System.out.println(user.getBirth());
	}

}
