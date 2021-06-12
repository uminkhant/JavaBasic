package com.jdc.mkt.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jdc.mkt.model.Department;
import com.jdc.mkt.util.MyException;

import static com.jdc.mkt.util.ConnectionManager.getConnetion;

public class DepartmentService {

	public List<Department> getAllDep() {
		
		String sql = "select * from `SRC_db`.department_tbl where active=1";
		List<Department>list=new ArrayList<Department>();
		try (Connection con = getConnetion();
				PreparedStatement stmt = con.prepareStatement(sql)) {
			
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				Department d=new Department();
				d.setId(rs.getInt("id"));
				d.setName(rs.getString("d_name"));
				d.setActive(rs.getBoolean("active"));
				list.add(d);
			}
			
			
		} catch (Exception e) {
			throw new MyException(e.getMessage());
		}
		return list;
	}

	public void saveDep(String text) {
		String sql = "insert into `SRC_db`.department_tbl (d_name,active)values (?,?)";
		try (Connection con = getConnetion();
				PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, text);
			stmt.setBoolean(2, true);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			throw new MyException(e.getMessage());
		}
	}

	public void updateDep(Department d) {
		String sql = "update `SRC_db`.department_tbl set d_name=?,active=? where id=?";
		try (Connection con = getConnetion();
				PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, d.getName());
			stmt.setBoolean(2, d.isActive());
			stmt.setInt(3, d.getId());
			stmt.executeUpdate();
			
		} catch (Exception e) {
			throw new MyException(e.getMessage());
		}
		
	}

}
