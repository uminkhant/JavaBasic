package com.jdc.mkt.model;

import java.io.Serializable;

public class Department implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private boolean active;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
	
		return name;
	}
	
}
