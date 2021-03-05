package com.jdc.mkt;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentController implements Initializable{

	@FXML
	private TextField tf_reg;

	@FXML
	private TextField tf_name;

	@FXML
	private TextField tf_age;

	@FXML
	private TableView<Student> tv_student;
	
	private static int reg=0;

	@FXML
	void cancel() {

	}

	@FXML 
	void save() {
		reg++;
		tf_reg.setText(reg+"");
		Student st=new Student();
		st.setName(tf_name.getText());
		st.setAge(Integer.parseInt(tf_age.getText()));
		st.setId(reg);
		
		tv_student.getItems().add(st);
	}

	static void showForm() {
		Stage stage = new Stage();
		try {
			Parent root = FXMLLoader.load(StudentController.class.getResource("student.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

}
