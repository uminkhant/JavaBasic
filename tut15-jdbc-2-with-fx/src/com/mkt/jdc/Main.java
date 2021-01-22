package com.mkt.jdc;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Main extends Application implements Initializable {

	@FXML
	private TextField tf_name;
	@FXML
	private TextField tf_id;

	@FXML
	private TextField tf_age;

	@FXML
	private DatePicker dtp_dob;

	@FXML
	private TableView<Person> tv_person;

	@FXML
	private TableColumn<Person, Integer> col_num;

	@FXML
	private TableColumn<Person, String> col_name;

	@FXML
	private TableColumn<Person, Integer> col_age;

	@FXML
	private TableColumn<Person, LocalDate> col_dob;
	private PersonService service;
	private Person p;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = new PersonService();
		col_name.setCellValueFactory(new PropertyValueFactory("name"));
		col_age.setCellValueFactory(new PropertyValueFactory("age"));
		col_dob.setCellValueFactory(new PropertyValueFactory("dob"));

		col_num.setCellValueFactory(
				e -> new ReadOnlyObjectWrapper<Integer>(tv_person.getItems().indexOf(e.getValue()) + 1));

		tv_person.setOnMouseClicked(e -> {

			if (e.getClickCount() == 2) {
				p = tv_person.getSelectionModel().getSelectedItem();
				tf_id.setText(String.valueOf(p.getId()));
				tf_name.setText(p.getName());
				tf_age.setText(String.valueOf(p.getAge()));
				dtp_dob.setValue(p.getDob());
			}
		});
		loadView();

	}

	private void loadView() {
		tv_person.getItems().clear();
		tv_person.getItems().addAll(service.findAll());
	}

	@FXML
	void save() {

		p = new Person();
		p.setName(tf_name.getText());
		p.setAge(Integer.parseInt(tf_age.getText()));
		p.setDob(dtp_dob.getValue());

		if (tf_id.getText() != null && !tf_id.getText().isEmpty()) {
			
			p.setId(Integer.parseInt(tf_id.getText()));
			service.update(p);

		} else {
			service.save(p);
		}

		loadView();
		clear();

	}

	@FXML
	void delete() {
		if (tf_id.getText() != null && !tf_id.getText().isEmpty()) {
			service.delete(tf_id.getText());
			loadView();
			clear();
		}
	}

	private void clear() {
		p = null;
		tf_id.clear();
		tf_name.clear();
		tf_age.clear();
		dtp_dob.setValue(LocalDate.now());

	}

	@Override
	public void start(@SuppressWarnings("exports") Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Person.fxml"));
		stage.setScene(new Scene(root));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
