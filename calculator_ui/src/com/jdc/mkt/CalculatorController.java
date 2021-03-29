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
	private Label lb_ope;
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
			b.setOnAction(e -> check(b));
			return;
		});
	}

	private void check(Button b) {

		if (b.getText().matches("[0-9.]") || b.getText().equals("+/-")) {
			showOnLabel(b.getText());

		} else {

			checkOpeartor(b.getText());
		}
	}

	private void showOnLabel(String txt) {

		if (lb_current.getText().equals("0")) {
			lb_current.setText(txt);

		} else if (txt.equals("+/-")) {
			lb_sign.setText(lb_sign.getText().startsWith("+") ? "-" : "+");
		} else {
			lb_current.setText(lb_current.getText().concat(txt));

		}
	}

	private void checkOpeartor(String txt) {

		switch (txt) {
		case "C":
			lb_current.setText("0");
			lb_first.setText("");
			lb_ope.setText("");
			lb_sign.setText("+");
			break;
		case "=":
			
			lb_current.setText(String.format("%,.3f", calculate()));
			lb_sign.setText("+");
			
			break;
		default:
			lb_ope.setText(txt);
			lb_first.setText(lb_current.getText().startsWith("-")?lb_current.getText(): lb_sign.getText() + lb_current.getText());
			lb_current.setText("0");
			
			break;
		}
	}

	private double calculate() {

		double a = Double.parseDouble(lb_first.getText());
		double b = Double.parseDouble(lb_current.getText());
		
		switch (lb_ope.getText()) {
		case "+":
			return a + b;

		case "-":
			return a - b;

		case "×":
			return a * b;

		case "÷":
			return a / b;

		case "%":
			return a % b;
		default:
			return 0;
		}
	}

}
