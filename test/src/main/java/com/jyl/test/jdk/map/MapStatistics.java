package com.jyl.test.jdk.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapStatistics {
	
	/**
	 * [{"name":"LiLei","value":"1"},{"name":"LiLei","value":"2"},{"name":"HanMeimei","value":"3"},{"name":"HanMeimei","value":"4"}]
	 * [{"name":"LiLei","count":2},{"name":"HanMeimei","count":2}]
	 * @param args
	 */
	public static void main(String[] args) {
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		
		Set<String> set = new HashSet<String>();
		
		for(int i=0;i<10;i++){
			Map<String,Object> map = new HashMap<String,Object>();
			if(i%2 == 0){
				map.put("name", "LiLei");
				map.put("value", i);
			}else{
				map.put("name", "HanMeimei");
				map.put("value", i);
			}
			list.add(map);
		}
		
		for(Map<String,Object> map : list){
			set.add(map.get("name").toString());
		}
		
		for(String key : set){
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("name", key);
			for(Map<String,Object> map : list){
				if(map.get("name").equals(key)){
					if(resultMap.get("count") == null){
						resultMap.put("count", 1);
					}else{
						resultMap.put("count", Integer.valueOf(resultMap.get("count").toString())+1);
					}
				}
			}
			resultList.add(resultMap);
		}
		
		for(Map<String,Object> map : resultList){
			System.out.println(map.get("name") + " " + map.get("count").toString());
		}
	}
}
