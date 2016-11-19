package graphmessage.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import graphmessage.logic.LogicContainer;
import graphmessage.pojos.Message;

@Path("messages")
public class MessageResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerMessage(Message message) {
		LogicContainer.getLogic().registerMessage(message);
	}

}
