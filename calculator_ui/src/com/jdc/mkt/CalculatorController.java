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

	private static String ope;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		gd_btn.getChildren().stream().filter(e -> e instanceof Button).forEach(btn -> {
			Button b = (Button) btn;
			b.setOnAction(e -> check(b));
			return;
		});
	}

	private void check(Button b) {

		if (b.getText().matches("[0-9]")) {
			showOnLabel(b.getText());

		} else {

			showOnOpeartor(b.getText());

		}
	}

	private void showOnLabel(String txt) {

		if (!lb_current.getText().equals("0")) {
			lb_current.setText(lb_current.getText().concat(txt));
		} else {
			lb_current.setText(txt);
		}

	}

	private void showOnOpeartor(String txt) {

		if (!txt.equals("=")) {
			lb_sign.setText(txt);
			lb_first.setText(lb_current.getText());
			lb_current.setText("0");
		} else {
			double a=Double.parseDouble(lb_first.getText());
			double b=Double.parseDouble(lb_current.getText());
			calculate(a,b,lb_sign.getText());
		}
	}

	private void calculate(double a, double b, String text) {
		switch (text) {
		case "+":
			lb_current.setText(String.valueOf(a+b));
			break;
		case "-":
			lb_current.setText(String.valueOf(a-b));
			break;
		case "X":
			lb_current.setText(String.valueOf(a*b));
			break;
		case "/":
			lb_current.setText(String.valueOf(a/b));
			break;

		default:
			break;
		}
	}

}
