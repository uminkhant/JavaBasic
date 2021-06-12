package com.jdc.mkt.model;

import java.io.Serializable;

import java.time.LocalDate;


public class Servicing implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private Department department;
	private String error;
	private String service;
	private String remark;
	private LocalDate service_dt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public LocalDate getService_dt() {
		return service_dt;
	}
	public void setService_dt(LocalDate service_dt) {
		this.service_dt = service_dt;
	}
	
	
	
	
}
