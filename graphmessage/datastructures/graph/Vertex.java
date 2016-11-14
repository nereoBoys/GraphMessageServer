package graphmessage.datastructures.graph;

public class Vertex {
    
	public String label;
    
    public boolean wasVisited;

    public Vertex(String label){
        this.label = label;
        this.wasVisited = true;
    }

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isWasVisited() {
		return wasVisited;
	}

	public void setWasVisited(boolean wasVisited) {
		this.wasVisited = wasVisited;
	}
       
}