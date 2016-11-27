package graphmessage.datastructures.graph;

public class Graph {
	
    private final int MAX_VERTS = 5;
    private Vertex vertexList[];
    private int adjMat[][];
    private int nVerts;

    public Graph() {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int j = 0; j < MAX_VERTS; j++)
            for (int k = 0; k < MAX_VERTS; k++)
                adjMat[j][k] = 0;
    }

    public void addVertex(Long vertexId) {
        vertexList[nVerts++] = new Vertex(vertexId);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    public void displayVertex(int v) {
        System.out.println(vertexList[v].getId());
    }

    public void displayMatrix() {
        System.out.print("  ");
        for (int v = 0; v < nVerts; v++) {
            System.out.print(vertexList[v].getId() + " ");
        }
        for (int i = 0; i < MAX_VERTS; i++) {
            System.out.println();
            System.out.print(vertexList[i].getId() + " ");
            for (int j = 0; j < MAX_VERTS; j++) {
                System.out.print(adjMat[i][j] + " ");
            }
        }
    }
    
}