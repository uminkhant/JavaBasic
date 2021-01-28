package com.mkt.jdc.controller;

import com.mkt.jdc.model.User;
import com.mkt.jdc.service.UserService;
import com.mkt.jdc.util.ClinicException;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mkt.lib.MKLogin;
import mkt.lib.MKMessage;
import mkt.lib.MKMessage.MessageType;

public class LoginController {

	private UserService service;

	Label lb_logo = new Label("Login Form");
	Label lb_message = new Label();

	TextField tf_user = new TextField();
	PasswordField pf_password = new PasswordField();

	Button btnEnter = new Button("GoTo");
	Button btnCancelButton = new Button("Cancel");
	private String front_color = "#af0069";
	private String back_color = "#DDDDDD";
	private MKLogin mk;
	private MKMessage mm;

	public LoginController() {
		service = new UserService();
		mk = MKLogin.getInstance(lb_logo, lb_message, tf_user, pf_password, btnEnter, btnCancelButton, front_color,
				back_color, null);
		mm=MKMessage.getInstance(lb_message);

		btnEnter.setOnAction(e -> enter());
		btnCancelButton.setOnAction(e -> cancel());
	}

	public void showForm(Stage stage) {

		Parent root = mk.Login();
		stage.setScene(new Scene(root));
		stage.show();

	}

	void cancel() {
		Platform.exit();
	}

	void enter() {

		try {

			if (tf_user.getText().isEmpty() || tf_user.getText() == null) {
				throw new ClinicException("Please Type user name !");
			}
			User user = service.findByName(tf_user.getText());

			if (user == null) {
				throw new ClinicException("Please Re_type user name !");
			}

			if (!user.getPassword().equals(pf_password.getText())) {
				throw new ClinicException("Please Re_type password !");
			}
			MainController.showMainForm(user);
			tf_user.getScene().getWindow().hide();

		} catch (Exception e) {
			mm.showMessage(e.getMessage(),MessageType.ERROR);
		}
	}

}
