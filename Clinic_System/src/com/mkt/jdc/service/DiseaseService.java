package com.mkt.jdc.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mkt.jdc.model.Disease;
import com.mkt.jdc.model.Doctor;
import com.mkt.jdc.model.Patient;
import com.mkt.jdc.util.ClinicException;

import static com.mkt.jdc.util.ConnectionManager.getConnection;

public class DiseaseService {

	public void save(List<Disease> ds) {

		String sql = "insert into Diseases_tbl values(?,?,?,?,?,?,?)";
		try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

			for (Disease d : ds) {

				stmt.setInt(1, d.getPatient().getId());
				stmt.setInt(2, d.getDoctor().getId());
				stmt.setString(3, d.getDis());
				stmt.setString(4, d.getMedicine());
				stmt.setDouble(5, d.getCharge());
				stmt.setString(6, d.getRemark());
				stmt.setDate(7, Date.valueOf(d.getDt_check()));

				stmt.executeUpdate();
			}

		} catch (Exception e) {
			throw new ClinicException("DiseasesService save error");
		}
	}

	public List<Disease> find(Disease dis, LocalDate from, LocalDate to) {

		StringBuffer sb = new StringBuffer(
				" select d.name,p.name,dis.Diseases,dis.medicine,dis.charge,dis.dt_check,dis.remark from Diseases_tbl dis join Patient_tbl p on dis.patient_id=p.id join Doctor_tbl d on dis.doctor_id=d.id where 1=1");
		List<Disease> list = new ArrayList<Disease>();
		List<Object> temp = new ArrayList<Object>();

		if (dis != null) {

			if (dis.getDoctor() != null) {
				sb.append(" and d.name=?");
				temp.add(dis.getDoctor().getName());
			}
			if (dis.getPatient() != null) {
				sb.append(" and p.name=?");
				temp.add(dis.getPatient().getName());
			}

		}
		if (from != null) {
			sb.append(" dis.dt_check >=?");
			temp.add(from);
		}
		if (to != null) {
			sb.append(" dis.dt_check <=?");
			temp.add(to);
		}

		try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sb.toString())) {

			
			for(int x=0;x<temp.size();x++) {
				stmt.setObject(x+1, temp.get(x));
			}
			
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next()) {
				Doctor doc=new Doctor();
				doc.setName(rs.getString("d.name"));
				Patient p=new Patient();
				p.setName(rs.getString("p.name"));
				Disease disease=new Disease();
				disease.setDoctor(doc);
				disease.setPatient(p);
				disease.setDis(rs.getString("dis.Diseases"));
				disease.setMedicine(rs.getString("dis.medicine"));
				disease.setCharge(rs.getDouble("dis.charge"));
				disease.setDt_check(rs.getDate("dis.dt_check").toLocalDate());
				
				list.add(disease);
				
			}
			
		} catch (Exception e) {
			throw new ClinicException("DiseasesService save error");
		}
		return list;
	}

}
