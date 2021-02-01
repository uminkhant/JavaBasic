package com.mkt.jdc.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.mkt.jdc.model.Doctor;
import com.mkt.jdc.model.Patient;
import com.mkt.jdc.service.DoctorService;
import com.mkt.jdc.service.PatientService;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;
import mkt.lib.MKMessage;

public class DashBoardController implements Initializable{

    @FXML
    private HBox hb_doc;

    @FXML
    private Button btn_prev;

    @FXML
    private Button btn_next;

    @FXML
    private TextField tf_patient;
    @FXML
    private TextField tf_diseases;

    @FXML
    private TableView<Patient> tv_patient;
    @FXML
    private ComboBox<Patient>cbo_patient;
    @FXML
    private Label lb_message;

    @FXML
    private TableColumn<Patient, Integer> col_num;

    @FXML
    private PieChart pi_count;
    private PatientService pService;
    private DoctorService dService;
    private MKMessage mm;
    private List<Doctor>doc_list;
    private int doc_qty;

    @FXML
    void addPatient() {
    	//MainController.getPane().getChildren().clear();
    }

    @FXML
    void newPatient() {

    }
    
    @FXML
    void savePatient() {

    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		pService=new PatientService();
		dService=new DoctorService();
		mm=MKMessage.getInstance(lb_message);
		
		col_num.setCellValueFactory(col->new ReadOnlyObjectWrapper<Integer>(tv_patient.getItems().indexOf(col.getValue())+1));
	
		doc_list=dService.findAll();
		doc_qty=dService.findAll().size()-1;
		cbo_patient.getItems().addAll(pService.findAll());
		
		for (Doctor doctor : doc_list) {
			DBox db=new DBox(doctor.getName());
			hb_doc.getChildren().add(db);
		}
	
	}
	
	private void loadView(String name) {
		
		System.out.println(name);
	}
	
	
	private class DBox extends HBox{
		
		DBox(String name){
			
			SVGPath path=new SVGPath();
			path.setContent("M6 21c0 0.547-0.453 1-1 1s-1-0.453-1-1 0.453-1 1-1 1 0.453 1 1zM22 21.953c0 2.547-1.672 4.047-4.172 4.047h-13.656c-2.5 0-4.172-1.5-4.172-4.047 0-3.109 0.625-8.016 4.344-8.828-0.25 0.594-0.344 1.234-0.344 1.875v3.172c-1.203 0.422-2 1.563-2 2.828 0 1.656 1.344 3 3 3s3-1.344 3-3c0-1.266-0.812-2.406-2-2.828v-3.172c0-0.516 0.047-1.031 0.391-1.453 1.313 1.031 2.938 1.625 4.609 1.625s3.297-0.594 4.609-1.625c0.344 0.422 0.391 0.938 0.391 1.453v1c-2.203 0-4 1.797-4 4v1.391c-0.313 0.281-0.5 0.688-0.5 1.109 0 0.828 0.672 1.5 1.5 1.5s1.5-0.672 1.5-1.5c0-0.422-0.187-0.828-0.5-1.109v-1.391c0-1.094 0.906-2 2-2s2 0.906 2 2v1.391c-0.313 0.281-0.5 0.688-0.5 1.109 0 0.828 0.672 1.5 1.5 1.5s1.5-0.672 1.5-1.5c0-0.422-0.187-0.828-0.5-1.109v-1.391c0-1.422-0.766-2.75-2-3.453 0-1.141 0.109-2.359-0.344-3.422 3.719 0.812 4.344 5.719 4.344 8.828zM17 8c0 3.313-2.688 6-6 6s-6-2.688-6-6 2.688-6 6-6 6 2.688 6 6zM6 21c0 0.547-0.453 1-1 1s-1-0.453-1-1 0.453-1 1-1 1 0.453 1 1zM22 21.953c0 2.547-1.672 4.047-4.172 4.047h-13.656c-2.5 0-4.172-1.5-4.172-4.047 0-3.109 0.625-8.016 4.344-8.828-0.25 0.594-0.344 1.234-0.344 1.875v3.172c-1.203 0.422-2 1.563-2 2.828 0 1.656 1.344 3 3 3s3-1.344 3-3c0-1.266-0.812-2.406-2-2.828v-3.172c0-0.516 0.047-1.031 0.391-1.453 1.313 1.031 2.938 1.625 4.609 1.625s3.297-0.594 4.609-1.625c0.344 0.422 0.391 0.938 0.391 1.453v1c-2.203 0-4 1.797-4 4v1.391c-0.313 0.281-0.5 0.688-0.5 1.109 0 0.828 0.672 1.5 1.5 1.5s1.5-0.672 1.5-1.5c0-0.422-0.187-0.828-0.5-1.109v-1.391c0-1.094 0.906-2 2-2s2 0.906 2 2v1.391c-0.313 0.281-0.5 0.688-0.5 1.109 0 0.828 0.672 1.5 1.5 1.5s1.5-0.672 1.5-1.5c0-0.422-0.187-0.828-0.5-1.109v-1.391c0-1.422-0.766-2.75-2-3.453 0-1.141 0.109-2.359-0.344-3.422 3.719 0.812 4.344 5.719 4.344 8.828zM17 8c0 3.313-2.688 6-6 6s-6-2.688-6-6 2.688-6 6-6 6 2.688 6 6z");
			path.getStyleClass().add("ico_color");
			Label lb=new Label(name);
			lb.getStyleClass().add("element_color");
			getChildren().addAll(path,lb);
			setSpacing(5);
			setAlignment(Pos.CENTER);
			getStyleClass().add("button_color");
			setPrefSize(150, 50);
			
			setOnMouseClicked(e->loadView(name));
		}

		
		
	}

}


