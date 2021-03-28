package com.jdc.mkt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{

	@Override
	public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {
		Parent root=FXMLLoader.load(getClass().getResource("calculator.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
