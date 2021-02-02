package com.mkt.jdc.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Disease {

	private Doctor doctor;
	private Patient patient;
	private String dis;
	private List<PatientHistory>phs;
	
}
