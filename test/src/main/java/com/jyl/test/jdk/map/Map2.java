package com.jyl.test.jdk.map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Map2 {
	public static void main(String[] args) {
		Map<String, Integer> tempMap = new HashMap<String, Integer>();
		  
		tempMap.put("a", 1);
		tempMap.put("b", 2);
		tempMap.put("c", 3);
		
		// JDK1.4中
		// 遍历方法一 hashmap entrySet() 遍历
		System.out.println("方法一");
		Iterator<Entry<String, Integer>> it = tempMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
		    Object key = entry.getKey();
		    Object value = entry.getValue();
		    System.out.println("key=" + key + " value=" + value);
		}
		
		System.out.println("");
		// JDK1.5中,应用新特性For-Each循环
		// 遍历方法二
		System.out.println("方法二");
		for (Map.Entry<String, Integer> entry : tempMap.entrySet()) {
			String key = entry.getKey().toString();
			String value = entry.getValue().toString();
			System.out.println("key=" + key + " value=" + value);
		}
		System.out.println("");
		// 遍历方法三 hashmap keySet() 遍历
		System.out.println("方法三");
		for (Iterator i = tempMap.keySet().iterator(); i.hasNext();) {
			Object obj = i.next();
			System.out.println(obj);// 循环输出key
			System.out.println("key=" + obj + " value=" + tempMap.get(obj));
		}
		
		for (Iterator i = tempMap.values().iterator(); i.hasNext();) {
			Object obj = i.next();
			System.out.println(obj);// 循环输出value
		}
		System.out.println("");

		// 遍历方法四 treemap keySet()遍历
		System.out.println("方法四");
		for (Object o : tempMap.keySet()) {
			System.out.println("key=" + o + " value=" + tempMap.get(o));
		}
		System.out.println("11111");

		// java如何遍历Map <String, ArrayList> map = new HashMap <String,
		// ArrayList>();
		System.out.println("java  遍历Map <String, ArrayList> map = new HashMap <String, ArrayList>();");
		Map<String, ArrayList> map = new HashMap<String, ArrayList>();
		Set<String> keys = map.keySet();
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			ArrayList arrayList = map.get(key);
			for (Object o : arrayList) {
				System.out.println(o + "遍历过程");
			}
		}
		System.out.println("2222");
		Map<String, List> mapList = new HashMap<String, List>();
		for (Map.Entry entry : mapList.entrySet()) {
			String key = entry.getKey().toString();
			List<String> values = (List) entry.getValue();
			for (String value : values) {
				System.out.println(key + " --> " + value);
			}
		}
	}
}
