package graphmessage.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import graphmessage.logic.LogicContainer;
import graphmessage.pojos.Response;
import graphmessage.pojos.User;

@Path("users")
public class UserResource {

	@POST
	@Path("check")
	@Produces(MediaType.APPLICATION_JSON)
	public Response userLogin(Long userId) { //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<posible error
		return LogicContainer.getLogic().checkUserBackground(userId);	
	}
	
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void userLogin(User user) {
		LogicContainer.getLogic().registerUser(user);
	}
	
}
