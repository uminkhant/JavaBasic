package com.jdc.mkt;
	
import com.jdc.mkt.controller.Login;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage st) throws Exception {
		Login login=new Login();
		login.showLogin(st);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
