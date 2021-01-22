package com.mkt.jdc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonService {

	private static final String url = "jdbc:mysql://localhost:3306/test1_db";
	private static final String user = "root";
	private static final String pass = "admin123";

	public void save(Person p) {

		String sql = "insert into person_tbl(age,name,dob)values(?,?,?)";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(2, p.getName());
			stmt.setInt(1, p.getAge());
			stmt.setDate(3, Date.valueOf(p.getDob()));

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Person> findAll() {
		List<Person> list = new ArrayList<Person>();
		String sql = "select * from person_tbl";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement stmt = con.prepareStatement(sql)) {

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Person p = new Person();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setAge(rs.getInt("age"));
				p.setDob(rs.getDate("dob").toLocalDate());

				list.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void delete(String id) {
		String sql = "delete from person_tbl where id=?";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, Integer.parseInt(id));
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void update(Person p) {
		String sql = "update person_tbl  set name=? ,age=?, dob=? where id=?";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement stmt = con.prepareStatement(sql)) {
			
			stmt.setString(1, p.getName());
			stmt.setInt(2, p.getAge());
			stmt.setDate(3, Date.valueOf(p.getDob()));
			stmt.setInt(4, p.getId());
			
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
