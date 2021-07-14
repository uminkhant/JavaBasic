package mkt.lib.message;


import javafx.scene.control.Label;
import mkt.lib.MKMessage;

public class MessageImpl implements MKMessage{

	private Label message;

	
	public MessageImpl(Label message) {
		this.message = message;
	}

	public void showMessage(String mess, MessageType style) {

		message.setText("✼ "+mess+" ✼");
		checkColor(style);

		Thread th = new Thread(() -> {
			try {
				
				message.setVisible(true);
				Thread.sleep(2000);
				message.setVisible(false);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		th.start();
	}

	private void checkColor(MessageType style) {

		switch (style) {
		case SUCCESS:
			message.setStyle("-fx-text-fill:green;");
			break;
		case WARNNING:
			message.setStyle("-fx-text-fill:yellow;");

			break;
		case ERROR:
			message.setStyle("-fx-text-fill:red;");
			break;

		}
	}
}
