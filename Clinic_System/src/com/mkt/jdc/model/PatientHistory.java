package com.mkt.jdc.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PatientHistory {

	private int id;
	private String medicine;
	private int charge;
	private String remark;
	private LocalDate rc_dt;
	private Disease disease;
}
