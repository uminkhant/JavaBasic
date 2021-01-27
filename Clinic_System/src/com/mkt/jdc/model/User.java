package com.mkt.jdc.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {

	private int id;
	private String name;
	private String loginId;
	private Role role;
	private boolean active;
	private String password;
	
	public enum Role{
		Admin,Cashier,Doctor
	}
}
