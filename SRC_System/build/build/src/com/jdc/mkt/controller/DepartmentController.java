package com.jdc.mkt.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.mkt.model.Department;
import com.jdc.mkt.service.DepartmentService;
import com.jdc.mkt.util.MyException;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.shape.SVGPath;
import mkt.lib.MKMessage;
import mkt.lib.MKMessage.MessageType;

public class DepartmentController implements Initializable{

    @FXML
    private TextField tf_dep;

    @FXML
    private TextField tf_id;

    @FXML
    private Label lb_save;

    @FXML
    private SVGPath lb_delete;

    @FXML
    private SVGPath lb_clear;

    @FXML
    private TableView<Department> tv_department;

    @FXML
    private TableColumn<Department, Integer> col_num;
    private DepartmentService service;
    private MKMessage message;
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service=new DepartmentService();
		message=MKMessage.getInstance(DController.getMessageLabel());
		col_num.setCellValueFactory(col->new ReadOnlyObjectWrapper<Integer>(tv_department.getItems().indexOf(col.getValue())+1));
		lb_save.setOnMouseClicked(e->saveDep());
		lb_delete.setOnMouseClicked(e->deleteDep());
		lb_clear.setOnMouseClicked(e->clear());
		tv_department.setOnMouseClicked(e->{
			if(e.getClickCount()==2) {
				Department d=tv_department.getSelectionModel().getSelectedItem();
				tf_id.setText(d.getId()+"");
				tf_dep.setText(d.getName());
			}
		});

		if(null != service.getAllDep()) {
			showTable();
		}
	}
	
	void showTable() {
		tv_department.getItems().clear();
		tv_department.getItems().addAll(service.getAllDep());
		
	}

	private void clear() {
		tf_dep.clear();
	}

	private void deleteDep() {
		Department d=tv_department.getSelectionModel().getSelectedItem();
		d.setActive(false);
		service.updateDep(d);
		message.showMessage("successfully deleted department !", MessageType.SUCCESS);
		showTable();
	}

	private void saveDep() {
		try {
			if(tf_dep.getText().isEmpty() || tf_dep.getText()==null) {
				throw new MyException("Please type department !");
			}
			
			service.saveDep(tf_dep.getText());
			showTable();
			message.showMessage("successfully save department !", MessageType.SUCCESS);
			
		}catch (Exception e) {
			e.printStackTrace();
			message.showMessage(e.getMessage(), MessageType.ERROR);
		}
	}
	

}
