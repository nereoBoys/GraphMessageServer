package graphmessage.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import graphmessage.logic.LogicContainer;

@Path("graph")
public class GraphResource {
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void addReportRequest(@Suspended AsyncResponse asyncResponse) throws InterruptedException {
        LogicContainer.getLogic().enqueueGraphRequest(asyncResponse);
    }

}

