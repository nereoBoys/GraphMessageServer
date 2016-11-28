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
     * @return Response 
     */
	public Response checkUserBackground(User user) {
		
		if(isBanned(user.getMacAddress())) {
			return new Response(CONNECTION_REFUSED_USER_BANNED);
		} else {
			return new Response(CONNECTION_STABLISHED);
		}

	}
	
	private boolean isBanned(String userMacAddress) {
		BannedUserRegistry bannedUserRegistry = databaseManager.readBannedUserRegistry();
		return bannedUserRegistry.getBannedUsers().contains(userMacAddress);
	}
	
	/** Metodo utilizado para registrar usuarios en la base 
	 * de datos y en el grafo
	 * 
	 * @param user
	 */
	public void registerUser(User user) {
		addUserToGraph(user);
		System.out.print("user added to graph\n");//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<PRINT
		addUserToRegistry(user);
		System.out.print("user added to registry\n");//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<PRINT
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
    
    /** Motodo utilizado para insertar en una cola los request
     * de los usuarios para ser notificados cuando se realiza algun 
     * cambio en el grafo
     * 
     * @param asyncResponse
     */
	public void enqueueGraphRequest(AsyncResponse asyncResponse) {
		reportRequestQueue.enqueque(asyncResponse);
	}
	
	/** Metodo utilizado para analizar y registrar mensajes en 
	 * la base de datos
	 * 
	 * @param message
	 */
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
		if(textMessageRegistry != null) {
			for(TextMessage registeredTextMessage:textMessageRegistry.getTextMessages()) {
				if(registeredTextMessage.getMessage().getSender().equals(message.getSender()) &&
						registeredTextMessage.getMessage().getReciever().equals(message.getReciever())) {
					textMessage = registeredTextMessage;
				}
		
			}
		}
		return textMessage;
	}
	
	private boolean analizeInapropiateContent(Message message) {
		boolean inapropiate = false;
		String[] words = message.getContent().split(" ");
		for(String word:words) {
			if(word.equalsIgnoreCase(PICHA) || word.equalsIgnoreCase(MIERDA) || word.equalsIgnoreCase(PUTA) ||
					word.equalsIgnoreCase(HIJUEPUTA) || word.equalsIgnoreCase(CAREPICHA) ||	word.equalsIgnoreCase(CAGO) ||
					word.equalsIgnoreCase(CAGAR) || word.equalsIgnoreCase(FUCK) || word.equalsIgnoreCase(BITCH) ||
					word.equalsIgnoreCase(DICK) || word.equalsIgnoreCase(CUNT) || word.equalsIgnoreCase(SHIT)) {
				inapropiate = true;
				break;
			}
		}
		return inapropiate; 
	}
	
	private void banUser(String userMacAddress) {
		BannedUserRegistry bannedUserRegistry = databaseManager.readBannedUserRegistry();
		bannedUserRegistry.getBannedUsers().add(userMacAddress);
		databaseManager.writeBannedUserRegistry(bannedUserRegistry);
	}
	
	private void addToTextMessageRegistry(Message message) {
		TextMessageRegistry textMessageRegistry = databaseManager.readTextMessageRegistry();
		textMessageRegistry.addTextMessage(new TextMessage(message));
		databaseManager.writeTextMessageRegistry(textMessageRegistry);
	}
	
	private void addToMessageRegistry(Message message) {
		MessageRegistry messageRegistry = databaseManager.readMessageRegistry();
		messageRegistry.getMessages().add(message);
		databaseManager.writeMessageRegistry(messageRegistry);
	}
	
	/** metodo utilizado para buscar los mensajes que contengan una palabara
	 * 
	 * @param word
	 * @return 
	 */
	public MessageRegistry searchTextMessageByWord(String word) {
		MessageRegistry messageRegistry = new MessageRegistry();
		TextMessageRegistry textMessageRegistry = databaseManager.readTextMessageRegistry();
		for(TextMessage textMessage : textMessageRegistry.getTextMessages()) {
			if (textMessage.getbTree().search(word) != null) {
				messageRegistry.getMessages().add(textMessage.getMessage());
			}
		}
		return messageRegistry;
	}
	
}
