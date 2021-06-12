package com.jdc.mkt.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.mkt.util.MyException;
import com.jdc.mkt.util.SetupDb;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mkt.lib.MKMessage;
import mkt.lib.MKMessage.MessageType;

public class DbFormController implements Initializable {
	
		@FXML
		private TextField tf_user;
		@FXML
		private TextField tf_pass;
		@FXML
		private Label lb_message;
		private MKMessage message;

		
		public static void showDbForm() {
			
			try {
				Parent root = FXMLLoader.load(DbFormController.class.getResource("DbForm.fxml"));
				Stage st = new Stage();
				st.setScene(new Scene(root));
				st.showAndWait();
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		@FXML
		private void enter() {
			try {
				if(tf_user.getText().isEmpty() || tf_user.getText()==null) {
					throw new MyException("Please type mysql user name !");
				}
				if(tf_pass.getText().isEmpty() || tf_pass.getText()==null) {
					throw new MyException("Please type mysql password !");
				}				
				
				SetupDb.createTable(tf_user.getText(),tf_pass.getText());
				cancel();
				
			}catch (Exception e) {
				message.showMessage(e.getMessage(), MessageType.ERROR);
			}
		}
		@FXML
		private void cancel() {
			tf_pass.getScene().getWindow().hide();
		}
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			message=MKMessage.getInstance(lb_message);
			
		}
	}

