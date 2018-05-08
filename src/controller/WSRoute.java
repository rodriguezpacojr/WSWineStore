package controller;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Route;
import model.Log;

@Path("/route")
public class WSRoute 
{	
	@POST
	@Path("/insertroute/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Route insertRoute(Route objOD, @PathParam("token") String token) 
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			objOD.insertRoute();
			return objOD;
		}
		else
			return null;		
	}
	
	@PUT
	@Path("/updateroute/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateRoute(Route obj, @PathParam("token") String token) 
	{
		Log objL = new Log();
		if(objL.validateToken(token))
		{
			obj.updateRoute();
		}
	}
	
	@DELETE
	@Path("/deleteroute/{keyRoute}/{token}")
	public void deleteRoute(@PathParam("keyRoute") int keyRoute, @PathParam("token") String token) 
	{
		Log objL = new Log();
		if(objL.validateToken(token))
		{
			Route obj = new Route();
			obj.setKeyRoute(keyRoute);		
			obj.deleteRoute();
		}		
	}
	
	@GET
	@Path("/getroutes/{keyEmployee}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Route> getRoutes(@PathParam("keyEmployee") int keyEmployee, @PathParam("token") String token) 
	{
		Log objL = new Log();
		if(objL.validateToken(token))
		{
			Route obj = new Route();
			obj.setKeyEmployee(keyEmployee);		
			return obj.listRoutesEmployee();
		}else		
			return null;
	}
	
	
	@GET
	@Path("/listroutes/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Route> listRoutes(@PathParam("token") String token)
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			Route objOD = new Route();
			return objOD.listRoutes();
		}
		else
			return null;		
	}	
}