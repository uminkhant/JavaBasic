package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class MainController {

	@FXML
    private TextField tf_frist;

    @FXML
    private TextField tf_second;
    
    @FXML
    private TextField tf_result;
    
    @FXML
    private HBox hb_radio;
    
    @FXML
    void clear() {
    	tf_frist.clear();
    	tf_second.clear();
    	
    	for(Node node:hb_radio.getChildren()) {
    		RadioButton rbn=(RadioButton)node;
    		
    		if(rbn.isSelected()) {
    			rbn.setSelected(false);   			
    		}
    		
    	}
    }

    @FXML
    void enter() {
    	for(Node node:hb_radio.getChildren()) {
    		RadioButton rbn=(RadioButton)node;
    		
    		if(rbn.isSelected()) {
    			calculate(rbn.getText());
    			return;
    		}
    		
    	}
    }

	private void calculate(String ope) {
		
		int a=Integer.parseInt(tf_frist.getText());
		int b=Integer.parseInt(tf_second.getText());
		
		switch(ope) {
		case "+":
			tf_result.setText(String.valueOf(a+b));
			break;
		case "-":
			tf_result.setText(String.valueOf(a-b));
			break;
		case "x":
			tf_result.setText(String.valueOf(a*b));	
			break;
		case "/":
			tf_result.setText(String.valueOf(a/b));			
			break;
		case "%":
			tf_result.setText(String.valueOf(a%b));			
			break;
		}
		clear();
	}

}
