package com.jyl.test.jdk.list;


public class Iterator2List {
	
	public static void main(String[] args) {
		
		/*
		 方式1：
		#Apache Commons Collections:
		import org.apache.commons.collections.IteratorUtils;
		Iterator<Element> myIterator = //some iterator
		List<Element> myList=IteratorUtils.toList(myIterator);   


		方式二：
		或者自己转换
		public static <T> List<T> copyIterator(Iterator<T> iter) {
		    List<T> copy = new ArrayList<T>();
		    while (iter.hasNext())
		        copy.add(iter.next());
		    return copy;
		}

		使用方式：
		List<String> list = Arrays.asList("1", "2", "3");
		Iterator<String> iter = list.iterator();
		List<String> copy = copyIterator(iter);

		方式3：
		#Guava
		import com.google.common.collect.Lists;
		Iterator<Element> myIterator =  //some iterator
		List<Element> myList = Lists.newArrayList(myIterator);
		
		*/
	}

}
