package controller;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Order;
import model.Log;

@Path("/order")
public class WSOrder 
{	
	@POST
	@Path("/insertorder/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Order insertOrder(Order objHis, @PathParam("token") String token) 
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			objHis.insertOrder();
			return objHis;
		}
		else
			return null;		
	}
	
	@GET
	@Path("/getdata/{keycustomer}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Order getData(@PathParam("keycustomer") int keyCustomer, @PathParam("token") String token)
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			Order obj = new Order();
			obj.setKeyCustomer(keyCustomer);
			obj.getData();
			return obj;
		}	
		else
			return null;	
	}
			
	@GET
	@Path("/listordersu/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> listOrderU(@PathParam("token") String token)
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			Order objHis = new Order();
			return objHis.listOrdersU();
		}
		else
			return null;		
	}
	
	@GET
	@Path("/listordersd/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> listOrderD(@PathParam("token") String token)
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			Order objHis = new Order();
			return objHis.listOrdersD();
		}
		else
			return null;		
	}
}