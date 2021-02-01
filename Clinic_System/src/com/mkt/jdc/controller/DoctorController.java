package com.mkt.jdc.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.mkt.jdc.model.Doctor;
import com.mkt.jdc.model.Gender;
import com.mkt.jdc.model.User;
import com.mkt.jdc.service.DoctorService;
import com.mkt.jdc.service.UserService;
import com.mkt.jdc.util.ClinicException;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mkt.lib.MKMessage;
import mkt.lib.MKMessage.MessageType;

public class DoctorController implements Initializable {

	@FXML
	private TextField tf_name;

	@FXML
	private ComboBox<User> cbo_user;

	@FXML
	private ComboBox<Gender> cbo_gender;

	@FXML
	private TextField tf_specialist;

	@FXML
	private TextField tf_contact;

	@FXML
	private CheckBox chk_active;

	@FXML
	private TextField tf_id;

	@FXML
	private Label lb_message;

	@FXML
	private TableView<Doctor> tv_doctor;

	@FXML
	private TableColumn<Doctor, Integer> col_num;
	private UserService uService;
	private DoctorService dService;
	private MKMessage mm;
	private Doctor d;
	@FXML
	void delete() {
		try {

			if(tf_id.getText()!=null||!tf_id.getText().isEmpty()) {
				d.setActive(false);
				dService.update(d);
				mm.showMessage("successfully save doctor", MessageType.SUCCESS);
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
			
			if(tf_name.getText().isEmpty()||tf_name.getText()==null) {
				throw new ClinicException("Please type doctor name ");
			}
			
			if(tf_specialist.getText().isEmpty()||tf_specialist.getText()==null) {
				throw new ClinicException("Please type specilist ");
			}
			if(tf_contact.getText().isEmpty()||tf_contact.getText()==null) {
				throw new ClinicException("Please type contact ");
			}
			if(cbo_user.getValue()==null) {
				throw new ClinicException("Please select one software user ");
			}
			
			d=new Doctor();
			d.setName(tf_name.getText());
			d.setContact(tf_contact.getText());
			d.setSpecialist(tf_specialist.getText());
			d.setUser(cbo_user.getValue());
			d.setActive(true);
			d.setGender(cbo_gender.getValue()==null?Gender.Other:cbo_gender.getValue());
			
			if(tf_id.getText()==null||tf_id.getText().isEmpty()) {
				dService.save(d);
				mm.showMessage("successfully save doctor", MessageType.SUCCESS);
			}else {
				d.setId(Integer.parseInt(tf_id.getText()));
				dService.update(d);
				mm.showMessage("successfully update doctor", MessageType.SUCCESS);
			}
			
			loadView();
			clear();
		} catch (Exception e) {
			mm.showMessage(e.getMessage(), MessageType.ERROR);
		}
	}

	private void loadView() {
		tv_doctor.getItems().clear();
		tv_doctor.getItems().addAll(dService.findAll());
	}

	private void clear() {
		tf_contact.clear();
		tf_id.clear();
		tf_name.clear();
		tf_specialist.clear();
		cbo_user.getSelectionModel().clearSelection();
		cbo_gender.getSelectionModel().clearSelection();
		tf_name.setDisable(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		uService = new UserService();
		dService = new DoctorService();
		cbo_user.getItems().addAll(uService.findAll());
		cbo_gender.getItems().addAll(Gender.values());
		mm = MKMessage.getInstance(lb_message);
		
		loadView();
		
		col_num.setCellValueFactory(col->new ReadOnlyObjectWrapper<Integer>(tv_doctor.getItems().indexOf(col.getValue())+1));
		
		tv_doctor.setOnMouseClicked(e->{
			if(e.getClickCount()==2) {
				d=tv_doctor.getSelectionModel().getSelectedItem();
				
				tf_id.setText(String.valueOf(d.getId()));
				tf_name.setText(d.getName());
				tf_name.setDisable(true);
				tf_contact.setText(d.getContact());
				tf_specialist.setText(d.getSpecialist());
				cbo_user.setValue(d.getUser());
				cbo_gender.setValue(d.getGender());
				
			}
		});
	
	}

}
