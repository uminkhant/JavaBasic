package com.mkt.jdc.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.mkt.jdc.model.Disease;
import com.mkt.jdc.model.Doctor;
import com.mkt.jdc.model.Patient;
import com.mkt.jdc.service.DiseaseService;
import com.mkt.jdc.service.DoctorService;
import com.mkt.jdc.service.PatientService;
import com.mkt.jdc.util.ClinicException;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import mkt.lib.MKMessage;
import mkt.lib.MKMessage.MessageType;

public class DashBoardController implements Initializable {

	@FXML
	private TilePane tp_doctor;

	@FXML
	private TextField tf_medicine;
	@FXML
	private TextField tf_diseases;
	@FXML
	private TextField tf_charge;
	@FXML
	private TextField tf_remark;

	@FXML
	private ComboBox<Patient> cbo_patient;

	@FXML
	private Label lb_message;

	@FXML
	private ProgressIndicator pi_patient;
	@FXML
	private TableColumn<Disease, Integer> col_num;
	@FXML
	private TableView<Disease> tv_patient;

	private PatientService pService;
	private DoctorService dService;
	private DiseaseService dis_service;
	private MKMessage mm;
	private Doctor doctor;

	@FXML
	void clear() {
		clearAll();
		tv_patient.getItems().clear();
	}

	private void clearAll() {
		doctor = null;
		tf_charge.clear();
		tf_diseases.clear();
		tf_medicine.clear();
		tf_remark.clear();
		cbo_patient.getSelectionModel().clearSelection();
	}

	@FXML
	void newPatient() {
		MainController.getMain().showSubForm("patient");

	}

	@FXML
	void addDisease() {

		try {
			if (doctor == null) {
				throw new ClinicException("Please select doctor first !");
			}
			if (cbo_patient.getValue() == null) {
				throw new ClinicException("Please select patient !");
			}

			if (tf_diseases.getText().isEmpty() || tf_diseases.getText() == null) {
				throw new ClinicException("Please type diseases !");
			}
			if (tf_medicine.getText().isEmpty() || tf_medicine.getText() == null) {
				throw new ClinicException("Please type medicine !");
			}
			if (tf_charge.getText().isEmpty() || tf_charge.getText() == null) {
				throw new ClinicException("Please type charge !");
			}

			Disease d = new Disease();
			d.setDis(tf_diseases.getText());
			d.setDoctor(doctor);
			d.setPatient(cbo_patient.getValue());
			d.setMedicine(tf_medicine.getText());
			d.setCharge(Double.parseDouble(tf_charge.getText()));
			d.setRemark(tf_remark.getText());
			d.setDt_check(LocalDate.now());

			tv_patient.getItems().add(d);
			clearAll();

		} catch (Exception e) {
			mm.showMessage(e.getMessage(), MessageType.ERROR);
		}
	}

