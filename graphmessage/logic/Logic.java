package graphmessage.logic;

import javax.ws.rs.container.AsyncResponse;

import graphmessage.constants.Constants;
import graphmessage.database.DatabaseManager;
import graphmessage.datastructures.graph.Graph;
import graphmessage.datastructures.queue.Queue;
import graphmessage.pojos.BannedUserRegistry;
import graphmessage.pojos.Message;
import graphmessage.pojos.MessageRegistry;
import graphmessage.pojos.Response;
import graphmessage.pojos.TextMessage;
import graphmessage.pojos.TextMessageRegistry;
import graphmessage.pojos.User;
import graphmessage.pojos.UserRegistry;

public class Logic implements Constants {
	
    private DatabaseManager databaseManager = new DatabaseManager();
    private Queue<AsyncResponse> reportRequestQueue = new Queue<>();
    
    /**Este metodo verifica si el usuario se encuentra en la lista de usuarios
     * bloquados, de ser asi se envia un mensaje de error, de lo contrario se 
     * envia un mensaje para establecer la conexion
     * 
     * @param user
     * @return
     */
	public Response checkUserBackground(Long userId) {
		
		if(isBanned(userId)) {
			return new Response(CONNECTION_REFUSED_USER_BANNED);
		} else {
			return new Response(CONNECTION_STABLISHED);
		}

	}
	
	private boolean isBanned(Long userId) {
		BannedUserRegistry bannedUserRegistry = databaseManager.readBannedUserRegistry();
		return bannedUserRegistry.getBannedUsers().contains(userId);
	}
	
	public void registerUser(User user) {
		addUserToGraph(user);
		addUserToRegistry(user);
	}
	
	private void addUserToGraph(User user) {
		Graph graph = databaseManager.readGraph(); //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<GRAFO
//		graph.addVertex(user);
//		graph.checkEdges();
		sendUpdatedGraph(graph);
		databaseManager.writeGraph(graph);
	}
	
	private void addUserToRegistry(User user) {
		UserRegistry userRegistry = databaseManager.readUserRegistry();
		userRegistry.getUsers().add(user);
		databaseManager.writeUserRegistry(userRegistry);
	}
	
    private void sendUpdatedGraph(Graph graph) {
    	while(!reportRequestQueue.isEmpty()) {
    		reportRequestQueue.dequeque().resume(graph);
    	}
    }
    
	public void enqueueGraphRequest(AsyncResponse asyncResponse) {
		reportRequestQueue.enqueque(asyncResponse);
	}

	public void registerMessage(Message message) {
		if(message.getType().equals("text")) {
			if(analizeSpam(message) || analizeInapropiateContent(message)) {
				banUser(message.getSender());
			}
			addToTextMessageRegistry(message);
		}
		addToMessageRegistry(message);
	}
	
	private boolean analizeSpam(Message message) {
		TextMessage textMessage = getLastTextMessage(message);
		return (textMessage != null && 
				textMessage.getMessage().getContent().equalsIgnoreCase(message.getContent())); 
	}
	
	private TextMessage getLastTextMessage(Message message) {
		TextMessage textMessage = null;
		TextMessageRegistry textMessageRegistry = databaseManager.readTextMessageRegistry();
		for(TextMessage registeredTextMessage:textMessageRegistry.getTextMessages()) {
			if(registeredTextMessage.getMessage().getSender() == message.getSender() &&
					registeredTextMessage.getMessage().getReciever() == message.getReciever()) {
				textMessage = registeredTextMessage;
			}
		}
		return textMessage;
	}
	
	private boolean analizeInapropiateContent(Message message) {
		return false; //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<,
	}

	public void banUser(Long user) {
		BannedUserRegistry bannedUserRegistry = databaseManager.readBannedUserRegistry();
		bannedUserRegistry.getBannedUsers().add(user);
		databaseManager.writeBannedUserRegistry(bannedUserRegistry);
	}
	
	private void addToTextMessageRegistry(Message message) {
		TextMessageRegistry textMessageRegistry = databaseManager.readTextMessageRegistry();
		textMessageRegistry.getTextMessages().add(new TextMessage(message));
		databaseManager.writeTextMessageRegistry(textMessageRegistry);
	}
	
	
	
	private void addToMessageRegistry(Message message) {
		MessageRegistry messageRegistry = databaseManager.readMessageRegistry();
		messageRegistry.getMessages().add(message);
		databaseManager.writeMessageRegistry(messageRegistry);
	}
	
}
