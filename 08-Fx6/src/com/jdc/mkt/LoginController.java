package com.jdc.mkt;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField tf_name;

    @FXML
    private PasswordField pf_login;
    
    private static final String name="admin";
    private static final String pass="admin";

    @FXML
    void cancel() {
    	Platform.exit();
    }

    @FXML
    void enter() {
    	
    	if(tf_name.getText().equals(name) && pf_login.getText().equals(pass)) {
    		StudentController.showForm();
    	}else {
    		System.out.println("try again");
    	}
    	
    	
    }

}
