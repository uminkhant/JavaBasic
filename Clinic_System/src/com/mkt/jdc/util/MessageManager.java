package com.mkt.jdc.util;

import javafx.scene.control.Label;

public class MessageManager {

	private Label lb_message;

	public enum MessageType {
		WARNING, ERROR, SUCCESSFUL;
	}

	public MessageManager(Label lb_message) {
		this.lb_message = lb_message;
	}

	public void showMessage(String mess, MessageType t) {
		
		messageStyle(t);
		lb_message.setText(mess);
		lb_message.setVisible(true);
		
		Thread th=new Thread(()->{
			
			try {
				Thread.sleep(3000);
				lb_message.setVisible(false);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		th.start();
		
	}

	
	private void messageStyle(MessageType t) {
		switch (t) {
		case WARNING:
			lb_message.setStyle("-fx-text-fill:yellow");
			break;
		case ERROR:
			lb_message.setStyle("-fx-text-fill:red");
			break;
		case SUCCESSFUL:
			lb_message.setStyle("-fx-text-fill:green");
			break;

		default:
			lb_message.setStyle("-fx-text-fill:black");

			break;
		}
	}

}
