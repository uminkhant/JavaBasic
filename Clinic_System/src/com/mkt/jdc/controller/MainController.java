package com.mkt.jdc.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.mkt.jdc.model.User;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController implements Initializable{

	@FXML
	private Label lb_user;

	@FXML
	private VBox vb_sideBar;

	@FXML
	private StackPane st_pane;
	@FXML
	private PieChart pi_do_qty;

	private static User user;

	private static MainController controller;


	public static MainController getMain() {
		return controller;
	}

	public static User getUser() {
		return user;
	}

	@FXML
	void changeForm(MouseEvent event) {

		HBox hbButton = (HBox) event.getSource();

		hbButton.getChildren().filtered(lb -> lb instanceof Label).forEach(e -> {

			Label lb = (Label) e;

			if (lb.getText().equals("EXIT")) {

				Platform.exit();
			}
			showSubForm(lb.getText());
		});
	}

	public void showSubForm(String formName) {

		try {
			Parent root = FXMLLoader.load(MainController.class.getResource("fxml/" + formName.toLowerCase() + ".fxml"));
			st_pane.getChildren().clear();
			st_pane.getChildren().add(root);
			slideForm(root);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	private void slideForm(Node node) {

		TranslateTransition tt = new TranslateTransition();
		tt.setNode(node);
		tt.setFromX(700);
		tt.setToX(0);
		tt.setAutoReverse(false);
		tt.setCycleCount(1);
		tt.setDuration(Duration.millis(1000));
		tt.play();
	}

	public static void showMainForm(User user) {
		MainController.user = user;

		try {
			FXMLLoader loader = new FXMLLoader(MainController.class.getResource("fxml/Main.fxml"));
			Parent root = loader.load();

			controller = loader.getController();

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showSubForm("dashboard");
	}

}
