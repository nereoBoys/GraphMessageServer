package graphmessage.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import graphmessage.logic.LogicContainer;
import graphmessage.pojos.Message;
import graphmessage.pojos.MessageRegistry;

@Path("messages")
public class MessageResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerMessage(Message message) {
		LogicContainer.getLogic().registerMessage(message);
	}
	
	@GET
	@Path("{word}")
	@Produces(MediaType.APPLICATION_JSON)
	public MessageRegistry searchTextMessageByWord(@PathParam("word") String word) {
		return LogicContainer.getLogic().searchTextMessageByWord(word);
	}

}
