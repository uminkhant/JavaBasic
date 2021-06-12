package com.jdc.mkt.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jdc.mkt.model.Department;
import com.jdc.mkt.model.Servicing;
import com.jdc.mkt.service.DepartmentService;
import com.jdc.mkt.service.ServicingSevice;
import com.jdc.mkt.util.MyException;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.SVGPath;
import mkt.lib.MKMessage;
import mkt.lib.MKMessage.MessageType;

public class ServicingController implements Initializable{

    @FXML
    private ComboBox<Department> cbo_department;

    @FXML
    private TextField tf_id;

    @FXML
    private Label lb_save;

    @FXML
    private SVGPath lb_delete;

    @FXML
    private SVGPath lb_clear;

    @FXML
    private TextArea tf_error;

    @FXML
    private TextArea tf_service;

    @FXML
    private TextArea tf_remark;

    @FXML
    private TableView<Servicing> tv_servicing;

    @FXML
    private TableColumn<Servicing, Integer> col_num;
    private ServicingSevice service;
    private DepartmentService dService;
    private MKMessage message;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service=new ServicingSevice();
		message = MKMessage.getInstance(DController.getMessageLabel());
		dService=new DepartmentService();
		
		cbo_department.getItems().addAll(dService.getAllDep());
		col_num.setCellValueFactory(col->new ReadOnlyObjectWrapper<Integer>(tv_servicing.getItems().indexOf(col.getValue())+1));
		lb_save.setOnMouseClicked(e->saveService());
		lb_delete.setOnMouseClicked(e->deleteService());
		lb_clear.setOnMouseClicked(e->clear());
		tv_servicing.setOnMouseClicked(e->{
			if(e.getClickCount()==2) {
				Servicing s=tv_servicing.getSelectionModel().getSelectedItem();
				tf_error.setText(s.getError());
				tf_id.setText(s.getId()+"");
				tf_remark.setText(s.getRemark());
				tf_service.setText(s.getService());
				tf_error.setDisable(true);
			}
		});
		
		if(null != service.getAllService()) {
			showTable();
		}
	}

	private void clear() {
		tf_error.clear();
		tf_id.clear();
		tf_remark.clear();
		tf_service.clear();
		cbo_department.getSelectionModel().clearSelection();
		tf_error.setDisable(false);
	}

	private void deleteService() {
		//Servicing s=tv_servicing.getSelectionModel().getSelectedItem();
		
		
	}

	private void saveService() {
		try {
			if(tf_service.getText().isEmpty() || tf_service.getText()==null) {
				throw new MyException("Please type servicing !");
			}
			if(tf_remark.getText().isEmpty() || tf_remark.getText()==null) {
				throw new MyException("Please type remark !");
			}
			if(tf_error.getText().isEmpty() || tf_error.getText()==null) {
				throw new MyException("Please type error !");
			}
			if(cbo_department.getValue()==null) {
				throw new MyException("Please select one department !");
			}
			
			Servicing se=new Servicing();
			
			se.setDepartment(cbo_department.getSelectionModel().getSelectedItem());
			se.setError(tf_error.getText());
			se.setService(tf_service.getText());
			se.setRemark(tf_remark.getText());
			se.setService_dt(LocalDate.now());
			service.saveServicing(se);
			message.showMessage("Successfully save servicing ", MessageType.SUCCESS);
			showTable();
			clear();
			
		}catch (Exception e) {
			message.showMessage(e.getMessage(), MessageType.ERROR);
		}
		
		
	}
	private void showTable() {
		tv_servicing.getItems().clear();
		tv_servicing.getItems().addAll(service.getAllService());
	}

}
