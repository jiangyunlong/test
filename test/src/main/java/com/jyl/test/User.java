package com.jyl.test;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class User {
	
	private Long id;
	
	private String name;
	
	private String mobilephone;
	
	private Integer age;
	
	private Date birth;
	
	private List<String> hobbies;
	
	private List<User> friends;

	private Map<String,String> clothes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@com.fasterxml.jackson.databind.annotation.JsonSerialize(using = com.jyl.test.provider.DateSerializer.class)
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public Map<String, String> getClothes() {
		return clothes;
	}

	public void setClothes(Map<String, String> clothes) {
		this.clothes = clothes;
	}
}
