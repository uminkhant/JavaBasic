package com.mkt.jdc.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Patient {

	private int id;
	private String name;
	private int age;
	private boolean married;
	private Gender gender;
	private String address;
	private String contact;
	private boolean active;
	

	@Override
	public String toString() {
		return name;
	}
}
