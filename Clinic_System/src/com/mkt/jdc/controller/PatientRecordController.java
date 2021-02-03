package com.mkt.jdc.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.mkt.jdc.model.Disease;
import com.mkt.jdc.model.Doctor;
import com.mkt.jdc.model.Patient;
import com.mkt.jdc.service.DiseaseService;
import com.mkt.jdc.service.DoctorService;
import com.mkt.jdc.service.PatientService;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PatientRecordController implements Initializable {

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
		cbo_doctor.getSelectionModel().clearSelection();
		cbo_patient.getSelectionModel().clearSelection();
		dtp_from.setValue(LocalDate.now());
		dtp_to.setValue(LocalDate.now());
	}

	@FXML
	void search() {

		loadView(dis);

	}

	void loadView(Disease dis) {
		tv_disease.getItems().clear();
		tv_disease.getItems().addAll(dis_service.find(dis, dtp_from.getValue(), dtp_to.getValue()));
		getTotal();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		doc_service = new DoctorService();
		p_service = new PatientService();
		dis_service = new DiseaseService();
		dis = new Disease();
		dtp_from.setValue(LocalDate.now());
		dtp_to.setValue(LocalDate.now());

		cbo_doctor.getItems().addAll(doc_service.findAll());
		cbo_patient.getItems().addAll(p_service.findAll());
		loadView(dis);

		col_num.setCellValueFactory(
				col -> new ReadOnlyObjectWrapper<Integer>(tv_disease.getItems().indexOf(col.getValue()) + 1));

		cbo_patient.getSelectionModel().selectedItemProperty().addListener((a, b, c) -> {
			dis.setPatient(cbo_patient.getValue());
			loadView(dis);

		});

		cbo_doctor.getSelectionModel().selectedItemProperty().addListener((a, b, c) -> {
			dis.setDoctor(cbo_doctor.getValue());
			loadView(dis);

		});

	}

	private void getTotal() {
		Double d = (double) tv_disease.getItems().stream().mapToDouble(c -> c.getCharge()).sum();
		lb_total.setText(String.valueOf(d));
	}

}
