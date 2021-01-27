package com.mkt.jdc.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.mkt.jdc.model.User;
import com.mkt.jdc.service.UserService;
import com.mkt.jdc.util.ClinicException;
import com.mkt.jdc.util.MessageManager;
import com.mkt.jdc.util.MessageManager.MessageType;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable{

	@FXML
	private TextField tf_user;

	@FXML
	private PasswordField pf_pass;

	@FXML
	private Label lb_message;
	private MessageManager mm;
	private UserService service;
	
	@FXML
	void cancel() {
		Platform.exit();
	}

	@FXML
	void enter() {

		try {

			if (tf_user.getText().isEmpty() || tf_user.getText() == null) {
				throw new ClinicException("Please Type user name !");
			}
			User user=service.findByName(tf_user.getText());
			
			if(user==null) {
				throw new ClinicException("Please Re_type user name !");
			}
			
			if(!user.getPassword().equals(pf_pass.getText())) {
				throw new ClinicException("Please Re_type password !");
			}
			MainController.showMainForm(user);
			tf_user.getScene().getWindow().hide();
						
		} catch (Exception e) {
			mm.showMessage(e.getMessage(),MessageType.ERROR);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		mm=new MessageManager(lb_message);
		service=new UserService();
	}

}
