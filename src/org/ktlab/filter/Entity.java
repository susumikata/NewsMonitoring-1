package org.ktlab.filter;

public class Entity {
	private String name;
	private Integer frequent;
	
	public Entity() {}
	
	public Entity(String name, Integer frequent) {
		this.name = name;
		this.frequent = frequent;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getFrequent() {
		return frequent;
	}
	
	public void setFrequent(Integer frequent) {
		this.frequent = frequent;
	}
}
