package com.mkt.jdc.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.mkt.jdc.model.User;
import com.mkt.jdc.model.User.Role;
import com.mkt.jdc.service.UserService;
import com.mkt.jdc.util.ClinicException;
import com.mkt.jdc.util.MessageManager;
import com.mkt.jdc.util.MessageManager.MessageType;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UserController implements Initializable {

	@FXML
	private TextField tf_user;

	@FXML
	private TextField tf_loginId;

	@FXML
	private ComboBox<Role> cbo_role;

	@FXML
	private PasswordField pf_pass;

	@FXML
	private PasswordField pf_confirm;

	@FXML
	private CheckBox chk_active;

	@FXML
	private TextField tf_id;
	@FXML
	private Label lb_message;

	@FXML
	private TableView<User> tv_user;
	@FXML
	private TableColumn<User, Integer> col_num;
	private UserService service;
	private MessageManager mm;
	private User user;

	@FXML
	void delete() {
		try {
			if (tf_id.getText().isEmpty() || tf_id.getText() == null) {
				throw new ClinicException("Please select one row !");
			}

			user.setActive(false);
			service.updateUser(user);
			mm.showMessage("Successfully delete user !", MessageType.SUCCESSFUL);
			loadView();
			clear();

		} catch (Exception e) {
			mm.showMessage(e.getMessage(), MessageType.ERROR);
		}
	}

	@FXML
	void save() {

		try {

			if (tf_user.getText().isEmpty() || tf_user.getText() == null) {
				throw new ClinicException("Please type user name !");
			}
			if (tf_loginId.getText().isEmpty() || tf_loginId.getText() == null) {
				throw new ClinicException("Please type loginID !");
			}
			if (pf_pass.getText().isEmpty() || pf_pass.getText() == null) {
				throw new ClinicException("Please type password !");
			}
			if (!pf_pass.getText().equals(pf_confirm.getText())) {
				throw new ClinicException("Please re_type confirm password !");
			}

			user = new User();
			user.setName(tf_user.getText());
			user.setLoginId(tf_loginId.getText());
			user.setPassword(pf_pass.getText());
			user.setRole(cbo_role.getValue() == null ? Role.Cashier : cbo_role.getValue());
			user.setActive(true);

			if (tf_id.getText().isEmpty() || tf_id.getText() == null) {
				service.saveUser(user);
				mm.showMessage("Successfully save user !", MessageType.SUCCESSFUL);
			} else {
				user.setId(Integer.parseInt(tf_id.getText()));
				service.updateUser(user);
				mm.showMessage("Successfully update user !", MessageType.SUCCESSFUL);
			}
			loadView();
			clear();

		} catch (Exception e) {
			mm.showMessage(e.getMessage(), MessageType.ERROR);
		}
	}

	private void clear() {
		tf_id.clear();
		tf_loginId.clear();
		tf_user.clear();
		pf_confirm.clear();
		pf_pass.clear();
		cbo_role.getSelectionModel().clearSelection();
		tf_loginId.setDisable(false);
		user = null;
	}

	private void loadView() {
		tv_user.getItems().clear();
		tv_user.getItems().addAll(service.findAll());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbo_role.getItems().addAll(Role.values());
		service = new UserService();
		mm = new MessageManager(lb_message);

		col_num.setCellValueFactory(col -> new ReadOnlyObjectWrapper(tv_user.getItems().indexOf(col.getValue()) + 1));
		loadView();
		clear();

		tv_user.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
				user = tv_user.getSelectionModel().getSelectedItem();
				tf_loginId.setDisable(true);
				tf_id.setText(String.valueOf(user.getId()));
				tf_loginId.setText(user.getLoginId());
				tf_user.setText(user.getName());
				pf_pass.setText(user.getPassword());
				chk_active.setSelected(user.isActive());
				cbo_role.setValue(user.getRole());
			}
		});

	}

}
