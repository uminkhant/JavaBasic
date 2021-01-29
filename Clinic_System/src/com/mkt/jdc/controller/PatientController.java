package com.mkt.jdc.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.mkt.jdc.model.Gender;
import com.mkt.jdc.model.Patient;
import com.mkt.jdc.service.PatientService;
import com.mkt.jdc.util.ClinicException;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mkt.lib.MKMessage;
import mkt.lib.MKMessage.MessageType;

public class PatientController implements Initializable {

	@FXML
	private TextField tf_patient;

	@FXML
	private TextField tf_age;

	@FXML
	private ComboBox<Gender> cbo_gender;

	@FXML
	private RadioButton rbn_yes;

	@FXML
	private TextField tf_contact;

	@FXML
	private TextField tf_address;

	@FXML
	private Label lb_message;

	@FXML
	private CheckBox chk_active;

	@FXML
	private TextField tf_id;

	@FXML
	private TableView<Patient> tv_patient;

	@FXML
	private TableColumn<Patient, Integer> col_num;

	private PatientService service;
	private MKMessage mm;
	private Patient p;

	@FXML
	void delete() {
		try {
			
			if (tf_id.getText() != null || !tf_id.getText().isEmpty()) {
				p.setActive(false);
				service.update(p);
				mm.showMessage("Successfully deleted patient", MessageType.SUCCESS);
			}
			loadView();
			clear();

		} catch (Exception e) {
			mm.showMessage(e.getMessage(), MessageType.ERROR);
		}
	}

	@FXML
	void save() {

		try {

			if (tf_patient.getText().isEmpty() || tf_patient.getText() == null) {
				throw new ClinicException("Please type patient name ");
			}

			if (tf_age.getText().isEmpty() || tf_age.getText() == null) {
				throw new ClinicException("Please type patient name ");
			}
			if (tf_contact.getText().isEmpty() || tf_contact.getText() == null) {
				throw new ClinicException("Please type patient name ");
			}
			if (tf_address.getText().isEmpty() || tf_address.getText() == null) {
				throw new ClinicException("Please type patient name ");
			}
			if (tf_age.getText().isEmpty() || tf_age.getText() == null) {
				throw new ClinicException("Please type patient name ");
			}

			p = new Patient();
			p.setName(tf_patient.getText());
			p.setAge(Integer.parseInt(tf_age.getText()));
			p.setGender(cbo_gender.getValue() == null ? Gender.Male : cbo_gender.getValue());
			p.setMarried(rbn_yes.isSelected());
			p.setContact(tf_contact.getText());
			p.setAddress(tf_address.getText());
			p.setActive(true);

			if (tf_id.getText() == null || tf_id.getText().isEmpty()) {
				service.save(p);
				mm.showMessage("Successfully save patient", MessageType.SUCCESS);
			} else {
				p.setId(Integer.parseInt(tf_id.getText()));
				service.update(p);
				mm.showMessage("Successfully update patient", MessageType.SUCCESS);
			}
			loadView();
			clear();

		} catch (Exception e) {
			mm.showMessage(e.getMessage(), MessageType.ERROR);
		}
	}

	private void clear() {
		tf_age.clear();
		tf_contact.clear();
		tf_id.clear();
		tf_patient.clear();
		tf_address.clear();
		cbo_gender.getSelectionModel().clearSelection();
		rbn_yes.setSelected(true);
		tf_patient.setDisable(false);
	}

	private void loadView() {
		tv_patient.getItems().clear();
		tv_patient.getItems().addAll(service.findAll());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = new PatientService();
		mm = MKMessage.getInstance(lb_message);
		loadView();
		cbo_gender.getItems().addAll(Gender.values());
		col_num.setCellValueFactory(
				col -> new ReadOnlyObjectWrapper<Integer>(tv_patient.getItems().indexOf(col.getValue()) + 1));

		tv_patient.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
				p = tv_patient.getSelectionModel().getSelectedItem();

				tf_id.setText(String.valueOf(p.getId()));
				tf_patient.setText(p.getName());
				tf_age.setText(String.valueOf(p.getAge()));
				tf_contact.setText(p.getContact());
				tf_address.setText(p.getAddress());
				cbo_gender.setValue(p.getGender());

				if (p.isMarried()) {
					rbn_yes.setSelected(true);
				} else {
					rbn_yes.setSelected(true);
				}

				tf_patient.setDisable(true);

			}
		});

	}

}