	@FXML
	void save() {
		try {
			if (tv_patient.getItems().isEmpty()) {
				throw new ClinicException("There is no record patient !");
			}
			
			dis_service.save(tv_patient.getItems());
			clear();
			mm.showMessage("Successfully save all.", MessageType.SUCCESS);
			
		} catch (Exception e) {
			mm.showMessage(e.getMessage(), MessageType.ERROR);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		pService = new PatientService();
		dService = new DoctorService();
		dis_service=new DiseaseService();
		
		mm = MKMessage.getInstance(lb_message);

		cbo_patient.getItems().addAll(pService.findAll());

		for (Doctor doctor : dService.findAll()) {
			DBox db = new DBox(doctor);
			tp_doctor.getChildren().add(db);

		}

		col_num.setCellValueFactory(
				col -> new ReadOnlyObjectWrapper<Integer>(tv_patient.getItems().indexOf(col.getValue()) + 1));

		
	}

	private class DBox extends VBox {

		DBox(Doctor d) {
			SVGPath path = new SVGPath();
			path.setContent(
					"M6 21c0 0.547-0.453 1-1 1s-1-0.453-1-1 0.453-1 1-1 1 0.453 1 1zM22 21.953c0 2.547-1.672 4.047-4.172 4.047h-13.656c-2.5 0-4.172-1.5-4.172-4.047 0-3.109 0.625-8.016 4.344-8.828-0.25 0.594-0.344 1.234-0.344 1.875v3.172c-1.203 0.422-2 1.563-2 2.828 0 1.656 1.344 3 3 3s3-1.344 3-3c0-1.266-0.812-2.406-2-2.828v-3.172c0-0.516 0.047-1.031 0.391-1.453 1.313 1.031 2.938 1.625 4.609 1.625s3.297-0.594 4.609-1.625c0.344 0.422 0.391 0.938 0.391 1.453v1c-2.203 0-4 1.797-4 4v1.391c-0.313 0.281-0.5 0.688-0.5 1.109 0 0.828 0.672 1.5 1.5 1.5s1.5-0.672 1.5-1.5c0-0.422-0.187-0.828-0.5-1.109v-1.391c0-1.094 0.906-2 2-2s2 0.906 2 2v1.391c-0.313 0.281-0.5 0.688-0.5 1.109 0 0.828 0.672 1.5 1.5 1.5s1.5-0.672 1.5-1.5c0-0.422-0.187-0.828-0.5-1.109v-1.391c0-1.422-0.766-2.75-2-3.453 0-1.141 0.109-2.359-0.344-3.422 3.719 0.812 4.344 5.719 4.344 8.828zM17 8c0 3.313-2.688 6-6 6s-6-2.688-6-6 2.688-6 6-6 6 2.688 6 6zM6 21c0 0.547-0.453 1-1 1s-1-0.453-1-1 0.453-1 1-1 1 0.453 1 1zM22 21.953c0 2.547-1.672 4.047-4.172 4.047h-13.656c-2.5 0-4.172-1.5-4.172-4.047 0-3.109 0.625-8.016 4.344-8.828-0.25 0.594-0.344 1.234-0.344 1.875v3.172c-1.203 0.422-2 1.563-2 2.828 0 1.656 1.344 3 3 3s3-1.344 3-3c0-1.266-0.812-2.406-2-2.828v-3.172c0-0.516 0.047-1.031 0.391-1.453 1.313 1.031 2.938 1.625 4.609 1.625s3.297-0.594 4.609-1.625c0.344 0.422 0.391 0.938 0.391 1.453v1c-2.203 0-4 1.797-4 4v1.391c-0.313 0.281-0.5 0.688-0.5 1.109 0 0.828 0.672 1.5 1.5 1.5s1.5-0.672 1.5-1.5c0-0.422-0.187-0.828-0.5-1.109v-1.391c0-1.094 0.906-2 2-2s2 0.906 2 2v1.391c-0.313 0.281-0.5 0.688-0.5 1.109 0 0.828 0.672 1.5 1.5 1.5s1.5-0.672 1.5-1.5c0-0.422-0.187-0.828-0.5-1.109v-1.391c0-1.422-0.766-2.75-2-3.453 0-1.141 0.109-2.359-0.344-3.422 3.719 0.812 4.344 5.719 4.344 8.828zM17 8c0 3.313-2.688 6-6 6s-6-2.688-6-6 2.688-6 6-6 6 2.688 6 6z");
			path.getStyleClass().add("ico_color");

			Label lb = new Label(d.getName());
			lb.getStyleClass().add("element_color");
			lb.setStyle("-fx-font-size:15px");

			Label sp = new Label(d.getSpecialist());
			sp.getStyleClass().add("element_color");

			HBox hb = new HBox();
			hb.getChildren().addAll(path, lb);
			hb.setSpacing(2);
			hb.setAlignment(Pos.CENTER);

			getChildren().addAll(hb, sp);
			setSpacing(5);
			setPadding(new Insets(5));
			setAlignment(Pos.CENTER);
			getStyleClass().add("button_color");
			setPrefSize(175, 50);

			setOnMouseClicked(e -> doctor = d);

		}

	}

}
