package graphmessage.database;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import graphmessage.constants.Constants;
import graphmessage.datastructures.graph.Graph;
import graphmessage.pojos.BannedUserRegistry;
import graphmessage.pojos.MessageRegistry;
import graphmessage.pojos.TextMessageRegistry;
import graphmessage.pojos.UserRegistry;

public class DatabaseManager implements Constants {
	
	/**Metodo usado para leer el archivo xml que contiene el registro de los 
	 * usuarios baneados en el servidor
	 * 
	 * @return BannedUserRegistry
	 */
	public BannedUserRegistry readBannedUserRegistry() {
		
		BannedUserRegistry bannedUserRegistry = null;
		
		try {
			File file = new File(BANNED_USER_REGISTRY_PATH);
			JAXBContext jaxbContext = JAXBContext.newInstance(BannedUserRegistry.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			bannedUserRegistry = (BannedUserRegistry) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return bannedUserRegistry;
	}
	
	/** Metodo usado para sobreescribir el archivo xml que contiene
	 * el registro de los usuarios baneados
	 * 
	 * @param bannedUserRegistry
	 */
	public void writeBannedUserRegistry(BannedUserRegistry bannedUserRegistry) {
		try {
			File file = new File(BANNED_USER_REGISTRY_PATH);
			JAXBContext jaxbContext = JAXBContext.newInstance(BannedUserRegistry.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(bannedUserRegistry, file);
			jaxbMarshaller.marshal(bannedUserRegistry, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	/**Metodo usado para leer el archivo xml que contiene el grafo de los 
	 * usuarios activos
	 * 
	 * @return Graph
	 */
	public Graph readGraph() {
		
		Graph graph = null;
		
		try {
			File file = new File(GRAPH_PATH);
			JAXBContext jaxbContext = JAXBContext.newInstance(Graph.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			graph = (Graph) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return graph;
	}

	/** Metodo usado para sobreescribir el archivo xml que contiene
	 * el grafo de los usuarios activos
	 * 
	 * @param graph
	 */
	public void writeGraph(Graph graph) {
		try {
			File file = new File(GRAPH_PATH);
			JAXBContext jaxbContext = JAXBContext.newInstance(Graph.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(graph, file);
			jaxbMarshaller.marshal(graph, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	/** Metodo usado para sobreescribir el archivo xml que contiene
	 * el registro de los usuarios registrados
	 * 
	 * @param userRegistry
	 */
	public UserRegistry readUserRegistry() {
		
		UserRegistry userRegistry = null;
		
		try {
			File file = new File(USER_REGISTRY_PATH);
			JAXBContext jaxbContext = JAXBContext.newInstance(UserRegistry.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			userRegistry = (UserRegistry) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return userRegistry;
	}

	/** Metodo usado para sobreescribir el archivo xml que contiene
	 * el registro de los usuarios registrados
	 * 
	 * @param userRegistry
	 */	public void writeUserRegistry(UserRegistry userRegistry) {
		try {
			File file = new File(USER_REGISTRY_PATH);
			JAXBContext jaxbContext = JAXBContext.newInstance(UserRegistry.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(userRegistry, file);
			jaxbMarshaller.marshal(userRegistry, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**Metodo usado para leer el archivo xml que contiene el registro de los 
	 * usuarios registrados
	 * 
	 * @return MessageRegistry
	 */
	public MessageRegistry readMessageRegistry() {
		
		MessageRegistry messageRegistry = null;
		
		try {
			File file = new File(MESSAGE_REGISTRY_PATH);
			JAXBContext jaxbContext = JAXBContext.newInstance(MessageRegistry.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			messageRegistry = (MessageRegistry) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return messageRegistry;
	}

	/** Metodo usado para sobreescribir el archivo xml que contiene
	 * el registro de los mensajes registrados
	 * 
	 * @param messageRegistry
	 */
	public void writeMessageRegistry(MessageRegistry messageRegistry) {
		try {
			File file = new File(MESSAGE_REGISTRY_PATH);
			JAXBContext jaxbContext = JAXBContext.newInstance(MessageRegistry.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(messageRegistry, file);
			jaxbMarshaller.marshal(messageRegistry, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	private TextMessageRegistry textMessageRegistry = new TextMessageRegistry();
	
	/**Metodo usado para leer el archivo xml que contiene el registro de los 
	 * mensajes registrados
	 * 
	 * @return TextMessageRegistry
	 */
	public TextMessageRegistry readTextMessageRegistry() {
		return textMessageRegistry;
	}
	
	/**Metodo usado para sobreescribir el archivo xml que contiene
	 * el registro de los mensajes de texto registrados
	 * 
	 * @param textMessageRegistry
	 */
	public void writeTextMessageRegistry(TextMessageRegistry textMessageRegistry) {
		this.textMessageRegistry = textMessageRegistry;
	}

}
