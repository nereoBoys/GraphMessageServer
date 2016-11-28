package graphmessage.pojos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TextMessageRegistry {
	
	private ArrayList<TextMessage> textMessages = new ArrayList<>();

	public ArrayList<TextMessage> getTextMessages() {
		return textMessages;
	}

	public void setTextMessages(ArrayList<TextMessage> textMessages) {
		this.textMessages = textMessages;
	}
	
	public void addTextMessage(TextMessage textMessage) {
		textMessages.add(textMessage);
	}

}
