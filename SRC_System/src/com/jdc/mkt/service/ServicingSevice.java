package com.jdc.mkt.service;

import com.jdc.mkt.model.Department;
import com.jdc.mkt.model.Servicing;
import com.jdc.mkt.util.MyException;

import static com.jdc.mkt.util.ConnectionManager.getConnetion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServicingSevice {

	public List<Servicing> getAllService() {
		String sql = "select s.id,s.department_id,s.error,s.service,s.remark,s.service_dt,d.id,d.d_name,d.active from `SRC_db`.service_tbl s left join `SRC_db`.department_tbl d on s.department_id=d.id";
		List<Servicing> list = new ArrayList<>();
		try (Connection con = getConnetion(); PreparedStatement stmt = con.prepareStatement(sql)) {

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Servicing s = new Servicing();
				Department d = new Department();
				d.setId(rs.getInt("d.id"));
				d.setName(rs.getString("d.d_name"));
				d.setActive(rs.getBoolean("d.active"));
				s.setId(rs.getInt("s.id"));
				s.setDepartment(d);
				s.setError(rs.getString("s.error"));
				s.setService(rs.getString("s.service"));
				s.setRemark(rs.getString("s.remark"));
				s.setService_dt(rs.getDate("s.service_dt").toLocalDate());
				list.add(s);

			}

		} catch (Exception e) {
			throw new MyException(e.getMessage());
		}
		return list;
	}

	public void saveServicing(Servicing se) {
		String sql = "insert into `src_db`.service_tbl (department_id,error,service,remark,service_dt) values(?,?,?,?,?);";
		try (Connection con = getConnetion(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, se.getDepartment().getId());
			stmt.setString(2, se.getError());
			stmt.setString(3, se.getService());
			stmt.setString(4, se.getRemark());
			stmt.setDate(5, Date.valueOf(se.getService_dt()));
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(e.getMessage());
		}
	}

}
