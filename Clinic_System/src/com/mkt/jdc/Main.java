package com.mkt.jdc;

import com.mkt.jdc.controller.LoginController;

import javafx.application.Application;
import javafx.stage.Stage;

@SuppressWarnings("exports")
public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		LoginController login=new LoginController();
		login.showForm(primaryStage);
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}