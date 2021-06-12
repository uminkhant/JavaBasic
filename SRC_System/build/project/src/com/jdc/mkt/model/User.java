package com.jdc.mkt.model;

import java.io.Serializable;

import com.jdc.mkt.util.UserRole;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String loginId;
	private String name;
	private String password;
	private boolean active;
	private UserRole role;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
		
}
