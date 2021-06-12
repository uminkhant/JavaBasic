package com.jdc.mkt.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.mkt.model.User;
import com.jdc.mkt.service.UserService;
import com.jdc.mkt.util.MyException;
import com.jdc.mkt.util.UserRole;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.shape.SVGPath;
import mkt.lib.MKMessage;
import mkt.lib.MKMessage.MessageType;

public class UserController implements Initializable {

	@FXML
	private ComboBox<UserRole> cbo_role;

	@FXML
	private TextField tf_id;

	@FXML
	private Label lb_save;

	@FXML
	private SVGPath lb_delete;

	@FXML
	private SVGPath lb_clear;

	@FXML
	private TextField tf_name;

	@FXML
	private TextField tf_loginId;

	@FXML
	private PasswordField pf_pass;

	@FXML
	private PasswordField pf_confirm;

	@FXML
	private TableView<User> tv_user;

	@FXML
	private TableColumn<User, Integer> col_num;

	private UserService service;
	private MKMessage message;
	private User user;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		cbo_role.getItems().addAll(UserRole.values());
		service = new UserService();
		message = MKMessage.getInstance(DController.getMessageLabel());
		col_num.setCellValueFactory(
				col -> new ReadOnlyObjectWrapper<Integer>(tv_user.getItems().indexOf(col.getValue()) + 1));

		lb_save.setOnMouseClicked(e -> save());
		lb_delete.setOnMouseClicked(e -> delete());
		lb_clear.setOnMouseClicked(e -> clear());
		tv_user.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
				tf_loginId.setDisable(true);
				user = tv_user.getSelectionModel().getSelectedItem();
				tf_loginId.setText(user.getLoginId());
				tf_id.setText(user.getId() + "");
				tf_name.setText(user.getName());
				pf_pass.setText(user.getPassword());
				cbo_role.setValue(user.getRole());
			}
		});

		if(null!=service.getAllUser()) {viewTable();}
	}

	private void delete() {
		user = tv_user.getSelectionModel().getSelectedItem();
		user.setActive(false);
		service.updateUser(user);
		viewTable();
		message.showMessage("successfully deleted user !", MessageType.SUCCESS);
	}

	private void save() {
		try {
			if (tf_name.getText().isEmpty() || tf_name.getText() == null) {
				throw new MyException("Please type user name ");
			}
			if (tf_loginId.getText().isEmpty() || tf_loginId.getText() == null) {
				throw new MyException("Please type LoginId ");
			}

			if (pf_pass.getText().isEmpty() || pf_pass.getText() == null) {
				throw new MyException("Please type Password ");
			}
			if (!pf_confirm.getText().equals(pf_pass.getText())) {
				throw new MyException("Please confirm again ! ");
			}
			user = new User();
			user.setActive(true);
			user.setLoginId(tf_loginId.getText());
			user.setName(tf_name.getText());
			user.setPassword(pf_pass.getText());
			user.setRole(cbo_role.getValue());

			if (tf_id.getText().isEmpty() || tf_id.getText() == null) {

				if (service.getUserByName(tf_loginId.getText()) != null) {
					throw new MyException("Login ID alerady exist,plase try another .");
				}
				service.saveUser(user);
				message.showMessage("successfully save user !", MessageType.SUCCESS);
			} else {

				user.setId(Integer.parseInt(tf_id.getText()));
				service.updateUser(user);
				message.showMessage("successfully updated user !", MessageType.SUCCESS);
			}

			viewTable();
			clear();

		} catch (Exception e) {
			message.showMessage(e.getMessage(), MessageType.ERROR);
		}
	}

	private void viewTable() {
		tv_user.getItems().clear();
		tv_user.getItems().addAll(service.getAllUser());

	}

	private void clear() {
		tf_id.clear();
		tf_loginId.clear();
		tf_name.clear();
		pf_pass.clear();
		pf_confirm.clear();
		tf_loginId.setDisable(false);
		cbo_role.getSelectionModel().clearSelection();
	}

}
