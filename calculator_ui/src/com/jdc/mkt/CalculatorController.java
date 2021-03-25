package com.jdc.mkt;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CalculatorController implements Initializable {

	@FXML
	private Label lb_sign;

	@FXML
	private Label lb_first;

	@FXML
	private Label lb_current;

	@FXML
	private GridPane gd_btn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		gd_btn.getChildren().stream().filter(e -> e instanceof Button).forEach(btn -> {
			Button b = (Button) btn;
			b.setOnAction(e -> showOnLabel(b));
			return;
		});
	}

	private void showOnLabel(Button b) {
		if (lb_sign.getText().equals("0") || lb_sign.getText() == null || lb_sign.getText().isEmpty()) {
			lb_current.setText(lb_current.getText().concat(b.getText()));
			
		}else {
			
		}
	}

}
