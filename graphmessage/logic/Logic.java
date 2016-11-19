package graphmessage.logic;

import javax.ws.rs.container.AsyncResponse;

import graphmessage.datastructures.graph.Graph;
import graphmessage.datastructures.queue.Queue;
import graphmessage.pojos.Message;
import graphmessage.pojos.Response;
import graphmessage.pojos.User;

public class Logic {
	
    private Queue<AsyncResponse> reportRequestQueue = new Queue<>();

	public Response inspectUser(User user) {//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		return null;
	}

	public void registerMessage(Message message) {
		
	}
	
	public void enqueueGraphRequest(AsyncResponse asyncResponse) {
		reportRequestQueue.enqueque(asyncResponse);
	}
	
    public void sendUpdatedGraph() {
    	Graph graph = null; //databaseManager.readGraph();
    	while(!reportRequestQueue.isEmpty()) {
    		reportRequestQueue.dequeque().resume(graph);
    	}
    }
	
}
