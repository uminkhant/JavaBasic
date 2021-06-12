package com.jdc.mkt.controller;

import com.jdc.mkt.model.User;
import com.jdc.mkt.service.UserService;
import com.jdc.mkt.service.db.ConfigurtaionObj;
import com.jdc.mkt.util.MyException;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mkt.lib.MKLogin;
import mkt.lib.MKMessage;
import mkt.lib.MKMessage.MessageType;

public class Login {

	private String front_cl = "#0F6C8B";
	private String back_cl = "#EEEEEE";
	private String path;
	private MKLogin log;
	private MKMessage mess;
	private User user;
	private UserService service;

	public void showLogin(Stage stage) {

		log = MKLogin.getInstance("SRC System", front_cl, back_cl, path);
		mess = MKMessage.getInstance(log.getMessage());
		service = new UserService();
		Parent root = log.Login();
		stage.setScene(new Scene(root));
		stage.show();
		initialize();

	}

	public void initialize() {
		log.getEnter().setOnAction(e -> enter());
		log.getCancel().setOnAction(e -> Platform.exit());

	}

	private void enter() {
		ConfigurtaionObj con = new ConfigurtaionObj();

		try {

			con.getDat();

			if (log.getTextField().getText() == null || log.getTextField().getText().isEmpty()) {
				throw new MyException("Please type login ID !");
			}

			user = service.getUserByName(log.getTextField().getText());

			if (null == user) {
				throw new MyException("Plase type again Login ID !");
			}
			if (!user.getPassword().equals(log.getPasswordField().getText())) {
				throw new MyException("Plase type again Password !");
			}
			DController.showDashboard(user);
			log.getEnter().getScene().getWindow().hide();
			
		} catch (MyException e) {
			//DbFormController.showDbForm();
			mess.showMessage(e.getMessage(), MessageType.ERROR);
		}
	}
}
