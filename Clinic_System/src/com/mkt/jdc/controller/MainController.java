package com.mkt.jdc.controller;

import java.io.IOException;

import com.mkt.jdc.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {

	@FXML
	private Label lb_user;

	@FXML
	private VBox vb_sideBar;

	@FXML
	private StackPane st_pane;

	private static User user;

	public static User getUser() {
		return user;
	}

	@FXML
	void changeForm(MouseEvent event) {

		HBox hbButton = (HBox) event.getSource();

		hbButton.getChildren().filtered(lb -> lb instanceof Label).forEach(e -> {
			showSubForm(e);
		});
	}

	private void showSubForm(Node e) {
		Label lb = (Label) e;

		try {
			Parent root = FXMLLoader
					.load(MainController.class.getResource("fxml/" + lb.getText().toLowerCase() + ".fxml"));
			st_pane.getChildren().clear();
			st_pane.getChildren().add(root);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public static void showMainForm(User user) {
		MainController.user = user;
		
		try {
			FXMLLoader loader=new FXMLLoader(MainController.class.getResource("fxml/Main.fxml"));
			Parent root=loader.load();
			
			MainController controller=loader.getController();
			controller.lb_user.setText(user.getName());
			
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
