package graphmessage.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import graphmessage.logic.LogicContainer;
import graphmessage.pojos.Response;
import graphmessage.pojos.User;

@Path("users")
public class UserResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response userLogin(User user) {
		return LogicContainer.getLogic().inspectUser(user);	
	}
}
