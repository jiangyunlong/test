package com.jyl.test.json.util;

import net.sf.json.JSONObject;

public class JsonObjectUtil {

    public static String bean2Json(Object obj){
        JSONObject jsonObject = JSONObject.fromObject(obj);
        return jsonObject.toString();
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T json2Bean(String jsonStr,Class<T> objClass){
        return (T)JSONObject.toBean(JSONObject.fromObject(jsonStr), objClass);
    }
	
}
