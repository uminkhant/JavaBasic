package mkt.lib;

import javafx.scene.control.Label;
import mkt.lib.message.MessageImpl;

public interface MKMessage {

	static MKMessage getInstance(Label lb) {
		return new MessageImpl(lb);
	}
	
	public enum MessageType{
		SUCCESS, WARNNING, ERROR
	}
	
	public void showMessage(String mess, MessageType style);
}
