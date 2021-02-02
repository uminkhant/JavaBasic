package com.mkt.jdc.model;


import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Disease {

	private Doctor doctor;
	private Patient patient;
	private String dis;
	private String medicine;
	private double charge;
	private String remark;
	private LocalDate dt_check;
}
