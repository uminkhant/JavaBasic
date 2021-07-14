package mkt.lib;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

 interface MKModel {

	public TextField getTextField();
	public PasswordField getPasswordField();
	public Label getMessage();
	public Button getEnter();
	public Button getCancel();
}
