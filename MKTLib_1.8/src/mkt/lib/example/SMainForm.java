package mkt.lib.example;

import javafx.application.Application;
import javafx.stage.Stage;

public class SMainForm extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Sample sp=new Sample();
		sp.sampleForm(primaryStage);
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
