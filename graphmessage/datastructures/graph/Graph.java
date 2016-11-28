package graphmessage.datastructures.graph;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Graph {
	
   private final int MAX_VERTS = 5;
   private Vertex vertexList[] = new Vertex[MAX_VERTS];
   private int adjacencyMatrix[][] = new int[MAX_VERTS][MAX_VERTS];
   private int vertexQuantity = 0;

   public void addVertex(Long data) {
       vertexList[vertexQuantity++] = new Vertex(data);
   }

   public void addEdge(int start, int end) {
       adjacencyMatrix[start][end] = 1;
       adjacencyMatrix[end][start] = 1;
   }

   public void displayVertex(int v) {
       System.out.println(vertexList[v].getData());
   }

   public void displayMatrix() {
       System.out.print("  ");
       for (int v = 0; v < vertexQuantity; v++) {
           System.out.print(vertexList[v].getData() + " ");
       }
       for (int i = 0; i < MAX_VERTS; i++) {
           System.out.println();
           System.out.print(vertexList[i].getData() + " ");
           for (int j = 0; j < MAX_VERTS; j++) {
               System.out.print(adjacencyMatrix[i][j] + " ");
           }
       }
   }
   
}