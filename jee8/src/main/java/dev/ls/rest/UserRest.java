package dev.ls.rest;

import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import dev.ls.entity.UserCredentials;
import dev.ls.service.UserCredentialService;
import dev.ls.util.MaxAge;

@Path("credentials")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRest {
	@Inject
	UserCredentialService userCredentialService;
	@Context
	UriInfo uriInfo;
	@Inject
	private Logger logger;
	
	/*========CREATE==========*/
	
	@POST
	@Path("credentials")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createCredential(@BeanParam UserCredentials userCredentials,@HeaderParam("referer") String referer,@Context HttpHeaders httpHeaders) {
		
		System.out.println(" "+userCredentials.getUserName()+" "+userCredentials.getPassword());
		for(String h : httpHeaders.getRequestHeaders().keySet()) {
			System.out.println(" "+h+" = "+httpHeaders.getRequestHeaders().getFirst(h));
		}
		System.out.println(" "+referer);
		userCredentialService.createCredential(userCredentials);
		URI uri = uriInfo.getAbsolutePathBuilder().path(userCredentials.getId().toString()).build();
		return Response.created(uri).status(Response.Status.CREATED).build();
	}
	/*------------------------*/
	
	/*========READ ALL==========*/
	@GET
	@Path("credentials")
	public Response getCredentials(@Context HttpHeaders httpHeaders) {
		List<MediaType> acceptanceList = httpHeaders.getAcceptableMediaTypes();
		for(int i=0;i<acceptanceList.size();i++) {
			System.out.println(acceptanceList.get(i).toString());
		}
		return Response.ok(userCredentialService.getAllCredentials()).status(Response.Status.OK).build();
	}
	/*------------------------*/
	
	/*========READ BY ID==========*/
	@GET
	@Path("credentials/{id: \\d+}")
	@MaxAge(age=200)	
	public Response getCredentialById(@PathParam("id") Integer id) {
		return Response.ok(userCredentialService.findCredentialById(id)).status(Response.Status.FOUND).build();
	}
	/*------------------------*/
	
	/*========DUMMY METHOD ==========*/
	@DELETE
	@Path("credentials/{id: \\d+}")
	public Response deleteCredentialById(@PathParam("id") @NotNull Integer id) {
		logger.log(Level.INFO,"Inside delete resource method.");
		return Response.ok().build(); 
	}
	
	
	/*------------------------*/
}
