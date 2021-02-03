package com.mkt.jdc.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.mkt.jdc.model.Disease;
import com.mkt.jdc.model.Doctor;
import com.mkt.jdc.model.Patient;
import com.mkt.jdc.service.DiseaseService;
import com.mkt.jdc.service.DoctorService;
import com.mkt.jdc.service.PatientService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PatientRecordController implements Initializable{

    @FXML
    private ComboBox<Patient> cbo_patient;

    @FXML
    private ComboBox<Doctor> cbo_doctor;

    @FXML
    private DatePicker dtp_from;

    @FXML
    private DatePicker dtp_to;

    @FXML
    private CheckBox chk_active;

    @FXML
    private TextField tf_id;

    @FXML
    private Label lb_message;
    @FXML
    private Label lb_total;

    @FXML
    private TableView<Disease> tv_disease;

    @FXML
    private TableColumn<Disease, Integer> col_num;
    
    private DoctorService doc_service;
    private PatientService p_service;
    private DiseaseService dis_service;
    private Disease dis;

    @FXML
    void clear() {

    }

    @FXML
    void search() {
    	
    }
    
    void loadView() {
    	tv_disease.getItems().clear();
    	tv_disease.getItems().addAll(dis_service.find(dis,dtp_from.getValue(),dtp_to.getValue()));
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		doc_service=new DoctorService();
		p_service=new PatientService();
		dis_service=new DiseaseService();
		
		cbo_doctor.getItems().addAll(doc_service.findAll());
		cbo_patient.getItems().addAll(p_service.findAll());
	//	dtp_from.setValue(LocalDate.now());
		//dtp_to.setValue(LocalDate.now());
		loadView();
		
	}

}
