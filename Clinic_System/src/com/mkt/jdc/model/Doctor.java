package com.mkt.jdc.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class Doctor {

	private int id;
	private String name;
	private String specialist;
	private Gender gender;
	private String contact;
	private boolean active;
	private User user;
	
	@Override
	public String toString() {
		
		return name;
	}
	
}
