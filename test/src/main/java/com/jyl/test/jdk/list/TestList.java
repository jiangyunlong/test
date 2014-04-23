package com.jyl.test.jdk.list;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.junit.Test;

public class TestList {
	
	/*public static void main(String[] args) {
		
		Map<Integer,User> map = new HashMap<Integer,User>();
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i=0;i<5;i++){
			User user = new User();
			user.setId(i);
			user.setName("abc" + i);
			map.put(i, user);
		}
		
		for(User user : map.values()){
			System.out.println(user.getName());
		}
		
		for(int i=0;i<2;i++){
			list.add(i);
		}
		
		for(int i : list){
			map.remove(i);
		}
		for(User user : map.values()){
			System.out.println(user.getName());
		}
	}*/
	
	/*public static void main(String[] args) {
		
		List<String> list = new ArrayList<String>();
		list.add("a");list.add("b");list.add("c");
		list.remove(list.size()-1);
		System.out.println(list);
	}*/
	
	@Test
	public void testLastindexOf(){

		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		System.out.println(list.get(list.size()-1));
	}
	
	//@Test
	public void testListMethod(){
		String a = "A", b = "B", c = "C", d = "D", e = "E";
		
		/**
		 * set和add
		 */
		List<String> list = new ArrayList<String>();
		list.add(a);
		list.add(e);
		list.add(d);
		
		list.set(1, b);// 将索引位置为1的对象e修改为对象b
		list.add(2, c);// 将对象c添加到索引位置为2的位置
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));// 利用get(int index)方法获得指定索引位置的对象
		}
		
		/**
		 * subList利用从索引位置的对象重新生成一个List集合
		 */
		/*List<String> list = new ArrayList<String>();
		list.add(a);          // 索引位置为 0
		list.add(b);          // 索引位置为 1
		list.add(c);          // 索引位置为 2
		list.add(d);          // 索引位置为 3
		list.add(e);          // 索引位置为 4
		
		list = list.subList(1, 3);// 利用从索引位置 1 到 3 的对象重新生成一个List集合
		//返回列表中指定的 fromIndex（包括 ）和 toIndex（不包括）之间的部分视图。
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}*/
		
		/**
		 * indexOf,lastIndexOf
		 */
		/*List<String> list = new ArrayList<String>();
		list.add(a);          // 索引位置为 0
		list.add(e);      // 索引位置为 1
		list.add(b);          // 索引位置为 2
		list.add(e);      // 索引位置为 3
		list.add(c);          // 索引位置为 4
		list.add(e);      // 索引位置为 5
		list.add(d);          // 索引位置为 6
		
		System.out.println(list.indexOf(e));
		System.out.println(list.lastIndexOf(e));
		System.out.println(list.indexOf(b));
		System.out.println(list.lastIndexOf(b));*/
	}
	
	
	public static void main(String args[]) {  
        List<String> list=new ArrayList<String>();  
        list.add("A");  
        list.add("B");  
        list.add("C");  
        list.add("A");  
		// List中允许元素重复
		for(int i=0;i<list.size();i++){
			System.out.print(" "+list.get(i));// A B C A
		}
		// 去除重复的元素且保持原有元素的顺序
		List<String> list2 = new ArrayList<String>(new LinkedHashSet<String>(list));  
		for(int i=0;i<list2.size();i++){
			System.out.print(" "+list2.get(i));// A B C 
		}
	}
	
	public <e> List<e> function (List<e> list) {  
		return new ArrayList<e>(new LinkedHashSet<e>(list));  
	}
	
}
