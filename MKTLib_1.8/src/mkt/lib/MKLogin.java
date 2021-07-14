package mkt.lib;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import mkt.lib.login.LoginImpl;

public interface  MKLogin extends MKModel{

	  Parent Login();

	  HBox password();
	  
	  
	  public static MKLogin getInstance(String lb, String front_color,
				String back_color, String path) {
		  
		  return new LoginImpl(lb,front_color,back_color,path);
	  }
	
}
