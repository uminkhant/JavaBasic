module myfxmodules {
	
	exports com.mkt.jdc ;
	
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	opens com.mkt.jdc to javafx.fxml;
	
	
}