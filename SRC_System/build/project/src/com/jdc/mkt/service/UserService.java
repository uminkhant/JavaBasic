package com.jdc.mkt.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdc.mkt.model.User;
import com.jdc.mkt.util.MyException;
import com.jdc.mkt.util.UserRole;

import static com.jdc.mkt.util.ConnectionManager.getConnetion;

public class UserService {
	private static final String init_name = "jdc", init_pass = "admin";

	public User getUserByName(String name) {
		String sql = "select * from `SRC_db`.user_tbl where loginId=? and active=true ";
		try (Connection con = getConnetion(); PreparedStatement stmt = con.prepareStatement(sql)) {
			
			stmt.setString(1, name);
			ResultSet rs= stmt.executeQuery();
			while(rs.next()) {
				User u=new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("u_name"));
				u.setLoginId(rs.getString("loginId"));
				u.setPassword(rs.getString("password"));
				u.setRole(UserRole.valueOf(rs.getString("role")));
				return u;
			}
		
		} catch (SQLException e) {

			if (name.equals(init_name)) {
				User us = new User();
				us.setLoginId(init_name);
				us.setPassword(init_pass);
				us.setRole(UserRole.ADMIN);
				return us;
			} else {
				throw new MyException(e.getMessage());
			}
		}
		return null;
	}

	public List<User> getAllUser() {
		String sql = "select * from `SRC_db`.user_tbl where active=1";
		List<User>list=new ArrayList<User>();
		try (Connection con = getConnetion(); PreparedStatement stmt = con.prepareStatement(sql)) {
			
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				User u=new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("u_name"));
				u.setLoginId(rs.getString("loginId"));
				u.setPassword(rs.getString("password"));
				u.setRole(UserRole.valueOf(rs.getString("role")));
				list.add(u);
			}
			
			
		} catch (Exception e) {
			throw new MyException(e.getMessage());
		}
		return list;
	}

	public void saveUser(User user) {
		String sql="INSERT INTO `SRC_db`.user_tbl(u_name,loginId,password,role,active)values(?,?,?,?,?)";
		try(Connection con=getConnetion();PreparedStatement stmt=con.prepareStatement(sql)){
			
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getLoginId());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getRole().name());
			stmt.setBoolean(5, user.isActive());
			stmt.executeUpdate();
			
		}catch (Exception e) {
			throw new MyException(e.getMessage());
		}
	}
	public void updateUser(User user) {
		String sql="update `SRC_db`.user_tbl set u_name=?,password=?,role=?,active=? where id=?";
		try(Connection con=getConnetion();PreparedStatement stmt=con.prepareStatement(sql)){
			
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getRole().name());
			stmt.setBoolean(4, user.isActive());
			stmt.setInt(5, user.getId());
			stmt.executeUpdate();
			
		}catch (Exception e) {
			throw new MyException(e.getMessage());
		}
	}

}
