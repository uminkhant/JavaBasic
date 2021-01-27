module clinic_system {

	requires javafx.graphics;
	requires lombok;
	requires javafx.fxml;
	requires javafx.controls;
	requires java.sql;
	requires MKTLib;
	
	opens com.mkt.jdc.controller;
	opens com.mkt.jdc.model to javafx.base;
	exports com.mkt.jdc to  javafx.graphics;
	
}