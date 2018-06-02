package controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Log;
import model.User;

@Path("/user")
public class WSUser 
{		
	
	@GET
	@Path("/count/{table}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public User count(@PathParam("table") String table, @PathParam("token") String token)
	{
		Log objL = new Log();
		
		if(objL.validateToken(token))
		{
			User objU = new User();
			objU.setTable(table);
			objU.count();
			return objU;
		}
		else
			return null;
	}
	
	@GET
	@Path("/validate/{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public User validateUser(@PathParam("username") String username, @PathParam("password") String password)
	{
		User objU = new User();
		objU.setUserName(username);
		objU.setPassword(password);
		objU.validateUser();
		return objU;
	}
	
	@GET
	@Path("/listusers/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> listUsers(@PathParam("token") String token)
	{			
		Log objL = new Log();
		
		if(objL.validateToken(token))
		{
			User objU = new User();			
			return objU.listUsers();
		}
		else
			return null;		
	}	
}