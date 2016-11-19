package graphmessage.datastructures.graph;

import graphmessage.datastructures.list.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Graph {
	
	private List<Vertex> vertexList;
	private int adjacencyMatrix[][];

	public Graph() {
		this.vertexList = new List<Vertex>();
		generateAdjacencyMatrix();
	}
	
	public void generateAdjacencyMatrix() {
		int vertexQuantity = getVertexQuantity();
		this.adjacencyMatrix = new int[vertexQuantity][vertexQuantity];
		for (int j = 0; j < vertexQuantity; j++) {
			for (int k = 0; k < vertexQuantity; k++) {
				adjacencyMatrix[j][k] = 0;
			}
		}
	}
	
	public int getVertexQuantity() {
		return this.vertexList.getLenght();
	}

	public void addVertex(String label) {
		vertexList.insertTail(new Vertex(label));
	}

	public void addEdge(int start, int end) {
		adjacencyMatrix[start][end] = 1;
		adjacencyMatrix[end][start] = 1;
	}

	public void displayVertex(int vertexListPosition) {
		System.out.println(vertexList.getNode(vertexListPosition).getData().getLabel());
	}
/*
	public void displayMatrix() {
		System.out.print("  ");
		for (int v = 0; v < nVerts; v++) {
			System.out.print(vertexList[v].label + " ");
		}
		for (int i = 0; i < MAX_VERTS; i++) {
			System.out.println();
			System.out.print(vertexList[i].label + " ");
			for (int j = 0; j < MAX_VERTS; j++) {
				System.out.print(adjMat[i][j] + " ");
			}
		}
	}
	*/
	
	   public String algoritmoFloyd( long [][] matriz){
	        int vertices = matriz.length;
	        long matrizCostos[][]= matriz;
	        String caminos [][]= new String[vertices][vertices];
	        String caminosAuxiliares[][] = new String[vertices][vertices];
	        String caminoRecorrido="";
	        String cadena ="";
	        String camino1="";
	        int i,j,k;
	        float temp, temp2, temp3, temp4, masCorto;
	        
	        for (i=0; i<vertices; i++){
	            for (j=0; j<vertices; j++){
	                caminos[i][j]="";
	                caminosAuxiliares[i][j]="";
	            }
	        }
	        
	        
	        for (k=0; k<vertices; k++){
	            for (i=0; i<vertices; i++){
	                for (j=0; j<vertices; j++){
	                    
	                    temp=matrizCostos[i][j];
	                    temp2=matrizCostos[i][k];
	                    temp3=matrizCostos[k][j];
	                    temp4=temp2+temp3;
	                    //minimo
	                    
	                    masCorto=Math.min(temp, temp4);
	                    
	                    if(temp!=temp4){
	                        if(masCorto==temp4){
	                            caminoRecorrido="";
	                            caminosAuxiliares[i][j]=k+"";
	                            caminos[i][j]=caminosR(i,k,caminosAuxiliares,caminoRecorrido)+ (k+1);
	                            
	                        }
	                    
	                    }
	                    
	                    matrizCostos[i][j]= (long) masCorto;
	        
	                }
	        
	            }
	        }
	        
	        for (i=0; i<vertices; i++){
	            for (j=0; j<vertices; j++){
	               cadena=cadena+"["+matrizCostos[i][j]+"]";
	            }
	            cadena=cadena+"\n";
	        }
	        ////
	        
	        for (i=0; i<vertices; i++){
	            for (j=0; j<vertices; j++){
	                if(matrizCostos[i][j] != 1000000000){
	                    if(i != j){
	                        if(caminos[i][j].equals("")){
	                            camino1+= "De ("+(i+1)+"---->"+(j+1)+") irse por...("+(i+1)+", "+(j+1)+")\n";
	                        }else{
	                            camino1+= "De ("+(i+1)+"---->"+(j+1)+") irse por...("+(i+1)+", "+caminos[i][j]+", "+(j+1)+")\n";
	                        
	                        }
	                        
	                    }
	                    
	                }
	            }
	        }
	        return "Matriz de todos los Caminos"+cadena+"\n"
	                +"caminos"+camino1;
	        
	    }
	    
	    public String caminosR(int i, int k, String[][] caminosAuxiliares, String caminoRecorrido ){
	        if (caminosAuxiliares[i][k].equals("")){
	            return "";
	            
	        } else{
	            caminoRecorrido += caminosR(i,Integer.parseInt(caminosAuxiliares[i][k]),caminosAuxiliares, caminoRecorrido)
	                    + (Integer.parseInt(caminosAuxiliares[i][k].toString())+1)+", ";
	            return caminoRecorrido;
	        }
	    }
}