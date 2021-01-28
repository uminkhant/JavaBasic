package com.mkt.jdc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mkt.jdc.model.Gender;
import com.mkt.jdc.model.Patient;
import com.mkt.jdc.util.ClinicException;

import static com.mkt.jdc.util.ConnectionManager.getConnection;

public class PatientService {

	public List<Patient> findAll() {
		String sql = "select * from Patient_tbl where active=1";
		List<Patient>list=new ArrayList<Patient>();

		try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next()) {
				Patient p=new Patient();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setAge(rs.getInt("age"));
				p.setContact(rs.getString("contact"));
				p.setAddress(rs.getString("address"));
				p.setGender(Gender.valueOf(rs.getString("gender")));
				p.setMarried(rs.getBoolean("married"));
				p.setActive(rs.getBoolean("active"));
				
				list.add(p);
			}

		} catch (Exception e) {
			throw new ClinicException(e.getMessage());
		}
		return list;
	}

	public void save(Patient p) {
		String sql = "insert into Patient_tbl(name,age,contact,address,gender,married,active)values(?,?,?,?,?,?,?)";

		try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, p.getName());
			stmt.setInt(2, p.getAge());
			stmt.setString(3, p.getContact());
			stmt.setString(4,p.getAddress());
			stmt.setString(5, p.getGender().name());
			stmt.setBoolean(6, p.isMarried());
			stmt.setBoolean(7, p.isActive());
			
			stmt.executeUpdate();
			
		} catch (Exception e) {
			throw new ClinicException(e.getMessage());
		}

	}

	public void update(Patient p) {
		String sql = "update Patient_tbl set age=?,contact=?,address=?,gender=?,married=?,active=? where id=?";

		try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

			
			stmt.setInt(1, p.getAge());
			stmt.setString(2, p.getContact());
			stmt.setString(3,p.getAddress());
			stmt.setString(4, p.getGender().name());
			stmt.setBoolean(5, p.isMarried());
			stmt.setBoolean(6, p.isActive());
			stmt.setInt(7, p.getId());
			
			stmt.executeUpdate();
			
		} catch (Exception e) {
			throw new ClinicException(e.getMessage());
		}

	}

}
