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

import model.Customer;
import model.Log;
import model.Route;

@Path("/customer")
public class WSCustomer 
{	
	@POST
	@Path("/insertcustomer/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Customer insertCustomer(Customer obj, @PathParam("token") String token) 
	{
		Log objL = new Log();
		
		if(objL.validateToken(token))
		{
			obj.insertCustomer();
			return obj;
		}
		else
			return null;
	}
	
	@PUT
	@Path("/updatecustomer/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCustomer(Customer obj, @PathParam("token") String token) 
	{
		Log objL = new Log();
		
		if(objL.validateToken(token))		
			obj.updateCustomer();
	}
	
	@DELETE
	@Path("/deletecustomer/{keyCustomer}/{token}")
	public void deleteCustomer(@PathParam("keyCustomer") int keyCustomer, @PathParam("token") String token) 
	{
		Log objL = new Log();
		
		if(objL.validateToken(token))
		{
			Customer obj = new Customer();
			obj.setKeyCustomer(keyCustomer);		
			obj.deleteCustomer();
		}
	}
	
	@GET
	@Path("/getcustomer/{keyCustomer}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer customer(@PathParam("keyCustomer") int keyCustomer, @PathParam("token") String token)
	{
		Log objL = new Log();		
		if(objL.validateToken(token))
		{
			Customer obj = new Customer();
			obj.setKeyCustomer(keyCustomer);
			obj.getCustomer();
			return obj;
		}
		else
			return null;	
	}		
	
	@GET
	@Path("/listcustomersroute/{keyRoute}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> listCustomerRoute(@PathParam("keyRoute") int keyRoute,@PathParam("token") String token)
	{			
		Log objL = new Log();
		
		if(objL.validateToken(token))
		{
			Customer objE = new Customer();	
			objE.setKeyRoute(keyRoute);
			return objE.listCustomersRoute();
		}
		else
			return null;		
	}
	
	@GET
	@Path("/listcustomersemployee/{userName}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getC(@PathParam("userName") String userName, @PathParam("token") String token) 
	{
		Log objL = new Log();
		if(objL.validateToken(token))
		{
			Customer obj = new Customer();
			obj.setUserName(userName);		
			return obj.listCustomersEmployee();
		}else		
			return null;
	}
	
	@GET
	@Path("/listcustomers/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> listCustomer(@PathParam("token") String token)
	{			
		Log objL = new Log();
		
		if(objL.validateToken(token))
		{
			Customer objE = new Customer();			
			return objE.listCustomers();
		}
		else
			return null;		
	}
}