package com.jdc.mkt.util;

import static com.jdc.mkt.util.ConnectionManager.getConnetion;

import java.sql.Connection;
import java.sql.Statement;

import com.jdc.mkt.service.db.ConfigurtaionObj;

public class SetupDb {
	
	
	public static void createTable(String user,String pass) {
	
		ConfigurtaionObj cobj=new ConfigurtaionObj();
		cobj.setDat(user, pass);
		cobj.getDat();
		
		String DROP_DB=" DROP DATABASE IF EXISTS `SRC_db`";

		String DB = " CREATE DATABASE IF NOT EXISTS SRC_db ";

		String USER_TBL = "  CREATE TABLE IF NOT EXISTS `SRC_db`.`user_tbl` ( `id` INT(11) NOT NULL AUTO_INCREMENT,"
				+ "  `u_name` VARCHAR(45) NOT NULL,  `loginId` VARCHAR(45) NOT NULL,"
				+ "  `password` VARCHAR(45) NULL DEFAULT NULL,  `role` VARCHAR(45) NULL DEFAULT NULL,"
				+ "  `active` TINYINT(4) NULL DEFAULT NULL,  PRIMARY KEY (`id`)) ENGINE = InnoDB"
				+ "   DEFAULT CHARACTER SET = utf8; ";

		String SERVICE_TBL = "CREATE TABLE IF NOT EXISTS `src_db`.`service_tbl` ("
				+ "  `id` INT(11) NOT NULL AUTO_INCREMENT,"
				+ "  `department_id` INT(11) NOT NULL,"
				+ "  `error` VARCHAR(45) NULL DEFAULT NULL,"
				+ "  `service` VARCHAR(45) NULL DEFAULT NULL,"
				+ "  `remark` VARCHAR(45) NULL DEFAULT NULL,"
				+ "  `service_dt` DATETIME NULL DEFAULT NULL,"
				+ "  PRIMARY KEY (`id`, `department_id`),"
				+ "  INDEX `fk_service_tbl_department_tbl_idx` (`department_id` ASC) VISIBLE,"
				+ "  CONSTRAINT `fk_service_tbl_department_tbl`"
				+ "    FOREIGN KEY (`department_id`)\n"
				+ "    REFERENCES `src_db`.`department_tbl` (`id`)"
				+ "    ON DELETE NO ACTION "
				+ "    ON UPDATE NO ACTION) "
				+ " ENGINE = InnoDB"
				+ " DEFAULT CHARACTER SET = utf8; ";
		
		String DEPARTMENT_TBL = " CREATE TABLE IF NOT EXISTS `SRC_db`.`department_tbl` ("
				+ "  `id` INT(11) NOT NULL AUTO_INCREMENT, `d_name` VARCHAR(45) NOT NULL,"
				+ "  `active` TINYINT(4) NULL DEFAULT NULL,  PRIMARY KEY (`id`)) ENGINE = InnoDB"
				+ "  DEFAULT CHARACTER SET = utf8;";
		
		
		try (Connection con = getConnetion(); Statement stmt = con.createStatement()) {

			stmt.executeUpdate(DROP_DB);
			stmt.executeUpdate(DB);
			stmt.executeUpdate(DEPARTMENT_TBL);
			stmt.executeUpdate(SERVICE_TBL);
			stmt.executeUpdate(USER_TBL);
			
			
			
			System.out.println("successfully created db !");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
}
