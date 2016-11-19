package graphmessage.pojos;

import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
	
	private Long sender;
	private Long reciever;
	private SimpleDateFormat time; 
	//private Graph path;
	private String content;
	private String type;
	
	public Long getSender() {
		return sender;
	}
	
	public void setSender(Long sender) {
		this.sender = sender;
	}
	
	public Long getReciever() {
		return reciever;
	}
	
	public void setReciever(Long reciever) {
		this.reciever = reciever;
	}
	
	public SimpleDateFormat getTime() {
		return time;
	}
	
	public void setTime(SimpleDateFormat time) {
		this.time = time;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
}
