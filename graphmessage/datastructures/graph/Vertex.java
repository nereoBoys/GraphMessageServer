package graphmessage.datastructures.graph;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Vertex {
    
	private Long data;
    
    public Vertex() {}

    public Vertex(Long data){
        this.data = data;
    }

	public Long getData() {
		return data;
	}

	public void setData(Long data) {
		this.data = data;
	}

}