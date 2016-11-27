package graphmessage.datastructures.graph;

public class Vertex {
	
    public Long id;
    public boolean wasVisited;

    public Vertex(Long id){
        this.id = id;
        wasVisited = true;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isWasVisited() {
		return wasVisited;
	}

	public void setWasVisited(boolean wasVisited) {
		this.wasVisited = wasVisited;
	}
    
}