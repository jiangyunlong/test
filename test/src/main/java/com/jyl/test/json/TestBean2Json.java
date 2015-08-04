package com.jyl.test.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.jyl.test.User;
import com.jyl.test.json.util.GsonUtil;
import com.jyl.test.json.util.JacksonUtil;
import com.jyl.test.json.util.JsonObjectUtil;


public class TestBean2Json {
	
    private User user;
    
    private User createAUser(Long id, String name,List<User> friends) {
    	User newUser = new User();
    	newUser.setId(id);
    	newUser.setName(name);
    	newUser.setAge(24);
    	newUser.setBirth(new Date());
    	newUser.setMobilephone("10086");
        List<String> hobbies = new ArrayList<String>();
        hobbies.add("basketBall");
        hobbies.add("footBall");
        hobbies.add("running");
        newUser.setHobbies(hobbies);
        Map<String,String> clothes = new HashMap<String, String>();
        clothes.put("coat", "Nike");
        clothes.put("trousers", "adidas");
        clothes.put("shoes", "NewBalance");
        newUser.setClothes(clothes);
        newUser.setFriends(friends);
        return newUser;
    }
    
    @Before
    public void init(){
        List<User> friends = new ArrayList<User>();
        friends.add(createAUser(1L,"Kimi",null));
        friends.add(createAUser(2L,"Tony",null));
        friends.add(createAUser(3L,"Sandy",null));
        user = createAUser(4L,"Kobe",friends);
    }
    
    //@Test
    public void testGsonBean2Json(){
        System.out.println(GsonUtil.bean2Json(user));
        
        //13.429s
        for (int i = 0; i < 1000000; i++) {
            GsonUtil.bean2Json(user);
        }
    }
    
    //@Test
    public void testJsonObjectBean2Json(){
        System.out.println(JsonObjectUtil.bean2Json(user));
        
        //110.503s
        for (int i = 0; i < 1000000; i++) {
            JsonObjectUtil.bean2Json(user);
        }
    }
    
    @Test
    public void testJacksonBean2Json() throws Exception{
        System.out.println(JacksonUtil.bean2Json(user));
        
        //5.013s
        /*for (int i = 0; i < 1000000; i++) {
            JacksonUtil.bean2Json(user);
        }*/
    }
}
