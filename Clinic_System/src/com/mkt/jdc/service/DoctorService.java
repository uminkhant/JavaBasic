package com.mkt.jdc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mkt.jdc.model.Doctor;
import com.mkt.jdc.model.Gender;
import com.mkt.jdc.model.User;
import com.mkt.jdc.model.User.Role;
import com.mkt.jdc.util.ClinicException;

import static com.mkt.jdc.util.ConnectionManager.getConnection;

public class DoctorService {

	public List<Doctor> findAll() {
		String sql="select d.id,d.name,d.gender,d.contact,d.specialist,d.active,u.id,u.name,u.loginId,u.password,u.role from Doctor_tbl d join User_tbl u on d.user_id=u.id where d.active=1";
		List<Doctor>list=new ArrayList<Doctor>();
		
		try(Connection con=getConnection();PreparedStatement stmt=con.prepareStatement(sql)){
			
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next()) {
				Doctor d=new Doctor();
				User u=new User();
				u.setId(rs.getInt("u.id"));
				u.setName(rs.getString("u.name"));
				u.setLoginId(rs.getString("u.loginId"));
				u.setPassword(rs.getString("u.password"));
				u.setRole(Role.valueOf(rs.getString("u.role")));
				
				d.setUser(u);
				d.setId(rs.getInt("d.id"));
				d.setName(rs.getString("d.name"));
				d.setGender(Gender.valueOf(rs.getString("d.gender")));
				d.setContact(rs.getString("d.contact"));
				d.setSpecialist(rs.getString("d.specialist"));
				d.setActive(rs.getBoolean("d.active"));
				
				list.add(d);
			}
		}catch (Exception e) {
			throw new ClinicException(e.getMessage());
		}
		return list;
	}

	public void save(Doctor d) {
		String sql="insert into Doctor_tbl (name,gender,contact,specialist,user_id,active)values(?,?,?,?,?,?)";
		try(Connection con=getConnection();PreparedStatement stmt=con.prepareStatement(sql)){
			
			stmt.setString(1, d.getName());
			stmt.setString(2, d.getGender().name());
			stmt.setString(3, d.getContact());
			stmt.setString(4, d.getSpecialist());
			stmt.setInt(5, d.getUser().getId());
			stmt.setBoolean(6, d.isActive());
			
			stmt.executeUpdate();
			
		}catch (Exception e) {
			throw new ClinicException(e.getMessage());
		}
	}

	public void update(Doctor d) {
		String sql="update Doctor_tbl set gender=?,contact=?,specialist=?,user_id=?,active=? where id=?";
		try(Connection con=getConnection();PreparedStatement stmt=con.prepareStatement(sql)){
			
			stmt.setString(1, d.getGender().name());
			stmt.setString(2, d.getContact());
			stmt.setString(3, d.getSpecialist());
			stmt.setInt(4, d.getUser().getId());
			stmt.setBoolean(5, d.isActive());
			stmt.setInt(6, d.getId());
			
			stmt.executeUpdate();
			
		}catch (Exception e) {
			throw new ClinicException(e.getMessage());
		}
	}

}
