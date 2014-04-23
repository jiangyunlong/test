package com.jyl.test.jdk.list;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.jyl.test.jdk.genericity.User;

public class TestListSort extends Thread{
	
	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		List<User> list = new ArrayList<User>();
		for(int i=1;i<=5;i++){
			try {
				sleep(1000);
				User user = new User();
				user.setId(i);
				user.setName("user" + i);
				user.setBirth(new Date());
				list.add(user);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(User user : list){
			System.out.println("id: " + user.getId() + ", date: " + sdf.format(user.getBirth()));
		}
		
		Collections.sort(list, new Comparator<User>(){          
	        public int compare(User o1, User o2) {  
	            try{             
	            	//按时间倒序
	                return o2.getBirth().compareTo(o1.getBirth());                         
	            }catch(Exception e){
	                e.printStackTrace();
	            }         
	            return -1;                        
	        }
		});
		
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		for(User user : list){
			System.out.println("id: " + user.getId() + ", date: " + sdf.format(user.getBirth()));
		}
	}
}
