package com.jdc.mkt.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.mkt.model.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DController implements Initializable {

	@FXML
	private Label lb_user;
	@FXML
	private Label lb_userName;
	@FXML
	private Label lb_exit;

	@FXML
	private Label lb_department;

	@FXML
	private Label lb_service;
	@FXML
	private Label lb_message;
	@FXML
	private Label lb_db;

	@FXML
	private StackPane st_pane;
	private static User user;
	private static DController controller;
	
	static Label getMessageLabel() {

		return controller.lb_message;

	}

	static void showDashboard(User user) {

		DController.user = user;

		try {
			FXMLLoader loader = new FXMLLoader(DController.class.getResource("Dashboard.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			controller = loader.getController();
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lb_userName.setText(user.getName());
		
		lb_department.setOnMouseClicked(e -> showSubForm("Department"));
		lb_exit.setOnMouseClicked(e -> Platform.exit());
		lb_service.setOnMouseClicked(e -> showSubForm("Servicing"));
		lb_user.setOnMouseClicked(e -> showSubForm("User"));
		lb_db.setOnMouseClicked(e -> DbFormController.showDbForm());
		
		if (user.getRole().name().equals("ADMIN")) {
			lb_db.setVisible(true);
			lb_user.setVisible(true);
		} else {
			lb_db.setVisible(false);
			lb_user.setVisible(false);
		}

		//showSubForm("Servicing");
	}

	private void showSubForm(String name) {

		try {
			Parent root = FXMLLoader.load(DController.class.getResource(name + ".fxml"));
			st_pane.getChildren().clear();
			st_pane.getChildren().add(root);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
