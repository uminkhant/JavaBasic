package com.mkt.jdc.service;

import com.mkt.jdc.model.User;
import com.mkt.jdc.model.User.Role;
import com.mkt.jdc.util.ClinicException;

import static com.mkt.jdc.util.ConnectionManager.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserService {

	public User findByName(String text) {

		String sql = "select * from User_tbl where loginId=? and active=1";

		try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, text);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setRole(Role.valueOf(rs.getString("role")));
				user.setLoginId(rs.getString("loginId"));
				user.setPassword(rs.getString("password"));
				return user;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ClinicException(e.getMessage());
		}

		return null;
	}

	public List<User> findAll() {
		String sql = "select * from User_tbl where active=1";
		List<User> list = new ArrayList<User>();

		try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setRole(Role.valueOf(rs.getString("role")));
				user.setLoginId(rs.getString("loginId"));
				user.setPassword(rs.getString("password"));
				list.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ClinicException(e.getMessage());
		}
		return list;
	}

	public void saveUser(User user) {
		String sql = "insert into User_tbl (name,loginId,role,password,active)values(?,?,?,?,?)";

		try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getLoginId());
			stmt.setString(3, user.getRole().name());
			stmt.setString(4, user.getPassword());
			stmt.setBoolean(5, user.isActive());
			
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new ClinicException(e.getMessage());
		}
	}

	public void updateUser(User user) {
		
		String sql = "update  User_tbl set name=?,role=?,password=?,active=? where id=?";

		try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getRole().name());
			stmt.setString(3, user.getPassword());
			stmt.setBoolean(4, user.isActive());
			stmt.setInt(5,user.getId());
			
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new ClinicException(e.getMessage());
		}
	}

}
