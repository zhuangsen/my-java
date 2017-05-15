package com.zs.test;

public class Person {
	private String id;
	private String name;
	
	public Person(){}
	
	public Person(String name,String id){
		this.name = name;
		this.setId(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
