package graphmessage.pojos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response {
	
	public Response() {}
	
	public Response(String status) {
		this.status = status;
	}
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
