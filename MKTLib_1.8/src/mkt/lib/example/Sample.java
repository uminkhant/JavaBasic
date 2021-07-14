package mkt.lib.example;


import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mkt.lib.MKLogin;

public class Sample{

	Label lb_logo = new Label("Login Form");
	private String front_color="red";
	private String back_color="blue";

	public void sampleForm(Stage stage) {
		
		MKLogin mk = MKLogin.getInstance("Hello",front_color, back_color, null);
		Parent root = mk.Login();
		stage.setScene(new Scene(root));
		stage.show();
		mk.getEnter().setOnAction(e -> mk.getMessage().setText(mk.getTextField().getText()));
		mk.getCancel().setOnAction(e -> Platform.exit());
	}

	

	
}