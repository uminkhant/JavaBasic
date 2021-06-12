package com.jdc.mkt.service.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.jdc.mkt.util.ConnectionManager;

public class ConfigurtaionObj {

	private static List<String> list;

	public ConfigurtaionObj() {
		list = new ArrayList<String>();
	}

	public void setDat(String name, String pass) {
		try (FileWriter fr = new FileWriter("configuration.txt"); BufferedWriter br = new BufferedWriter(fr)) {

			br.write("Mysql_user_name_:");
			br.append(name);
			br.newLine();
			br.write("Mysql_password__:");
			br.append(pass);

			System.out.println("create user name password");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getDat() {
		try (FileReader fr = new FileReader("configuration.txt"); BufferedReader br = new BufferedReader(fr)) {

			String str = "";
			while ((str = br.readLine()) != null) {
				String s = str.substring(17);
				list.add(s);
				
			}
			setCon();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkFileExist() {
		
		File f = new File("configuration.txt");

		return f.exists();
	}

	private static void setCon() {
		ConnectionManager con = new ConnectionManager();
		con.setUser(list.get(0));
		con.setPass(list.get(1));
	}

}
